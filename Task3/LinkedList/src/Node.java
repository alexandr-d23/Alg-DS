public class Node {

    private Object data;
    private Node next;

    public Node(Object data){
        this.data=data;
        next=null;
    }

    public Node getNext(){
        return next;
    }

    public Object getData(){
        return data;
    }

    public void setNext(Node next){
        this.next=next;
    }

}
