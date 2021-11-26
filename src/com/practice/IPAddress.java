package com.practice;

class IPAddress {
    public static void main(String[] args) {
        validateIP("192.168.123.456");
    }

    static boolean validateIP(String ip) {
        if (ip == null || ip.length() == 0) return false;

        String[] arr = ip.split("\\.");
        if (arr.length != 4) return false;

        for (String num : arr) {
            try {
                int temp = Integer.parseInt(num);
                System.out.println(num);
                if (temp >= 0 && temp <= 255) continue;
            } catch (Exception e) {
                System.out.println("Exception:" + num);
                return false;
            }
        }
        return true;
    }
}
