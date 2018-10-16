import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        while (sc.hasNext()) {
            list.add(sc.nextInt());
        }

        System.out.println("Variance: " + variance(unbox(list))) ;
    }

    public static OptionalDouble variance(int[] numbers) {

        if (numbers.length <= 1) {
            return OptionalDouble.empty();
        }

        double average = IntStream.of(numbers)
                                  .average()
                                  .getAsDouble();



         double d = IntStream.of(numbers)
                            .mapToDouble(x -> (x - average) * (x - average))
                            .sum() / (numbers.length - 1);

         return OptionalDouble.of(d);


    }



    public static int[] unbox(ArrayList<Integer> numbers) {

        return Stream.of(numbers.toArray())
                .mapToInt(x -> (Integer) x )
                .toArray();
    }
}
