package Modul4.tugas2;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


class DataPemilih{
    private HashMap<String, String> users;
    private HashMap<String, ArrayList> userDetails;
    private HashMap<String, String> userVotes;
    private ArrayList<String> userInfo;
    private String currentUser;

    public DataPemilih() {
        this.users = new HashMap<>();
        this.userDetails = new HashMap<>();
        this.userVotes = new HashMap<>();
        this.currentUser = null;
    }

    void register(String email, String password, String nama, String NIK){
        boolean duplicateNIK = false;

        for (ArrayList<String> userInfo : userDetails.values()) {
            if (userInfo.get(1).equals(NIK)) {
                duplicateNIK = true;
                break;
            }
        }

        if (!email.endsWith("@gmail.com") || users.containsKey(email) || duplicateNIK || NIK.length() != 16) {
            System.out.println("Gagal mendaftar");
            return;
        }

        users.put(email, password);
        userInfo = new ArrayList<>();
        userInfo.add(nama);
        userInfo.add(NIK);
        userDetails.put(email, userInfo);

        System.out.println("Berhasil Mendaftar");
    }

    void login(String email, String password) {
        if (!users.containsKey(email) || !users.get(email).equals(password) || !email.endsWith("@gmail.com")) {
            System.out.println("Gagal login");
            currentUser = null;
            return;
        }

        currentUser = email;

        System.out.println("Login berhasil");
        System.out.println("Nama: " + userDetails.get(email).get(0));
        System.out.println("NIK: " + userDetails.get(email).get(1));
    }

    void logout(){
        currentUser = null;
        System.out.println("Logout berhasil");
    }

    public String getCurrentUser() {
        return currentUser;
    }

    boolean pemilihan(){
        return userVotes.containsKey(currentUser);
    }

    void setUserVote(String candidateName){
        userVotes.put(currentUser, candidateName);
    }

    void displayUser(){
        System.out.println("\nNama Akun Terdaftar: ");

        if (userDetails != null && !userDetails.isEmpty()) {
            for (String email : users.keySet()) {
                ArrayList<String> userInfo = userDetails.get(email);
                String nama = userInfo.get(0);
                String NIK = userInfo.get(1);
                System.out.println("Email: " + email);
                System.out.println("Nama: " + nama);
                System.out.println("NIK: " + NIK);
                System.out.println();
            }
        }else {
            System.out.println("Belum ada akun yang terdaftar");
        }

    }
}

class VotingSystem{
    private HashMap<String, Integer> candidates;
    private ArrayList<String> candidateList;

    public VotingSystem() {
        this.candidates = new HashMap<>();
        this.candidateList = new ArrayList<>();
    }

    void addCandidate(String candidateName){
        candidates.put(candidateName.toLowerCase(), 0);
        candidateList.add(candidateName);
    }

    void voteCandidate(String candidateName){
        if (candidates.containsKey(candidateName.toLowerCase())) {
            int countVotes = candidates.get(candidateName.toLowerCase());
            candidates.put(candidateName.toLowerCase(), countVotes+1);
            System.out.println("Terima kasih, suara Anda telah direkam");
        }
    }

    void displayVoting(){
        System.out.println("\nHasil Voting");
        for (String candidate : candidateList) {
            System.out.println("Kandidat " + candidate + ": " + candidates.get(candidate.toLowerCase()) + " suara");
        }
    }
}

public class kegiatan2 {
    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();
        DataPemilih dataPemilih = new DataPemilih();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        votingSystem.addCandidate("B");
        votingSystem.addCandidate("C");
        votingSystem.addCandidate("A");


        do {
            System.out.println("Selamat datang di Sistem Voting Online");
            System.out.println("Pilih menu:");
            System.out.println("1. Login");
            System.out.println("2. Daftar");
            System.out.println("3. Hasil Vote");
            System.out.println("4. Akun User");
            System.out.println("5. Exit");
            System.out.print("Pilihan Anda: ");
            try {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Password: ");
                        String pass = sc.nextLine();
                        dataPemilih.login(email, pass);
                        if (dataPemilih.getCurrentUser() != null) {
                            do {
                                System.out.println("\n1. Pilih kandidat");
                                System.out.println("2. Logout");
                                System.out.print("Pilihan Anda: ");
                                try {
                                    int subChoice = sc.nextInt();
                                    sc.nextLine();
                                    switch (subChoice) {
                                        case 1:
                                            if (!dataPemilih.pemilihan()) {
                                                System.out.println("\nPilih kandidat yang ingin Anda dukung:");
                                                System.out.println("- Kandidat B");
                                                System.out.println("- Kandidat C");
                                                System.out.println("- Kandidat A");
                                                System.out.print("Masukkan nama kandidat: ");
                                                String voteChoice = sc.nextLine();
                                                if (voteChoice.equalsIgnoreCase("A") || voteChoice.equalsIgnoreCase("B") || voteChoice.equalsIgnoreCase("C")) {
                                                    votingSystem.voteCandidate(voteChoice);
                                                    dataPemilih.setUserVote(voteChoice);
                                                } else {
                                                    System.out.println("Kandidat yang Anda pilih tidak ada atau input tidak valid");
                                                }
                                            } else {
                                                System.out.println("Maaf, Anda sudah memberikan suara sebelumnya.");
                                            }
                                            break;
                                        case 2:
                                            dataPemilih.logout();
                                            break;
                                        default:
                                            System.out.println("Pilihan tidak valid");
                                    }
                                } catch (InputMismatchException E) {
                                    System.out.println("Input harus angka");
                                    sc.nextLine();
                                }
                            } while (dataPemilih.getCurrentUser() != null);
                        }

                        break;
                    case 2:
                        System.out.println("REGISTER");
                        System.out.print("Email: ");
                        String regEmail = sc.nextLine();
                        System.out.print("Password: ");
                        String regPass = sc.nextLine();
                        System.out.print("Nama: ");
                        String regName = sc.nextLine();
                        System.out.print("NIK: ");
                        String regNIK = sc.nextLine();
                        dataPemilih.register(regEmail, regPass, regName, regNIK);
                        break;
                    case 3:
                        votingSystem.displayVoting();
                        break;
                    case 4:
                        dataPemilih.displayUser();
                        break;
                    case 5:
                        System.out.println("Terima kasih telah menggunakan Sistem Voting Online.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException E) {
                System.out.println("Input harus angka");
                sc.nextLine();
            }
        } while (choice != 5);
        sc.close();
    }
}