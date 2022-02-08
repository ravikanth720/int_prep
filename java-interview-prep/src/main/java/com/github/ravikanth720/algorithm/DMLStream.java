package com.github.ravikanth720.algorithm;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.*;

public class DMLStream {
    /**
     * A class which constructs a view of the Hash Table's state given the input DML Events.
     */
    public static class HashTable {
        private Instant highWatermark = Instant.EPOCH;
        private static final String DELETE = "DELETE";
        private static final String INSERT = "INSERT";
        private static final String UPSERT = "UPSERT";
        private Map<String, String> map;

        public HashTable(ArrayList<String> rawEvents) {
            map = new HashMap<>();

            for (String event: rawEvents) {
                if (event == null) continue;

                String[] parts = event.split("\\|");
                if (parts.length < 3) continue;

                try {
                    Date eventDate = new Date(Long.parseLong(parts[0]));
                    highWatermark = eventDate.toInstant();
                } catch(NumberFormatException e) {
                    // skip event
                    continue;
                }

                switch (parts[1]) {
                    case DELETE:
                        map.remove(parts[2]);
                        break;
                    case INSERT:
                        if (parts.length >= 4 && !map.containsKey(parts[2])) {
                            map.put(parts[2], parts[3]);
                        }
                        break;
                    case UPSERT:
                        if (parts.length >= 4) {
                            map.put(parts[2], parts[3]);
                        }
                }
            }
        }

        /**
         * Retrieve the state of the HashTable after applying all input events
         *
         * @return a Map representing the Keys and Values of the current state
         */
        public Map<String, String> getTable() {
            return map;
        }

        /**
         * Retrieve the high-watermark of the HashTable as the millisecond epoch timestamp
         * of the latest event read or Instant.EPOCH in the case where no event occurred.
         *
         * @return an Instant representing the high watermark
         */
        public Instant getHighWatermark() {
            return highWatermark;
        }
    }

    /**
     * A class which wraps a raw binary WAL and reconstructs DML Events.
     */
    public static class WAL {
        ArrayList<String> events;
        static final String[] OPERATIONS = new String[]{"INSERT", "UPSERT", "DELETE"};
        /**
         * Construct the WAL
         * @param input the raw binary WAL
         */
        public WAL(byte[] input) {
            events = new ArrayList<>();

            StringBuilder event = new StringBuilder();

            int i = 0, idx = 0, next = 64, len = input.length;
            boolean delete = false;

            try {
                while (idx < len && idx + next < len) {
                    switch (i) {
                        case 0:
                            if (event.length() != 0) {
                                events.add(event.toString());
                                event = new StringBuilder();
                            }
                            ByteBuffer time = ByteBuffer.wrap(input, idx, next);
                            event.append(time.getLong());
                            i++;
                            idx += next + 1;
                            next = 1;
                            break;

                        case 1:
                            ByteBuffer operationBuffer = ByteBuffer.wrap(input, idx, next);
                            delete = operationBuffer.getShort() == 2;
                            event.append("|");
                            event.append(OPERATIONS[operationBuffer.getShort()]);
                            idx += next + 1;
                            next = 16;
                            i++;
                            break;

                        case 2:
                            ByteBuffer kLen = ByteBuffer.wrap(input, idx, next);
                            idx += next + 1;
                            i++;
                            next = kLen.getInt();
                            break;

                        case 3:
                            String value = new String(input, idx, next);
                            event.append("|");
                            event.append(value);
                            idx += next + 1;
                            // Delete
                            if (delete) {
                                i = 0;
                                next = 64;
                                delete = false;
                            } else {
                                i = 2;
                                next = 16;
                                delete = false;
                            }
                            break;
                    }
                }
            } catch (BufferUnderflowException e) {
                // Not enough left in Buffer, ignore for this test
            }
        }

        /**
         * Retrieve all events contained within the WAL as their string values in time order
         * DML Event String Format: "<epoch_milli>|<message_name>|<key>|<value>"
         *
         * @return a time-ordered sequence of DML Event strings
         */
        public ArrayList<String> getEvents() {
            return events;
        }
    }

    public  static void main(String[] a) {
        DMLStream d = new DMLStream();
        WAL w = new WAL(new byte[0]);
    }
}
