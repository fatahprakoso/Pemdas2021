import java.util.Scanner;

public class StudiKasus {
    public static void main(String[] args) {
        // ------------------ Deklarasi & Inisialisasi Variabel ----------------
        String namaPemesan, stasiunAsal, stasiunTujuan, nomorKTP, alamat, nomorTelp, jenisTiket;
        String tanggalKeberangkatan, kodeStasiunAsal, kodeStasiunAkhir, kodeJenisTiket;
        long nominalPembayaran;
        byte jumlahTiket;
        String border = "====================================================";
        String border2 = "-------------------------------------------";
        Scanner scan = new Scanner(System.in);
        // ---------------------------------------------------------------------

        boolean controlFlow = true;
        do{
            // ------------------ Header/Main menu ---------------------------------
            System.out.println("\n" + border);
            System.out.println("         🚆 FILKOM RAIL EXPRESS 🚆");
            System.out.println("       PROGRAM PEMESANAN TIKET KERETA");
            System.out.println(border);
            System.out.println("Menu");
            System.out.println("1. Daftar Tiket");
            System.out.println("2. Jenis Tiket");
            System.out.println("3. Stasiun");
            System.out.println("4. Keluar");
            System.out.print("Masukkan pilihan Anda: ");
            int pilihan = scan.nextInt();
            scan.nextLine();
            // ---------------------------------------------------------------------


            switch (pilihan) {
                case 1:
                    // ------------------ Input Data Diri ----------------------------------
                    System.out.println(border2);
                    System.out.println("Daftar Tiket");
                    System.out.println(border2);
                    System.out.print("Nama                  : ");
                    namaPemesan = scan.nextLine();
                    System.out.print("Nomor KTP             : ");
                    nomorKTP = scan.nextLine();
                    System.out.print("Alamat                : ");
                    alamat = scan.nextLine();
                    System.out.print("Nomor Telepon         : ");
                    nomorTelp = scan.nextLine();
                    // ---------------------------------------------------------------------


                    // ------------------ Input Data Tiket ---------------------------------
                    System.out.println("\n" + border2);
                    System.out.println("Silahkan masukkan data tiket");
                    System.out.println(border2);

                    System.out.println("\nList Stasiun Tersedia:");
                    System.out.printf(" %-15s %-15s %-15s\n", "1. Malang", "4. Semarang", "7. Serang");
                    System.out.printf(" %-15s %-15s\n", "2. Surabaya", "5. Bandung");
                    System.out.printf(" %-15s %-15s\n", "3. Yogyakarta", "6. Jakarta");

                    System.out.println("\nJenis Tiket:");
                    System.out.printf(" %-20s %-20s %-20s\n\n", "1. Hijau (Ekonomi)", "2. Kuning (Premium)", "3. Merah (Eksklusif)");

                    System.out.print("Tanggal Keberangkatan : ");
                    tanggalKeberangkatan = scan.nextLine();


                    // ------------------ Error Handler Input Stasiun ----------------------
                    do{
                        System.out.print("Stasiun Asal          : ");
                        stasiunAsal = scan.nextLine().toLowerCase();

                        if(stasiunAsal.equals("malang")||stasiunAsal.equals("surabaya")
                        ||stasiunAsal.equals("yogyakarta")||stasiunAsal.equals("semarang")
                        ||stasiunAsal.equals("bandung")||stasiunAsal.equals("jakarta")
                        ||stasiunAsal.equals("serang")) break;

                        System.out.println("Stasiun tidak ditemukan! Silakan masukkan stasiun yang sesuai!");

                    } while(true);

                    do{
                        System.out.print("Stasiun Tujuan        : ");
                        stasiunTujuan = scan.nextLine().toLowerCase();

                        if(stasiunTujuan.equals("malang")||stasiunTujuan.equals("surabaya")
                        ||stasiunTujuan.equals("yogyakarta")||stasiunTujuan.equals("semarang")
                        ||stasiunTujuan.equals("bandung")||stasiunTujuan.equals("jakarta")
                        ||stasiunTujuan.equals("serang")) break;

                        System.out.println("Stasiun tidak ditemukan! Silakan masukkan stasiun yang sesuai!");

                    } while(true);
                    // ---------------------------------------------------------------------


                    // ------------------ Error Handler Input Jenis Tiket ------------------
                    do{
                        System.out.print("Jenis Tiket           : ");
                        jenisTiket = scan.nextLine().toLowerCase();

                        if(jenisTiket.equals("merah")||jenisTiket.equals("kuning")
                        ||jenisTiket.equals("hijau")) break;

                        System.out.println("Silakan masukkan tiket yang sesuai!");
                    } while(true);
                    // ---------------------------------------------------------------------


                    System.out.print("Jumlah Tiket          : ");
                    jumlahTiket = scan.nextByte();
                    // ---------------------------------------------------------------------


                    // ------------------ Perhitungan Jarak Tempuh -------------------------
                    final int MLGSBY = 100, MLGYGY = 400, MLGSMR = 450, MLGBDG = 800, MLGJKT = 900, MLGSRG = 1000;
                    int stasiun1, stasiun2, jarak;

                    switch (stasiunAsal) {
                        case "surabaya":
                            stasiun1 = MLGSBY;
                            kodeStasiunAsal = "SBY";
                            break;
                        case "yogyakarta":
                            stasiun1 = MLGYGY;
                            kodeStasiunAsal = "YOG";
                            break;
                        case "semarang":
                            stasiun1 = MLGSMR;
                            kodeStasiunAsal = "SMR";
                            break;
                        case "bandung":
                            stasiun1 = MLGBDG;
                            kodeStasiunAsal = "BDG";
                            break;
                        case "jakarta":
                            stasiun1 = MLGJKT;
                            kodeStasiunAsal = "JKT";
                            break;
                        case "serang":
                            stasiun1 = MLGSRG;
                            kodeStasiunAsal = "SRG";
                            break;
                        case "Malang":
                            stasiun1 = 0;
                            kodeStasiunAsal = "MLG";
                            break;
                        default:
                            stasiun1 = 0;
                            kodeStasiunAsal = "ERROR";
                    }

                    switch (stasiunTujuan) {
                        case "surabaya":
                            stasiun2 = MLGSBY;
                            kodeStasiunAkhir = "SBY";
                            break;
                        case "yogyakarta":
                            stasiun2 = MLGYGY;
                            kodeStasiunAkhir = "YOG";
                            break;
                        case "semarang":
                            stasiun2 = MLGSMR;
                            kodeStasiunAkhir = "SMR";
                            break;
                        case "bandung":
                            stasiun2 = MLGBDG;
                            kodeStasiunAkhir = "BDG";
                            break;
                        case "jakarta":
                            stasiun2 = MLGJKT;
                            kodeStasiunAkhir = "JKT";
                            break;
                        case "serang":
                            stasiun2 = MLGSRG;
                            kodeStasiunAkhir = "SRG";
                            break;
                        case "Malang":
                            stasiun2 = 0;
                            kodeStasiunAkhir = "MLG";
                            break;
                        default:
                            stasiun2 = 0;
                            kodeStasiunAkhir = "ERROR";
                    }

                    jarak = Math.abs(stasiun1-stasiun2);
                    // ---------------------------------------------------------------------


                    // ------------------ Pemilihan Jenis Sevice (tiket) -------------------
                    long totalBiaya, kembalian;

                    switch (jenisTiket) {
                        case "merah":
                            totalBiaya = 1000*jarak;
                            kodeJenisTiket = "03";
                            break;
                        case "kuning":
                            totalBiaya = 710*jarak;
                            kodeJenisTiket = "02";
                            break;
                        case "hijau":
                            totalBiaya = 440*jarak;
                            kodeJenisTiket = "01";
                            break;
                        default:
                            totalBiaya = 0;
                            kodeJenisTiket = null;
                    }
                    // ---------------------------------------------------------------------


                    // ------------------ Pemberian Diskon ---------------------------------
                    if(jarak>700){
                        totalBiaya -= jenisTiket.equalsIgnoreCase("hijau") ? totalBiaya*(20/100.0) : 0;
                        totalBiaya -= jenisTiket.equalsIgnoreCase("kuning") ? totalBiaya*(25/100.0) : 0;
                        totalBiaya -= jenisTiket.equalsIgnoreCase("merah") ? totalBiaya*(30/100.0) : 0;
                    } else if(jarak>400){
                        totalBiaya -= jenisTiket.equalsIgnoreCase("kuning") ? totalBiaya*(20/100.0) : 0;
                        totalBiaya -= jenisTiket.equalsIgnoreCase("merah") ? totalBiaya*(25/100.0) : 0;
                    } else if(jarak>=300){
                        totalBiaya -= jenisTiket.equalsIgnoreCase("merah") ? totalBiaya*(20/100.0) : 0;
                    } else{
                        totalBiaya -= totalBiaya*(5/100.0);
                    }
                    // ---------------------------------------------------------------------


                    // ------------------ Perhitungan total biaya --------------------------
                    totalBiaya *= jumlahTiket;
                    // ---------------------------------------------------------------------


                    do{
                        // ------------------ Input nominal pembayaran -------------------------
                        System.out.print("Nominal Pembayaran    : ");
                        nominalPembayaran = scan.nextLong();
                        scan.nextLine();
                        System.out.println(border);
                        // ---------------------------------------------------------------------


                        // ------------------ Perhitungan kembalian ----------------------------
                        kembalian = nominalPembayaran - totalBiaya;
                        // ---------------------------------------------------------------------


                        // ------------------ Output -------------------------------------------
                        if(kembalian >= 0){
                            System.out.println("\nPemesanan Tiket Berhasil !\n");
                            System.out.println(border);
                            System.out.println("         DETAIL PEMESANAN");
                            System.out.println(border);
                            String bufferId = kodeStasiunAsal + "-" + kodeStasiunAkhir + kodeJenisTiket + nomorKTP.substring(nomorKTP.length()-3);
                            System.out.printf("ID                    : %s%03d\n",bufferId,jumlahTiket);
                            System.out.println("Nama                  : " + namaPemesan);
                            System.out.println("KTP                   : " + nomorKTP);
                            System.out.println("Alamat                : " + alamat);
                            System.out.println("Nomor Telepon         : " + nomorTelp);
                            System.out.println("Tanggal Keberangkatan : " + tanggalKeberangkatan);
                            System.out.println("Rute                  : " + stasiunAsal + " - " + stasiunTujuan + " -> " + jarak + " km");
                            System.out.println("Jumlah Tiket          : " + jumlahTiket);
                            System.out.println("Total Harga           : Rp." + totalBiaya);
                            System.out.println("Nominal Pembayaran    : Rp." + nominalPembayaran);
                            System.out.println("Kembalian             : Rp." + kembalian);

                            System.out.println(border);
                            System.out.println("\nSelamat menikmati perjalanan Anda 🙏");
                            break;
                        } else {
                            System.out.println("\nMaaf, uang Anda tidak mencukupi!");
                            System.out.println("1. Membatalkan Pesanan");
                            System.out.println("2. Mengulangi Pembayaran");
                            int isBatal = scan.nextInt();

                            if(isBatal == 1) break;
                        }
                    } while(true);
                    System.out.println("\n" + border);

                    scan.nextLine();
                    break;
                    // ---------------------------------------------------------------------
                case 2:
                    // ------------------ Menampilkan List Tiket ---------------------------
                    System.out.println(border2);
                    System.out.println("Jenis Tiket");
                    System.out.println(border2);
                    System.out.println("1. Hijau (Ekonomi)  : Rp.440/km");
                    System.out.println("2. Kuning (premium) : Rp.710/km");
                    System.out.println("3. Merah (Ekslusif) : Rp.1000/km");
                    scan.nextLine();
                    break;
                    // ---------------------------------------------------------------------
                case 3:
                    // ------------------ Menampilkan Stasiun ------------------------------
                    System.out.println(border2);
                    System.out.println("List Stasiun Tersedia");
                    System.out.println(border2);
                    System.out.printf(" %-15s %-15s %-15s\n", "1. Malang", "4. Semarang", "7. Serang");
                    System.out.printf(" %-15s %-15s\n", "2. Surabaya", "5. Bandung");
                    System.out.printf(" %-15s %-15s\n", "3. Yogyakarta", "6. Jakarta");
                    scan.nextLine();
                    break;
                    // ---------------------------------------------------------------------
                default:
                    controlFlow = false;
            }
        } while(controlFlow);

        scan.close();
    }
}