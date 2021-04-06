public class GameEngine {

    //init player dan ruangan (masih satu ruangan)
    Player objPlayer = new Player("Player","Orang yang baru tersadar di ruangan yg tidak dikenal");
    Ruangan objRuangan = new Ruangan("Ruangan 1","Ruangan dengan satu pintu dan satu jendela");

    public GameEngine() {
        //GameInfo tdk perlu dicreate kerena atributnya static
        GameInfo.objPlayer = objPlayer;
        GameInfo.objRuangan = objRuangan;
        GameInfo.isGameOver = false;

        //tambahkan player ke ruangan dan satu objek
        objRuangan.tambahElemen(objPlayer);

    }

    public void mulai() {
        while (!GameInfo.isGameOver) {
            objRuangan.pilihanAksi();
        }
    }

    public static void main(String[] args) {
        GameEngine ge = new GameEngine();
        ge.mulai();
    }
}
