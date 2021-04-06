import java.util.ArrayList;

public class Ruangan extends ElemenGame{

    public Ruangan(String nama, String deskripsi) {
        super(nama, deskripsi);

        //contoh polymorhpish, bisa banyak jenis class yang masuk, Pintu, ItemAmbil,NPC dan yg lain nanti

        tambahElemen(new Pintu("Pintu","Pintu dengan tulisan exit"));
        tambahElemen(new ItemAmbil("Roti","Roti coklat kecil",this));
        tambahElemen(new NPC("NPC Penjaga","Penjaga pintu"));
    }

    @Override
    public ArrayList<Aksi> getArrAksi() {
        ArrayList<Aksi> arrOut  = super.getArrAksi(); //ambil aksi default parent2
        
        //tambahkan aksi ambil atau dibuang tergantung dari owner
        //arrOut.add(new Aksi("Buang",201,this));
        arrOut.add(new Aksi("Keluar Game",301,this));
        return arrOut;
    }

    @Override
    public void aksi(int idAksi) {
        if (idAksi == 301) {
            GameInfo.isGameOver = true;  //exit, perhatikan penggunaan variabel static
        } else {
            super.aksi(idAksi); //punya parent
        }
    }

}
