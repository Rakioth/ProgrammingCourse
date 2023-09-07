package com.raks.psp.example02;

public class Adder {
    public static void main(String[] args) {
        var processName = args[0];
        var startNo     = Integer.parseInt(args[1]);
        var endNo       = Integer.parseInt(args[2]);
        var result      = startNo;

        for (int i = startNo; i <= endNo; i++) {
            result += i;
            System.out.printf("[%s]:%d%n", processName, result);
        }
        System.out.println(result);
    }
}

