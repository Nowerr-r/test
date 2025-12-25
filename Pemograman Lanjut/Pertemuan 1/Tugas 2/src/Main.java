import java.util.Scanner;

// Custom Exception
class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukkan angka positif: ");

            // Cek apakah input berupa angka
            if (!scanner.hasNextDouble()) {
                throw new Exception("Error: Input tidak valid!");
            }

            double angka = scanner.nextDouble();

            if (angka <= 0) {
                throw new InvalidNumberException("Error: Angka harus positif!");
            }

            System.out.println("Angka " + angka + " valid!");

        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
