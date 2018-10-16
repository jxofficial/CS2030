import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
        public static void main (String[]args){


            Scanner sc = new Scanner(System.in);
            List<Integer> digits = new ArrayList<>();

            while (sc.hasNext()) {
                digits.add(sc.nextInt());
            }

            System.out.println("Number of occurrences: " + countRepeats(digits.toArray(new Integer[digits.size()])) );


        }

        public static long countRepeats(Integer[] array) {
            return IntStream.rangeClosed(1, array.length - 1)
                            .filter(i -> array[i] == array[i -1] &&
                                    (i == array.length - 1 ||
                                    array[i] != array[i+1]))
                            .count();

        }


}
