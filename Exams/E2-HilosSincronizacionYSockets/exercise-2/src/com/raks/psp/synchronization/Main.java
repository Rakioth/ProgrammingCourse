package com.raks.psp.synchronization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.printf("%sAvailable Options%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[36m", "\u001B[0m");
        boolean exit = false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!exit)
                switch (br.readLine()) {
                    case "-t1" -> {
                        SlotMachine slotMachine = new SlotMachine(5, 3000, 5, 3);
                        slotMachine.start();
                        exit = true;
                    }
                    case "-t2" -> {
                        HorseRace horseRace = new HorseRace(5, 3000, 3);
                        horseRace.start();
                        exit = true;
                    }
                    default    -> System.out.printf("%sUnknown Option%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[31m", "\u001B[0m");
                }
        }
    }

}