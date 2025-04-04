package org.siit.homework.week7.comparator;

import org.siit.homework.week7.student.Student;

import java.util.Comparator;

public class ComparatorByLastName implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        int result = o1.getLastName().compareTo(o2.getLastName());
        if (result == 0) {
            result = o1.getFirstName().compareTo(o2.getFirstName());
        }
        return result;
    }
}
