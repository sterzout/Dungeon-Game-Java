public class DLinkedNode<T> {
    private T dataItem;
    private double priority;
    private DLinkedNode<T> next;
    private DLinkedNode<T> prev;
//    initializing private instance variables dataItem, priority, next and prev for the linkedlist

    public DLinkedNode (T data, double prio){
        this.dataItem = data;
        this.priority = prio;
//DLinkedNode constructor initializing parameters
    }
    public DLinkedNode(){
        this(null,0 );
    }
//DLinkedNode constructor with empty
    public double getPriority() {
        return priority;
//Getter for Priority
    }
    public DLinkedNode<T> getNext() {
        return next;
    }
//Getter for next

    public void setNext(DLinkedNode<T> next) {
        this.next = next;
        //   Getter for next

    }

    public DLinkedNode<T> getPrev() {
        return prev;
    }
    //Getter for prev


    public void setPrev(DLinkedNode<T> prev) {
        this.prev = prev;
        //   Getter for prev

    }


    public void setPriority(double priority) {
        this.priority = priority;
        //   Setter for priority

    }

    public T getDataItem() {
        return dataItem;
    }
//   Getter for dataItem

    public void setDataItem(T dataItem) {
        this.dataItem = dataItem;
        //   Setter for dataItem

    }
}
