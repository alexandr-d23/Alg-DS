import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

class AVLTree<T extends Comparable> {
    private Node<T> root;
    private int size;

    public AVLTree(){
        size = 0;
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
        System.out.println();
        for (Node<T> node : collection ){
            if(node!=null) {
                System.out.print(node.getData().toString() +"  ");
                if(node.getLeftChild()!=null)children.add(node.getLeftChild());
                if(node.getRightChild()!=null)children.add(node.getRightChild());
            }
        }
        if(children.size()>0) {
            breadth(children);
        }
    }


    public void insert(T data){
        root = insert(root,data);
    }

    public void delete(T data){
        delete(null,root,data,true);
    }

    public int getSize(){
        return size;
    }

    private int calculateHeight(Node node){
        if(node == null)return 0;
        node.height = Math.max(calculateHeight(node.leftChild),calculateHeight(node.rightChild))+1;
        return node.height;
    }

    private Node insert(Node node, T data) {

        if (node == null)
        {
            size++;
            return (new Node(data));
        }

        if (data.compareTo(node.data) <= 0) {
            node.leftChild = insert(node.leftChild, data);
        }
        else if (data.compareTo(node.data) > 0) {
            node.rightChild = insert(node.rightChild, data);
        }
        else {
            return node;
        }
        node.height = 1 + Math.max(height(node.leftChild), height(node.rightChild));
        return insertingBalance(node,data);
    }


    private Node rightRotation(Node node) {
        Node left = node.leftChild;
        Node rightOfLeft = left.rightChild;
        left.rightChild = node;
        node.leftChild = rightOfLeft;
        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
        left.height = Math.max(height(left.leftChild), height(left.rightChild)) + 1;
        return left;
    }

    private Node leftRotation(Node node) {
        Node right = node.rightChild;
        Node leftOfRight = right.leftChild;
        right.leftChild = node;
        node.rightChild = leftOfRight;
        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
        right.height = Math.max(height(right.leftChild), height(right.rightChild)) + 1;
        return right;
    }

    private int getBalance(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.leftChild) - height(N.rightChild);
    }


    private Node insertingBalance(Node node, T data){
        int balance = getBalance(node);
        if (balance > 1 && data.compareTo(node.leftChild.data) <= 0)
        {
            return rightRotation(node);
        }
        if (balance < -1 && data.compareTo(node.rightChild.data)>0)
        {
            return leftRotation(node);
        }
        if (balance > 1 && data.compareTo(node.leftChild.data)>0) {
            node.leftChild = leftRotation(node.leftChild);
            return rightRotation(node);
        }
        if (balance < -1 && data.compareTo(node.rightChild.data)<=0) {
            node.rightChild = rightRotation(node.rightChild);
            return leftRotation(node);
        }
        return node;
    }


    private Node deletingBalance(Node root){
        // calculateHeight(this.root);
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.leftChild) > 0)
        {
            return rightRotation(root);
        }
        if (balance > 1 && getBalance(root.leftChild) < 0)
        {
            root.leftChild = leftRotation(root.leftChild);
            return rightRotation(root);
        }
        if (balance < -1 && getBalance(root.rightChild) < 0)
        {
            return leftRotation(root);
        }
        if (balance < -1 && getBalance(root.rightChild) > 0)
        {
            root.rightChild = rightRotation(root.rightChild);
            return leftRotation(root);
        }

        return root;
    }





    public Node minNode(Node node)
    {
        Node current = node;
        while (current.leftChild != null)
        {
            current = current.leftChild;
        }
        return current;
    }

    private Node delete(Node parent,Node root, T data,boolean isLeft)
    {
        if (root == null)
        {
            return null;
        }
        if (data.compareTo(root.data)<0)
        {
            root.leftChild = delete(root,root.leftChild, data,true);
        }
        else if (data.compareTo(root.data)>0)
        {
            root.rightChild = delete(root,root.rightChild, data,false);
        }
        else
        {
            if(root.leftChild != null && root.rightChild !=null){
                Node next = minNode(root.rightChild);
                root.data = next.data;
                root.rightChild = delete(root,root.rightChild, (T) next.data,false);
            }
            else {
             if (root.leftChild != null) {
                    if (parent == null) {
                        this.root = root.leftChild;
                        root = root.leftChild;
                    }
                    if (isLeft) {
                        parent.leftChild = root.leftChild;
                        root = root.leftChild;
                    } else {
                        parent.rightChild = root.leftChild;
                        root = root.leftChild;
                    }
                    size--;
                } else if (root.rightChild != null) {
                    if (parent == null) {
                        this.root = root.rightChild;
                        root = root.rightChild;
                    }
                    if (isLeft) {
                        parent.leftChild = root.rightChild;
                        root = root.rightChild;
                    } else {
                        parent.rightChild = root.rightChild;
                        root = root.rightChild;
                    }
                    size--;
                } else {
                    if (parent == null) {
                        this.root = null;
                        root = null;
                    } else if (isLeft) {
                        parent.leftChild = null;
                        root = null;
                    } else {
                        parent.rightChild = null;
                        root = null;
                    }
                    size--;
                }
            }
        }
        if (root == null)
        {
            return null;
        }
        root.height = Math.max(height(root.leftChild), height(root.rightChild)) + 1;

        return deletingBalance(root);
    }


    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private class Node<T>{

        private Node leftChild;
        private Node rightChild;
        private T data;
        private int height;
        private Node(T data){
            this.data = data;
            height = 1;
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
            return  Objects.equals(getLeftChild(), node.getLeftChild()) &&
                    Objects.equals(getRightChild(), node.getRightChild()) &&
                    Objects.equals(getData(), node.getData());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getLeftChild(), getRightChild(), getData());
        }

        @Override
        public String toString() {
            return "Node{" +
                    " leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    ", data=" + data +
                    '}';
        }
    }
}

