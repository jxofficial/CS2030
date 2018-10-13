import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        // collect all the divisors using n % factor = 0
        // add up all the divisors

        Scanner sc = new Scanner(System.in);
        System.out.println( isPerfect( sc.nextInt() ) );


    }

    public static boolean isPerfect(int n) {
        return IntStream.rangeClosed(1, n-1)
                 .filter(factor -> n % factor == 0)
                 .sum() == n;
    }


}
