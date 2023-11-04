package com.raks.psp.example03;

public class Adder {

    public static void main(String[] args) {
        int startNo = Integer.parseInt(args[0]);
        int endNo   = Integer.parseInt(args[1]);
        int result  = startNo;

        for (int i = startNo; i <= endNo; i++)
             result += i;
        System.out.println(result);
    }

}