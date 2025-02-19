class TicketBookingSystem {
    private final boolean[] seats;
    private final int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats]; 
    }

    
    public synchronized void bookSeat(String userName, boolean isVIP, int seatNumber) {
        
        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println(userName + ": Invalid seat number!");
            return;
        }

        
        seatNumber--;

        
        if (seats[seatNumber]) {
            System.out.println(userName + ": Seat " + (seatNumber + 1) + " is already booked!");
        } else {
         
            seats[seatNumber] = true;
            System.out.println(userName + (isVIP ? " (VIP)" : "") + " booked seat " + (seatNumber + 1));
        }
    }

    
    public void showBookingStatus() {
        System.out.println("Current seat booking status:");
        for (int i = 0; i < totalSeats; i++) {
            System.out.println("Seat " + (i + 1) + ": " + (seats[i] ? "Booked" : "Available"));
        }
    }
}

class UserThread extends Thread {
    private final TicketBookingSystem system;
    private final String userName;
    private final boolean isVIP;
    private final int seatNumber;

    public UserThread(TicketBookingSystem system, String userName, boolean isVIP, int seatNumber) {
        this.system = system;
        this.userName = userName;
        this.isVIP = isVIP;
        this.seatNumber = seatNumber;
    }

    @Override
    public void run() {
        system.bookSeat(userName, isVIP, seatNumber);
    }
}

public class TicketBookingSimulation {

    public static void main(String[] args) {
        int totalSeats = 5;
        TicketBookingSystem system = new TicketBookingSystem(totalSeats);

        // Test Case 1: No Seats Available Initially
        System.out.println("Test Case 1: No Seats Available Initially");
        system.showBookingStatus(); // Expected: No bookings yet.

        // Test Case 2: Successful Booking
        System.out.println("\nTest Case 2: Successful Booking");
        Thread t1 = new UserThread(system, "Anish", true, 1); // VIP booking
        Thread t2 = new UserThread(system, "Bobby", false, 2); // Regular booking
        Thread t3 = new UserThread(system, "Charlie", true, 3); // VIP booking
        t1.start();
        t2.start();
        t3.start();
        
        // Wait for threads to finish booking
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.showBookingStatus(); // Expected: Seats 1, 2, and 3 booked.

        // Test Case 3: Thread Priorities (VIP First)
        System.out.println("\nTest Case 3: Thread Priorities (VIP First)");
        Thread t4 = new UserThread(system, "Bobby", false, 4); // Regular booking (low priority)
        Thread t5 = new UserThread(system, "Anish", true, 4); // VIP booking (high priority)
        t4.setPriority(Thread.MIN_PRIORITY);
        t5.setPriority(Thread.MAX_PRIORITY);
        t4.start();
        t5.start();
        
        try {
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.showBookingStatus(); // Expected: VIP (Anish) gets Seat 4.

        // Test Case 4: Preventing Double Booking
        System.out.println("\nTest Case 4: Preventing Double Booking");
        Thread t6 = new UserThread(system, "Anish", true, 1); // VIP booking already booked seat
        Thread t7 = new UserThread(system, "Bobby", false, 1); // Regular user trying to book already booked seat
        t6.start();
        t7.start();
        
        try {
            t6.join();
            t7.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.showBookingStatus(); // Expected: Seat 1 already booked by Anish (VIP).

        // Test Case 5: Booking After All Seats Are Taken
        System.out.println("\nTest Case 5: Booking After All Seats Are Taken");
        Thread t8 = new UserThread(system, "Eve", false, 3); // Regular user tries to book an already booked seat
        Thread t9 = new UserThread(system, "Frank", false, 3); // Another regular user tries to book Seat 3
        t8.start();
        t9.start();

        try {
            t8.join();
            t9.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.showBookingStatus(); // Expected: Seat 3 already booked.

        // Test Case 6: Invalid Seat Selection
        System.out.println("\nTest Case 6: Invalid Seat Selection");
        Thread t10 = new UserThread(system, "Greg", false, 0); // Invalid seat number
        Thread t11 = new UserThread(system, "Hannah", false, 6); // Invalid seat number
        t10.start();
        t11.start();

        try {
            t10.join();
            t11.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.showBookingStatus(); // Expected: Invalid seat numbers.

        // Test Case 7: Simultaneous Bookings (Concurrency Test)
        System.out.println("\nTest Case 7: Simultaneous Bookings (Concurrency Test)");
        Thread t12 = new UserThread(system, "User1", false, 1);
        Thread t13 = new UserThread(system, "User2", false, 2);
        Thread t14 = new UserThread(system, "User3", false, 3);
        Thread t15 = new UserThread(system, "User4", false, 4);
        Thread t16 = new UserThread(system, "User5", false, 5);
        Thread t17 = new UserThread(system, "User6", false, 1); // Duplicate seat 1
        Thread t18 = new UserThread(system, "User7", false, 2); // Duplicate seat 2
        Thread t19 = new UserThread(system, "User8", false, 3); // Duplicate seat 3
        Thread t20 = new UserThread(system, "User9", false, 4); // Duplicate seat 4
        Thread t21 = new UserThread(system, "User10", false, 5); // Duplicate seat 5

        // Start all threads
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t16.start();
        t17.start();
        t18.start();
        t19.start();
        t20.start();
        t21.start();

        // Wait for all threads to finish
        try {
            t12.join();
            t13.join();
            t14.join();
            t15.join();
            t16.join();
            t17.join();
            t18.join();
            t19.join();
            t20.join();
            t21.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.showBookingStatus(); // Expected: 5 users book successfully, 5 receive error messages.
    }
}
