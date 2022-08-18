import java.util.Scanner;

public class Formating{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a = scan.nextInt();

        System.out.printf("%05d\n", a);
    }
}