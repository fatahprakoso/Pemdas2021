import java.util.Scanner;
//
public class StudiKasus {
    public static void main(String[] args) {
        // ------------------ Deklarasi & Inisialisasi Variabel ----------------

        // variabel-variabel untuk menyimpan data user
        String namaPemesan, nomorKTP, alamat, nomorTelp;

        // variabel-variabel untuk menyimpan data tiket
        String stasiunAsal, stasiunTujuan, jenisTiket, tanggalKeberangkatan;

        long nominalPembayaran;
        byte jumlahTiket;
        String border = "====================================================";
        String border2 = "-------------------------------------------";

        // Representasi setiap index pada array riwayat:
        // [i][0] : idPesanan
        // [i][1] : tanggalKeberangkatan
        // [i][2] : nomorKTP
        // [i][3] : namaPemesan
        String [][] riwayat = new String[5][4];

        // Digunakan untuk melakukan FIFO
        int indexRiwayat = 0;

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
            System.out.println("1. Daftar");
            System.out.println("2. Lihat Jenis Tiket");
            System.out.println("3. Stasiun");
            System.out.println("4. Riwayat Pesanan");
            System.out.println("5. Cek Pesanan");
            System.out.println("6. Keluar");
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

                        // Melakukan pengecekkan input user
                        if(stasiunAsal.equals("malang")||stasiunAsal.equals("surabaya")
                        ||stasiunAsal.equals("yogyakarta")||stasiunAsal.equals("semarang")
                        ||stasiunAsal.equals("bandung")||stasiunAsal.equals("jakarta")
                        ||stasiunAsal.equals("serang")) break;

                        // Akan di-eksekusi jika stasiun yang diinputkan user salah atau tidak tersedia
                        System.out.println("Stasiun tidak ditemukan! Silakan masukkan stasiun yang sesuai!");

                    } while(true);


                    do{
                        System.out.print("Stasiun Tujuan        : ");
                        stasiunTujuan = scan.nextLine().toLowerCase();

                        // Melakukan pengecekkan input user
                        if(stasiunTujuan.equals("malang")||stasiunTujuan.equals("surabaya")
                        ||stasiunTujuan.equals("yogyakarta")||stasiunTujuan.equals("semarang")
                        ||stasiunTujuan.equals("bandung")||stasiunTujuan.equals("jakarta")
                        ||stasiunTujuan.equals("serang")) break;

                        // Akan di-eksekusi jika stasiun yang diinputkan user salah atau tidak tersedia
                        System.out.println("Stasiun tidak ditemukan! Silakan masukkan stasiun yang sesuai!");

                    } while(true);
                    // ---------------------------------------------------------------------


                    // ------------------ Error Handler Input Jenis Tiket ------------------
                    do{
                        System.out.print("Jenis Tiket           : ");
                        jenisTiket = scan.nextLine().toLowerCase();

                        // Melakukan pengecekkan input user
                        if(jenisTiket.equals("merah")||jenisTiket.equals("kuning")
                        ||jenisTiket.equals("hijau")) break;

                        // Akan di-eksekusi jika tiket yang diinputkan user salah atau tidak tersedia
                        System.out.println("Silakan masukkan tiket yang sesuai!");

                    } while(true);
                    // ---------------------------------------------------------------------


                    System.out.print("Jumlah Tiket          : ");
                    jumlahTiket = scan.nextByte();
                    // ---------------------------------------------------------------------


                    // ------------------ Perhitungan Jarak Tempuh -------------------------
                    /*
                    Konstanta-konstanta di bawah ini merupakan perhitungan jarak kota-kota yang ada
                    menuju ke kota Malang.
                    */
                    final int MLGSBY = 100, MLGYGY = 400, MLGSMR = 450, MLGBDG = 800, MLGJKT = 900, MLGSRG = 1000;

                    /*
                    Variabel di bawah ini merepresentasikan jarak stasiun asal dan stasiun tujuan
                    yang relatif terhadap Kota Malang, atau dapat dikatakan juga sebagai variabel
                    yang menyimpan jarak dari stasiun asal dan stasiun tujuan menuju ke Kota Malang
                    */
                    int stasiun1, stasiun2;

                    // Jarak akan didapatkan dengan mengambil selisih dari stasiun1 dan stasiun2
                    int jarak;

                    // Variabel di bawah ini digunakan untuk menyusun id pesanan
                    String kodeStasiunAsal, kodeStasiunAkhir;

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
                        default:
                            stasiun1 = 0;
                            kodeStasiunAsal = "MLG";
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
                        default:
                            stasiun2 = 0;
                            kodeStasiunAkhir = "MLG";
                    }

                    // jarak dapat didapatkan dengan menghitung selisih stasiun1 dan stasiun2
                    jarak = Math.abs(stasiun1-stasiun2);

                    // ---------------------------------------------------------------------


                    // ------------------ Pemilihan Jenis Sevice (tiket) -------------------

                    // Variabel di bawah ini digunakan untuk menyusun id pesanan
                    String kodeJenisTiket;

                    // Didapatkan dengan perkalian jarak dengan harga tiket
                    long totalBiaya;

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
                        long kembalian = nominalPembayaran - totalBiaya;
                        // ---------------------------------------------------------------------


                        // ------------------ Output -------------------------------------------

                        // Notifikasi keberhasilan pemesanan tiket
                        if(kembalian >= 0){
                            System.out.println("\nPemesanan Tiket Berhasil !\n");
                            System.out.println(border);
                            System.out.println("         DETAIL PEMESANAN");
                            System.out.println(border);
                            String bufferId = kodeStasiunAsal + "-" + kodeStasiunAkhir + kodeJenisTiket + nomorKTP.substring(nomorKTP.length()-3);
                            String idPesanan = String.format("%s%03d",bufferId,jumlahTiket);
                            System.out.println("ID                    : " + idPesanan);
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

                            // ------------------ input data ke array sebagai penyimpanan data -----
                            riwayat[indexRiwayat][0] = idPesanan;
                            riwayat[indexRiwayat][1] = tanggalKeberangkatan;
                            riwayat[indexRiwayat][2] = nomorKTP;
                            riwayat[indexRiwayat][3] = namaPemesan;
                            // Representasi setiap index pada array riwayat:
                            // [i][0] : idPesanan
                            // [i][1] : tanggalKeberangkatan
                            // [i][2] : nomorKTP
                            // [i][3] : namaPemesan

                            /*
                            Percabangan di bawah ini digunakan untuk melakukan increment index pertama pada
                            array riwayat. Selain itu, juga digunakan untuk mencegah error yang dikarenakan
                            keterbatasan kapasitas array riwayat, serta melakukan algoritma FIFO (queue)
                            */
                            if(indexRiwayat==4) indexRiwayat = 0;
                            else indexRiwayat++;

                            // ---------------------------------------------------------------------

                            break;
                        }

                        // Akan dieksekusi jika pemesanan tiket gagal karena uang tidak mencukupi
                        else {
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
                case 4:
                    // ------------------ Menampilkan Riwayat Pesanan ----------------------
                    System.out.println(border2);
                    System.out.println("Riwayat Pemesanan Tiket");
                    System.out.println(border2);
                    for (int i = 0; i < riwayat.length; i++) {

                        // jika array riwayat kosong pada index tertentu, maka akan berhenti looping
                        if(riwayat[i][0]==null) break;

                        // Menampilkan data-data dalam array riwayat sesuai index pada looping
                        System.out.printf("%d. %s: %s\n", (i+1),riwayat[i][0],riwayat[i][1]);
                    }
                    scan.nextLine();
                    break;
                    // ---------------------------------------------------------------------
                case 5:
                    // ------------------ Cek Pesanan --------------------------------------
                    System.out.println(border2);
                    System.out.println("Cek Tiket");
                    System.out.println(border2);

                    /*
                    User akan menginputkan id pesanan dan nomor ktp yang digunakan untuk
                    mencari pesanan yang tersimpan pada array riwayat
                    */
                    System.out.print("Masukkan ID Pesanan Anda : ");
                    String authID = scan.nextLine();
                    System.out.print("Masukkan Nomor KTP Anda  : ");
                    String authKTP = scan.nextLine();
                    System.out.println();

                    // Akan true jika pesanan tidak ditemukan
                    boolean notFound = false;

                    // Looping digunakan untuk menelusuri seluruh index pertama array riwayat
                    for (int i = 0; i < riwayat.length; i++) {

                        /*
                        jika array riwayat kosong pada index tertentu, maka akan berhenti looping
                        dan me-set notFound menjadi true
                        */
                        if(riwayat[i][0]==null){
                            notFound = true;
                            break;
                        }

                        // Mengecek kesesuaian id dan nomor ktp yang diinputkan user
                        if(riwayat[i][0].equals(authID) && riwayat[i][2].equals(authKTP)){

                            // Representasi setiap index pada array riwayat:
                            // [i][0] : idPesanan
                            // [i][1] : tanggalKeberangkatan
                            // [i][2] : nomorKTP
                            // [i][3] : namaPemesan
                            String idPesanan = riwayat[i][0];

                            /*
                            Variabel di bawah ini digunakan untuk menyimpan data-data pada
                            id pesanan
                            */
                            String cekStasiunAsal, cekStasiunTujuan , cekJenisTiket;


                            // ------------------ Mengambil data pada id pesanan -------------------
                            switch (idPesanan.substring(0,3)) {
                                case "SBY":
                                cekStasiunAsal = "Surabaya";
                                    break;
                                case "YOG":
                                cekStasiunAsal = "Yogyakarta";
                                    break;
                                case "SMR":
                                cekStasiunAsal = "Semarang";
                                    break;
                                case "BDG":
                                cekStasiunAsal = "Bandung";
                                    break;
                                case "JKT":
                                cekStasiunAsal = "Jakarta";
                                    break;
                                case "SRG":
                                cekStasiunAsal = "Serang";
                                    break;
                                case "MLG":
                                cekStasiunAsal = "Malang";
                                    break;
                                default:
                                    cekStasiunAsal = "Error";
                            }

                            switch (idPesanan.substring(4,7)) {
                                case "SBY":
                                cekStasiunTujuan = "Surabaya";
                                    break;
                                case "YOG":
                                cekStasiunTujuan = "Yogyakarta";
                                    break;
                                case "SMR":
                                cekStasiunTujuan = "Semarang";
                                    break;
                                case "BDG":
                                cekStasiunTujuan = "Bandung";
                                    break;
                                case "JKT":
                                cekStasiunTujuan = "Jakarta";
                                    break;
                                case "SRG":
                                cekStasiunTujuan = "Serang";
                                    break;
                                case "MLG":
                                cekStasiunTujuan = "Malang";
                                    break;
                                default:
                                cekStasiunTujuan = "Error";
                            }

                            switch (idPesanan.substring(7,9)) {
                                case "03":
                                    cekJenisTiket = "Merah";
                                    break;
                                case "02":
                                    cekJenisTiket = "Kuning";
                                    break;
                                case "01":
                                    cekJenisTiket = "Hijau";
                                    break;
                                default:
                                    cekJenisTiket = "Error";
                            }
                            // ---------------------------------------------------------------------


                            // Gabungan stasiun asal dan stasiun tujuan
                            String rute = cekStasiunAsal + "-" + cekStasiunTujuan;


                            // ------------------ Output data yang dicari/dicek --------------------
                            System.out.println(border);
                            System.out.println("         DETAIL PEMESANAN");
                            System.out.println(border);
                            // Representasi setiap index pada array riwayat:
                            // [i][0] : idPesanan
                            // [i][1] : tanggalKeberangkatan
                            // [i][2] : nomorKTP
                            // [i][3] : namaPemesan
                            System.out.println("ID Pesanan               : " + riwayat[i][0]);
                            System.out.println("Nomor KTP                : " + riwayat[i][2]);
                            System.out.println("Nama Pemesan             : " + riwayat[i][3]);
                            System.out.println("Rute                     : " + rute);
                            System.out.println("Tanggal Keberangkatan    : " + riwayat[i][1]);
                            System.out.println("Jenis Tiket              : " + cekJenisTiket);
                            System.out.println("Jumlah Tiket             : " + idPesanan.substring(idPesanan.length()-3));
                            System.out.println(border);
                            // ---------------------------------------------------------------------

                            break;
                        }
                        // Jika hingga indeks terakhir data pesanan tidak ditemukan, maka notFound true
                        else if(i == riwayat.length-1) notFound = true;

                    }

                    // Notifikasi jika pesanan tidak ditemukan
                    if(notFound) System.out.println("Pesanan tidak ditemukan");

                    scan.nextLine();
                    break;
                    // ---------------------------------------------------------------------
                default:
                    // ------------------ Keluar applikasi ---------------------------------
                    controlFlow = false;
                    // ---------------------------------------------------------------------
            }
        } while(controlFlow);

        scan.close();
    }
}