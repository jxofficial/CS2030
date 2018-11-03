package cs2030.mystream;


public class Main {

    public static void main(String[] args) {
	IFL.iterate(0, x -> x + 1).forEach(System.out::println);
    }
}
