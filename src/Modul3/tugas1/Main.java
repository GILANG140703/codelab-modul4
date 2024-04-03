package Modul3.tugas1;

import java.util.Scanner;

class Stack<T> {
    private int maxSize;
    private int top;
    private T[] array;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.array = (T[]) new Object[maxSize];
        this.top = -1;
    }

    public void push(T item) {
        if (isFull()) {
            System.out.println("Stack is full.");
            return;
        }
        array[++top] = item;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return array[top--];
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return array[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }
}

class Browser {
    private String currentURL;
    private Stack<String> history;
    private Stack<String> forwardHistory;

    public Browser() {
        history = new Stack<>(10);
        forwardHistory = new Stack<>(10);
        currentURL = "about:blank";
    }

    public void visitURL(String url) {
        history.push(currentURL);
        currentURL = url;
        System.out.println("Saat ini mengunjungi: " + currentURL);
    }

    public void back() {
        if (!history.isEmpty()) {
            forwardHistory.push(currentURL);
            currentURL = history.pop();
            System.out.println("Kembali ke: " + currentURL);
        } else {
            System.out.println("Tidak ada riwayat untuk kembali.");
        }
    }

    public void forward() {
        if (!forwardHistory.isEmpty()) {
            history.push(currentURL);
            currentURL = forwardHistory.pop();
            System.out.println("Maju ke: " + currentURL);
        } else {
            System.out.println("Tidak ada riwayat untuk maju.");
        }
    }

    public String getCurrentURL() {
        return currentURL;
    }
}

public class Main {
    public static void main(String[] args) {
        Browser browser = new Browser();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Kunjungi URL");
            System.out.println("2. Kembali");
            System.out.println("3. Maju");
            System.out.println("4. URL Saat Ini");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan URL baru: ");
                    scanner.nextLine(); // Clear the buffer
                    String url = scanner.nextLine();
                    browser.visitURL(url);
                    break;
                case 2:
                    browser.back();
                    break;
                case 3:
                    browser.forward();
                    break;
                case 4:
                    System.out.println("URL saat ini: " + browser.getCurrentURL());
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan browser.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (choice != 0);
        scanner.close();
    }
}


