package com.github.ravikanth720.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidIP {
    public static List<String> validateAddresses(List<String> addresses) {
        List<String> result = new ArrayList<>();
        if (addresses != null) {
            for (String address : addresses) {
                if (validIPV4(address)) {
                    result.add("IPV4");
                } else if (validIPV6(address)) {
                    result.add("IPV6");
                } else {
                    result.add("Neither");
                }
            }
        }

        return result;
    }

    private static boolean validIPV4(String ip) {
        String[] parts = ip.split("\\.", -1);
        if (parts.length != 4) return false;

        for (String part: parts) {
            if (part.length() > 1 && part.charAt(0) == '0' || part.charAt(0) == '-')
                return false;

            try {
                int decimalVal = Integer.parseInt(part);
                if (decimalVal<0 || decimalVal>255) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    private static boolean validIPV6(String ip) {
        String[] check = ip.split("::");
        if (check.length > 2) {
            return false;
        }

        String[] parts = ip.split(":", -1);
        if (parts.length != 8) return false;

        for (String part: parts) {
            if (part.length() < 1 || part.length() > 4) return false;

            if (part.length() > 1 && part.charAt(0) == '-') return false;

            try {
                Long.parseLong(part, 16);
            } catch(NumberFormatException e) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(validateAddresses(Arrays.asList(
                "121.18.19.20",
                "0.12.12.34",
                "121.234.12.12",
                "23.45.12.56",
                "0.1.2.3",
                "::1",
                "2001:0db8:0000:0000:0000:ff00:0042:8329",
                "2001:db8:0:0:0:ff00:42:8329",
                "2001:db8::ff00:42:8329",
                "000.012.234.23",
                "666.666.23.23"
        )));
/*
        */
    }
}
