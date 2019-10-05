package algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DroppedRequests {

    int getDroppedRequests(List<Integer> requests) {
        int droppedRequests = 0;
        TreeMap<Integer, Integer> requestsPerSec = new TreeMap<>();

        for (Integer sec: requests) {
            int count = requestsPerSec.getOrDefault(sec, 0) + 1;
            requestsPerSec.put(sec, count);

            if (count > 3) {
                droppedRequests++;
            }
        }

        Map.Entry<Integer, Integer> item = requestsPerSec.firstEntry();
        int aggregator = 0;
        while (item != null) {
            int sec = item.getKey();
            aggregator += item.getValue();
            requestsPerSec.put(sec, aggregator);
            item = requestsPerSec.higherEntry(sec);
        }

        for (int sec = 10; sec <= requestsPerSec.lastKey(); sec++) {
            int requestsInLast10s = requestInLastXSecs(requestsPerSec, sec, 10);
            droppedRequests += Math.max(requestsInLast10s - 20, 0);

            if (sec >= 60) {
                int requestsInLast60s = requestInLastXSecs(requestsPerSec, sec, 60);
                droppedRequests += Math.max(requestsInLast60s - 100, 0);
            }
        }

        return droppedRequests;
    }

    private int requestInLastXSecs(TreeMap<Integer, Integer> map, int sec, int X) {
        int requestsAtSec = map.lowerEntry(sec + 1).getValue();
        Map.Entry<Integer, Integer> requestEntryXSecsAgo = map.lowerEntry(sec - (X-1));
        int requestXSecsAgo = 0;
        if (requestEntryXSecsAgo != null) {
            requestXSecsAgo = requestEntryXSecsAgo.getValue();
        }

        return requestsAtSec - requestXSecsAgo;
    }

    public static void main(String[] args) {
        DroppedRequests d = new DroppedRequests();
        System.out.println(
                d.getDroppedRequests(Arrays.asList(1,1,1,1, 2,2,2,3,3,3, 5,5, 6, 6, 6, 7,7, 9,9,9, 9, 11,11,11))
        );
        System.out.println(Arrays.asList(1,1,1,1, 2,2,2,3,3,3, 5,5, 6, 6, 6, 7,7, 9,9,9, 9, 11,11,11).size());
    }
}
