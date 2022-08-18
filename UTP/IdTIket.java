import java.util.Scanner;

public class IdTIket{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String tiket = in.nextLine();
        String asal, tujuan, jenisTiket, jumlahTiket;
        jumlahTiket = tiket.substring(tiket.length()-3);

        tiket = tiket.toLowerCase();

        switch(tiket.substring(0,3)){
            case "mlg":
                asal="Malang";
                break;
            case "jkt":
                asal="Jakarta";
                break;
            case "sby":
                asal="Surabaya";
                break;
            case "bdg":
                asal="Bandung";
                break;
            case "smr":
                asal="Semarang";
                break;
            case "yog":
                asal="Yogyakarta";
                break;
            case "srg":
                asal="Serang";
                break;
            default:
                asal="Error!";
        }

        switch(tiket.substring(4,7)){
            case "mlg":
                tujuan="Malang";
                break;
            case "jkt":
                tujuan="Jakarta";
                break;
            case "sby":
                tujuan="Surabaya";
                break;
            case "bdg":
                tujuan="Bandung";
                break;
            case "smr":
                tujuan="Semarang";
                break;
            case "yog":
                tujuan="Yogyakarta";
                break;
            case "srg":
                tujuan="Serang";
                break;
            default:
                tujuan="Error!";
        }

        switch (tiket.substring(7,9)) {
            case "01":
                jenisTiket="Hijau";
                break;
            case "02":
                jenisTiket="Kuning";
                break;
            case "03":
                jenisTiket="Merah";
                break;
            default:
                jenisTiket="Error!";
                break;
        }

        System.out.printf("%s-%s, Tiket %s : %s",asal,tujuan,jenisTiket,jumlahTiket);
    }
}
