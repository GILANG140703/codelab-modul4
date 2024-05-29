package Modul5.Tugas1;

import java.util.Scanner;

// Kelas Node untuk merepresentasikan node dalam Binary Tree
import java.util.Scanner;

// Kelas Node untuk merepresentasikan node dalam Binary Tree
class Node {
    String value;
    Node left, right;

    public Node(String value) {
        this.value = value;
        left = right = null;
    }
}

// Kelas BinaryTree untuk membuat dan melakukan traversal pada Binary Tree
class BinaryTree {
    Node root;

    // Konstruktor untuk BinaryTree
    public BinaryTree() {
        root = null;
    }

    // Method untuk menambahkan node ke dalam Binary Tree
    public void insert(String value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, String value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value.compareTo(root.value) < 0) {
            root.left = insertRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    // Method untuk menambahkan node ke dalam Binary Tree dengan posisi yang ditentukan
    public void insertWithPosition(String value, String position) {
        root = insertRecWithPosition(root, value, position);
    }

    private Node insertRecWithPosition(Node root, String value, String position) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (position.startsWith("L")) {
            root.left = insertRecWithPosition(root.left, value, position.substring(1));
        } else if (position.startsWith("R")) {
            root.right = insertRecWithPosition(root.right, value, position.substring(1));
        }
        return root;
    }

    // Method untuk menampilkan PreOrder traversal
    public void printPreOrder() {
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // Method untuk menampilkan InOrder traversal
    public void printInOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.value + " ");
            inOrderRec(root.right);
        }
    }

    // Method untuk menampilkan PostOrder traversal
    public void printPostOrder() {
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.value + " ");
        }
    }
}

// Kelas utama untuk menjalankan program
public class tugas1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        int choice = 0;

        // Memasukkan nilai ke dalam Binary Tree (tidak perli do while)
        do {
            System.out.println("Pilih tipe input (1: Angka/Huruf/Kombinasi, 2 Exit): ");
            try {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Masukkan nilai pertama: ");
                        String rootValue = sc.nextLine();
                        tree.insert(rootValue);

                        System.out.println("Masukkan jumlah nilai lain yang ingin dimasukkan (tanpa nilai pertama): ");
                        int count = sc.nextInt();
                        sc.nextLine(); // membersihkan newline

                        for (int i = 0; i < count; i++) {
                            System.out.println("Masukkan nilai ke-" + (i + 2) + ": ");
                            String value = sc.nextLine();
                            System.out.println("Masukkan posisi (L untuk kiri, R untuk kanan, LL untuk kiri dari kiri, RL untuk kanan dari kiri, dan seterusnya): ");
                            String position = sc.nextLine();
                            tree.insertWithPosition(value, position);
                        }

                        System.out.println("PreOrder Traversal: ");
                        tree.printPreOrder();

                        System.out.println("InOrder Traversal: ");
                        tree.printInOrder();

                        System.out.println("PostOrder Traversal: ");
                        tree.printPostOrder();

                        break;
                    case 2:
                        System.out.println("Terim kasih");
                        break;
                    default:
                        System.out.println("Input ulang");
                }

            } catch (Exception e) {
                System.out.println("Input tidak valid. Harap masukkan pilihan yang benar.");
                sc.nextLine();
            }
        } while (choice != 2);

        sc.close();
    }
}