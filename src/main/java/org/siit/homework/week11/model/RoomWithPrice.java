package org.siit.homework.week11.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomWithPrice {

    private String roomType;
    private String roomBedType;
    private double roomPrice;
    private String roomPriceSeason;

}
