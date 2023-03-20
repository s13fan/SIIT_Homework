package org.siit.homework.week8.comparator;

import org.siit.homework.week8.athlete.Athlete;

import java.util.Comparator;

public class ComparatorByDuration implements Comparator<Athlete> {
    @Override
    public int compare(Athlete o1, Athlete o2) {
        return o1.getDuration().compareTo(o2.getDuration());
    }
}
