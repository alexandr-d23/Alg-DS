import java.util.Arrays;
import java.util.Collection;

public class MultiSet<T>{
    protected Element[] arr;
    protected int cursor;

    public MultiSet() {
        this.arr = new Element[1000];
        cursor=0;
    }

    public void add(T o){
        int ind=getIndex(o);
        if(ind == -1){
            if(cursor==arr.length)arr= Arrays.copyOf(arr,arr.length*2);
            arr[cursor]=new Element(o,1);
            cursor++;
        }
        else{
            arr[ind].increaseAmount();
            cursor++;
        }
    }

    public void add(T o, int occurrences){
        int ind=getIndex(o);
        if(ind == -1){
            if(cursor==arr.length)arr= Arrays.copyOf(arr,arr.length*2);
            arr[cursor]=new Element(o,occurrences);
        }
        else{
            arr[ind].setAmount(arr[ind].getAmount()+occurrences);
            cursor++;
        }
    }

    public boolean has(T o){
        for(int i=0;i<cursor;i++){
            if(arr[i].object.equals(o))return true;
        }
        return false;
    }

    public int count(T o){
        if(getIndex(o)!=0)return arr[getIndex(o)].amount;
        return 0;
    }

    public void remove(T o){
        for(int i=0;i<cursor;i++){
            if(arr[i].object.equals(o)){
                arr[i].decreaseAmount();
                if(arr[i].getAmount()==0){
                    cursor--;
                    arr[i]=arr[cursor];

                }
            }
        }
    }

    public void remove(T o ,int occurrences){
        for(int i=0;i<cursor;i++){
            if(arr[i].object.equals(o)){
                arr[i].setAmount(arr[i].getAmount()-occurrences);
                if(arr[i].getAmount()==0){
                    cursor--;
                    arr[i]=arr[cursor];

                }
            }
        }
    }



    private int getIndex(T o){
        for(int i=0;i<cursor;i++){
            if(arr[i].object.equals(o))return i;
        }
        return -1;
    }

}
