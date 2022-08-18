import java.util.*;
public class Tes{
    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        //Judul

        System.out.println("--------------------------------------");
        System.out.println("        FILKOM RAIL EXPRESS");
        System.out.println("   Program Pemesanan Tiket Kereta");
        System.out.println("--------------------------------------");
        System.out.println("\n"+"Silahkan Isi Data Diri Anda: ");
        //-------------------------------------------------------------------
//        System.out.print("\n"+"Nama: ");
//        String a;
//        a= in.nextLine();
//        System.out.print("Nomer KTP: ");
//        long b;
//        b= in.nextLong();
//        in.nextLine();
//        System.out.print("Alamat: ");
//        String c;
//        c=in.nextLine();
//        System.out.print("Nomer Telepon: ");
//        long d;
//        d= in.nextLong();
//        in.nextLine();
        //--------------------------------------------------------------------------
        System.out.println("--------------------------------------");
        System.out.println("List Stasiun yang tersedia");
        System.out.println("--------------------------------------");
        System.out.printf("%-17s%-17s%-17s\n","1. Malang","4. Semarang","7. Serang");
        System.out.printf("%-17s%-17s\n","2. Surabaya","5. Bandung");
        System.out.printf("%-17s%-17s\n","3. Jogjakarta","6. Jakarta");
        System.out.println("\n"+"--------------------------------------");
        System.out.println("Pilihan Jenis Tiket");
        System.out.println("--------------------------------------");
        System.out.print("||Hijau(Ekonomi)= Rp.4.400/10Km||"+"\t\t"+"||Kuning(Premium)= Rp.7.100/10Km||");
        System.out.print("\t\t"+"||Merah(Exclusive)= Rp.10.000/10Km||");
        System.out.print("\n\nStasiun Keberangkatan: ");
        String x= in.nextLine();
        System.out.print("Stasiun Tujuan: ");
        String y= in.nextLine();
        System.out.print("Jenis Tiket: ");
        String z= in.nextLine();
        System.out.print("Jumlah Tiket Yang Dibeli: ");
        int q= in.nextInt();
        //------------------------------------------------------------------------------------------------
        int jarak=0;
        int jarak2=0;
        int total;

        switch (x){
            case "Malang" :jarak+=0;
                break;
            case "Surabaya" :jarak+=100;
                break;
            case "Jogjakarta": jarak+=400;
                break;
            case "Semarang": jarak+=450;
                break;
            case "Bandung": jarak+=800;
                break;
            case "Jakarta": jarak+=900;
                break;
            case "Serang": jarak+=1000;
                break;
            default: {
            }
            switch (y){
                case "Malang" :jarak2+=0;
                    break;
                case "Surabaya" :jarak2+=100;
                    break;
                case "Jogjakarta": jarak2+=400;
                    break;
                case "Semarang": jarak2+=450;
                    break;
                case "Bandung": jarak2+=800;
                    break;
                case "Jakarta": jarak2+=900;
                    break;
                case "Serang": jarak2+=1000;
                    break;
                default: {
                }
            }
        }
        System.out.println(jarak2);
    }
}