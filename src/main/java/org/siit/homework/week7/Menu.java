package org.siit.homework.week7;

import org.siit.homework.week7.exception.EmptyVariableException;
import org.siit.homework.week7.exception.InvalidInputFormatException;
import org.siit.homework.week7.exception.OutOfBoundsException;
import org.siit.homework.week7.student.Student;

import java.time.DateTimeException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.*;

import static org.siit.homework.week7.student.repository.StudentRepository.*;
import static org.siit.homework.week7.student.repository.StudentRepository.addStudent;

public abstract class Menu {

    public static String operationChoice;
    public static Logger logger = Logger.getLogger(Menu.class.getName());

    public static void choice4(Set<Student> studentSet, Scanner scanner) {
        System.out.println("You chose to list all the students ordered by Last Name or Birth Date");
        System.out.println("Please select the sorting criteria by typing the corresponding number:");
        System.out.println("1. By last name");
        System.out.println("2. By birth date");
        String sortingCriteria = scanner.nextLine();
        try {
            listSortedStudents(studentSet, sortingCriteria);
        } catch (InvalidInputFormatException e) {
            logger.log(Level.SEVERE, e.getMessage(), e.getCause());
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        }
        System.out.print("Choose another operation: ");
        operationChoice = scanner.nextLine();
    }

    public static void choice3(Set<Student> studentSet, Scanner scanner) {
        System.out.println("You chose to retrieve all students of specified age!");
        System.out.print("Please enter the age: ");
        String ageToRetrieveBy = scanner.nextLine();
        try {
            retrieveStudentsOfAge(studentSet, ageToRetrieveBy);
        } catch (InvalidInputFormatException e) {
            logger.log(Level.SEVERE, e.getMessage(), e.getCause());
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, e.getMessage(), e.getCause());
            System.out.println("Input must be in number format. NO LETTERS ALLOWED");
            System.out.println("Please try again");
        }
        System.out.print("Choose another operation: ");
        operationChoice = scanner.nextLine();
    }

    public static void choice2(Set<Student> studentSet, Scanner scanner) {
        System.out.println("You chose to delete a student from the repository!");
        System.out.print("Please enter the CNP of the student you want to delete: ");
        String idToBeRemoved = scanner.nextLine();
        try {
            deleteStudent(studentSet, idToBeRemoved);
        } catch (EmptyVariableException e) {
            logger.log(Level.SEVERE, e.getMessage(), e.getCause());
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        }
        System.out.print("Choose another operation: ");
        operationChoice = scanner.nextLine();
    }

    public static void choice1(Set<Student> studentSet, Scanner scanner) {
        String userInputFirstName;
        String userInputLastName;
        String userInputDateOfBirth;
        String userInputGender;
        String userInputId;

        System.out.println("You chose to add a new student to the repository!");
        System.out.println("Please provide the following details:");
        try {
            System.out.print("Student's first name: ");
            userInputFirstName = scanner.nextLine();
            System.out.print("Student's last name: ");
            userInputLastName = scanner.nextLine();
            System.out.print("Student's date of birth (YYYY-MM-DD format): ");
            userInputDateOfBirth = scanner.nextLine();
            System.out.print("Student's gender (M/F): ");
            userInputGender = scanner.nextLine();
            System.out.print("Student's CNP: ");
            userInputId = scanner.nextLine();
            addStudent(studentSet, userInputFirstName, userInputLastName,
                    userInputDateOfBirth, userInputGender, userInputId);
        } catch (EmptyVariableException | InvalidInputFormatException | OutOfBoundsException e) {
            logger.log(Level.SEVERE, e.getMessage(), e.getCause());
            System.out.println(e.getMessage());
            System.out.println("Please try again");
        } catch (DateTimeException e) {
            logger.log(Level.SEVERE, e.getMessage());
            System.out.println("Birth date should respect the YYYY-MM-DD format");
            System.out.println("Please try again");
        }
        System.out.print("Choose another operation: ");
        operationChoice = scanner.nextLine();
    }

    public static void printMenu() {
        System.out.println("Welcome to StdRepo!");
        System.out.println("You can choose from the following operations by typing the number:");
        System.out.println("1 - To add a student to the repo");
        System.out.println("2 - To delete a student from the repo");
        System.out.println("3 - To retrieve all students that meet the age criteria");
        System.out.println("4 - To list all the students from the repo");
        System.out.println("QUIT - To exit the app");
        System.out.print("Type here: ");
    }

}
