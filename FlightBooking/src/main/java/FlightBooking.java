import java.sql.*;
import java.util.Scanner;

public class FlightBooking {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Updated credentials based on your requirement
        String url = "jdbc:mysql://localhost:3306/airlinedb";
        String user = "root";
        String password = "root123"; 

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            con.setAutoCommit(false); // Enable manual transaction control

            System.out.print("Enter Flight ID (1 for Air India, 2 for Indigo): ");
            int flightId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Passenger Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Seats Required: ");
            int seats = sc.nextInt();

            // 1. Check seat availability and price
            String checkQuery = "SELECT available_seats, price_per_seat FROM flights WHERE flight_id=?";
            PreparedStatement ps1 = con.prepareStatement(checkQuery);
            ps1.setInt(1, flightId);

            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                int available = rs.getInt("available_seats");
                double price = rs.getDouble("price_per_seat");

                if (available >= seats) {
                    // 2. Update flight seats
                    String updateQuery = "UPDATE flights SET available_seats=? WHERE flight_id=?";
                    PreparedStatement ps2 = con.prepareStatement(updateQuery);
                    ps2.setInt(1, available - seats);
                    ps2.setInt(2, flightId);
                    ps2.executeUpdate();

                    double total = seats * price;

                    // 3. Insert booking record
                    String insertQuery = "INSERT INTO bookings(passenger_name, flight_id, seats_booked, total_amount) VALUES(?,?,?,?)";
                    PreparedStatement ps3 = con.prepareStatement(insertQuery);
                    ps3.setString(1, name);
                    ps3.setInt(2, flightId);
                    ps3.setInt(3, seats);
                    ps3.setDouble(4, total);
                    ps3.executeUpdate();

                    con.commit(); // Finalize transaction
                    System.out.println("-----------------------------------");
                    System.out.println("Booking Successful!");
                    System.out.println("Passenger: " + name);
                    System.out.println("Total Amount: ₹" + total);
                    System.out.println("-----------------------------------");

                } else {
                    con.rollback(); // Undo changes if seats aren't enough
                    System.out.println("Booking Failed: Not enough seats available (Only " + available + " left)");
                }
            } else {
                System.out.println("Flight not found with ID: " + flightId);
            }

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
