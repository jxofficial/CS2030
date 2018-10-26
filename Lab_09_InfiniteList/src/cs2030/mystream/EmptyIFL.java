package cs2030.mystream;

public class EmptyIFL<T> extends IFL<T> {

    public EmptyIFL() { }

    @Override
    boolean isEmpty() {

        return true;
    }
}
