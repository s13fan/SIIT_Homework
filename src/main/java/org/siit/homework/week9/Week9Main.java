package org.siit.homework.week9;

import org.siit.homework.week9.services.IPersonService;
import org.siit.homework.week9.services.Person;
import org.siit.homework.week9.services.PersonServiceImpl;

import java.util.List;

public class Week9Main {

    public static final IPersonService personService = new PersonServiceImpl();
    public static final String INPUT_FILE_PATH = "src/main/resources/week9input.csv";
    public static final String OUTPUT_FILE_PATH = "src/main/resources/week9output.csv";

    public static void main(String[] args) {

        List<Person> personList = personService.readInput(INPUT_FILE_PATH);
        personList = personService.manipulateData(personList, 7);
        personService.writeOutput(personList, OUTPUT_FILE_PATH);

    }
}
