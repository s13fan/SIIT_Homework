package org.siit.homework.week11.operation;

import org.siit.homework.week11.model.RoomWithPrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelationTableOperation {

    public static final String RELATION_TABLE_INSERT = "INSERT INTO accommodation_room_fare_relation VALUES(?, ?, ?)";
    public static final String RELATION_TABLE_SELECT = "SELECT accommodation.type, accommodation.bed_type, room_fare.value_eur, room_fare.season\n" +
            "FROM accommodation\n" +
            "JOIN accommodation_room_fare_relation \n" +
            "ON accommodation.id=accommodation_room_fare_relation.accommodation_id\n" +
            "JOIN room_fare \n" +
            "ON accommodation_room_fare_relation.room_fare_id=room_fare.id";

    public static void insertAccommodationWithFare(Connection connection, int id, int accommodationId, int fareId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(RELATION_TABLE_INSERT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, accommodationId);
            preparedStatement.setInt(3, fareId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<RoomWithPrice> selectRoomWithPrice(Connection connection) {
        List<RoomWithPrice> roomWithPriceList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(RELATION_TABLE_SELECT);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                RoomWithPrice room = RoomWithPrice.builder()
                        .roomType(resultSet.getString("accommodation.type"))
                        .roomBedType(resultSet.getString("accommodation.bed_type"))
                        .roomPrice(resultSet.getDouble("room_fare.value_eur"))
                        .roomPriceSeason(resultSet.getString("room_fare.season"))
                        .build();
                roomWithPriceList.add(room);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return roomWithPriceList;
    }

}
