package org.siit.homework.week7.student;

import org.siit.homework.week7.student.Student;

import java.util.*;

import static org.siit.homework.week7.Menu.*;

public class Week7Main {

    public static final String QUIT_MENU = "QUIT";

    public static void main(String[] args) {

        Set<Student> studentSet = new HashSet<>();

        Scanner scanner = new Scanner(System.in);
        printMenu();
        operationChoice = scanner.nextLine();
        while (!operationChoice.equals(QUIT_MENU)) {
            switch (operationChoice) {
                case "1":
                    choice1(studentSet, scanner);
                    break;
                case "2":
                    choice2(studentSet, scanner);
                    break;
                case "3":
                    choice3(studentSet, scanner);
                    break;
                case "4":
                    choice4(studentSet, scanner);
                    break;
                default:
                    System.out.print("Operation not supported, please try again: ");
                    operationChoice = scanner.nextLine();
                    break;
            }
        }
        scanner.close();
    }


}
