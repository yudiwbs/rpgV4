import java.util.ArrayList;
import java.util.Scanner;

public class ElemenGame {
    private Scanner sc = new Scanner(System.in);
    //bagian dari game  yang dapat mengandung aksi: item, player, npc, ruangan dst
    private String deskripsi;
    private String nama;

    private int cc;
    private ArrayList<ElemenGame> arrElemenGame = new ArrayList<>();  //bisa compound, punya item lagi
    

    private ArrayList<Aksi> arrAksi = new ArrayList<>(); //daftar aksi keseluruhan


    //menampilkan secara rekursif, karena item bisa punya punya subitem lalu punya subitem lagi dst

    private void tampilkanAksi (ElemenGame objElemenGame) {
        //aksi internal
        System.out.println(objElemenGame.getNama());
        arrAksi.addAll(objElemenGame.getArrAksi()); //tambahkan aksi internel kalau ada
        for (Aksi ak:objElemenGame.getArrAksi()) {
            System.out.printf("%d. %s \n",cc,ak.getNamaAksi());
            cc++;
        }
        //aksi milik item/obj yang dimilik
        if (objElemenGame.arrElemenGame.size() > 0) {
                System.out.printf("%s memiliki: \n",objElemenGame.nama);
                for (ElemenGame eg : objElemenGame.arrElemenGame) {
                        tampilkanAksi(eg); //rekursif
                }

        }

    }

    public void pilihanAksi() {
        // UI pilihan aksi
        System.out.println("===============");
        System.out.println("Pilihan aksi:");

        cc = 1;
        arrAksi.clear(); //kosongkan aksi yang akan ditampilkan di UI
        tampilkanAksi(this);

        System.out.print("Pilihan aksi:");
        int pil = sc.nextInt();
        //eksekusi, polymorphism berguna disini
        System.out.println("===============");
        arrAksi.get(pil-1).eksekusiAksi();

    }

    public void hapusElemen(ElemenGame eg) {
        //hapus elemen pada arrElemenGame
        arrElemenGame.remove(eg);
    }

    public void tambahElemen(ElemenGame eg) {
        arrElemenGame.add(eg);
    }

    public String getNama() {
        return nama;
    }

    //constrcutor
    public ElemenGame(String nama,String deskripsi) {
        this.deskripsi = deskripsi;
        this.nama = nama;
    }

    public ArrayList<Aksi> getArrAksi() {
    //output: arrayList aksi objek ini (digunakan oleh UI).
    //bisa dimanfaatkan untuk membuat aksi yang dinamik
        ArrayList<Aksi> arrAksi = new ArrayList<>();
        arrAksi.add(new Aksi("Deskripsikan",101,this));
        return arrAksi;
    }

    public void aksi(int idAksi) {
        //eksekusi berdasarkan id
        if (idAksi==101) {
            System.out.printf("Deskripsi: %s \n",deskripsi);
        }
    }

    //cari item yang dimiliki, return elemennya  jika ada, jika tdk ada null
    public ElemenGame cariItem(String namaElemen) {
        for (ElemenGame eg:arrElemenGame) {
            if (namaElemen.equals(eg.getNama())) {
                return (eg);
            }
        }
        return(null); //tidak ketemu
    }

}
