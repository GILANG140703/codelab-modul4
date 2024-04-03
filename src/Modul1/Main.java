package Modul1;

import java.util.Scanner;

enum JenisBarang {
    SANDANG,
    PANGAN,
    PAPAN
}

class Barang<T, U> {
    private T nama;
    private U harga;
    private JenisBarang jenis;

    public Barang(T nama, U harga, JenisBarang jenis) {
        this.nama = nama;
        this.harga = harga;
        this.jenis = jenis;
    }

    public T getNama() {
        return nama;
    }

    public U getHarga() {
        return harga;
    }

    public JenisBarang getJenis() {
        return jenis;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukan nama: ");
        String nama = scanner.nextLine();

        System.out.print("Masukkan harga: ");
        int harga = scanner.nextInt();

        System.out.println("Pilih jenis barang:");
        System.out.println("0. SANDANG");
        System.out.println("1. PANGAN");
        System.out.println("2. PAPAN");
        System.out.print("Masukkan pilihan: ");
        int pilihan = scanner.nextInt();

        JenisBarang jenis = null;
        switch (pilihan) {
            case 0:
                jenis = JenisBarang.SANDANG;
                break;
            case 1:
                jenis = JenisBarang.PANGAN;
                break;
            case 2:
                jenis = JenisBarang.PAPAN;
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                System.exit(0);
        }

        Barang<String, Integer> barang = new Barang<>(nama, harga, jenis);

        System.out.println("\nInformasi barang:");
        System.out.println("Nama: " + barang.getNama());
        System.out.println("Harga: " + barang.getHarga());
        System.out.println("Jenis: " + barang.getJenis());
    }
}
