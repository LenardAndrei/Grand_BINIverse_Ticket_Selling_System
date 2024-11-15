// Abstract class for a general ticket
public abstract class Ticket {
    private String ticketType;
    private String eventName;
    private String seat;
    private double price;
    private int day;

    // Constructor
    public Ticket(String ticketType, String eventName, String seat, double price, int day) {
        this.ticketType = ticketType;
        this.eventName = eventName;
        this.seat = seat;
        this.price = price;
        this.day = day;
    }

    // Getter methods
    public String getTicketType() {
        return ticketType;
    }

    public String getEventName() {
        return eventName;
    }

    public String getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public int getDay() {
        return day;
    }

    // Abstract method to print the ticket
    public abstract void printTicket();
}

// Concrete class for General Admission Ticket
class GeneralTicket extends Ticket {
    public GeneralTicket(String ticketType, String eventName, String seat, double price, int day) {
        super(ticketType, eventName, seat, price, day);
    }

    @Override
    public void printTicket() {
        System.out.println("Ticket Type: " + getTicketType() + " | " + getEventName() + " | Day: " + getDay() + " | Seat: " + getSeat() + " | Price: " + getPrice());
    }
}

// Concrete class for Upper Box Ticket
class UpperBox extends Ticket {
    public UpperBox(String ticketType, String eventName, String seat, double price, int day) {
         super(ticketType, eventName, seat, price, day);
    }

    @Override
    public void printTicket() {
        System.out.println("Ticket Type: " + getTicketType() + " | " + getEventName() + " | Day: " + getDay() + " | Seat: " + getSeat() + " | Price: " + getPrice());
    }
}

// Concrete class for Lower Box Ticket
class LowerBox extends Ticket {
    public LowerBox(String ticketType, String eventName, String seat, double price, int day) {
        super(ticketType, eventName, seat, price, day);
    }

    @Override
    public void printTicket() {
        System.out.println("Ticket Type: " + getTicketType() +  " | " + getEventName() + " | Day: " + getDay() + " | Seat: " + getSeat() + " | Price: " + getPrice());
    }
}

// Concrete class for Patron B Ticket
class PatronBTicket extends Ticket {
    public PatronBTicket(String ticketType, String eventName, String seat, double price, int day) {
        super(ticketType, eventName, seat, price, day);
    }

    @Override
    public void printTicket() {
        System.out.println("Ticket Type: " + getTicketType() + " | " + getEventName() + " | Day: " + getDay() + " | Seat: " + getSeat() + " | Price: " + getPrice());
    }
}

// Concrete class for Patron A Ticket
class PatronATicket extends Ticket {
    public PatronATicket(String ticketType, String eventName, String seat, double price, int day) {
        super(ticketType, eventName, seat, price, day);
    }

    @Override
    public void printTicket() {
        System.out.println("Ticket Type: " + getTicketType() + " | " + getEventName() + " | Day: " + getDay() + " | Seat: " + getSeat() + " | Price: " + getPrice());
    }
}

// Concrete class for VIP Ticket
class VIPTicket extends Ticket {
    public VIPTicket(String ticketType, String eventName, String seat, double price, int day) {
        super(ticketType, eventName, seat, price, day);
    }

    @Override
    public void printTicket() {
        System.out.println("Ticket Type: " + getTicketType() + " | " + getEventName() + " | Day: " + getDay() + " | Seat: " + getSeat() + " | Price: " + getPrice());
    }
}
