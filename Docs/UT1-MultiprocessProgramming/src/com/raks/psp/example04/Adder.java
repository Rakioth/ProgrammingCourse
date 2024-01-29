package com.raks.psp.example04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Adder {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                int startNo = Integer.parseInt(br.readLine());
                int endNo   = Integer.parseInt(br.readLine());
                int result  = startNo;

                for (int i = startNo; i < endNo; i++)
                     result += i;
                System.out.println(result);
            }
        }
    }

}