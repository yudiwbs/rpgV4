import java.util.ArrayList;

public class Pintu extends  ElemenGame {
    public Pintu(String nama, String deskripsi) {
        super(nama, deskripsi);
    }

    private void bukaKunci() {
        //cek apakah player punya kunci
        System.out.println("Membuka pintu ....");
        ElemenGame kunci = GameInfo.objPlayer.cariItem("Kunci"); //tidak perlu casting karena hanya perlu cek ada atau tidak
        if (kunci!=null) {
            System.out.println("Player memasukkan kunci dan, pintu terbuka! TAMAT");
            GameInfo.isGameOver = true;
        } else {
            System.out.println("Player mencoba membuka dan pintu TERKUNCI!");
        }
    }


    @Override
    public ArrayList<Aksi> getArrAksi() {
        ArrayList<Aksi> arrOut  = super.getArrAksi(); //ambil aksi default
        //tambahkan aksi lihat status kesehatan
        arrOut.add(new Aksi("Buka pintu", 601, this));
        return arrOut;
    }

    @Override
    public void aksi(int idAksi) {
        if (idAksi == 601) {
            bukaKunci();
        }
        else {
            super.aksi(idAksi); //punya parent
        }
    }


}
