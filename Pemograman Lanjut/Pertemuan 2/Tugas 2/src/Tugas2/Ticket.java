package Tugas2;

public abstract class Ticket {
    public String passengerName;
    public String startLocation;
    public String destination;
    public double price;

    public Ticket(String passengerName, String startLocation, String destination, double price) {
        this.passengerName = passengerName;
        this.startLocation = startLocation;
        this.destination = destination;
        this.price = price;
    }

    // Method to display basic info passenger and trip
    public abstract void displayInfo();
}
