package cs2030.mystream;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.*;

public interface MyIntStream {
    static MyIntStream of(int... values) {
        return JXImplementor.of(values);
    }

    static MyIntStream range(int start, int end) {
        return JXImplementor.range(start, end);
    }


    static MyIntStream rangeClosed(int start, int end) {
        return JXImplementor.rangeClosed(start, end);
    }

    int count();

    int sum();

    OptionalDouble average();

    OptionalInt min();

    OptionalInt max();

    void forEach(IntConsumer action);

    MyIntStream peek(Consumer<? super Integer> c);

    MyIntStream limit(int maxSize);

    MyIntStream filter(IntPredicate predicate);

    MyIntStream map(IntUnaryOperator mapper);

    int reduce(int identity, IntBinaryOperator op);

    OptionalInt reduce(IntBinaryOperator op);

    MyIntStream distinct();



}
