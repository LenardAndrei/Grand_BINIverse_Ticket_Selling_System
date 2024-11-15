public class TicketSeller {
    public static Ticket sellTicket(String ticketType, String eventName, String seat, double price, int day) {
        switch (ticketType.toUpperCase()) {
            case "GENERAL ADMISSION":
                return new GeneralTicket(ticketType, eventName, seat, price, day);
            case "UPPER BOX":
                return new UpperBox(ticketType, eventName, seat, price, day);
            case "LOWER BOX":
                return new LowerBox(ticketType, eventName, seat, price, day);
            case "PATRON B":
                return new PatronBTicket(ticketType, eventName, seat, price, day);
            case "PATRON A":
                return new PatronATicket(ticketType, eventName, seat, price, day);
            case "VIP":
                return new VIPTicket(ticketType, eventName, seat, price, day);
            default:
                return null; // Invalid ticket type
        }
    }
}
