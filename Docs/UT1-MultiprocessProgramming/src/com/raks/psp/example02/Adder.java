package com.raks.psp.example02;

public class Adder {

    public static void main(String[] args) {
        String processName = args[0];
        int    startNo     = Integer.parseInt(args[1]);
        int    endNo       = Integer.parseInt(args[2]);
        int    result      = startNo;

        for (int i = startNo; i <= endNo; i++) {
            result += i;
            System.out.printf("[%s]:%d%n", processName, result);
        }
        System.out.println(result);
    }

}