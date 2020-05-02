import java.util.Arrays;

public class Stack<T> {
    private Object[] arr;
    private int count;

    public Stack(){
        arr=new Object[100];
        count=0;
    }

    public void add(T o){
        if(count>=arr.length){
            arr= Arrays.copyOf(arr, arr.length*2);
        }
        arr[count]=o;
        count++;
    }

    public T pop(){
        if(count<0)throw new IndexOutOfBoundsException("Stack is empty");
        count--;
        return (T)arr[count];

    }
    public T peek(){
        if(count<0)throw new IndexOutOfBoundsException("Stack is empty");
        return (T)(arr[count-1]);
    }

    public int size(){
        return count;
    }

}
