// CoffeeOrderInput.java (Introduce Parameter Object + Encapsulate Field)
import java.util.Scanner;

public class CoffeeOrderInput {
    public final String customerName;
    public final String drinkName;
    public final int quantity;
    public final double unitPrice;

    private CoffeeOrderInput(String customerName, String drinkName, int quantity, double unitPrice) {
        this.customerName = customerName;
        this.drinkName = drinkName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public static CoffeeOrderInput readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Drink: ");
        String drink = scanner.nextLine();
        System.out.print("Qty: ");
        int qty = Integer.parseInt(scanner.nextLine());
        double price = 15000; // could be fetched from menu
        return new CoffeeOrderInput(name, drink, qty, price);
    }
}
