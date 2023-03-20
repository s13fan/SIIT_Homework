package org.siit.homework.week9.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Person {

    String firstName;
    String lastName;
    LocalDate dateOfBirth;

}
