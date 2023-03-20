package org.siit.homework.week7.student;


import java.util.Objects;

@SuppressWarnings("All")
public class Student {

    private String firstName;
    private String lastName;
    private String birthDate; //YYYY-MM-DD format enforced by regex
    private String gender;
    private String id;

    public Student(String firstName, String lastName, String birthDate, String gender, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender=" + gender +
                ", id='" + id + '\'' +
                '}';
    }
}
