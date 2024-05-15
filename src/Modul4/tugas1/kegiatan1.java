package Modul4.tugas1;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

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
        }else {
            System.out.println("Kandidat yang anda pilih tidak ada");
        }
    }

    void displayVoting(){
        System.out.println("\nHasil Voting");
        for (String candidate : candidateList) {
            System.out.println("Kandidat " + candidate + ": " + candidates.get(candidate.toLowerCase()) + " suara");
        }
    }
}
public class kegiatan1 {
    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();
        Scanner sc = new Scanner(System.in);
        String pilihan;

        votingSystem.addCandidate("B");
        votingSystem.addCandidate("C");
        votingSystem.addCandidate("A");

        do {
            System.out.println("\nPilih kandidat yang ingin Anda dukung:");
            System.out.println("- Kandidat B");
            System.out.println("- Kandidat C");
            System.out.println("- Kandidat A");
            System.out.print("Masukkan nama kandidat (atau ketik 'selesai' untuk keluar): ");
            pilihan = sc.nextLine();

            if (!pilihan.equalsIgnoreCase("selesai")) {
                votingSystem.voteCandidate(pilihan);
            }

        } while (!pilihan.equalsIgnoreCase("selesai"));

        votingSystem.displayVoting();
        sc.close();
    }
}