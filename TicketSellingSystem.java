import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketSellingSystem {
    public void start() {
        Scanner scanner = new Scanner(System.in);

        SeatingGen generalSeating = new SeatingGen(50);  // 50 seats for General Admission
        SeatingUB upperboxSeating = new SeatingUB(70); // 70 seats for Upper Box
        SeatingLB lowerboxSeating = new SeatingLB(40); // 40 seats for Lower Box
        SeatingPatronB patronbSeating = new SeatingPatronB(50); // 50 seats for Patron B
        SeatingPatronA patronaSeating = new SeatingPatronA(30);   // 30 seats for Patron A
        SeatingVIP vipSeating = new SeatingVIP(15); // 15 seats for VIP
        List<Ticket> ticketsOrdered = new ArrayList<>(); // List to store tickets

        System.out.println("Welcome to the Grand BINIverse Ticket System!");
        System.out.println();
        System.out.println("Ticket Selling Instructions:");
        System.out.println("You can only choose one concert day per transaction. The available concert days are:");
        System.out.println("1. Day 1 (November 16, 2024)");
        System.out.println("2. Day 2 (November 17, 2024)");
        System.out.println("3. Day 3 (November 18, 2024)");
        System.out.println();

        System.out.println("You can choose from the following ticket types:");
        System.out.println("VIP Ticket | Price: 11,000 pesos");
        System.out.println("Patron A | Price: 8,000 pesos");
        System.out.println("Patron B | Price: 7,300 pesos");
        System.out.println("Lower Box | Price: 5,800 pesos");
        System.out.println("Upper Box| Price: 2,600 pesos");
        System.out.println("General Admission | Price: 1,500 pesos");
        System.out.println();
        System.out.println("Only one email can only purchase a maximum of 4 e-tickets for the concert day they have selected in a single transaction.");
        System.out.println();
        System.out.println("Note:");
        System.out.println("In line with our commitment to sustainability and the United Nations' Sustainable Development Goals ");  
        System.out.println("(SDG 12: Responsible Consumption and Production), all tickets for this event will be issued as e-tickets." );      
        System.out.println("This shift to digital tickets helps reduce paper waste and contributes to our shared efforts in promoting");     
        System.out.println("environmental responsibility.");
        System.out.println();
        System.out.println("Your e-ticket will be sent to the email address you provide during the transaction. On the day of the");
        System.out.println("concert, simply present the e-ticket email at the venue for verification. No physical tickets are needed");
        System.out.println("your email confirmation will serve as your entry pass. This ensures a smoother and eco-friendly");
        System.out.println("experience for everyone!");
        System.out.println();
        System.out.println("After you complete your order, you can choose a charity to receive 10% of your payment as a donation, supporting social responsibility.");
        
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();  // Waits for the user to press Enter

        System.out.println("\nGrand BINIverse Ticket System!");
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        String customerEmail;
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}$";  // Basic email pattern 
        Pattern pattern = Pattern.compile(emailRegex);


        // Check if the file exists; create it of it doesn't
        File emailFile = new File("emailData.txt");
        if (!emailFile.exists()) {
            try {
                emailFile.createNewFile(); // Create the file
            } catch (IOException e) {
                System.out.println("An error occured while creating the file.");
                e.printStackTrace();
            }
        }

        while (true) {
            System.out.print("Enter your email: ");
            customerEmail = scanner.nextLine().trim();

            // Create matcher object
            Matcher matcher = pattern.matcher(customerEmail);

            // Check if the email format is valid
            if (!matcher.matches()) {
                System.out.println("Invalid email format. Please try again.");
                continue; // Prompt again if format is invalid
            }

            // Check if the email already exists in the file
            boolean emailExists = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(emailFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Check if the existing line matches the entered email
                    if (line.equalsIgnoreCase(customerEmail)) {
                        emailExists = true;
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }

            // If the email does not exist, save it; otherwise, notify the user
            if (!emailExists) {
                try (FileWriter writer = new FileWriter(emailFile, true)) {
                    writer.write(customerEmail + "\n"); // Write the email to the file
                    System.out.println();
                } catch (IOException e) {
                    System.out.println("An error occurred while saving the email.");
                    e.printStackTrace();
                }
                break; // Exit the loop after saving
            } else {
                System.out.println("The email " + customerEmail + " is already used. Please enter a different email.");
            }
        }

        // Variable to store valid payment method
        String paymentMethod = "";

        // Keep asking for the payment method until valid input is provided
        while (true) {
            System.out.print("Select payment method (Credit Card/PayPal): ");
            paymentMethod = scanner.nextLine().trim();
            
            // Check if the input is valid
            if (paymentMethod.equalsIgnoreCase("Credit Card")) {
                System.out.println("You selected Credit Card.\n");
                break;  // Exit the loop if valid input
            } else if (paymentMethod.equalsIgnoreCase("PayPal")) {
                System.out.println("You selected PayPal.\n");
                break;  // Exit the loop if valid input
            } else {
                // Invalid input case
                System.out.println("Invalid payment method. Please select either 'Credit Card' or 'PayPal'.\n");
            }
        }

        if (paymentMethod.equalsIgnoreCase("Credit Card")) {
            // If the user chooses Credit Card
            String creditCardNumber;
            while (true) {
                System.out.print("Enter your 16-digit credit card number: ");
                creditCardNumber = scanner.nextLine().trim();
                // Validate the credit card number
                if (creditCardNumber.length() == 16 && creditCardNumber.matches("\\d+")) {
                    System.out.println("Credit card number accepted.\n");
                    break;  // Exit the loop if valid input
                } else {
                    System.out.println("Invalid credit card number. It should be exactly 16 digits and contain only numbers.\n");
                }
            }
        } else if (paymentMethod.equalsIgnoreCase("PayPal")) {
            // If the user chooses PayPal
            String paypalAccount;
            while (true) {
                System.out.print("Enter your PayPal email or phone number: ");
                paypalAccount = scanner.nextLine();
                // Validate the PayPal account (email or phone number)
                if (paypalAccount.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}$")) {
                    System.out.println("Email accepted for PayPal.\n");
                    break;  // Exit the loop if valid email
                } else if (paypalAccount.matches("\\d{11,}")) { // Phone numbers have at least 11 digits
                    System.out.println("Phone number accepted for PayPal.\n");
                    break;  // Exit the loop if valid phone number
                } else {
                    System.out.println("Invalid input. Enter a valid email or phone number.\n");
                }
            }
        }

        // Variable to store the user's concert day choice
        int concertDay = 0;

        // Keep asking until the input is valid
        while (concertDay < 1 || concertDay > 3) {
            System.out.println("Select the day of the concert:");
            System.out.println("1. Day 1 (November 16, 2024)");
            System.out.println("2. Day 2 (November 17, 2024)");
            System.out.println("3. Day 3 (November 18, 2024)");

            System.out.print("Choose day: ");
            concertDay = scanner.nextInt(); // Get the user input as an integer
            scanner.nextLine();  // Consume the leftover newline character

            if (concertDay == 1) {
                System.out.println("You selected Day 1 (November 16, 2024).");
            } else if (concertDay == 2) {
                System.out.println("You selected Day 2 (November 17, 2024).");
            } else if (concertDay == 3) {
                System.out.println("You selected Day 3 (November 18, 2024).");
            } else {
                System.out.println("Invalid input. Please select a valid day (Day 1, Day 2, or Day 3).");
            }
        }

        // Limit the user to selecting up to 4 tickets
        int totalTickets = 0;
        boolean continueOrdering = true;

        while (continueOrdering && totalTickets < 4) {
            System.out.println("\nTicket Types:");
            System.out.println("VIP | Price: 11,000 pesos");
            System.out.println("Patron A | Price: 8,000 pesos");
            System.out.println("Patron B | Price: 7,300 pesos");
            System.out.println("Lower Box | Price: 5,800 pesos");
            System.out.println("Upper Box | Price: 2,600 pesos");
            System.out.println("General Admission | Price: 1,500 pesos");
            System.out.print("Choose ticket type: ");
            String ticketType = scanner.nextLine().toUpperCase().trim();

            // Display available seats based on the ticket type
            if (ticketType.equals("GENERAL ADMISSION")) {
                generalSeating.displaySeatLayout(concertDay);
            } else if (ticketType.equals("UPPER BOX")) {
                upperboxSeating.displaySeatLayout(concertDay);
            } else if (ticketType.equals("LOWER BOX")) {
                lowerboxSeating.displaySeatLayout(concertDay);  
            } else if (ticketType.equals("PATRON B")) {
                patronbSeating.displaySeatLayout(concertDay);      
            } else if (ticketType.equals("PATRON A")) {
                patronaSeating.displaySeatLayout(concertDay);
            } else if (ticketType.equals("VIP")) {
                vipSeating.displaySeatLayout(concertDay);
            } else {
                System.out.println("Invalid ticket type.");
                continue;  
            }

            // Prompt user to select a seat for the selected ticket type
            System.out.print("Select a seat (enter seat number): ");
            int selectedSeatNumber = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            boolean seatSelected = false;
            String seatName = "";

            // Depending on the ticket type, attempt to select a seat
            if (ticketType.equals("GENERAL ADMISSION")) {
                seatSelected = generalSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = generalSeating.getSeatName(selectedSeatNumber);

            } else if (ticketType.equals("UPPER BOX")) {
                seatSelected = upperboxSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = upperboxSeating.getSeatName(selectedSeatNumber);

            } else if (ticketType.equals("LOWER BOX")) {
                seatSelected = lowerboxSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = lowerboxSeating.getSeatName(selectedSeatNumber);  

            } else if (ticketType.equals("PATRON B")) {
                seatSelected = patronbSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = patronbSeating.getSeatName(selectedSeatNumber);  

            } else if (ticketType.equals("PATRON A")) {
                seatSelected = patronaSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = patronaSeating.getSeatName(selectedSeatNumber);

            } else if (ticketType.equals("VIP")) {
                seatSelected = vipSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = vipSeating.getSeatName(selectedSeatNumber);
            }

            // If the seat selection was successful, add the ticket to the order
            if (seatSelected) {
                double price = 0;
                switch (ticketType) {
                    case "VIP":
                        price = 11000;
                        break;
                    case "PATRON A":
                        price = 8000;
                        break;
                    case "PATRON B":
                        price = 7300;
                        break;
                    case "LOWER BOX":
                        price = 5800;
                        break;
                    case "UPPER BOX":
                        price = 2600;
                        break;
                    case "GENERAL ADMISSION":
                        price = 1500;
                        break;
                }

                Ticket ticket = TicketSeller.sellTicket(ticketType, "Grand BINIverse", seatName, price, concertDay);
                if (ticket != null) {
                    ticketsOrdered.add(ticket);
                    totalTickets++;  // Increment ticket counter
                    System.out.println("Ticket successfully added to order!");
                    ticket.printTicket();
                }
            } else {
                System.out.println("Invalid seat selection or seat already occupied.");
            }

            while (true) {
                if (ticketsOrdered.size() < 4) {
                    System.out.print("Do you want to order another ticket? (yes/no): ");
                    String userInput = scanner.nextLine().trim().toLowerCase();
                    
                    // Error handling: check for valid input ("yes" or "no")
                    if (userInput.equals("yes")) {
                        continueOrdering = true;
                        break;  // Exit the loop and continue ordering tickets
                    } else if (userInput.equals("no")) {
                        continueOrdering = false;
                        break;  // Exit the loop, user doesn't want to order more tickets
                    } else {
                        // Invalid input, ask the user to enter a valid response
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                } else {
                    System.out.println("You already ordered the maximum amount of 4 tickets.");
                    break;  // Exit the loop as the user can't order more tickets
                }
            }
            // Saving ticket orders in txt file
            try (FileWriter writer = new FileWriter("ticketData.txt", true)) {
                writer.write(customerName + " | " + customerEmail + " | " + ticketType + " | " + seatName + " | Day: " + concertDay + "\n"); 
            } catch (IOException e) {
                System.out.println("An error occured.");
                e.printStackTrace();
            }
        }

        // Display final order summary
        System.out.println("___________________________________________________________________________________________________");
        System.out.println("\nOverall Ticket Order Summary for " + customerName + ":");
        System.out.println("Your e-ticket will be sent to " + customerEmail);
        System.out.println("On the day of the concert, simply present the e-ticket email at the venue for verification. ");
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println();
        if (ticketsOrdered.isEmpty()) {
            System.out.println("No tickets ordered.");
        } else {
            double totalAmount = 0;
            for (Ticket ticket : ticketsOrdered) {
                ticket.printTicket();
                totalAmount += ticket.getPrice();
            }
            System.out.println("Total amount: " + totalAmount + " pesos.");
            System.out.println("\n___________________________________________________________________________________________________");

            System.out.print("Press Enter to continue...");
            scanner.nextLine();  // Waits for the user to press Enter

            System.out.println("\nThank you for purchasing your ticket to the Grand BINIverse event!\n" + //
                                "As part of our commitment to social responsibility, 10% of your ticket price will be donated to charity.\n");

            System.out.println("Please select a charity to receive your donation:");
            System.out.println();
            System.out.println("1. Gawad Kalinga\n" +
                                 "Aims to end poverty for millions by building sustainable communities and providing housing and livelihood programs.\n");
            System.out.println();
            System.out.println("2. Children's Hour Philippines\n" +
                                 "Provides education, healthcare, and protection to underprivileged children, giving them a better chance at a brighter future.\n");
            System.out.println();
            System.out.println("3. Tahanang Walang Hagdanan\n" +
                                 "Provides skills training and livelihood opportunities for persons with disabilities.");
            System.out.println();
            System.out.print("Type the number of the charity you want to donate: ");
            int charity = scanner.nextInt();

            System.out.println();
            
            while(true) {
                if (charity == 1) {
                    System.out.println("Thank you for choosing Gawad Kalinga! Your contribution will make a positive impact on their mission.");
                    System.out.print("Exiting the system...");                  
                    break;
                } else if (charity == 2) {
                    System.out.println("Thank you for choosing Children's Hour Philippines! Your contribution will make a positive impact on their mission.");
                    System.out.print("Exiting the system...");
                    break;
                } else if (charity == 3) {
                    System.out.println("Thank you for choosing Tahanang Walang Hagdanan! Your contribution will make a positive impact on their mission.");
                    System.out.print("Exiting the system...");
                    break;
                } else {
                    System.out.println("Invalid input");
                    System.out.print("Type the number of the charity you want to donate: ");
                    charity = scanner.nextInt();
                }
            }
        }
        scanner.close();
    }
}