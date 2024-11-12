# Grand_BINIverse_Ticket_Selling_System

Brief Introduction

  The Grand BINIverse Ticket-Selling System is a Java-based application designed to streamline ticket sales for the Grand BINIverse, a 3-day concert event offering six ticket categories: VIP, Patron A, Patron B, Lower Box, Upper Box, and General Admission. Customers enter their name, email, and preferred payment method, select a concert day, and choose from available seats. Each transaction allows up to four tickets, with each email restricted to a single transaction for one concert day. Upon completion, an e-ticket is generated and sent via email, supporting SDG 12: Responsible Consumption and Production. Moreover, 10% of the ticket price is donated to a customer-selected charity, promoting social responsibility.

How OOP Principles was applied

Encapsulation

The Ticket class accomplishes encapsulation by initializing the fields ticketType, eventName, seat, price, and day as private. This stops external code from directly altering certain fields by limiting direct access to them from outside the class. When an instance of the Ticket class is created, the fields are initialized using the constructor. Because it guarantees that the fields are given proper values at the moment of object creation, this is an essential component of encapsulation. For every private field, the Ticket class offers public getter methods allowing read-only access. All subclasses are required to implement the abstract method printTicket(), which is defined by the Ticket class. This enables each distinct ticket type to print ticket details according to its own behavior while still following a consistent interface.
