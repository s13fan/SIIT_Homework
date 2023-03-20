DESCRIPTION

define a database for a booking application that should contain the following tables
accommodation with the following columns
-- id: int (primary key) - can also be bigint or uuid
-- type: varchar(32)
-- bed_type: varchar(32)
-- max_guests: int
-- description: varchar(512)

room_fare with the following columns
-- id: int (primary key)
-- value: double
-- season: varchar(32)

accommodation_room_fare_relation with the following columns
-- id: int (primary key)
-- accommodation_id: int (foreign key of accommodation)
-- room_fare_id: int (foreign key of room_fair)

write a test which inserts (INSERT INTO...) data in the tables using the PreparedStatement and 
keep into consideration the connection(s) contained in the accommodation_room_fare_relation table
write a unit test which interrogates the database (SELECT * FROM...) and 
print out (in the console) the prices for each room in the database. Use data models!!!

NOTES 
respect the table and column names and the data types
the "accommodation_room_fair_relation" is a connection table between the rooms and the prices
you will have to use JOIN the interrogation queries
for inserting in the database, using Java, I would recommend using PreparedStatement.executeUpdate(...)