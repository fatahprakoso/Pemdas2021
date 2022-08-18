import java.util.Scanner;

public class Array {
    public static void main(String[] args){
        // tipe-data [] namaVariabel = {data, data, data};   // inisialisasi
        // tipe-data [] namaVariabel = new tipe-data[panjang-array];  // deklarasi
        Scanner in = new Scanner(System.in);

        // inisialisasi
        String [] namaMahasiswa = {"Fatah", "Yesver", "Noverdi"};
        boolean [] arrBoolean = {true, false, true, false};
        namaMahasiswa[1] = "Orang";

        // deklarasi
        String [] namaMahasiswa2 = new String[5];
        namaMahasiswa2[2] = "Fatah";  // index dimulai dari 0

        // System.out.println(Arrays.toString(namaMahasiswa));

        int [][] matrix22 = {{1,2},{2,3}}; //inisialisasi

        int [][] matrix33 = new int[3][3]; //deklarasi
        matrix33[0][1] = 200;
        matrix33[1][1] = 300;
        matrix33[2][1] = 100;
        matrix33[1][2] = 500;
        matrix33[0][0] = 600;
        // System.out.println(Arrays.deepToString(matrix33));

        for (int i = 0; i < matrix33.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix33[0].length; j++) {
                System.out.print(matrix33[i][j] + ", ");
            }
            System.out.println("]");
        }

        int [][] matrix3D = new int [3][3];
    }
}
