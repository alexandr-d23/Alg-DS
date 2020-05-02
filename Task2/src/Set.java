import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Set<T> {
    private List<T> list;

    public Set(){
        list=new ArrayList<>();
    }

    public void add(T o){
        if(!has(o)){
            list.add(o);
        }
    }

    public boolean has(T o){
        int size=list.size();
        for(int i=0;i<size;i++){
            if(list.get(i).equals(o))return true;
        }
        return false;
    }

    public boolean isEmpty(){
        return(list.size()>0);
    }
    public int size(){
        return list.size();
    }

}
