package org.siit.homework.week8;

import org.siit.homework.week8.athlete.Athlete;
import org.siit.homework.week8.comparator.ComparatorByDuration;

import java.util.Set;
import java.util.TreeSet;

import static org.siit.homework.week8.services.LadderBoardService.readFile;
import static org.siit.homework.week8.services.LadderBoardService.writeResults;

public class Week8Main {
    public static void main(String[] args) {
        //As a constraint regarding input, the program doesn't work with race times bigger than 1 hour (with or without penalties)
        Set<Athlete> athleteSet = new TreeSet<>(new ComparatorByDuration());
        String filePath = "src/main/resources/input.csv";
        readFile(athleteSet, filePath);

        for (Athlete athlete : athleteSet) {
            System.out.println(athlete);
        }
        System.out.println();

        writeResults(athleteSet);

    }
}
