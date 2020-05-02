import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T>{

    private Node head;
    private int count;
    private Node last;

    public MyLinkedList(){
        head=null;
        count=0;
        last=null;
    }

    public MyLinkedList(MyLinkedList<T> collection){
       head=collection.head;
        Node fl=head;
        while(fl!=null){
            last=fl;
            this.add((T)fl.getData());
            fl=fl.getNext();
        }
    }

    public void addFirst(T e){
        Node m=new Node(e);
        m.setNext(head);
        head=m;
        if(count==1)last=head;
    }

    public void add(T e){
        if(head==null){
            head=new Node(e);
            last=head;

        }
        else{
            last.setNext(new Node(e));
            last=last.getNext();
        }
        count++;
    }

    public void addAfter(T e,int index){
        Node  node=new Node(e);
        Node previous = getNode(index);
        if(previous!=null){
            node.setNext(previous.getNext());
            previous.setNext(node);
        }
    }

    private Node getNode(int index){
        Node fl=head;
        for(int i=0;i<index-1;i++){
            fl=fl.getNext();
        }
        return fl;
    }

    public T get(int index){
        return (T)getNode(index).getData();
    }

    public int getSize(){
        return count;
    }

    private Node getNode(T data){
        Node fl=head;
        while(fl!=null){
            if(fl.getData().equals(data))return fl;
            fl=fl.getNext();
        }
        return null;
    }

    private int getIndex(T data){
        int c=0;
        Node fl=head;
        while(fl!=null){
            if(fl.getData().equals(data))return c;
            fl=fl.getNext();
            c++;
        }
        return -1;

    }

    public boolean isEmpty(){
        if(count==0)return true;
        return false;
    }

    private void remove(int index){
        if(index>=count||index<0){
            throw new IndexOutOfBoundsException();
        }
        if(index==0){
            if(count>1){
                head=head.getNext();
                count--;
                return;
            }
            head=null;
            count--;
            return;
        }
        else{
            Node previous=getNode(index-1);
            if(index == count-1){
                previous.setNext(null);
                last=previous;
            }
            else previous.setNext(previous.getNext().getNext());
        }
        count--;
    }

    private void remove(T e){
        int ind=getIndex(e);
        if(ind!=-1)remove(ind);
    }

    public MyLinkedList<T> merge(MyLinkedList<T> list){
        MyLinkedList<T> list2=new MyLinkedList<T>(this);
        Node fl=list.head;
        while(fl!=null){
            list2.add((T)fl.getData());
            fl=fl.getNext();
        }
        return list2;
    }

    public T getLast(){
        return (T)last.getData();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class MLLIterator<T> implements Iterator<T> {
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

        public void remove(int index){
            remove(index);
        }

        public void remove(T e){
            remove(e);
        }
    }
}
