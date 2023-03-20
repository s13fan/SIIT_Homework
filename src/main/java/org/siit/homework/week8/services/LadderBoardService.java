package org.siit.homework.week8.services;

import org.siit.homework.week8.athlete.Athlete;
import org.siit.homework.week8.enums.CountryCode;

import java.io.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public abstract class LadderBoardService {

    public static final String OUTPUT_FILE_PATH = "src/main/resources/results.txt";

    public static void readFile(Set<Athlete> athleteSet, String filePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                generateCollection(athleteSet, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateCollection(Set<Athlete> athleteSet, String line) {
        String[] split = line.split(",");
        if (!(split[4].length() == 5) || !(split[5].length() == 5) || !(split[6].length() == 5)) {
            throw new IllegalArgumentException("Wrong input format for " + split[1] + " shooting results!");
        }
        String[] shootingResults = {split[4], split[5], split[6]};
        int numberOfMissedShots = calculateMissedShots(shootingResults);
        Duration raceDuration = calculateTotalRaceTime(split[3], numberOfMissedShots);
        String totalRaceTime = convertDurationToString(raceDuration);
        Athlete athlete = new Athlete(Integer.parseInt(split[0]), split[1], CountryCode.valueOf(split[2]), split[3],
                shootingResults, numberOfMissedShots, totalRaceTime, raceDuration);
        athleteSet.add(athlete);
    }

    private static int calculateMissedShots(String[] shootingResults) {
        char findChar = 'o';
        int charCount = 0;
        for (String s : shootingResults) {
            s = s.toLowerCase().trim();
            if (s.contains("o")) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == findChar) {
                        charCount++;
                    }
                }
            }
        }
        return charCount;
    }

    private static Duration calculateTotalRaceTime(String raceTime, int missedShots) {
        Duration duration = convertStringToDuration(raceTime);
        duration = duration.plusSeconds(missedShots * 10L);
        return duration;
    }

    private static String convertDurationToString(Duration duration) {
        String s = duration.toString();
        if (s.length() == 5) {
            return String.valueOf(s.charAt(2)) + s.charAt(3) + ":00";
        } else if (s.length() == 7) {
            return String.valueOf(s.charAt(2)) + s.charAt(3) + ":0" + s.charAt(5);
        }
        return String.valueOf(s.charAt(2)) + s.charAt(3) + ":" + s.charAt(5) + s.charAt(6);
    }

    private static Duration convertStringToDuration(String time) {
        String[] splitRaceTime = time.split(":");
        if (splitRaceTime.length == 3) {
            return Duration.ofHours(Integer.parseInt(splitRaceTime[0]))
                    .plusMinutes(Integer.parseInt(splitRaceTime[1]))
                    .plusSeconds(Integer.parseInt(splitRaceTime[2]));
        }
        return Duration.ofMinutes(Integer.parseInt(splitRaceTime[0]))
                .plusSeconds(Integer.parseInt(splitRaceTime[1]));
    }

    public static void writeResults(Set<Athlete> athleteSet) {
        Iterator<Athlete> iterator = athleteSet.iterator();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH))) {
            Athlete next = iterator.next();
            writer.write("Winner - " + next.getName() + " " + next.getTotalRaceTime() + " (" +
                    next.getRaceTime() + " + " + next.getMissedShots() + ")" + "\n");
            next = iterator.next();
            writer.write("Runner-up - " + next.getName() + " " + next.getTotalRaceTime() + " (" +
                    next.getRaceTime() + " + " + next.getMissedShots() + ")" + "\n");
            next = iterator.next();
            writer.write("Third Place - " + next.getName() + " " + next.getTotalRaceTime() + " (" +
                    next.getRaceTime() + " + " + next.getMissedShots() + ")" + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
