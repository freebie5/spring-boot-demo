package org.sy.springbootshardingsphere;

public class Main {

    public static void main(String[] args) {
        String binary23 = "11111111111111111111111";
        Integer ss = Integer.parseInt(binary23, 2);
        System.out.println(binary23.length());
        System.out.println(ss);

        String binary52 = "1111111111111111111111111111111111111111111111111111";
        Long dd = Long.parseLong(binary52, 2);
        System.out.println(binary52.length());
        System.out.println(dd);

        String binary64 = "111111111111111111111111111111111111111111111111111111111111111";
        Long ee = Long.parseLong(binary64, 2);
        System.out.println(binary64.length());
        System.out.println(ee);
    }

}
