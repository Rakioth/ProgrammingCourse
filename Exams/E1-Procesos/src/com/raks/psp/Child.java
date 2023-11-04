package com.raks.psp;

public class Child {

    public static void main(String[] args) {
        switch (args[0]) {
            case "-t1" -> System.out.println(new StringBuilder(args[1]).reverse());
            case "-t2" -> System.out.println(args[1].toUpperCase());
            default    -> System.out.printf("%sUnknown Option%s [-t1 | Exercise Type 1] [-t2 | Exercise Type 2]%n", "\u001B[31m", "\u001B[0m");
        }
    }

}