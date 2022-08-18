import java.util.Scanner;

public class Tugas {
    public static void main(String[] args) {

        // Deklarasi & inisialisasi variabel
        Scanner in = new Scanner(System.in);
        String nama, ktp, alamat, noTelepon, tglBerangkat, asal, tujuan;
        byte jumlahTiket;
        long nominalPembayaran;
        String border = "======================================================";

        // Header
        System.out.println(border);
        System.out.println("               FILKOM RAIL EXPRESS");
        System.out.println("          Program Pemesanan Tiket Kereta");
        System.out.println(border);
        System.out.println("Silakan masukkan data berikut:");

        // Input
        System.out.print("Nama                 :");
        nama = in.nextLine();
        System.out.print("KTP                  :");
        ktp = in.nextLine();
        System.out.print("Alamat               :");
        alamat = in.nextLine();
        System.out.print("Nomor Telepon        :");
        noTelepon = in.nextLine();
        System.out.print("Tanggal Keberangkatan:");
        tglBerangkat = in.nextLine();
        System.out.print("Stasiun Asal         :");
        asal = in.nextLine();
        System.out.print("Stasiun Tujuan       :");
        tujuan = in.nextLine();
        System.out.print("Jumlah Tiket         :");
        jumlahTiket = in.nextByte();
        System.out.print("Nominal Pembayaran   :");
        nominalPembayaran = in.nextLong();
        System.out.println(border);

        // Proses
        long totalTagihan = jumlahTiket*50000;
        long kembalian = nominalPembayaran-totalTagihan;

        // Output
        if(kembalian<0){
            System.out.println("\nUANG TIDAK MENCUKUPI!!!\n");
            System.out.println(border);
        } else{
            System.out.println("\nPemesanan Berhasi!\n");
            System.out.println(border);
            System.out.println("           DETAIL PEMESANAN");
            System.out.println(border);

            System.out.println("Nama                 :" + nama);
            System.out.println("KTP                  :" + ktp);
            System.out.println("Alamat               :" + alamat);
            System.out.println("Nomor Telepon        :" + noTelepon);
            System.out.println("Tanggal Keberangkatan:" + tglBerangkat);
            System.out.println("Rute                 :" + asal + " - " + tujuan);
            System.out.println("Jumlah Tiket         :" + jumlahTiket);
            System.out.println("Total Tagihan        :" + totalTagihan);
            System.out.println("Nominal Pembayaran   :" + nominalPembayaran);
            System.out.println("Kembalian            :" + kembalian);

            System.out.println(border);

        }

        in.close();
    }
}