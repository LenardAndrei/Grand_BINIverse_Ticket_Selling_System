import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
        SeatingVIP vipSeating = new SeatingVIP(30); // 30 seats for VIP
        List<Ticket> ticketsOrdered = new ArrayList<>(); // List to store tickets
        
        System.out.println();
        System.out.println("Welcome to the Grand BINIverse Ticket System!");
        System.out.println();
        System.out.println("Available concert days are:");
        System.out.println("   1. Day 1 (November 16, 2024)");
        System.out.println("   2. Day 2 (November 17, 2024)");
        System.out.println("   3. Day 3 (November 18, 2024)");
        System.out.println("You can only choose one concert day per transaction.");
        System.out.println();
        System.out.println("Ticket Types and Prices:");
        System.out.printf("   %-20s | %s%n", "VIP Ticket", "Price: 11,000 pesos");
        System.out.printf("   %-20s | %s%n", "Patron A", "Price: 8,000 pesos");
        System.out.printf("   %-20s | %s%n", "Patron B", "Price: 7,300 pesos");
        System.out.printf("   %-20s | %s%n", "Lower Box", "Price: 5,800 pesos");
        System.out.printf("   %-20s | %s%n", "Upper Box", "Price: 2,600 pesos");
        System.out.printf("   %-20s | %s%n", "General Admission", "Price: 1,500 pesos");
        System.out.println("Note: Each email can purchase a maximum of 4 e-tickets per transaction.");
        System.out.println();
        System.out.println("Sustainability Commitment:");
        System.out.println("    In line with our commitment to sustainability and the United Nations' Sustainable Development Goals ");  
        System.out.println("    (SDG 12: Responsible Consumption and Production), all tickets for this event will be issued as e-tickets." );      
        System.out.println("    This shift to digital tickets helps reduce paper waste and contributes to our shared efforts in promoting");     
        System.out.println("    environmental responsibility.");
        System.out.println();
        System.out.println("E-Tickets Instructions:");
        System.out.println("    Your e-ticket will be sent to the email address you provide during the transaction. On the day of the");
        System.out.println("    concert, simply present the e-ticket email at the venue for verification. No physical tickets are needed");
        System.out.println("    your email confirmation will serve as your entry pass. This ensures a smoother and eco-friendly");
        System.out.println("    experience for everyone!");
        System.out.println();
        System.out.println("Donation Opportunity:");
        System.out.println("    After you complete your order, you can choose a charity to receive 10% of your payment as a donation, supporting social responsibility.");
        
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();  // Waits for the user to press Enter

        System.out.println("\nGrand BINIverse Ticket System!");
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        String customerEmail;
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}$";  // Basic email pattern 
        Pattern pattern = Pattern.compile(emailRegex);


        // Check if the file exists; create it if it doesn't exists
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
            System.out.println("Select payment method: ");
            System.out.println("[1] Credit Card");
            System.out.println("[2] Debit Card");
            System.out.println("[3] PayPal");
            System.out.print("Enter the number of your payment method: ");
            paymentMethod = scanner.nextLine().trim();
            
            // Check if the input is valid
            if (paymentMethod.equalsIgnoreCase("1")) {
                System.out.println("You selected Credit Card.\n");
                break;  
            } else if (paymentMethod.equalsIgnoreCase("2")) {
                System.out.println("You selected Debit Card.\n");
                break; 
            } else if (paymentMethod.equalsIgnoreCase("3")) {
                System.out.println("You selected PayPal.\n");
                break;  
            } else {
                System.out.println("Invalid payment method. Please select either [1] for 'Credit Card', [2] for 'Debit Card' or [3] for 'PayPal'.\n");
            }
        }

        if (paymentMethod.equalsIgnoreCase("1")) {
            // If the user chooses Credit Card
            String creditCardNumber;
            while (true) {
                System.out.print("Enter your 16-digit credit card number: ");
                creditCardNumber = scanner.nextLine().trim();
                // Validate the credit card number
                if (creditCardNumber.matches("(\\d{4} ?){3}\\d{4}")) {
                    System.out.println("Credit card number accepted.\n");
                    break;  
                } else {
                    System.out.println("Invalid credit card number.\n");
                }
            }
        } else if (paymentMethod.equalsIgnoreCase("2")) {
            // If the user chooses Debit Card
            String debitCardNumber;
            while (true) {
                System.out.print("Enter your 16-digit debit card number: ");
                debitCardNumber = scanner.nextLine().trim();
                // Validate the credit card number
                if (debitCardNumber.matches("(\\d{4} ?){3}\\d{4}")) {
                    System.out.println("Debit card number accepted.\n");
                    break;  
                } else {
                    System.out.println("Invalid debit card number.\n");
                }
            }
        } else if (paymentMethod.equalsIgnoreCase("3")) {
            // If the user chooses PayPal
            String paypalAccount;
            while (true) {
                System.out.print("Enter your PayPal email or phone number: ");
                paypalAccount = scanner.nextLine();
                // Validate the paypal account (email or phone number)
                if (paypalAccount.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}$")) { // Valid format for paypal email
                    System.out.println("Email accepted for PayPal.\n");
                    break;  
                } else if (paypalAccount.matches("^(\\+63|0)?9\\d{2}[-.\\s]?\\d{3}[-.\\s]?\\d{4}$|^(\\+63|0)?9\\d{10}$")) { // Phone numbers have at least 11 digits and in valid format
                    System.out.println("Phone number accepted for PayPal.\n");
                    break;  
                } else {
                    System.out.println("Invalid input. Enter a valid email or phone number.\n");
                }
            }
        }

        // Variable to store the user's concert day choice
        int concertDay = 0;

        while (concertDay < 1 || concertDay > 3) {
            System.out.println("Select the day of the concert:");
            System.out.println("[1] Day 1 (November 16, 2024)");
            System.out.println("[2] Day 2 (November 17, 2024)");
            System.out.println("[3] Day 3 (November 18, 2024)");

            System.out.print("Choose day: ");
             try {
                concertDay = scanner.nextInt(); 
                scanner.nextLine();  

                switch (concertDay) {
                    case 1:
                        System.out.println("You selected Day 1 (November 16, 2024).");
                        break;
                    case 2:
                        System.out.println("You selected Day 2 (November 17, 2024).");
                        break;
                    case 3:
                        System.out.println("You selected Day 3 (November 18, 2024).");
                        break;
                    default:
                        System.out.println("Invalid input. Please select a valid day (Day 1, Day 2, or Day 3).");
                        concertDay = 0; // Reset to ensure the loop continues
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3) to select the concert day.");
                scanner.nextLine();  // Clear the invalid input
            }
        }

        // Limit the user to selecting up to 4 tickets
        int totalTickets = 0;
        boolean continueOrdering = true;

        while (continueOrdering && totalTickets < 4) {
            System.out.println("\nTicket Types:");
            System.out.printf("[1]  %-20s | %s%n", "VIP Ticket", "Price: 11,000 pesos");
            System.out.printf("[2]  %-20s | %s%n", "Patron A", "Price: 8,000 pesos");
            System.out.printf("[3]  %-20s | %s%n", "Patron B", "Price: 7,300 pesos");
            System.out.printf("[4]  %-20s | %s%n", "Lower Box", "Price: 5,800 pesos");
            System.out.printf("[5]  %-20s | %s%n", "Upper Box", "Price: 2,600 pesos");
            System.out.printf("[6]  %-20s | %s%n", "General Admission", "Price: 1,500 pesos");
            
            System.out.print("Enter the number of your desired ticket type: ");
            String ticketType = scanner.nextLine().trim();

            
            // Display available seats based on the ticket type
            if (ticketType.equals("6")) {
                generalSeating.displaySeatLayout(concertDay);
            } else if (ticketType.equals("5")) {
                upperboxSeating.displaySeatLayout(concertDay);
            } else if (ticketType.equals("4")) {
                lowerboxSeating.displaySeatLayout(concertDay);  
            } else if (ticketType.equals("3")) {
                patronbSeating.displaySeatLayout(concertDay);      
            } else if (ticketType.equals("2")) {
                patronaSeating.displaySeatLayout(concertDay);
            } else if (ticketType.equals("1")) {
                vipSeating.displaySeatLayout(concertDay);
            } else {
                System.out.println("Invalid ticket type, enter number 1 to 6 only for valid ticket type.");
                continue;  
            }

            // Prompt user to select a seat for the selected ticket type             
            System.out.print("Select a seat (enter seat number): ");
            int selectedSeatNumber;
            try {
                selectedSeatNumber = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid seat number.");
                scanner.nextLine();  // Clear the invalid input
                continue;  
            }

            boolean seatSelected = false;
            String seatName = "";

            // Depending on the ticket type, attempt to select a seat
            if (ticketType.equals("6")) {
                seatSelected = generalSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = generalSeating.getSeatName(selectedSeatNumber);

            } else if (ticketType.equals("5")) {
                seatSelected = upperboxSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = upperboxSeating.getSeatName(selectedSeatNumber);

            } else if (ticketType.equals("4")) {
                seatSelected = lowerboxSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = lowerboxSeating.getSeatName(selectedSeatNumber);  

            } else if (ticketType.equals("3")) {
                seatSelected = patronbSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = patronbSeating.getSeatName(selectedSeatNumber);  

            } else if (ticketType.equals("2")) {
                seatSelected = patronaSeating.selectSeat(concertDay, selectedSeatNumber);
                seatName = patronaSeating.getSeatName(selectedSeatNumber);

            } else if (ticketType.equals("1")) {
                seatSelected = vipSeating.selectSeat(concertDay, selectedSeatNumber);
               seatName = vipSeating.getSeatName(selectedSeatNumber);
            }
            
            // If the seat selection was successful, add the ticket to the order
            if (seatSelected) {
                double price = 0;
                switch (ticketType) {
                    case "1":
                        price = 11000;
                        break;
                    case "2":
                        price = 8000;
                        break;
                    case "3":
                        price = 7300;
                        break;
                    case "4":
                        price = 5800;
                        break;
                    case "5":
                        price = 2600;
                        break;
                    case "6":
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
        switch (paymentMethod) {
            case "1":
                System.out.println("Payment Method: Credit Card");
                break;
            case "2":
                System.out.println("Payment Method: Debit Card");
                break;
            case "3":
                System.out.println("Payment Method: PayPal");
                break;
        }
        System.out.println("On the day of the concert, simply present the e-ticket email at the venue for verification. ");

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
            scanner.nextLine();  

            System.out.println("\nThank you for purchasing your ticket to the Grand BINIverse event!\n" + //
                                "As part of our commitment to social responsibility, 10% of your ticket price will be donated to charity.\n");

            System.out.println("Please select a charity to receive your donation:");
            System.out.println();
            System.out.println("[1] Gawad Kalinga\n" +
                                 "Aims to end poverty for millions by building sustainable communities and providing housing and livelihood programs.\n");
            System.out.println();
            System.out.println("[2] Children's Hour Philippines\n" +
                                 "Provides education, healthcare, and protection to underprivileged children, giving them a better chance at a brighter future.\n");
            System.out.println();
            System.out.println("[3] Tahanang Walang Hagdanan\n" +
                                 "Provides skills training and livelihood opportunities for persons with disabilities.");
            System.out.println();

            while (true) {
            System.out.print("Enter the number of the charity you want to donate to: ");
            
            try {
                int charity = scanner.nextInt();
                System.out.println();

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
                    System.out.println("Invalid input. Please enter (1, 2, or 3) to select a charity.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3) to select a charity.\n");
                scanner.nextLine();
            }
          }
        }
        scanner.close();
    }
}