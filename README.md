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

## Details of the chosen SDG and its integration into the project

The Grand BINIverse Ticket Selling System supports SDG 12: Responsible Consumption and Production by offering only e-tickets, which helps to minimize paper use and contributes to environmental sustainability. Additionally, 10% of each ticket payment goes to a charity selected by the customer, further supporting various Sustainable Development Goals:

1.	Gawad Kalinga - Aligns with SDG 1: No Poverty through poverty reduction and sustainable community building. It also supports SDG 8: Decent Work and Economic Growth by providing livelihood programs.
2.	Children’s Hour Philippines - Supports SDG 4: Quality Education by providing educational resources to underprivileged children. Its healthcare and protection programs also align with SDG 3: Good Health and Well-being and SDG 16: Peace, Justice, and Strong Institutions, which includes child protection.
3.	Tahanang Walang Hagdanan - Aligns with SDG 8: Decent Work and Economic Growth and SDG 10: Reduced Inequalities by providing skills training and livelihood opportunities for persons with disabilities, promoting inclusion and economic growth.

By integrating these SDG-aligned charities, the system not only supports responsible consumption but also empowers communities, promotes education, and reduces inequality.

## Instructions for running the program

When the program starts, it first displays the Ticket Selling Instructions, including the three concert dates, six ticket types with their prices, and important guidelines: only one email is allowed per transaction, with a maximum of four tickets. Additionally, a note about the SDGs supported by the system is shown. The user then presses Enter to proceed.

Next, the program prompts the user to enter their Customer Name and Email Address. The email must be in the correct format; otherwise, the system will repeatedly prompt the user to re-enter it until it is valid. After providing the name and email, the user chooses their Payment Method: either Credit Card or PayPal. If Credit Card is selected, the user enters an Account Number; if PayPal is chosen, they enter a PayPal email or contact number.

The user then selects the concert day they wish to attend (only one day per transaction), followed by a ticket type from the available options, along with its price. The user then selects a seat number. After selecting a ticket type and seat number, the user has the option to add more tickets or proceed to checkout to view the finalized order. This process repeats until the user chooses to finalize the order or reaches the four-ticket limit per transaction.

Upon finalizing the ticket order, a summary is displayed, and the user presses Enter to proceed. The system then shows three charity options, allowing the user to select one for a 10% donation from their ticket payment. After choosing a charity, the program exits.	

