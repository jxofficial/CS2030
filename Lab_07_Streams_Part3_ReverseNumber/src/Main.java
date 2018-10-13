import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        System.out.println( reverse(sc.nextInt()) );
    }

    static int reverse(int n) {
        return IntStream.iterate(n, x -> x / 10)
                .limit(20)
                .filter(x -> x != 0)
                .map(x -> x % 10)
                .reduce(0, (x,y) -> (x * 10 + y));
    }






}
