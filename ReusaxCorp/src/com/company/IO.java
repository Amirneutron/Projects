package com.company;

import java.util.Scanner;

public class IO {

    static Scanner scanner = new Scanner(System.in);

    public static String readLine() {
        return scanner.nextLine();
    }

    public static int readINT() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public static double readDouble() {
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }


    public static void eatLine() {
        scanner.nextLine();
    }
}
