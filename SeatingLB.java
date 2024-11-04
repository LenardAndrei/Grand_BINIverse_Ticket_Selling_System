import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class SeatingLB {
    private String[] seats;  // Array to store seat availability
    private int numSeats;    // Total number of seats

    // Constructor to initialize seating layout
    public SeatingLB(int numSeats) {
        this.numSeats = numSeats;
        this.seats = new String[numSeats];
    }

    // Method to display the seat layout for a specific day
    public void displaySeatLayout(int day) {
        System.out.println("Seat Layout for Day " + day + ":");
        
        if (!loadSeatAvailability(day)) {
            Arrays.fill(seats, "Available");  // Initialize seats if file not found
        }

        int rows = (int) Math.ceil((double) numSeats / 10);  // Assuming 10 seats per row for simplicity
        int seatIndex = 0;

        // Loop through rows
        for (int i = 0; i < rows; i++) {
            System.out.print("Row " + (i + 1) + ": ");
            
            // Loop through seats in the row
            for (int j = 0; j < 10; j++) {
                if (seatIndex < numSeats) {
                    System.out.print("Available".equals(seats[seatIndex]) ? "[ ] " : "[X] ");
                    seatIndex++;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Method to select a seat for a specific day
    public boolean selectSeat(int day, int seatNumber) {
        if (seatNumber >= 1 && seatNumber <= numSeats && "Available".equals(seats[seatNumber - 1])) {
            seats[seatNumber - 1] = "Occupied";
            saveSeatAvailability(day);  // Save updated availability for this day
            return true;
        }
        return false;
    }

    // Method to get seat name for a specific day
    public String getSeatName(int seatNumber) {
        return (seatNumber >= 1 && seatNumber <= numSeats) ? "Seat " + seatNumber : null;
    }

    // Method to load seat availability for a specific day
    private boolean loadSeatAvailability(int day) {
        String filePath = "seat_availability_LB_day" + day + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int seatIndex = 0;

            while ((line = reader.readLine()) != null && seatIndex < numSeats) {
                seats[seatIndex] = line.trim();
                seatIndex++;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Method to save seat availability for a specific day
    private void saveSeatAvailability(int day) {
        String filePath = "seat_availability_LB_day" + day + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String seat : seats) {
                writer.write(seat);
                writer.newLine();
            }
        } catch (IOException e) {
        }
    }
}
