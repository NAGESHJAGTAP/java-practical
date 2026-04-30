CREATE DATABASE IF NOT EXISTS airlinedb;
USE airlinedb;

DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS flights;

CREATE TABLE flights (
    flight_id INT PRIMARY KEY,
    flight_name VARCHAR(100),
    available_seats INT,
    price_per_seat DOUBLE
);

CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    passenger_name VARCHAR(100),
    flight_id INT,
    seats_booked INT,
    total_amount DOUBLE,
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);

-- Sample Data
INSERT INTO flights VALUES (1, 'Air India', 100, 5000);
INSERT INTO flights VALUES (2, 'Indigo', 50, 3000);
