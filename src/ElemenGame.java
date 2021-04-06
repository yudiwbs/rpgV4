import java.util.ArrayList;
import java.util.Scanner;
/* class inti, semua elemen game yang bisa punya aksi harus diturunkan dari class ini */

public class ElemenGame {
    private Scanner sc = new Scanner(System.in);
    //bagian dari game  yang dapat mengandung aksi: item, player, npc, ruangan dst
    private String deskripsi;
    private String nama;

    private int cc;  //internal untuk UI
    private ArrayList<ElemenGame> arrElemenGame = new ArrayList<>();  //bisa compound, punya item lagi
    private ArrayList<Aksi> arrAksi = new ArrayList<>(); //daftar aksi keseluruhan


    //UI: menampilkan aksi secara rekursif, karena item bisa punya punya subitem lalu punya subitem lagi dst
    private void tampilkanAksi (ElemenGame objElemenGame) {
        //aksi internal
        System.out.println(objElemenGame.getNama());
        arrAksi.addAll(objElemenGame.getArrAksi()); //tambahkan aksi internel kalau ada
        for (Aksi ak:objElemenGame.getArrAksi()) {
            System.out.printf("%d. %s \n",cc,ak.getNamaAksi());
            cc++;
        }
        //aksi milik item/obj yang dimiliki (subitem)
        if (objElemenGame.arrElemenGame.size() > 0) {
                System.out.printf("%s memiliki: \n",objElemenGame.nama);
                for (ElemenGame eg : objElemenGame.arrElemenGame) {
                        tampilkanAksi(eg); //rekursif, karena setiap subitem bisa punya itemlagi
                }

        }

    }

    //UI: tampilkan pilihan
    public void pilihanAksi() {
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



    //constrcutor
    public ElemenGame(String nama,String deskripsi) {
        this.deskripsi = deskripsi;
        this.nama = nama;
    }

    //output: arrayList aksi objek ini (digunakan oleh UI).
    //bisa dimanfaatkan untuk membuat aksi yang dinamik
    //ini akan dioverride class turunan
    public ArrayList<Aksi> getArrAksi() {
        ArrayList<Aksi> arrAksi = new ArrayList<>();
        arrAksi.add(new Aksi("Deskripsikan",101,this)); //aksi standard untuk semua elemen
        return arrAksi;
    }

    //eksekusi berdasarkan id
    //ini akan dioverride oleh class turunan
    public void aksi(int idAksi) {
        if (idAksi==101) {
            System.out.printf("Deskripsi: %s \n",deskripsi);
        }
    }

    //cari item yang dimiliki berdasarkan nama, return objek elemennya  jika ada, jika tdk ada null
    public ElemenGame cariItem(String namaElemen) {
        for (ElemenGame eg:arrElemenGame) {
            if (namaElemen.equals(eg.getNama())) {
                return (eg);
            }
        }
        return(null); //tidak ketemu
    }

    //hapus elemen pada arrElemenGame
    public void hapusElemen(ElemenGame eg) {
        arrElemenGame.remove(eg);
    }

    //tambah elemen pada arrElemenGame
    public void tambahElemen(ElemenGame eg) {
        arrElemenGame.add(eg);
    }

    public String getNama() {
        return nama;
    }


}
