package org.siit.homework.week9.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceImplTest {

    private static PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        personService = new PersonServiceImpl();
    }

    @Test
    void testManipulateData_whenInputListIsEmpty_thenReturnAnEmptyList() {
        List<Person> personList = new ArrayList<>();
        personService.manipulateData(personList, 1);
        assertTrue(personList.isEmpty());
    }

    @Test
    void testManipulateData_whenInputMonthIsNotInTheList_thenReturnAnEmptyList() {
        List<Person> personList = Collections.singletonList(
                new Person("Felix", "Cirebea", LocalDate.of(1996, 11, 25)));
        personList = personService.manipulateData(personList, 1);
        assertTrue(personList.isEmpty());
    }

    @Test
    void testManipulateData_whenInputIsValid_thenReturnAListNotEmpty() {
        List<Person> personList = Arrays.asList(
                new Person("Felix", "Cirebea", LocalDate.of(1996, 11, 25)),
                new Person("Ion", "Popescu", LocalDate.of(1975, 11, 14)),
                new Person("Gigel", "Ionescu", LocalDate.of(1985, 7, 13)));
        personList = personService.manipulateData(personList, 11);
        assertFalse(personList.isEmpty());
        assertEquals(2, personList.size());
    }


}