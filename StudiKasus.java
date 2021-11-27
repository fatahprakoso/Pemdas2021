import java.util.Scanner;


public class StudiKasus {

    // ------------------ Deklarasi & Inisialisasi Variabel Global -------------
    // Representasi setiap index pada array riwayat:
    // [i][0] : idPesanan
    // [i][1] : tanggalKeberangkatan
    // [i][2] : nomorKTP
    // [i][3] : namaPemesan
    static String [][] riwayat = new String[5][4];

    // Digunakan untuk melakukan FIFO
    static int indexRiwayat = 0;

    // lain-lain
    static Scanner scan = new Scanner(System.in);
    static String border = "====================================================";
    static String border2 = "-------------------------------------------";
    // ---------------------------------------------------------------------


    /** PRINT MENU UTAMA
     * Method ini akan mengeluarkan daftar sub-menu dan mengambil inputan user
     * untuk memilih sub-menu yang diinginkan serta mengembalikan nilai integer
     * yang merupakan representasi dari pilihan sub-menu user.
     */
    public static int menuUtama(){
        System.out.println(border);
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

        return pilihan;
    }


    /** DAFTAR ATAU PESAN TIKET
     * Method ini akan mengambil beberapa inputan dari user yang akan diproses
     * untuk memesan suatu tiket kereta api. Pada akhir method akan dilakukan
     * penyimpanan data-data yang sudah diinputkan oleh user sebelumnya ke array
     * riwayat.
     */
    public static void daftarTiket(){
        // variabel-variabel untuk menyimpan data user
        String namaPemesan, nomorKTP, alamat, nomorTelp;

        // variabel-variabel untuk menyimpan data tiket
        String stasiunAsal, stasiunTujuan, jenisTiket, tanggalKeberangkatan;

        long nominalPembayaran;
        byte jumlahTiket;

        // ------------------ Input Data Diri ----------------------------------
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
        infoPesanTiket();

        System.out.print("Tanggal Keberangkatan : ");
        tanggalKeberangkatan = scan.nextLine();

        stasiunAsal = inputStasiun("Stasiun Asal          : ");
        stasiunTujuan = inputStasiun("Stasiun Tujuan        : ");

        jenisTiket = inputJenisTiket("Jenis Tiket           : ");

        System.out.print("Jumlah Tiket          : ");
        jumlahTiket = scan.nextByte();
        // ---------------------------------------------------------------------


        // ------------------ Perhitungan Jarak Tempuh -------------------------
        int jarak = jarakAntarStasiun(stasiunAsal, stasiunTujuan);
        // ---------------------------------------------------------------------


        // ------------------ Perhitungan biaya --------------------------------
        long totalBiaya = hitungBiaya(jarak, jenisTiket, jumlahTiket);
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
            if(kembalian >= 0){
                String idPesanan = generateID(stasiunAsal, stasiunTujuan, jenisTiket, nomorKTP, jumlahTiket);
                detailPesanan(idPesanan, namaPemesan,nomorKTP, alamat, nomorTelp, tanggalKeberangkatan,
                stasiunAsal, stasiunTujuan, jarak, jumlahTiket, totalBiaya, nominalPembayaran, kembalian);

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
                Percabangan di bawah ini digunakan untuk melakukan increment
                index pertama pada array riwayat. Selain itu, juga digunakan
                untuk mencegah error yang dikarenakan keterbatasan kapasitas
                array riwayat, serta melakukan algoritma FIFO (queue)
                */
                if(indexRiwayat==4) indexRiwayat = 0;
                else indexRiwayat++;

                // ---------------------------------------------------------------------

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
        // ---------------------------------------------------------------------
    }


    /** PRINT INFORMASI LAYANAN PEMESANAN TIKET
     * Mencetak jenis tiket serta stasiun yang tersedia
     */
    public static void infoPesanTiket(){
        System.out.println("\n" + border2);
        System.out.println("Silahkan masukkan data tiket");
        System.out.println(border2);

        System.out.println("\nList Stasiun Tersedia:");
        infoStasiun();

        System.out.println("\nJenis Tiket:");
        infoJenisTiket();
    }


    /** PRINT INFORMASI JENIS TIKET
     * Mencetak jenis tiket yang tersedia
     */
    public static void infoJenisTiket() {
        System.out.println("1. Hijau (Ekonomi)  : Rp.440/km");
        System.out.println("2. Kuning (premium) : Rp.710/km");
        System.out.println("3. Merah (Ekslusif) : Rp.1000/km");
    }


    /** PRINT INFORMASI STASIUN
     * Mencetak stasiun yang tersedia
     */
    public static void infoStasiun() {
        System.out.printf(" %-15s %-15s %-15s\n", "1. Malang", "4. Semarang", "7. Serang");
        System.out.printf(" %-15s %-15s %-15s\n", "2. Surabaya", "5. Bandung");
        System.out.printf(" %-15s %-15s\n", "3. Yogyakarta", "6. Jakarta");
    }


    /** INPUT STASIUN
     * Method ini digunakan untuk menerima stasiun inputan user dan
     * mevalidasinya. Method ini akan mengembalikan nama stasiun dari stasiun
     * user yang sudah diinputkan sebelumnya.
     */
    public static String inputStasiun(String inputLabel){
        String stasiun;
        do{
            System.out.print(inputLabel);
            stasiun = scan.nextLine().toLowerCase();

            // Melakukan pengecekkan input user
            if(stasiun.equals("malang")||stasiun.equals("surabaya")
            ||stasiun.equals("yogyakarta")||stasiun.equals("semarang")
            ||stasiun.equals("bandung")||stasiun.equals("jakarta")
            ||stasiun.equals("serang")) break;

            // Akan di-eksekusi jika stasiun yang diinputkan user salah atau tidak tersedia
            System.out.println("Stasiun tidak ditemukan! Silakan masukkan stasiun yang sesuai!");

        } while(true);

        return stasiun;
    }


    /** INPUT TIKET
     * Method ini digunakan untuk menerima jenis tiket yang diinginkan user dan
     * mevalidasinya. Method ini akan mengembalikan nama jenis tiket yang
     * diinputkan oleh user sebelumnya.
     */
    public static String inputJenisTiket(String inputLabel) {
        String jenisTiket;
        do{
            System.out.print(inputLabel);
            jenisTiket = scan.nextLine().toLowerCase();

            // Melakukan pengecekkan input user
            if(jenisTiket.equals("merah")||jenisTiket.equals("kuning")
            ||jenisTiket.equals("hijau")) break;

            // Akan di-eksekusi jika tiket yang diinputkan user salah atau tidak tersedia
            System.out.println("Silakan masukkan tiket yang sesuai!");

        } while(true);

        return jenisTiket;
    }


    /** JARAK MALANG KE PARAMETER STASIUN
     * Method ini membutuhkan parameter String stasiun. Method ini akan
     * mengembalikan nilai integer yang merupakan jarak antara stasiun pada
     * parameter dengan stasiun Kota Malang
     */
    public static int mlgToStasiun(String stasiun) {
        /*
        Konstanta-konstanta di bawah ini merupakan perhitungan jarak kota-kota
        yang ada menuju ke kota Malang.
        */
        final int MLGSBY = 100, MLGYGY = 400, MLGSMR = 450, MLGBDG = 800, MLGJKT = 900, MLGSRG = 1000;
        int jarak = 0;

        switch (stasiun) {
            case "surabaya":
                jarak = MLGSBY;
                break;
            case "yogyakarta":
                jarak = MLGYGY;
                break;
            case "semarang":
                jarak = MLGSMR;
                break;
            case "bandung":
                jarak = MLGBDG;
                break;
            case "jakarta":
                jarak = MLGJKT;
                break;
            case "serang":
                jarak = MLGSRG;
                break;
            default:
                jarak = 0;
        }

        return jarak;
    }


    /** JARAK ANTAR STASIUN
     * Method ini memiliki 2 parameter, yaitu stasiunAsal dan stasiunTujuan.
     * Method ini akan mengembalikan selisih jarak dari stasiunAsal dan
     * stasiunTujuan
     */
    public static int jarakAntarStasiun(String stasiunAsal, String stasiunTujuan) {
        int jarakStasiunAsal = mlgToStasiun(stasiunAsal);
        int jarakStasiunTujuan = mlgToStasiun(stasiunTujuan);
        int result = Math.abs(jarakStasiunTujuan-jarakStasiunAsal);

        return result;
    }


    /** HITUNG TOTAL BIAYA DARI TIKET
     * Method ini akan melakukan perhitungan total biaya yang harus dibayarkan
     * oleh user berdasarkan jarak antar stasiun, jenis tiket, dan jumlah tiket.
     */
    public static long hitungBiaya(int jarak, String jenisTiket, int jumlahTiket) {
        long totalBiaya;

        switch (jenisTiket) {
            case "merah":
                totalBiaya = 1000*jarak;
                break;
            case "kuning":
                totalBiaya = 710*jarak;
                break;
            case "hijau":
                totalBiaya = 440*jarak;
                break;
            default:
                totalBiaya = 0;
        }

        long hargaDiskon = diskon(jarak, jenisTiket, totalBiaya);

        return jumlahTiket*hargaDiskon;
    }


    /** HITUNG DISKON
     * Method ini menghitung total biaya dengan diskon tertentu. Diskon
     * didapatkan berdasarkan jarak dan jenisTiket yang dibeli oleh user
     */
    public static long diskon(int jarak, String jenisTiket, long biayaNormal) {
        long totalBiaya = biayaNormal;

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

        return totalBiaya;
    }


    /** KONVERSI NAMA STASIUN KE KODE STASIUN
     * Method ini melakukan konversi nama stasiun ke kode stasiun yang nantinya
     * akan digunakan pada ID tiket.
     */
    public static String getKodeStasiun(String stasiun) {
        String kodeStasiun;
        switch (stasiun) {
            case "surabaya":
                kodeStasiun = "SBY";
                break;
            case "yogyakarta":
                kodeStasiun = "YOG";
                break;
            case "semarang":
                kodeStasiun = "SMR";
                break;
            case "bandung":
                kodeStasiun = "BDG";
                break;
            case "jakarta":
                kodeStasiun = "JKT";
                break;
            case "serang":
                kodeStasiun = "SRG";
                break;
            default:
                kodeStasiun = "MLG";
        }
        return kodeStasiun;
    }


    /** KONVERSI JENIS TIKET KE KODE JENIS TIKET
     * Method ini melakukan konversi jenis tiket ke kode jenis tiket yang
     * nantinya akan digunakan pada ID tiket.
     */
    public static String getKodeJenisTiket(String jenisTiket) {
        String kodeJenisTiket;
        switch (jenisTiket) {
            case "merah":
                kodeJenisTiket = "03";
                break;
            case "kuning":
                kodeJenisTiket = "02";
                break;
            case "hijau":
                kodeJenisTiket = "01";
                break;
            default:
                kodeJenisTiket = null;
        }
        return kodeJenisTiket;
    }


    /** GENERATE ID TIKET
     * Method ini akan mengembalikan ID yang dibentuk berdasrakn stasiunAsal,
     * StasiunTujuan, jenisTiket, KTP, dan jumlahTiket.
     */
    public static String generateID(String stasiunAsal, String stasiunTujuan, String jenisTiket, String KTP, byte jumlahTiket) {
        // Kode Stasiun Asal
        String SA = getKodeStasiun(stasiunAsal);

        // Kode Stasiun Tujuan
        String ST = getKodeStasiun(stasiunTujuan);

        // Kode Jenis Tiket
        String JT = getKodeJenisTiket(jenisTiket);

        String bufferId = SA + "-" + ST + JT + KTP.substring(KTP.length()-3);
        String idPesanan = String.format("%s%03d",bufferId,jumlahTiket);

        return idPesanan;
    }


    /** DETAIL PESANAN
     * Method ini akan menampilkan detail pemesanan tiket jika pemesanan tiket
     * berhasil dilakukann.
     */
    public static void detailPesanan(String idPesanan, String namaPemesan,
    String nomorKTP, String alamat, String nomorTelp, String tanggalKeberangkatan,
    String stasiunAsal, String stasiunTujuan, int jarak, byte jumlahTiket,
    long totalBiaya, long nominalPembayaran, long kembalian) {

        System.out.println("\nPemesanan Tiket Berhasil !\n");
        System.out.println(border);
        System.out.println("         DETAIL PEMESANAN");
        System.out.println(border);
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
    }


    /** PRINT 5 RIWAYAT PEMESANAN TIKET TERAKHIR
     * Method ini menampilkan id dan tanggal keberangkatan dari tiket pesanan.
     * Kedua data tersebut diambil dari array riwayat.
     */
    public static void printRiwayatPesanan() {
        for (int i = 0; i < riwayat.length; i++) {

            // jika array riwayat kosong pada index tertentu, maka akan berhenti looping
            if(riwayat[i][0]==null) break;

            // Menampilkan data-data dalam array riwayat sesuai index pada looping
            System.out.printf("%d. %s: %s\n", (i+1),riwayat[i][0],riwayat[i][1]);
        }
    }


    /** EKSTRAK ID
     * Method ini akan mengembalikan array String yang berisikan stasiunAsal,
     * stasiunTujuan, jenisTiket, dan jumlahTiket
     */
    public static String[] ekstrakID(String idPesanan) {
        /*
        Variabel di bawah ini digunakan untuk menyimpan data-data pada
        id pesanan
        */
        String stasiunAsal, stasiunTujuan, jenisTiket, jumlahTiket;


        // ------------------ Mengambil data pada id pesanan -------------------
        switch (idPesanan.substring(0,3)) {
            case "SBY":
            stasiunAsal = "Surabaya";
                break;
            case "YOG":
            stasiunAsal = "Yogyakarta";
                break;
            case "SMR":
            stasiunAsal = "Semarang";
                break;
            case "BDG":
            stasiunAsal = "Bandung";
                break;
            case "JKT":
            stasiunAsal = "Jakarta";
                break;
            case "SRG":
            stasiunAsal = "Serang";
                break;
            case "MLG":
            stasiunAsal = "Malang";
                break;
            default:
            stasiunAsal = "Error";
        }

        switch (idPesanan.substring(4,7)) {
            case "SBY":
            stasiunTujuan = "Surabaya";
                break;
            case "YOG":
            stasiunTujuan = "Yogyakarta";
                break;
            case "SMR":
            stasiunTujuan = "Semarang";
                break;
            case "BDG":
            stasiunTujuan = "Bandung";
                break;
            case "JKT":
            stasiunTujuan = "Jakarta";
                break;
            case "SRG":
            stasiunTujuan = "Serang";
                break;
            case "MLG":
            stasiunTujuan = "Malang";
                break;
            default:
            stasiunTujuan = "Error";
        }

        switch (idPesanan.substring(7,9)) {
            case "03":
                jenisTiket = "Merah";
                break;
            case "02":
                jenisTiket = "Kuning";
                break;
            case "01":
                jenisTiket = "Hijau";
                break;
            default:
                jenisTiket = "Error";
        }

        jumlahTiket = idPesanan.substring(idPesanan.length()-3);
        // ---------------------------------------------------------------------

        String[] result = {stasiunAsal, stasiunTujuan, jenisTiket, jumlahTiket};
        return result;
    }


    /** CHECK TIKET PESANAN
     * Method ini akan meminta user untuk menginputkan id pesanan yang ingin dicek
     * dan nomor ktp sebagai parameter autentikasinya. Setelah itu, method ini
     * akan menampilkan detail pesanan yang dimaksudkan
     */
    public static void checkPesanan() {
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
                String idPesanan = riwayat[i][0];

                // Mengambil data dari ID
                String[] detailID = ekstrakID(idPesanan);
                String stasiunAsal = detailID[0];
                String stasiunTujuan = detailID[1];
                String jenisTiket = detailID[2];
                String jumlahTiket = detailID[3];

                // Mengambil data dari array riwayat
                String tanggalBerangkat = riwayat[i][1];
                String ktp = riwayat[i][2];
                String pemesan = riwayat[i][3];

                // Gabungan stasiun asal dan stasiun tujuan
                String rute = stasiunAsal + "-" + stasiunTujuan;


                // ------------------ Output data yang dicari/dicek --------------------
                System.out.println(border);
                System.out.println("         DETAIL PEMESANAN");
                System.out.println(border);
                System.out.println("ID Pesanan               : " + idPesanan);
                System.out.println("Nomor KTP                : " + ktp);
                System.out.println("Nama Pemesan             : " + pemesan);
                System.out.println("Rute                     : " + rute);
                System.out.println("Tanggal Keberangkatan    : " + tanggalBerangkat);
                System.out.println("Jenis Tiket              : " + jenisTiket);
                System.out.println("Jumlah Tiket             : " + jumlahTiket);
                System.out.println(border);
                // ---------------------------------------------------------------------

                break;
            }
            // Jika hingga indeks terakhir data pesanan tidak ditemukan, maka notFound true
            else if(i == riwayat.length-1) notFound = true;
        }

        // Print jika pesanan tidak ditemukan
        if(notFound) System.out.println("Pesanan tidak ditemukan");
    }

    public static void main(String[] args) {
        int pilihan = menuUtama();

        boolean controlFlow = true;
        do{
            switch (pilihan) {
                case 1:
                    System.out.println(border2);
                    System.out.println("Daftar Tiket");
                    System.out.println(border2);
                    daftarTiket();
                    break;
                case 2:
                    // ------------------ Menampilkan List Tiket ---------------------------
                    System.out.println(border2);
                    System.out.println("Jenis Tiket");
                    System.out.println(border2);
                    infoJenisTiket();
                    scan.nextLine();
                    break;
                    // ---------------------------------------------------------------------
                case 3:
                    // ------------------ Menampilkan Stasiun ------------------------------
                    System.out.println(border2);
                    System.out.println("List Stasiun Tersedia");
                    System.out.println(border2);
                    infoStasiun();
                    scan.nextLine();
                    break;
                    // ---------------------------------------------------------------------
                case 4:
                    // ------------------ Menampilkan Riwayat Pesanan ----------------------
                    System.out.println(border2);
                    System.out.println("Riwayat Pemesanan Tiket");
                    System.out.println(border2);
                    printRiwayatPesanan();
                    scan.nextLine();
                    break;
                    // ---------------------------------------------------------------------
                case 5:
                    // ------------------ Cek Pesanan --------------------------------------
                    System.out.println(border2);
                    System.out.println("Cek Tiket");
                    System.out.println(border2);
                    checkPesanan();
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