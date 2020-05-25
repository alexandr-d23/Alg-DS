import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class BinaryTree<T extends Comparable> {
    private Node<T> root;
    private int size;

    public BinaryTree(){
        size = 0;
    }

    public void add(T data){
        if(root == null){
            root = new Node<>(null,data);
            size = 1;
        }
        else add(root,data);
    }

    private void add(Node node,T data){
        if(data.compareTo(node.getData())>0){
            if(node.getRightChild()==null){
                node.setRightChild(new Node(node,data));
                size++;
            }
            else {
                add(node.getRightChild(),data);
            }
        }
        else{
            if(node.getLeftChild()==null){
                node.setLeftChild(new Node(node,data));
                size++;
            }
            else {
                add(node.getLeftChild(),data);
            }
        }
    }

    public void BFC(){
        System.out.println("Breadth first search:");
        List<Node<T>> list = new ArrayList<>();
        list.add(root);
        breadth(list);
    }

    public void DFS(){
        System.out.println("Depth first search:");
        preOrder(root);
    }


    private void preOrder(Node node){
        if(node!= null){
            System.out.println(node.data.toString());
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }

    private void breadth(Collection<Node<T>> collection){
        List<Node<T>> children = new ArrayList<>();
        for (Node<T> node : collection ){
            if(node!=null) {
                System.out.println(node.getData().toString());
                if(node.getLeftChild()!=null)children.add(node.getLeftChild());
                if(node.getRightChild()!=null)children.add(node.getRightChild());
            }
        }
        if(children.size()>0) breadth(children);
    }

    public int getSize(){
        return size;
    }

    private class Node<T>{

        private Node parent;
        private Node leftChild;
        private Node rightChild;
        private T data;

        private Node(Node parent, T data) {
            this.parent = parent;
            this.data = data;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(parent, node.parent) &&
                    Objects.equals(getLeftChild(), node.getLeftChild()) &&
                    Objects.equals(getRightChild(), node.getRightChild()) &&
                    Objects.equals(getData(), node.getData());
        }

        @Override
        public int hashCode() {
            return Objects.hash(parent, getLeftChild(), getRightChild(), getData());
        }

        @Override
        public String toString() {
            return "Node{" +
                    "parent=" + parent +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    ", data=" + data +
                    '}';
        }
    }
}
