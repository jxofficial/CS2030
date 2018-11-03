package cs2030.mystream;



import java.util.ArrayList;
import java.util.function.*;
import java.util.Scanner;
import java.util.Optional;

/*
Some generic (pun intended) learning points:
For static methods, you have to specify static <U> IFL<U> generate(Supplier<U> s);
When you input a supplier, it will automatically determine what type U is.
This is because if you use type T based off the class, there are many many classes like IFL<String> IFL<Double> etc and the method cant be static anymore

For normal methods, you already have a IFL<T> object calling, and you should return another IFL object of the same type. Hence the type will match the class.
Anyway if the class specifies T is type Integer, you probably want to return IFL<Integer> anyway.

eg
    IFL<String> iflString = new IFL<>();
    iflString.head = () -> 1;                           this is NOT allowed!
    iflString.head = () -> "only strings allowed";      ALLOWED!

*/


public class IFL<T> implements InfiniteList {
     Supplier<T> head;
     Supplier<IFL<T>> tail;
     Optional<Predicate<? super T>> iflPredicate = Optional.empty();
     static long count;



    public IFL(Supplier<T> s, Supplier<IFL<T>> tail) {
        head = s;
        this.tail = tail;
    }

    protected IFL() {

    }

    boolean isEmpty() {
        return false;
    }

    private boolean isHeadFiltered() {
        if ( iflPredicate.isPresent()) {
            return iflPredicate.get().test(head.get());
        }

        return true; // predicate is empty
    }

/*
    "s" are all individual supplier objects.
    generate() will create two new suppliers: s1 and s2.
    Hence, the tail of ifl_1 is s2, and the head of ifl_1 is s1. s2.get() will SUPPLY / CREATE a new ifl_2
    s1 = head. s1.get() will return something, eg maybe "Hello" or 123 or any object.
    s1.get() will SUPPLY / CREATE "Hello"

    ifl_2 has head s3 and tail s4.
    ifl_2 will have a head s3, which is the same as s, and will SUPPLY/ CREATE / RETURN "Hello"
    ifl_3 is CREATED / SUPPLIED when s4.get() is called.
*/

    public static <U> IFL<U> generate(Supplier<U> s) {
        IFL<U> lazyIFL = new IFL<>();
        lazyIFL.head = s;
        lazyIFL.tail = () -> generate(s);
        return lazyIFL;
    }

    public void forEach(Consumer<? super T> action) {
        IFL<T> updatedIFL = this;

        while ( !updatedIFL.isEmpty() ) {
            System.out.println(updatedIFL);
            if ( updatedIFL.isHeadFiltered() ) {
                action.accept( updatedIFL.head.get() );
            }

            /*
             you only get ifl2, ifl3, ifl4 when you .get(), because tail is merely a supplier obj
             tail (supplier obj) creates/returns a new ifl only when you .get()
            */
            new Scanner(System.in).nextLine();
            updatedIFL = updatedIFL.tail.get();
        }
    }

/*
    (() -> seed) is a s supplier object. head = s therefore head.get() returns seed
    R for result.
    UnaryOperator is a special Function where both the input are return types are the same
    Both uses the abstract method R apply (U u);

*/
    public static <U> IFL<U> iterate(U seed, UnaryOperator<U> f) {
        IFL<U> lazyIFL = new IFL<>();
        lazyIFL.head = () -> seed;
        lazyIFL.tail = () -> IFL.iterate(f.apply(seed), f);
        return lazyIFL;
    }

/*
    Does not evaluate during the filter, it passes on the predicate
    Only evaluates when forEach asks for it.
*/
    public IFL<T> filter(Predicate<? super T> predicate) {
        IFL<T> lazyIFL = new IFL<>();

        lazyIFL.iflPredicate = Optional.of(predicate);
        lazyIFL.head = this.head;
        lazyIFL.tail = () -> this.tail.get().filter(predicate); // IMPORTANT! the .filter transforms it from ifl_x to ifl_x_copy with a predicate

        return lazyIFL;
    }

    public IFL<T> limit (int n) {
        IFL<T> lazyIFL = new IFL<>();

        if (n > 1) {
            lazyIFL.head = this.head;
            lazyIFL.tail = () -> this.tail.get().limit(n - 1);
        } else {
            lazyIFL.head = this.head;
            lazyIFL.tail = () -> new EmptyIFL<>();
        }

        return lazyIFL;

    }

    public long count() {
        count = 0;
        IFL<T> updatedIFL = this;

        while ( !updatedIFL.isEmpty() ) {
            System.out.println(updatedIFL);
            count++;
            new Scanner(System.in).nextLine();
            updatedIFL = updatedIFL.tail.get();
        }

        System.out.println(count);
        return count;
    }


    public Object[] toArray() {
        ArrayList<IFL<T>> list = new ArrayList<>();

        IFL<T> updatedIFL = this;

        while ( !updatedIFL.isEmpty() ) {
            System.out.println(updatedIFL);
            list.add(updatedIFL);
            new Scanner(System.in).nextLine();
            updatedIFL = updatedIFL.tail.get();
        }

        return list.toArray();
    }

    public IFL<T> takeWhile(Predicate<? super T> predicate) {
        IFL<T> lazyIFL = new IFL<>();

        lazyIFL.iflPredicate = Optional.of(predicate);
        lazyIFL.head = this.head;

        if ( predicate.test(head.get()) ){
            lazyIFL.tail = () -> this.tail.get().takeWhile(predicate);
        } else {
            lazyIFL = new EmptyIFL<>();
        }

        return lazyIFL;
    }


    public T reduce(T identity, BinaryOperator<T> accumulator) {
        IFL<T> updatedIFL = this;
        T result = identity;

        while ( !updatedIFL.isEmpty() ) {
            System.out.println(updatedIFL);
            if ( updatedIFL.isHeadFiltered() ) {
                System.out.println("head " + updatedIFL.head.get());
                result = accumulator.apply(result, updatedIFL.head.get());
                System.out.println("result " + result );
            }

            new Scanner(System.in).nextLine();
            updatedIFL = updatedIFL.tail.get();
        }

        return result;
    }


    public Optional<T> reduce(BinaryOperator<T> accumulator) {

        return Optional.empty();
    }

    public static void main(String[] args) {

        IFL.iterate(1, i -> i + 1).limit(3).forEach(System.out::println);




    }


}
