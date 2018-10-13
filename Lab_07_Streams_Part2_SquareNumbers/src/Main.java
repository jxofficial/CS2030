import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(isSquare(sc.nextInt()));
    }

    static boolean isSquare(int n) {
        return IntStream.of((int) Math.sqrt(n))
                .filter(p -> (p * p) == n)
                .findFirst() != OptionalInt.empty();
    }
}
