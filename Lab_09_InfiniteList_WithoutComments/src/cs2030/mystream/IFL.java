package cs2030.mystream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.*;
import java.util.Scanner;
import java.util.Optional;


public class IFL<T> implements InfiniteList<T> {
    Supplier<T> head;
    Supplier<IFL<T>> tail;
    Optional<Predicate<? super T>> iflPredicate = Optional.empty();
    private static long count = 0;
    private static ArrayList<Long> limits = new ArrayList<>();



    public IFL(Supplier<T> s, Supplier<IFL<T>> tail) {
        head = s;
        this.tail = tail;
    }

    protected IFL() {

    }

    protected boolean isEmpty() {
        return false;
    }

    private boolean isHeadFiltered() {
        if ( iflPredicate.isPresent()) {
            return iflPredicate.get().test(head.get());
        }

        return true; // predicate is Optional.empty();
    }

    static <U> IFL<U> generate(Supplier<U> s) {
        IFL<U> ifl_x = new IFL<>();
        ifl_x.head = s;
        ifl_x.tail = () -> generate(s);
        return ifl_x;
    }


    static <U> IFL<U> iterate(U seed, UnaryOperator<U> f) {
        IFL<U> ifl_x = new IFL<>();
        ifl_x.head = () -> seed;
        ifl_x.tail = () -> IFL.iterate(f.apply(seed), f);
        return ifl_x;
    }



    public IFL<T> takeWhile(Predicate<? super T> predicate) {
        IFL<T> ifl_x_copy = new IFL<>();

        ifl_x_copy.iflPredicate = Optional.of(predicate);
        ifl_x_copy.head = this.head;

        limits.clear();
        return ifl_x_copy;
    }

    public <R> IFL<R> map(Function<? super T, ? extends R> mapper) {
        IFL<R> ifl_x_mapped = new IFL<>();
        ifl_x_mapped.head = () -> mapper.apply(this.head.get());
        ifl_x_mapped.tail = () -> this.tail.get().map(mapper);

        return ifl_x_mapped;
    }

    public IFL<T> filter(Predicate<? super T> predicate) {
        IFL<T> ifl_x_copy = new IFL<>();

        ifl_x_copy.iflPredicate = Optional.of(predicate);
        ifl_x_copy.head = this.head;
        ifl_x_copy.tail = () -> this.tail.get().filter(predicate); // IMPORTANT! the .filter transforms it from ifl_x to ifl_x_copy with a predicate

        return ifl_x_copy;
    }

    public IFL<T> limit (long n) {
        limits.add(n);

        int index = limits.indexOf(Collections.min(limits));
        long minLimit = limits.get(index);

        IFL<T> ifl_x_copy = new IFL<>();

        if (minLimit > 1) {
            ifl_x_copy.head = this.head;
            ifl_x_copy.tail = () -> this.tail.get().limit(minLimit - 1);
        } else if (minLimit == 1) {
            ifl_x_copy.head = this.head;
            ifl_x_copy.tail = () -> new EmptyIFL<>();
        } else {
            ifl_x_copy = new EmptyIFL<>();
        }

        return ifl_x_copy;

    }


    public long count() {
        count = 0;
        IFL<T> updatedIFL = this;

        while ( !updatedIFL.isEmpty() ) {
            count++;
            updatedIFL = updatedIFL.tail.get();
        }

        limits.clear();
        return count;
    }

    public void forEach(Consumer<? super T> action) {
        IFL<T> updatedIFL = this;

        while ( !updatedIFL.isEmpty() ) {
            if ( updatedIFL.isHeadFiltered() ) {
                action.accept( updatedIFL.head.get() );
            }

            updatedIFL = updatedIFL.tail.get();
        }
        limits.clear();
    }

    public Object[] toArray() {
        ArrayList<IFL<T>> list = new ArrayList<>();

        IFL<T> updatedIFL = this;

        while ( !updatedIFL.isEmpty() ) {
            list.add(updatedIFL);
            updatedIFL = updatedIFL.tail.get();
        }

        limits.clear();
        return list.toArray();
    }


    public T reduce(T identity, BinaryOperator<T> accumulator) {
        IFL<T> updatedIFL = this;
        T result = identity;

        while ( !updatedIFL.isEmpty() ) {

            if ( updatedIFL.isHeadFiltered() ) {
                result = accumulator.apply(result, updatedIFL.head.get());
            }

            updatedIFL = updatedIFL.tail.get();
        }

        limits.clear();
        return result;
    }



    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        IFL<T> updatedIFL = this;
        Optional<T> result = Optional.empty();
        boolean foundAny = false;

        while (!updatedIFL.isEmpty()) {
            if (updatedIFL.isHeadFiltered()) {
                if (!foundAny) {
                    foundAny = true;
                    result = Optional.of(updatedIFL.head.get());
                } else {
                    result = Optional.of(accumulator.apply(result.get(), updatedIFL.head.get()));
                }
            }

            updatedIFL = updatedIFL.tail.get();
        }

        limits.clear();
        return result;
    }

    @Override
    public String toString() {
        return this.head.get().toString();
    }

    public static void main(String[] args) {
        System.out.println( IFL.iterate(0, x -> x + 1).limit(4).takeWhile(x -> x < 2).toArray() );

    }

}
