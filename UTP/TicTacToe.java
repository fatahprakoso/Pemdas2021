import java.util.Scanner;

class TicTacToe {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line1 = in.nextLine().replace(" ", "");
        String line2 = in.nextLine().replace(" ", "");
        String line3 = in.nextLine().replace(" ", "");
        String hasil = null;

        // horizontal
        if (line1.equals("XXX") || line1.equals("OOO")) {
            hasil = line1.charAt(0) == 'X'? "X menang" : "O menang";
        }else if (line2.equals("XXX") || line2.equals("OOO")) {
            hasil = line2.charAt(0) == 'X'? "X menang" : "O menang";
        }else if (line3.equals("XXX") || line3.equals("OOO")) {
            hasil = line3.charAt(0) == 'X'? "X menang" : "O menang";
        }else{
            // vertikal
            for(int i=0; i<3; i++){
                if(line1.charAt(i)==line2.charAt(i) && line1.charAt(i)==line3.charAt(i) ) {
                    hasil = line1.charAt(i) + " menang";
                    break;
                }
            }

            // Diagonal
            if(hasil==null){
                if(line1.charAt(0)==line2.charAt(1) && line1.charAt(0)==line3.charAt(2)){
                    hasil = line1.charAt(0) + " menang";
                }

                if(line1.charAt(2)==line2.charAt(1) && line1.charAt(2)==line3.charAt(0)){
                    hasil = line1.charAt(2) + " menang";
                }
            }
        }

        if(hasil==null) System.out.println("Draw");
        else System.out.println(hasil);
    }
}