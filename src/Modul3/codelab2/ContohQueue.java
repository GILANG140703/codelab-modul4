package Modul3.codelab2;

import java.util.LinkedList;
import java.util.Queue;

public class ContohQueue {
    public static void main(String[] args) {

        Queue<String> q = new LinkedList<>();

        q.add("Bebek");
        q.add("Angsa");
        q.add("Kuda");
        q.add("Buaya");
        q.add("Tikus");

        System.out.println("Elemen pertama (peek): " + q.peek());

        System.out.println("Animals: " + q);

        q.poll();
        q.poll();

        System.out.println("Animals setelah penghapusan: " + q);
    }
}
