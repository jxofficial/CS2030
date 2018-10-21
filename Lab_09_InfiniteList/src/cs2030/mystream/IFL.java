package cs2030.mystream;

import java.util.function.Supplier;

public class IFL<T> implements InfiniteList {
     Supplier<T> head;
     Supplier<IFL<T>> tail;



    private IFL(Supplier<T> s, Supplier<IFL<T>> tail) {
        head = s;
        this.tail = tail;
    }

    // "s" are all individual supplier objects.
    // generate() will create two new suppliers: s1 and s2.
    // Hence, the tail of ifl_1 is s2, and the head of ifl_1 is s1. s2.get() will SUPPLY / CREATE a new ifl_2
    // s1 = head. s1.get() will return something, eg maybe "Hello" or 123 or any object.
    // s1.get() will SUPPLY / CREATE "Hello"

    // ifl_2 has head s3 and tail s4.
    // ifl_2 will have a head s3, which is the same as s, and will SUPPLY/ CREATE / RETURN "Hello"
    // ifl_3 is CREATED / SUPPLIED when s4.get() is called.

    public static <U> IFL<U> generate(Supplier<U> s) {
        return new IFL<U>(s, () -> generate(s));
    }

        void forEachPrint() {
        IFL<T> list;
        list = this;

        while () {
            System.out.println(list.head.get());
            // you only get ifl2, ifl3, ifl4 when you .get(), because tail is merely a supplier obj
            // tail (supplier obj) creates/returns the item only when you .get()
            list = list.tail.get();
        }

    }


    public static void main(String[] args) {
        IFL.generate(() -> 1).forEachPrint();
    }


}
