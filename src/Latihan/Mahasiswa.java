package Latihan;

import java.util.Scanner;
import java.util.HashMap;

public class Mahasiswa {
    String nama, kelas, matkulPraktikum;
    int nim;

    public Mahasiswa (String nm, String kl, String mat, int ni){
        nama = nm;
        kelas = kl;
        matkulPraktikum = mat;
        nim = ni;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HashMap<String , Mahasiswa> mhs = new HashMap<>();
        String input;
        Mahasiswa data;

        mhs.put("1", new Mahasiswa("Andi", "3H", "Struktur Data", 103703110));
        mhs.put("2", new Mahasiswa("Budi", "3A", "Matematika Diskrit", 103703111));



        System.out.println("MASUKKAN ID :");
        input= in.nextLine();
        data = mhs.get(input);
        if ((data != null)) {
            System.out.println("Data Mahasiswa :"+data.nama +", kelas:"+ data.kelas+", mata kuliah praktikum :"+data.matkulPraktikum+", nim :"+data.nim);
        }
    }
}