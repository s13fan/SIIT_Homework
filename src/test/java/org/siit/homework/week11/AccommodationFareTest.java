package org.siit.homework.week11;

import org.junit.jupiter.api.*;
import org.siit.homework.week11.operation.AccommodationOperation;
import org.siit.homework.week11.operation.RelationTableOperation;
import org.siit.homework.week11.operation.RoomFareOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccommodationFareTest {

    private static Connection connection;

    @BeforeAll
    public static void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:homework_db", "sa", "");
    }

    @Test
    @Order(1)
    public void createData() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS accommodation\n" +
                "(\n" +
                "    id bigint NOT NULL,\n" +
                "    type character varying(32) NOT NULL,\n" +
                "    bed_type character varying(32) NOT NULL,\n" +
                "    max_guests bigint NOT NULL,\n" +
                "    description character varying(512),\n" +
                "    CONSTRAINT accommodation_pkey PRIMARY KEY (id)\n" +
                ")");
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS room_fare\n" +
                "(\n" +
                "    id bigint NOT NULL,\n" +
                "    value_eur double NOT NULL,\n" +
                "    season character varying(32) NOT NULL,\n" +
                "    CONSTRAINT room_fare_pkey PRIMARY KEY (id)\n" +
                ")");
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS accommodation_room_fare_relation\n" +
                "(\n" +
                "    id bigint NOT NULL,\n" +
                "    accommodation_id bigint NOT NULL,\n" +
                "    room_fare_id bigint NOT NULL,\n" +
                "    CONSTRAINT \"accommodationFK\" FOREIGN KEY (accommodation_id)\n" +
                "    REFERENCES accommodation (id),\n" +
                "    CONSTRAINT \"room_fareFK\" FOREIGN KEY (room_fare_id)\n" +
                "    REFERENCES room_fare (id)\n)");
        preparedStatement.executeUpdate();
    }

    @Test
    @Order(2)
    public void insertData() {
        AccommodationOperation.insertAccommodation(connection, 1, "single", "single", 1,
                "Single room with spa access, wi-fi and mini bar");
        AccommodationOperation.insertAccommodation(connection, 2, "single", "single", 1,
                "Single room with spa access, wi-fi and mini bar");
        AccommodationOperation.insertAccommodation(connection, 3, "double", "double", 2,
                "Double room with double bed, spa access, wi-fi, mini bar and breakfast included");
        AccommodationOperation.insertAccommodation(connection, 4, "suite", "queen size", 2,
                "Suite with spa and pool access, wi-fi and mini bar and breakfast included");
        AccommodationOperation.insertAccommodation(connection, 5, "suite", "king size", 2,
                "Suite with spa and pool access, wi-fi and mini bar, mountain view and breakfast included");
        AccommodationOperation.insertAccommodation(connection, 6, "quad", "2x king size", 4,
                "Quad room with spa access and pool, wi-fi, mini bar, mountain view, room service and breakfast included");

        RoomFareOperation.insertRoomFare(connection, 1, 150, "winter");
        RoomFareOperation.insertRoomFare(connection, 2, 200, "summer");
        RoomFareOperation.insertRoomFare(connection, 3, 200, "winter");
        RoomFareOperation.insertRoomFare(connection, 4, 300, "winter");
        RoomFareOperation.insertRoomFare(connection, 5, 350, "summer");
        RoomFareOperation.insertRoomFare(connection, 6, 350, "winter");

        RelationTableOperation.insertAccommodationWithFare(connection, 1, 1, 1);
        RelationTableOperation.insertAccommodationWithFare(connection, 2, 1, 2);
        RelationTableOperation.insertAccommodationWithFare(connection, 3, 2, 1);
        RelationTableOperation.insertAccommodationWithFare(connection, 4, 2, 2);
        RelationTableOperation.insertAccommodationWithFare(connection, 5, 3, 3);
        RelationTableOperation.insertAccommodationWithFare(connection, 6, 3, 2);
        RelationTableOperation.insertAccommodationWithFare(connection, 7, 4, 4);
        RelationTableOperation.insertAccommodationWithFare(connection, 8, 4, 5);
        RelationTableOperation.insertAccommodationWithFare(connection, 9, 5, 4);
        RelationTableOperation.insertAccommodationWithFare(connection, 10, 5, 5);
        RelationTableOperation.insertAccommodationWithFare(connection, 11, 6, 6);
        RelationTableOperation.insertAccommodationWithFare(connection, 12, 6, 5);

    }

    @Test
    @Order(3)
    public void selectData() {
        System.out.println(RelationTableOperation.selectRoomWithPrice(connection));
    }

}

