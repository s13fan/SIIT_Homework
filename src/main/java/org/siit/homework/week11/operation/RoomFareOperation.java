package org.siit.homework.week11.operation;

import org.siit.homework.week11.model.RoomFare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomFareOperation {

    public static final String ROOM_FARE_INSERT = "INSERT INTO room_fare values(?, ?, ?)";
    public static final String ROOM_FARE_SELECT_ALL = "SELECT * FROM room_fare";
    public static final String ROOM_FARE_SELECT_BY_ID = "SELECT * FROM room_fare WHERE id=?";
    public static final String ROOM_FARE_DELETE = "DELETE FROM room_fare WHERE id=?";

    public static void insertRoomFare(Connection connection, int id, double valueEur, String season) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ROOM_FARE_INSERT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setDouble(2, valueEur);
            preparedStatement.setString(3, season);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<RoomFare> selectAllRoomFares(Connection connection) {
        List<RoomFare> roomFareList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ROOM_FARE_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                RoomFare roomFare = RoomFare.builder()
                        .id(resultSet.getInt("id"))
                        .valueEur(resultSet.getDouble("value_eur"))
                        .season(resultSet.getString("season"))
                        .build();
                roomFareList.add(roomFare);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return roomFareList;
    }

    public static RoomFare selectRoomFareById(Connection connection, int id) {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(ROOM_FARE_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return RoomFare.builder()
                        .id(resultSet.getInt("id"))
                        .valueEur(resultSet.getDouble("value_eur"))
                        .season(resultSet.getString("season"))
                        .build();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return null;
    }

    public static void deleteRoomFare(Connection connection, int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ROOM_FARE_DELETE)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
