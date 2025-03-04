import java.util.*;
class Room {
    private int roomNumber;
    private String category;
    private boolean isBooked;

    public Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }
    public int getRoomNumber() { return roomNumber; }
    public String getCategory() { return category; }
    public boolean isBooked() { return isBooked; }
    public void bookRoom() { this.isBooked = true; }
    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - " + (isBooked ? "Booked" : "Available");
    }
}

class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private Map<Integer, String> bookings = new HashMap<>();

    public Hotel() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Deluxe"));
    }

    public void displayAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        boolean found = false;
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println(room);
                found = true;
            }
        }
        if (!found) System.out.println("No rooms available.");
    }

    public void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.next();
        displayAvailableRooms();
        
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();

        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && !room.isBooked()) {
                room.bookRoom();
                bookings.put(roomNumber, name);
                System.out.println("Room " + roomNumber + " successfully booked for " + name);
                return;
            }
        }
        System.out.println("Invalid room number or already booked.");
    }

    public void viewBookings() {
        System.out.println("\n--- Booking Details ---");
        if (bookings.isEmpty()) {
            System.out.println("No reservations made.");
        } else {
            for (Map.Entry<Integer, String> entry : bookings.entrySet()) {
                System.out.println("Room " + entry.getKey() + " booked by " + entry.getValue());
            }
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\nOptions: 1. View Rooms 2. Book Room 3. View Bookings 4. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    hotel.makeReservation(scanner);
                    break;
                case 3:
                    hotel.viewBookings();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
