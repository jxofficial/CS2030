package cs2030.mystream;

public class EmptyIFL<T> extends IFL<T> {

    public EmptyIFL() { }

    @Override
    protected boolean isEmpty() {
        return true;
    }
}
