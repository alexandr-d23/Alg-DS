import java.util.Arrays;

public class Queue<T> {
    private Object[] arr;
    private int end;
    private int begin;
    
    public Queue(){
        arr = new Object[100];
        end = 0;
        begin = 0;
    }
    
    public void add(T e){
        if(end >= arr.length){
            arr = Arrays.copyOf(arr, arr.length*2);
        }
        arr[end] = e;
        end++;
    }
    
    public T pop(){
        if(begin == end)throw new IndexOutOfBoundsException("Queue is empty");
        begin++;
         if(begin == end){
            int ind = begin-1;
            begin = 0;
            end = 0;
            return (T)arr[ind];
        }
        return (T)arr[begin-1];


    }
    
    public T peek(){
        if(begin == end)throw new IndexOutOfBoundsException("Queue is empty");
        return (T)arr[begin];
    }

    public int size(){
        return end-begin;
    }



}
