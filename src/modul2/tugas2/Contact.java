package modul2.tugas2;

import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private Contact next;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.next = null;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Contact getNext() {
        return next;
    }

    public void setNext(Contact next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return name + " " + phoneNumber;
    }
}

class ContactManager {
    private Contact head;

    public ContactManager() {
        head = null;
    }

    public void addContact(Contact contact) {
        boolean isDuplicate = checkDuplicate(contact.getName(), contact.getPhoneNumber());
        if (isDuplicate) {
            System.out.println("Maaf, kontak telah ada. Masukkan nama dan nomor telepon yang berbeda.");
        } else {
            if (head == null) {
                head = contact;
            } else {
                Contact current = head;
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(contact);
            }
            System.out.println("Kontak berhasil ditambahkan!");
        }
    }

    private boolean checkDuplicate(String name, String phoneNumber) {
        Contact current = head;
        while (current != null) {
            if (current.getName().equalsIgnoreCase(name) && current.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }


    public void displayContacts() {
        if (head == null) {
            System.out.println("Daftar Kontak Kosong");
        } else {
            System.out.println("Daftar Kontak:");
            Contact current = head;
            int i = 1;
            while (current != null) {
                System.out.println(i + ". " + current);
                current = current.getNext();
                i++;
            }
        }
    }

    public void searchContact(String name) {
        Contact current = head;
        boolean found = false;
        while (current != null) {
            if (current.getName().equalsIgnoreCase(name)) {
                System.out.println("Kontak ditemukan!");
                System.out.println("Nama: " + current.getName());
                System.out.println("Nomor Telepon: " + current.getPhoneNumber());
                found = true;
                break;
            }
            current = current.getNext();
        }
        if (!found) {
            System.out.println("Kontak tidak ditemukan!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager();

        System.out.println("Selamat datang di Manajemen Kontak!");

        int choice;
        do {
            System.out.println("\n1. Tambah Kontak");
            System.out.println("2. Tampilkan Kontak");
            System.out.println("3. Cari Kontak");
            System.out.println("4. Keluar");

            System.out.print("\nPilih menu (1-4): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama kontak: ");
                    String name = scanner.nextLine();
                    String phoneNumber;
                    do {
                        System.out.print("Masukkan nomor telepon (hanya angka): ");
                        phoneNumber = scanner.nextLine();
                        if (!phoneNumber.matches("\\d+")) {
                            System.out.println("Nomor telepon hanya boleh berisi angka. Silakan coba lagi.");
                        }
                    } while (!phoneNumber.matches("\\d+"));
                    Contact contact = new Contact(name, phoneNumber);
                    contactManager.addContact(contact);
                    break;
                case 2:
                    contactManager.displayContacts();
                    break;
                case 3:
                    System.out.print("Masukkan nama kontak yang ingin dicari: ");
                    String searchName = scanner.nextLine();
                    contactManager.searchContact(searchName);
                    break;
                case 4:
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
