package org.siit.homework.week6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is the main class for week 6 homework");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the expression: ");
        String expression = scanner.nextLine();

        Calculator calculator = new Calculator();
        calculator.computeString(expression);

//        You can use this inputs
//        10 cm + 1 m - 10 mm = 1090 mm
//        15 cm + 8 m = 815 cm
//        1000 m + 3 km = 4000 m
//        28 m - 15 dm + 2 dm + 1 m = 277 dm


    }
}
