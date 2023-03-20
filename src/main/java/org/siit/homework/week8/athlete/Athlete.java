package org.siit.homework.week8.athlete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.siit.homework.week8.enums.CountryCode;

import java.time.Duration;
import java.util.Objects;

@AllArgsConstructor
@Getter
@ToString
public class Athlete {

    private int number;
    private String name;
    private CountryCode country;
    private String raceTime;
    private String[] shootingResults;
    private int missedShots;
    private String totalRaceTime;
    private Duration duration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return number == athlete.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
