public class Element {
    protected Object object;
    protected int amount;

    public Element(Object object,int amount){
        this.object=object;
        this.amount=amount;
    }

    public Object getObject() {
        return object;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void increaseAmount(){
        amount++;
    }
    public void decreaseAmount(){
        amount--;
    }

}
