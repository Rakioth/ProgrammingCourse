package com.danielmunoz.psp.sincronizacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.printf("%sAvailable Options%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[36m", "\u001B[0m");
        var exit = false;

        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            while (!exit)
                switch (stdIn.readLine()) {
                    case "-t1" -> {
                        var slotMachine = new SlotMachine(5, 3000, 5, 3);
                        slotMachine.start();
                        exit = true;
                    }
                    case "-t2" -> {
                        var horseRace = new HorseRace(5, 3000, 3);
                        horseRace.start();
                        exit = true;
                    }
                    default    -> System.out.printf("%sUnknown Option%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[31m", "\u001B[0m");
                }
        }
    }
}
