import java.util.Scanner;
import java.time.Year;

// 🔹 Kelas model untuk menyimpan data orang (Refactor 1)
class Orang {
    String nama;
    int tahunLahir;
    int umur;
}

public class CekUmurOtomatisRefactor {

    // 🔹 Main method (Refactor 2: Pemecahan logika utama menjadi beberapa metode)
    public static void main(String[] args) {
        Orang orang = inputData();         // mengambil input dari pengguna
        hitungUmur(orang);                 // menghitung umur otomatis
        tampilkanHasil(orang);             // menampilkan hasil
    }

    // 🔹 Metode inputData (Refactor 3: Menambahkan validasi input)
    public static Orang inputData() {
        Scanner input = new Scanner(System.in);
        Orang orang = new Orang();

        System.out.println("=== PROGRAM CEK UMUR OTOMATIS ===");
        System.out.print("Masukkan Nama Anda   : ");
        orang.nama = input.nextLine();

        System.out.print("Masukkan Tahun Lahir : ");

        // ✅ Validasi input agar tidak error jika bukan angka
        while (!input.hasNextInt()) {
            System.out.println("Input harus berupa angka! Coba lagi:");
            System.out.print("Masukkan Tahun Lahir : ");
            input.next(); // hapus input yang salah
        }

        orang.tahunLahir = input.nextInt();

        // ✅ Validasi input tahun lahir
        int tahunSekarang = Year.now().getValue();
        if (orang.tahunLahir > tahunSekarang) {
            System.out.println("❌ Tahun lahir tidak valid!");
            System.exit(0); // keluar dari program
        }

        return orang;
    }

    // 🔹 Metode hitungUmur (Refactor 4: Pemindahan logika perhitungan umur)
    public static void hitungUmur(Orang orang) {
        int tahunSekarang = Year.now().getValue();
        orang.umur = tahunSekarang - orang.tahunLahir;
    }

    // 🔹 Metode kategoriUmur (Refactor 5: Memisahkan logika kategori umur)
    public static String kategoriUmur(int umur) {
        if (umur < 13) {
            return "Anak-anak";
        } else if (umur < 20) {
            return "Remaja";
        } else if (umur < 60) {
            return "Dewasa";
        } else {
            return "Lansia";
        }
    }

    // 🔹 Metode tampilkanHasil (Refactor 6: Penataan tampilan output)
    public static void tampilkanHasil(Orang orang) {
        System.out.println("\n===============================");
        System.out.printf("Nama         : %s%n", orang.nama);
        System.out.printf("Tahun Lahir  : %d%n", orang.tahunLahir);
        System.out.printf("Umur         : %d tahun%n", orang.umur);
        System.out.printf("Kategori     : %s%n", kategoriUmur(orang.umur));
        System.out.println("===============================");
        System.out.println("LABORATORIUM INFORMATIKA UMM");
    }
}
