package org.siit.homework.week9.services;

import java.time.LocalDate;
import java.util.List;

public interface IPersonService {

    List<Person> readInput(String filePath);

    List<Person> manipulateData(List<Person> personList, int targetMonth);

    void writeOutput(List<Person> personList, String outputFilePath);

    void generateList(String line, List<Person> personList);

    LocalDate convertDateOfBirth(String dob);

}
