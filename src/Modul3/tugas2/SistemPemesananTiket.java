package Modul3.tugas2;

import java.util.Scanner;

class PesananTiket {
    String nama;
    int jumlah;

    public PesananTiket(String nama, int jumlah) {
        this.nama = nama;
        this.jumlah = jumlah;
    }
}

class AntrianTiket {
    private PesananTiket[] antrian;
    private int depan;
    private int belakang;
    private int kapasitas;
    private int ukuran;
    private int nomorPesanan;

    public AntrianTiket(int kapasitas) {
        this.kapasitas = kapasitas;
        antrian = new PesananTiket[kapasitas];
        depan = 0;
        belakang = -1;
        ukuran = 0;
        nomorPesanan = 1;
    }

    public void tambahPesanan(PesananTiket pesanan) {
        if (ukuran == kapasitas) {
            System.out.println("Antrian penuh, tidak dapat menambahkan pemesanan baru.");
            return;
        }
        belakang = (belakang + 1) % kapasitas;
        antrian[belakang] = pesanan;
        ukuran++;
        System.out.println("Pesanan berhasil ditambahkan. Nomor pesanan: " + nomorPesanan++);
    }

    public void hapusPesanan(int nomorPesanan) {
        if (ukuran == 0) {
            System.out.println("Antrian kosong, tidak ada pemesanan untuk dihapus.");
            return;
        }
        int indexHapus = (nomorPesanan - (nomorPesanan - ukuran) - 1 + kapasitas) % kapasitas;
        System.out.println("Pesanan berhasil dihapus. Nomor pesanan: " + nomorPesanan + ", Nama: " + antrian[indexHapus].nama + ", Jumlah Tiket: " + antrian[indexHapus].jumlah);
        for (int i = indexHapus; i < ukuran - 1; i++) {
            antrian[i] = antrian[i + 1];
        }
        belakang = (belakang - 1 + kapasitas) % kapasitas;
        ukuran--;
    }

    public void tampilkanAntrian() {
        if (ukuran == 0) {
            System.out.println("Antrian kosong, tidak ada pemesanan.");
            return;
        }
        System.out.println("Daftar Pesanan:");
        for (int i = 0; i < ukuran; i++) {
            int index = (depan + i) % kapasitas;
            System.out.println("Nomor Pesanan: " + (nomorPesanan - ukuran + i) + ", Nama: " + antrian[index].nama + ", Jumlah Tiket: " + antrian[index].jumlah);
        }
    }
}


public class SistemPemesananTiket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Membuat antrian dengan kapasitas 10
        AntrianTiket antrianTiket = new AntrianTiket(3);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Pesanan");
            System.out.println("2. Hapus Pesanan");
            System.out.println("3. Tampilkan Pesanan");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline di buffer

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama pemesan: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan jumlah tiket yang ingin dipesan: ");
                    int jumlah = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan newline di buffer
                    PesananTiket pesananBaru = new PesananTiket(nama, jumlah);
                    antrianTiket.tambahPesanan(pesananBaru);
                    break;
                case 2:
                    System.out.print("Masukkan nomor pesanan yang ingin dihapus: ");
                    int nomorHapus = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan newline di buffer
                    antrianTiket.hapusPesanan(nomorHapus);
                    break;
                case 3:
                    antrianTiket.tampilkanAntrian();
                    break;
                case 4:
                    System.out.println("Terima kasih, sampai jumpa!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }
}
