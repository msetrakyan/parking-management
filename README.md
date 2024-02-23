Main functionality of the project is to connect users to their communities, provide access to available parking spots, and give them the ability to park, release, or book a parking.

Users can access only the parkings that are in their community.

For booking, the user must provide the booking spot he wants, and then timeframes of the booking. If there are no overlapping bookings in the DB, the booking is stored.

Parking can also be without booking, if there is available parking, the user can park immediately.

The maintaining of correct bookings in the DB is done by 2 scheduling methods, one checks for bookings that are expired (bookings that have ended) and clears them from the table, whilst also making the parking available again, and the other checks for bookings that are about to start (bookings that are inProcess = false, and have started), and changes the rows accordingly.

Project is secured using JWT, there are 2 roles, user and admin.

Unit tests are written only for one repository, due to Time management issues.
