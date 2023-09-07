package com.raks.psp.example03;

public class Adder {
    public static void main(String[] args) {
        var startNo = Integer.parseInt(args[0]);
        var endNo   = Integer.parseInt(args[1]);
        var result  = startNo;

        for (int i = startNo; i <= endNo; i++)
             result += i;
        System.out.println(result);
    }
}