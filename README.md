# Grand_BINIverse_Ticket_Selling_System

## Brief Introduction
The Grand BINIverse Ticket-Selling System is a Java-based application designed to streamline ticket sales for the Grand BINIverse, a 3-day concert event offering six ticket categories: VIP, Patron A, Patron B, Lower Box, Upper Box, and General Admission. Customers enter their name, email, and preferred payment method, select a concert day, and choose from available seats. Each transaction allows up to four tickets, with each email restricted to a single transaction for one concert day. Upon completion, an e-ticket is generated and sent via email, supporting SDG 12: Responsible Consumption and Production. Moreover, 10% of the ticket price is donated to a customer-selected charity, promoting social responsibility.

## How OOP Principles was applied

### Encapsulation
The Ticket class accomplishes encapsulation by initializing the fields ticketType, eventName, seat, price, and day as private. This stops external code from directly altering certain fields by limiting direct access to them from outside the class. When an instance of the Ticket class is created, the fields are initialized using the constructor. Because it guarantees that the fields are given proper values at the moment of object creation, this is an essential component of encapsulation. For every private field, the Ticket class offers public getter methods allowing read-only access. All subclasses are required to implement the abstract method printTicket(), which is defined by the Ticket class. This enables each distinct ticket type to print ticket details according to its own behavior while still following a consistent interface.

### Inheritance
Inheritance is used to create different types of tickets by extending a base class. Ticket class is the base class, it contains fields like ticketType, eventName, seat, price, and day, along with getter methods and an abstract method printticket(). The classes GeneralTicket, UpperBox, LowerBox, PatronBTicket, PatronATicket, and VIPTicket all extend the Ticket class. Means they inherit the fields and methods of Ticket class, and it implement the abstract printTicket() method.
 
Additionally, The TicketSeller class utilizes polymorphism through the sellTicket method to manage different types of tickets. It accepts a ticketType parameter, which determines which specific subclass of Ticket to instantiate. The method created and returns an object of the appropriate ticket subclass but as a generic Ticket type. This allows the caller to treat all ticket types uniformly while still leveraging the specific behaviors defined in each subclass, enabling flexible and organized handling of different ticket options.

### Polymorphism
Polymorphism is demonstrated through the use of the Ticket class and its subclasses (GeneralTicket, UpperBox, LowerBox, PatronBTicket, PatronATicket, VIPTicket). The Ticket class defines an abstract method printTicket(). This means that any subclass of Ticket overrides this method to provide its own implementation of how to print ticket details. Each concrete class (like GeneralTicket, UpperBox, etc.) implements the printTicket() method in its own way. 

Additionally, the TicketSeller class leverages polymorphism in its sellTicket() method. By returning different Ticket subclasses based on the ticket type, TicketSeller can create and return any specific ticket type as a general Ticket object. This approach allows the program to handle various ticket types uniformly while preserving their unique printing behaviour when printTicket() is called.

### Abstraction
Abstraction is mainly demonstrated by the Ticket class, which serves as an abstract base for all types of tickets. The Ticket class is marked as abstract, meaning it cannot be instantiated on its own. It’s a general blueprint that outlines the properties and behaviors that all ticket types should have. The ticket class contains an abstract method printTicket(). This method doesn’t have an implementation within Ticket itself, enforcing that any subclass must provide its own specific version of printTicket(). 

