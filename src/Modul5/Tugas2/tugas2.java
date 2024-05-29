package Modul5.Tugas2;
import java.util.InputMismatchException;
import java.util.Scanner;

// Node class untuk merepresentasikan sebuah buku dalam BST
class BookNode {
    int ISBN;
    String judul;
    BookNode left, right;

    public BookNode(int ISBN, String judul) {
        this.ISBN = ISBN;
        this.judul = judul;
        left = right = null;
    }
}

// Class untuk merepresentasikan Binary Search Tree (BST) dari buku-buku
class BookInventory {
    BookNode root;

    public BookInventory() {
        root = null;
    }

    // Method untuk menambahkan buku ke dalam BST
    public void addBook(int ISBN, String judul) {
        if (findBook(ISBN) != null) {
            System.out.println("ISBN " + ISBN + " sudah ada di dalam sistem. Buku tidak bisa ditambahkan.");
            return;
        }

        root = addBookRecursive(root, ISBN, judul);
    }

    private BookNode addBookRecursive(BookNode root, int ISBN, String judul) {
        // Jika BST kosong, buat node baru
        if (root == null) {
            root = new BookNode(ISBN, judul);
            return root;
        }

        // Jika ISBN lebih kecil, masukkan ke kiri
        if (ISBN < root.ISBN) {
            root.left = addBookRecursive(root.left, ISBN, judul);
            // Jika ISBN lebih besar atau sama, masukkan ke kanan
        }else {
            root.right = addBookRecursive(root.right, ISBN, judul);
        }
        return root;
    }

    // Method untuk mencari buku berdasarkan ISBN
    public BookNode findBook(int ISBN) {
        return findBookRecursive(root, ISBN);
    }

    private BookNode findBookRecursive(BookNode root, int ISBN) {

        if (root == null || root.ISBN == ISBN)
            return root;

        // Cari ke kiri jika ISBN yang dicari lebih kecil
        if (root.ISBN > ISBN) {
            return findBookRecursive(root.left, ISBN);
        }
        // Cari ke kanan jika ISBN yang dicari lebih besar
        return findBookRecursive(root.right, ISBN);
    }

    // Tree Traversal: PreOrder
    public void preOrderTraversal(BookNode root) {
        if (root != null) {
            System.out.println(root.ISBN + " " + root.judul);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }


    // Tree Traversal: InOrder
    public void inOrderTraversal(BookNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.ISBN + " " + root.judul);
            inOrderTraversal(root.right);
        }
    }

    // Tree Traversal: PostOrder
    public void postOrderTraversal(BookNode root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.ISBN + " " + root.judul);
        }
    }
}

// Main class untuk menjalankan program
public class tugas2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Membuat objek untuk mengelola inventaris buku
        BookInventory inventory = new BookInventory();

        // Menampilkan judul aplikasi
        System.out.println("System Inventaris Buku");

        // Menampilkan menu
        int choice = 0;

        inventory.addBook(123,"Java Programming");
        inventory.addBook(21,"Python Programming");
        inventory.addBook(456,"Data Structures and Algorithm");
        inventory.addBook(143,"Statistics");
        inventory.addBook(789,"Computer Networks");

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Buku ke Inventaris");
            System.out.println("2. Cari Buku berdasarkan ISBN");
            System.out.println("3. Tampilkan Inventaris Buku (PreOrder)");
            System.out.println("4. Tampilkan Inventaris Buku (InOrder)");
            System.out.println("5. Tampilkan Inventaris Buku (PostOrder)");
            System.out.println("6. Keluar");
            System.out.print("Pilihan Anda: ");

            try {

                choice = sc.nextInt();
                sc.nextLine(); // Membuang newline dari buffer

                switch (choice) {
                    case 1:
                        // Meminta pengguna untuk memasukkan data buku
                        System.out.println("\nMasukkan data buku ke dalam inventaris:");

                        // Meminta pengguna untuk memasukkan ISBN buku
                        System.out.print("ISBN: ");
                        int isbn = sc.nextInt();
                        sc.nextLine(); // Membuang newline dari buffer

                        // Meminta pengguna untuk memasukkan judul buku
                        System.out.print("Judul: ");
                        String title = sc.nextLine();

                        // Menambahkan buku ke dalam inventaris
                        inventory.addBook(isbn, title);
                        break;

                    case 2:
                        // Meminta pengguna untuk mencari buku berdasarkan ISBN
                        System.out.print("\nMasukkan ISBN yang ingin dicari: ");
                        int isbnToFind = sc.nextInt();
                        sc.nextLine(); // Membuang newline dari buffer

                        // Mencari buku berdasarkan ISBN
                        BookNode foundBook = inventory.findBook(isbnToFind);
                        if (foundBook != null) {
                            System.out.println("\nBuku ditemukan : ISBN = " + foundBook.ISBN + ", Judul = " + foundBook.judul);
                        }
                        else
                            System.out.println("\nBuku dengan ISBN " + isbnToFind + " tidak ditemukan.");
                        break;
                    case 3:
                        // Menampilkan inventaris secara PreOrder
                        System.out.println("\nInventaris Buku (PreOrder) :");
                        inventory.preOrderTraversal(inventory.root);
                        break;
                    case 4:
                        // Menampilkan inventaris secara terurut berdasarkan ISBN (InOrder)
                        System.out.println("\nInventaris Buku (terurut berdasarkan ISBN – InOrder) :");
                        inventory.inOrderTraversal(inventory.root);
                        break;
                    case 5:
                        // Menampilkan inventaris secara PostOrder
                        System.out.println("\nInventaris Buku (PostOrder) :");
                        inventory.postOrderTraversal(inventory.root);
                        break;
                    case 6:
                        System.out.println("Terima kasih!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("Input harus angka");
                sc.nextLine();
            }
        } while (choice != 6);
        sc.close();
    }
}