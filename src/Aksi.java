public class Aksi {
    private String namaAksi; //misal "Ambil item".
    private int idAksi;
    private ElemenGame objAksi;  //polymorphism

    public String getNamaAksi() {
        return namaAksi;
    }

    public Aksi(String namaAksi, int idAksi, ElemenGame objAksi) {
        this.namaAksi = namaAksi;
        this.idAksi = idAksi;
        this.objAksi = objAksi;
    }

    public void eksekusiAksi() {
        objAksi.aksi(idAksi);
    }

}
