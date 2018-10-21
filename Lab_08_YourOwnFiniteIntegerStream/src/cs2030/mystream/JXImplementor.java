package cs2030.mystream;

import java.util.*;
import java.util.function.*;

public class JXImplementor implements MyIntStream {
    List<Integer> numbers = new LinkedList<>();


    private JXImplementor() { }
    private JXImplementor(List<Integer> list) {
        this.numbers = list;
    }


    public static MyIntStream of(int... values) {

        JXImplementor stream = new JXImplementor();
        for (int i : values) {
            stream.numbers.add(i);
        }
        return stream;
    }

    public static MyIntStream range(int start, int end) {
        JXImplementor stream = new JXImplementor();
        for (int i = start; i < end; i++) {
            stream.numbers.add(i);
        }

        return stream;
    }

    public static MyIntStream rangeClosed(int start, int end) {
        JXImplementor stream = new JXImplementor();
        for (int i = start; i <= end; i++) {
            stream.numbers.add(i);
        }

        return stream;
    }

    @Override
    public int count() {

        if ( numbers.isEmpty() ) return 0;

        return numbers.size();
    }

    @Override
    public int sum() {
        int sum = 0;

        if (numbers.isEmpty() ) return sum;

        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }

        return sum;
    }

    @Override
    public OptionalDouble average() {
        if (numbers.isEmpty()) return OptionalDouble.empty();

        int sum = 0;

        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }

        return OptionalDouble.of(sum / numbers.size());

    }

    @Override
    public OptionalInt min() {
        if (numbers.isEmpty()) return OptionalInt.empty();

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) < min) min = numbers.get(i);
        }

        return OptionalInt.of(min);
    }

    @Override
    public OptionalInt max() {
        if (numbers.isEmpty()) return OptionalInt.empty();

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) > max) max = numbers.get(i);
        }

        return OptionalInt.of(max);
    }

    // action is a functional interface object that calls accept.
    @Override
    public void forEach(IntConsumer action) {
        for (int i = 0; i < numbers.size(); i++) {
            action.accept(numbers.get(i));
        }
    }

    @Override
    public MyIntStream peek(Consumer<? super Integer> c) {
        for (int i : this.numbers) {
            c.accept(i);
        }
        return new JXImplementor(this.numbers);
    }

    @Override
    public MyIntStream limit(int maxSize) {
        if (maxSize >= numbers.size()) {
            return this;
        } else {
            JXImplementor updatedStream = new JXImplementor();
            for (int i = 0; i < maxSize; i++) {
                updatedStream.numbers.add(this.numbers.get(i));
            }
            return updatedStream;
        }
    }

    @Override
    public MyIntStream filter(IntPredicate predicate) {
        JXImplementor filteredStream = new JXImplementor();
        for (int i : numbers) {
            if ( predicate.test(i) ) {
                filteredStream.numbers.add(i);
            }
        }
        return filteredStream;
    }

    @Override
    public MyIntStream map(IntUnaryOperator mapper) {
        JXImplementor mappedStream = new JXImplementor();
        for (int i : numbers) {
            mappedStream.numbers.add(mapper.applyAsInt(i));
        }

        return mappedStream;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {

        int result = identity;
        for (int i : numbers) {
            result = op.applyAsInt(result, i);
        }
        return result;
    }

    @Override
    public OptionalInt reduce(IntBinaryOperator op) {

        if (numbers.isEmpty()) return OptionalInt.empty();

        int result = 0;

        for (int i = 0; i < numbers.size(); i++) {
            result = op.applyAsInt(result, numbers.get(i));
        }

        return OptionalInt.of(result);
    }

    @Override
    public MyIntStream distinct() {
        Set<Integer> unique = new HashSet<>();
        JXImplementor distinct = new JXImplementor();

            unique.addAll(numbers);

            distinct.numbers.addAll(unique);

            return distinct;
    }


    @Override
    public String toString() {
        String output = "";
        for (int i : numbers) {
            output += i + ", ";
        }
        return output;
    }
}
