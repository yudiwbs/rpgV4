import java.util.ArrayList;

public class NPC extends ElemenGame{

    private boolean isBerkenalan=false;
    public NPC(String nama, String deskripsi) {
        super(nama, deskripsi);
        ItemAmbil kunci = new ItemAmbil("Kunci","Kunci kecil berkarat",this);
        kunci.setBisaDiambil(false); //hilangkan menu ambil
        tambahElemen(kunci);
    }

    private void berkenalan()  {
        System.out.println("halo, saya penjaga pintu");
        isBerkenalan = true;
    }

    private void mintaKunci()  {
        System.out.println("Meminta kunci...");
        //cek apakah kunci ada di npc
        ItemAmbil kunci = (ItemAmbil) cariItem("Kunci");  //casting, karena return dari cariItem adalah ElemenGame, padahal variabel adalan ItemAmbil

        if (kunci!=null)  {
            //ada
            if (isBerkenalan)  {
                //sudah kenalan, beri kunci
                System.out.println("Kunci diberikan");
                GameInfo.objPlayer.tambahElemen(kunci); //pindahkan kunci ke player
                kunci.setBisaDiambil(true);   //buat kunci bisa dibuang
                hapusElemen(kunci);           //buang kunci dari npc
                kunci.setOwner(GameInfo.objPlayer); //set owner, ini sebabnya perlu casting, karena owner hanya ada di ItemAmbil
            } else {
                System.out.println("Kenalan dulu dong!");
            }
        } else {
            System.out.println("Masa lupa, kunci sudah diberikan!");
        }
    }

    @Override
    public ArrayList<Aksi> getArrAksi() {
        ArrayList<Aksi> arrOut  = super.getArrAksi(); //ambil aksi default
        //tambahkan aksi lihat status kesehatan
        arrOut.add(new Aksi("Berkenalan", 501, this));
        arrOut.add(new Aksi("Minta kunci", 502, this));
        return arrOut;
    }

    @Override
    public void aksi(int idAksi) {
        if (idAksi == 501) {
            berkenalan();
        } else if (idAksi == 502) {
            mintaKunci();
        }
        else {
            super.aksi(idAksi); //punya parent
        }
    }

}
