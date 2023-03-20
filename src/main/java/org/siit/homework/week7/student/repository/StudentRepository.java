package org.siit.homework.week7.student.repository;

import org.siit.homework.week7.comparator.ComparatorByBirthDate;
import org.siit.homework.week7.comparator.ComparatorByLastName;
import org.siit.homework.week7.exception.EmptyVariableException;
import org.siit.homework.week7.exception.InvalidInputFormatException;
import org.siit.homework.week7.exception.OutOfBoundsException;
import org.siit.homework.week7.student.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentRepository {

    public static void addStudent(Set<Student> studentSet, String firstName,
                                  String lastName, String birthDate, String gender,
                                  String id) throws EmptyVariableException, OutOfBoundsException, InvalidInputFormatException {
        checkFirstName(firstName);
        checkLastName(lastName);
        checkBirthDate(birthDate);
        checkGender(gender);
        checkId(id);
        studentSet.add(new Student(firstName.trim(), lastName.trim(), birthDate, gender.toUpperCase(), id.trim()));
    }

    private static void checkId(String id) throws EmptyVariableException, InvalidInputFormatException {
        if (id.equals("")) {
            throw new EmptyVariableException("CNP field cannot be empty");
        }
        if (!isNumber(id.trim()) || id.trim().length() != 13) {
            throw new InvalidInputFormatException("CNP must 13 characters long and must contain only numbers");
        }
    }

    private static void checkGender(String gender) throws InvalidInputFormatException {
        if (!gender.equals("M") && !gender.equals("m") && !gender.equals("F") && !gender.equals("f")) {
            throw new InvalidInputFormatException("Gender should be M or F");
        }
    }

    private static void checkBirthDate(String birthDate) throws OutOfBoundsException, InvalidInputFormatException {
        LocalDate dateOfBirth = LocalDate.parse(birthDate);
        if (!isValidBirthDate(birthDate)) {
            throw new InvalidInputFormatException("Birth date should respect the YYYY-MM-DD format");
        }
        if ((dateOfBirth.getYear() <= 1900) || (dateOfBirth.getYear() > LocalDate.now().getYear())) {
            throw new OutOfBoundsException("Birth date should be between 1900 and " + LocalDate.now().getYear());
        }
        if (calculateAge(birthDate) < 18) {
            throw new OutOfBoundsException("Students must be at least 18 years old");
        }
    }

    private static void checkLastName(String lastName) throws EmptyVariableException {
        if (lastName.equals("")) {
            throw new EmptyVariableException("Last name field cannot be empty");
        }
    }


    private static void checkFirstName(String firstName) throws EmptyVariableException {
        if (firstName.equals("")) {
            throw new EmptyVariableException("First name field cannot be empty");
        }
    }

    private static boolean isValidBirthDate(String input) {
        String regex = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.matches();
    }

    public static void retrieveStudentsOfAge(Set<Student> studentSet, String input) throws InvalidInputFormatException {
        int inputAge = Integer.parseInt(input);
        if (inputAge < 0) {
            throw new InvalidInputFormatException("Age cannot be negative");
        }
        for (Student student : studentSet) {
            int studentAge = calculateAge(student.getBirthDate());
            if (studentAge == inputAge) {
                System.out.println(student);
            }
        }
    }

    private static int calculateAge(String birthDate) {
        LocalDate dateOfBirth = LocalDate.parse(birthDate);
        LocalDate currentDate = LocalDate.now();
        if (dateOfBirth != null) {
            return Period.between(dateOfBirth, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public static void deleteStudent(Set<Student> studentSet, String id) throws EmptyVariableException {
        if (studentSet.isEmpty()) {
            throw new EmptyVariableException("The repository is currently empty");
        }
        if (!studentSet.removeIf(student -> student.getId().equals(id))) {
            throw new EmptyVariableException("Student not found");
        } else {
            System.out.println("The student with CNP: " + id + " has been removed");
        }
    }

    public static void listSortedStudents(Set<Student> studentSet, String input) throws InvalidInputFormatException {
        if (input.equals("1")) {
            List<Student> listByLastName = new ArrayList<>(studentSet);
            listByLastName.sort(new ComparatorByLastName());
            for (Student student : listByLastName) {
                System.out.println(student);
            }
        } else if (input.equals("2")) {
            List<Student> listByBirthDate = new ArrayList<>(studentSet);
            listByBirthDate.sort(new ComparatorByBirthDate());
            for (Student student : listByBirthDate) {
                System.out.println(student);
            }
        } else {
            throw new InvalidInputFormatException("Invalid input");
        }
    }

    public static boolean isNumber(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
