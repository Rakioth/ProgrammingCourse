package com.raks.psp.example04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Adder {
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                var startNo = Integer.parseInt(input.readLine());
                var endNo   = Integer.parseInt(input.readLine());
                var result  = startNo;

                for (int i = startNo; i < endNo; i++)
                     result += i;
                System.out.println(result);
            }
        }
    }
}
