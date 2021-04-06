import java.util.ArrayList;

/*
      Item yang bisa diambil atau dibuang oleh player
 */

public class ItemAmbil extends ElemenGame {

    private boolean isBisaDiambil = true; //false misalnya objek dipegang npc yang tidak boleh diambil langsung
    private ElemenGame owner; //siapa pemilik item



    //constructor tambahan, perhatikan pemanggilan super untuk panggil construtor default
    public ItemAmbil(String nama, String deskripsi, ElemenGame owner) {
        super(nama, deskripsi);
        this.owner = owner;
    }

    public ItemAmbil(String nama, String deskripsi) {
        super(nama, deskripsi);
    }

    private void dibuang() {
        owner = GameInfo.objRuangan;
        System.out.println("Item dibuang");
        GameInfo.objPlayer.hapusElemen(this);  //hapus dari player
        GameInfo.objRuangan.tambahElemen(this);  //tambahkan ke ruangan5
    }

    private void diambil() {
        //pindahkan dari owner ke player
        // todo: diambil bisa saja dilakukan oleh npc nanti
        System.out.println("Item diambil");
        GameInfo.objPlayer.tambahElemen(this);    //GameInfo bisa langsung digunakan karena pake static
        owner.hapusElemen(this);                  //hapus dari owner
        owner = GameInfo.objPlayer;                  //playe jadi owne baru
    }


    @Override
    public ArrayList<Aksi> getArrAksi() {
        ArrayList<Aksi> arrOut  = super.getArrAksi(); //ambil aksi default
        //tambahkan aksi ambil atau dibuang tergantung dari owner

        if (isBisaDiambil) {
            if (owner == GameInfo.objPlayer) {  //sedang dipegang player
                arrOut.add(new Aksi("Buang", 202, this));
            } else {
                arrOut.add(new Aksi("Ambil", 201, this));
            }
        }
        return arrOut;
    }

    @Override
    public void aksi(int idAksi) {
        if (idAksi == 201) {
            diambil();
        } else if (idAksi == 202) {
            dibuang();
        }
        else {
            super.aksi(idAksi); //punya parent
        }
    }

    public ElemenGame getOwner() {
        return owner;
    }

    public void setOwner(ElemenGame owner) {
        this.owner = owner;
    }

    public boolean isBisaDiambil() {
        return isBisaDiambil;
    }

    public void setBisaDiambil(boolean bisaDiambil) {
        isBisaDiambil = bisaDiambil;
    }

}
