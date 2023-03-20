package org.siit.homework.week9.services;

import org.siit.homework.week9.services.Person.PersonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceImpl implements IPersonService {

    private static final PersonBuilder personBuilder = new PersonBuilder();

    @Override
    public List<Person> readInput(String filePath) {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                generateList(line, personList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public List<Person> manipulateData(List<Person> personList, int targetMonth) {
        return personList.stream()
                .filter(person -> person.getDateOfBirth().getMonthValue() == targetMonth)
                .map(person -> personBuilder
                        .firstName(person.getFirstName())
                        .lastName(person.getLastName())
                        .build())
                .sorted(Comparator.comparing(Person::getFirstName))
                .collect(Collectors.toList());
    }

    @Override
    public void writeOutput(List<Person> personList, String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Person person : personList) {
                writer.write(person.getFirstName() + "," + person.getLastName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateList(String line, List<Person> personList) {
        String[] splitLine = line.split(",");
        personList.add(personBuilder
                .firstName(splitLine[0])
                .lastName(splitLine[1])
                .dateOfBirth(convertDateOfBirth(splitLine[2]))
                .build());
    }

    @Override
    public LocalDate convertDateOfBirth(String dob) {
        String[] splitDOB = dob.split("-");
        return LocalDate.of(Integer.parseInt(splitDOB[0]),
                Integer.parseInt(splitDOB[1]),
                Integer.parseInt(splitDOB[2]));
    }

}
