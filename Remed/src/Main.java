import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Masukkan usia Anda: ");
            int usia = input.nextInt();

            // Validasi usia
            if (usia <= 0 || usia >= 120) {
                throw new InvalidAgeException(
                        "Usia tidak valid! Usia harus lebih dari 0 dan kurang dari 120."
                );
            }

            System.out.println("Usia Anda adalah: " + usia + " tahun");
        }
        catch (InvalidAgeException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Input harus berupa angka!");
        }
        finally {
            input.close();
        }
    }
}
