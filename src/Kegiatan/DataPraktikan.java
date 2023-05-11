package Kegiatan;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;

public class DataPraktikan {
    private static HashMap<String, String> tabelData = new HashMap<>();
    private static HashMap<String, String> tabelSesiLogin = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("Log in");

        DataPraktikan dataPraktikan = new DataPraktikan();
        Scanner in = new Scanner(System.in);
        int pilih = 0;



        if(dataPraktikan.login()) {

            while (pilih != 9) {
                System.out.println("List Data Praktikan : ");
                System.out.println("[1] Tambah Data");
                System.out.println("[2] Tampil Data");
                System.out.println("[3] List NIM Praktikan");
                System.out.println("[4] List nama Asisten");
                System.out.println("[5] Total Data");
                System.out.println("[6] Hapus Data");
                System.out.println("[7] Edit Data");
                System.out.println("[8] Cari Asisten Lab");
                System.out.println("[9] Logout");
                System.out.println("Pilih : ");
                pilih = in.nextInt();


                switch (pilih) {
                    case 1:
                        boolean success = tambahData("", "");
                        if (success) {
                            System.out.println("Data berhasil ditambahkan.");
                        } else {
                            System.out.println("Data gagal ditambahkan.");
                        }
                        System.out.println("\n");
                        break;
                    case 2:
                        tampilData();
                        System.out.println("\n");
                        break;
                    case 3:
                        listNimPraktikan();
                        System.out.println("\n");
                        break;
                    case 4:
                        listNamaAsisten();
                        System.out.println("\n");
                        break;
                    case 5:
                        totalEmail();
                        System.out.println("\n");
                        break;
                    case 6:
                        success = hapusData("", "");
                        if (success) {
                            System.out.println("Data berhasil dihapus.");
                        } else {
                            System.out.println("Data gagal dihapus.");
                        }
                        System.out.println("\n");
                        break;
                    case 7:
                        editData("", "");
                        System.out.println("\n");
                        break;
                    case 8:
                        search();
                        break;
                    case 9:
                        logout();
                        break;
                    default:
                        System.out.println("Input tidak valid. Silakan masukkan nomor opsi yang valid");
                        break;
                }
            }
        }

    }

    private static boolean tambahData(String nimPraktikan, String namaAsisten) {
        Scanner in = new Scanner(System.in);
        boolean loop = true;
        System.out.println("Tambahkan Data Baru");
        do {
            System.out.print("Masukkan NIM " +  ": ");
            nimPraktikan = in.nextLine();
            System.out.print("Masukkan Nama Asisten " +  ": ");
            namaAsisten = in.nextLine();
            String pattern = "IF[0-9]+"; //REGEX untuk NIM
            Pattern r = Pattern.compile(pattern); //Membuat Pattern dari NIM
            Matcher m = r.matcher(nimPraktikan); //Untuk Mencocokkan NIM yg dimasukkan sama pattern
            if (m.matches()) { //Kalau sama disimpan
                tabelData.put(nimPraktikan, namaAsisten);
                loop=false;
            } else {
                System.out.println("NIM yang dimasukkan tidak sesuai format.");
            }
        }while (loop);
        return true;
    }

    public static void tampilData() {
        for (Map.Entry<String, String> entry : tabelData.entrySet()) {
            System.out.println("NIM : " + entry.getKey());
            System.out.println("Nama Asisten : " + entry.getValue());
        }
    }

    public static void listNimPraktikan() {
        System.out.println("List NIM Praktikan: ");
        for (String nimPraktikan : tabelData.keySet()) {
            System.out.println("- "+nimPraktikan);
        }
    }

    public static void listNamaAsisten() {
        System.out.println("List Nama Asisten :");
        for (String namaAsisten : tabelData.values()) {
            System.out.println("- "+namaAsisten);
        }
    }


    public static int totalEmail() {
        int jumlahData = tabelData.size();
        System.out.println("Jumlah data : " + jumlahData);
        return jumlahData;
    }

    public static boolean hapusData(String nimPraktikan, String namaAsisten) {
        Scanner input = new Scanner(System.in);
        System.out.println("NIM yang akan dihapus : ");
        nimPraktikan = input.nextLine();
        System.out.println("Nama asisten yang akan dihapus : ");
        namaAsisten = input.nextLine();
        if (tabelData.containsKey(nimPraktikan) && tabelData.get(nimPraktikan).equals(namaAsisten)) { //mengecek nim dan nama apakah yang diinputkan ada datanya atau tidak
            tabelData.remove(nimPraktikan); //hapus data
            return true;
        }
        return false;

    }

    public static void editData(String nimPraktikan, String namaAsisten) {
        Scanner in = new Scanner(System.in);
        System.out.print("Masukkan NIM yang akan diubah: ");
        String nimLama = in.nextLine();

        if (tabelData.containsKey(nimLama)) {
            System.out.print("Masukkan NIM baru: ");
            nimPraktikan = in.nextLine();
            System.out.print("Masukkan nama asisten baru: ");
            namaAsisten = in.nextLine();
            String pattern = "IF[0-9]+";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(nimPraktikan);
            if (m.matches()) {
                tabelData.remove(nimLama);
                tabelData.put(nimPraktikan, namaAsisten);
                System.out.println("Data berhasil diubah!");
            } else {
                System.out.println("NIM yang dimasukkan tidak sesuai format.");
            }
        } else {
            System.out.println("NIM yang dimasukkan tidak ditemukan.");
        }
    }
    public static void search(){
        listNamaAsisten();
        Scanner in = new Scanner(System.in);
        System.out.print("Nama Asisten yang ingin dicari : ");
        String namaAsisten = in.nextLine();
        tabelData.forEach((key,value)->{
            if(value.equals(namaAsisten)){
                System.out.println("- "+key);
            }
        });
    }


    public boolean login() {
        Scanner in = new Scanner(System.in);
        String email, password;
        boolean condition=false;

        System.out.print("email : ");
        email = in.nextLine();
        System.out.print("password : ");
        password = in.nextLine();
        try {
            if(email.length()>10) {
                String subEmail = email.substring(email.length() - 10);
            }
            if(email.length()<=10) {
                throw new Exception("Email anda terlalu pendek");
            }
            String subEmail = email.substring(email.length()-10);
            if(!(subEmail.equals("@umm.ac.id"))){
                throw new Exception ("Email bukan email umm");
            }else {
                if(email.equals("akarim@umm.ac.id")&&password.equals("093")) {
                    condition = true;
                }
                throw new Exception("Email dan Password anda tidak sesuai");
            }
        }catch (Exception e){
            System.out.println(e+" ");
        }
        return condition;
    }
    public static void logout() {
        System.out.println("Anda telah keluar dari program.");
        System.exit(0);
    }

}