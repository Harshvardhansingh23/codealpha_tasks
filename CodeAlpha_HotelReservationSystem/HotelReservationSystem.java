
import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;
    private double pricePerNight;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getCategory() { return category; }
    public double getPrice() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }

    public void book() { isAvailable = false; }
    public void cancel() { isAvailable = true; }

    @Override
    public String toString() {
        return String.format("Room %-4d | %-10s | ₹%6.0f | %s",
                roomNumber, category, pricePerNight, isAvailable ? "Available" : "Booked");
    }
}

class Reservation {
    private String guestName;
    private Room room;
    private String date;

    public Reservation(String guestName, Room room, String date) {
        this.guestName = guestName;
        this.room = room;
        this.date = date;
    }

    public Room getRoom() { return room; }

    public void display() {
        System.out.printf("Guest: %-12s | Room: %d (%s) | Date: %s | ₹%.0f%n",
                guestName, room.getRoomNumber(), room.getCategory(), date, room.getPrice());
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> bookings = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample rooms
        rooms.add(new Room(101, "Standard", 2500));
        rooms.add(new Room(102, "Standard", 2600));
        rooms.add(new Room(201, "Deluxe",   4500));
        rooms.add(new Room(202, "Deluxe",   4800));
        rooms.add(new Room(301, "Suite",    8500));

        int choice;
        do {
            System.out.println("\n=== Hotel Booking System ===");
            System.out.println("1. View available rooms");
            System.out.println("2. Book a room");
            System.out.println("3. Cancel booking");
            System.out.println("4. Show all bookings");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            choice = getValidInt();

            switch (choice) {
                case 1 -> showAvailableRooms();
                case 2 -> bookRoom();
                case 3 -> cancelBooking();
                case 4 -> showBookings();
                case 5 -> System.out.println("Goodbye!");
                default -> System.out.println("Wrong choice.");
            }
        } while (choice != 5);

        sc.close();
    }

    private static int getValidInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter number: ");
            sc.next();
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    private static void showAvailableRooms() {
        System.out.print("Category (Standard/Deluxe/Suite) or Enter for all: ");
        String cat = sc.nextLine().trim();

        System.out.println("\nAvailable:");
        boolean any = false;
        for (Room r : rooms) {
            if (r.isAvailable() && (cat.isEmpty() || r.getCategory().equalsIgnoreCase(cat))) {
                System.out.println(r);
                any = true;
            }
        }
        if (!any) System.out.println("No rooms available.");
    }

    private static void bookRoom() {
        System.out.print("Your name: ");
        String name = sc.nextLine().trim();

        System.out.print("Room number: ");
        int num = getValidInt();

        System.out.print("Date (yyyy-mm-dd): ");
        String date = sc.nextLine().trim();

        Room selected = null;
        for (Room r : rooms) {
            if (r.getRoomNumber() == num && r.isAvailable()) {
                selected = r;
                break;
            }
        }

        if (selected != null) {
            selected.book();
            bookings.add(new Reservation(name, selected, date));
            System.out.println("Booked! Amount: ₹" + selected.getPrice() + " (simulated)");
        } else {
            System.out.println("Room not available or wrong number.");
        }
    }

    private static void cancelBooking() {
        System.out.print("Room number to cancel: ");
        int num = getValidInt();

        Reservation found = null;
        for (Reservation b : bookings) {
            if (b.getRoom().getRoomNumber() == num) {
                found = b;
                break;
            }
        }

        if (found != null) {
            found.getRoom().cancel();
            bookings.remove(found);
            System.out.println("Cancelled room " + num);
        } else {
            System.out.println("No booking found.");
        }
    }

    private static void showBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings.");
            return;
        }
        System.out.println("\nBookings:");
        for (Reservation b : bookings) {
            b.display();
        }
    }
}