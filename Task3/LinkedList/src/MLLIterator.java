import java.util.Iterator;

public class MLLIterator<T> implements Iterator<T> {
    private Node obj;
    private int count;
    public int cursor;
    public MLLIterator(Node first,int count){
        this.obj=first;
        this.count=count;
        cursor=0;
    }
    @Override
    public boolean hasNext() {
        return count>cursor;
    }

    @Override
    public T next() {
        Node previous=obj;
        obj=obj.getNext();
        return (T)previous.getData();
    }
}
