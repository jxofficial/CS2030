import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();


        System.out.print("Prime factors of " + input + " are: ");
        primeFactors(input).forEachOrdered(x -> System.out.print(x + " "));

    }



    static IntStream factors(int x) {
        int[] arrayFactors = IntStream.rangeClosed(2, x)
                .filter(factor -> x % factor == 0)
                .toArray();

        return IntStream.of(arrayFactors);

    }

    static IntStream primeFactors(int x) {
        return factors(x).filter(Main::isPrime);

    }


    static boolean isPrime(int n) {
        return IntStream.range(2, n)
                .noneMatch(x -> n % x == 0);
    }

}
