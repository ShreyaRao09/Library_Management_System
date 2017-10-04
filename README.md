# Library_Management_System
Library Management System implemented using Java Swing and MySQL

Involves the creation of a database host application that interfaces with a backend SQL database implementing a Library Management System. Users of the system are understood to be librarians (not book borrowers).

Functionality:

BOOK SEARCH AND AVAILABILITY
One can search for a book, given any combination of ISBN, title, and/or Author(s). It then display the following in the search results:
•   ISBN
•   Book title
•   Book author(s) (displayed as a comma separated list)
•   Book availability (if the book currently checked out or not)

BOOK LOANS
Checking Out Books
•	Can check out a book, given the combination of BOOK and BORROWER (Card number)
•	The due date should be 14 days after the check out.
•	Each BORROWER is permitted a maximum of 3 BOOK LOANS. If a BORROWER already has 3 BOOK_LOANS, then the checkout (i.e. create new BOOK_LOANS tuple) fails and return an error message.
•	If a book is already checked out, then the checkout fails and returns an error message.

Checking In Books
•	Can check in a book using Book id, Card number, and/or any part of BORROWER name. A list of potential results and a button is displayed. One can select the appropriate entry to check in

BORROWER MANAGEMENT
•	Can create new borrowers in the system using name, SSN, and address attributes.
•	It automatically generates new card number for each new borrower and uses a compatible format with the existing borrower IDs.
•	Borrowers are allowed to possess exactly one library card. If a new borrower is attempted with same SSN, then the system rejects and return an error message.

FINES
•	Fines are assessed at a rate of $0.25/day (twenty-five cents per day).
•	Fines table is updated every time we open the Fines window.
•	There are two scenarios for late books
    1.	Late books that have been returned — the fine will be [(the difference in days between the due date and date in) * $0.25].
    2.	Late book that are still out — the estimated fine will be [(the difference between the Due date and TODAY) * $0.25].
•	If the fine has been paid, the fine amount is not updated. Otherwise, it is.
•	It provides a mechanism for librarians to enter payment of fines 
•	It does not allow payment of a fine for books that are not yet returned.
•	It displays fines grouped by card number i.e., it sums the fine amount for each Borrower.

