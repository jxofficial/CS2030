package cs2030.mystream;

import java.util.Optional;
import java.util.function.*;

public interface InfiniteList<T> {

    static <U> IFL<U> generate(Supplier<U> s) {
        return IFL.generate(s);
    }

    static <U> IFL<U> iterate(U seed, UnaryOperator<U> f) {
        return IFL.iterate(seed, f);
    }

    IFL<T> takeWhile(Predicate<? super T> predicate);
    <R> IFL<R> map(Function<? super T, ? extends R> mapper);
    IFL<T> filter(Predicate<? super T> predicate);
    IFL<T> limit (long n);

    long count();
    void forEach(Consumer<? super T> action);
    Object[] toArray();
    T reduce(T identity, BinaryOperator<T> accumulator);
    Optional<T> reduce(BinaryOperator<T> accumulator);



}
