public class TicketSeller {
    public static Ticket sellTicket(String ticketType, String eventName, String seat, double price, int day) {
        switch (ticketType.toUpperCase()) {
            case "6":
                return new GeneralTicket("General Admission", eventName, seat, price, day);
            case "5":
                return new UpperBox("Upper Box", eventName, seat, price, day);
            case "4":
                return new LowerBox("Lower Box", eventName, seat, price, day);
            case "3":
                return new PatronBTicket("Patron B", eventName, seat, price, day);
            case "2":
                return new PatronATicket("Patron A", eventName, seat, price, day);
            case "1":
                return new VIPTicket("VIP", eventName, seat, price, day);
            default:
                return null; // Invalid ticket type
        }
    }
}
