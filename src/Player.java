import java.util.ArrayList;

public class Player extends  ElemenGame {

    int kesehatan=100;

    public Player(String nama, String deskripsi) {
        super(nama, deskripsi);
    }

    private void tampilkanStatus() {
        System.out.printf("Kesehatan player: %s  \n",kesehatan);
    }

    @Override
    public ArrayList<Aksi> getArrAksi() {
        ArrayList<Aksi> arrOut  = super.getArrAksi(); //ambil aksi default
        //tambahkan aksi lihat status kesehatan
        arrOut.add(new Aksi("Lihat status player", 401, this));
        return arrOut;
    }

    @Override
    public void aksi(int idAksi) {
        if (idAksi == 401) {
            tampilkanStatus();
        }
        else {
            super.aksi(idAksi); //punya parent
        }
    }

}
