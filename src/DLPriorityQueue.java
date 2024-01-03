    public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
            private DLinkedNode<T> front;
            private DLinkedNode<T> rear;
            private int count;
//  initializing the private instance variables front, rear, count.

            public DLPriorityQueue() {
                    front = null;
                    rear = null;
                    count = 0;
//      New queue is made with both front and rear null and count to zero.
            }


            public void add(T dataItem, double priority) {
                    DLinkedNode<T> newPriorityNode = new DLinkedNode<>(dataItem,priority);
//  using our newly created node check if front and rear are null is so the node is empty and the new queue equals front and rear.
                    if (front == null || rear == null) {
                            front = newPriorityNode;
                            rear = newPriorityNode;
//  check for our cases, if the priority is less than the front priority than we make this new node the front since
//  smallest are put at the front until largest at the back
                    } else if (priority < front.getPriority()) {
                            front.setPrev(newPriorityNode);
                            newPriorityNode.setNext(front);
                            newPriorityNode.setPrev(null);
                            front = newPriorityNode;
//  if the priority is greater than the rearmost node priority, we install it at the rear since this means it is the biggest in the Linked List
                    } else if (priority > rear.getPriority()) {
                            rear.setNext(newPriorityNode);
                            newPriorityNode.setPrev(rear);
                            newPriorityNode.setNext(null);
                            rear = newPriorityNode;
//  Otherwise, this means we install it somewhere in the middle as long as the node before curr is not null and if the priority is less than curr's.
                    }else{
                            DLinkedNode<T> curr = rear;
                            while (curr.getPrev()!= null && priority <= curr.getPriority()){
                                    curr = curr.getPrev();
                            }
                            newPriorityNode.setNext(curr.getNext());
                            newPriorityNode.setPrev(curr);
                            curr.getNext().setPrev(newPriorityNode);
                            curr.setNext(newPriorityNode);

                    }
//  increment count by 1 each time we add a node
                    count++;
            }


            public T removeMin() throws EmptyPriorityQueueException {
//  we set sNode to the front as sNode is always the smallest based on the rules of our sorted Linked List.
//  If queue is null throw new exception

                    if (isEmpty()) {
                            throw new EmptyPriorityQueueException("Not found");
                    }
//  we set sNode to the front as sNode is always the smallest based on the rules of our sorted Linked List.
                    T sNode = front.getDataItem();
//  as long as the front.getNext is not null we move the front up to the next node and remove the one before it which
//  is the smallest.
                    if (front.getNext() != null){
                            front.getNext().setPrev(null);
                    }
//  set the new front to the node ahead of front
                    front = front.getNext();
//  other case is if front is null then this would in turn mean rear is also null since the node is empty
                    if (front == null){
                            rear = null;
                    }
//  decrement count since we are removing a node
                    count--;
//  return sNode since we want to return the smallest node dataItem which is the one we removed which is at the front.
                    return sNode;
            }

            public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
                    DLinkedNode<T> current = rear;
                    int countConfirm = 0;
// we make a countConfirm variable to set either to 1 or 0 for it equalling the data item or not.
                    while (current != null) {
//  set our current to go through the Linked List but set it to rear since we want to go from rear to check the list
                            if (current.getDataItem().equals(dataItem)) {
                                    countConfirm = 1;
//  if we find current dataItem equal to our parameter dataItem then we set countConfirm to 1 which means yes.
                            }
//  We set current to current.getNext
                            current = current.getPrev();
                    }
                    if (countConfirm == 1) {
//  here we initialize the new curr as rear to iterate through the list again for our possible cases below but only if
// of where curr dataItem could be in the list and what we do for each different position it can be in.
                            DLinkedNode<T> curr = rear;
                            while (!curr.getDataItem().equals(dataItem)) {
                                    curr = curr.getPrev();
                            }
//  down below are the 3 different possible cases for where the curr which has the dataItem can be
                            if (curr == rear) {
//  if curr is rear, we make sure rear is not null then we make the new rear equal to the node before it
                                    rear = rear.getPrev();
                                    if (!(rear == null)) rear.setNext(null);
                            } else if (curr == front) {
//  if curr is front, we make sure front is not null then we make the new front equal to the that follows it
                                    front = front.getNext();
                                    if (!(front == null))front.setPrev(null);
                            } else {
//  If neither of the two cases above are true this means that it is somewhere in the middle.
//  Here we would need to integrate it into the linked list by changing the pointers as done down below.
                                    curr.getPrev().setNext(curr.getNext());
                                    curr.getNext().setPrev(curr.getPrev());
                            }
//  then we call the add method to add the node back in
//  add and increment the counter down. add will automatically add the newPriority into the list in order.

                            add(dataItem, newPriority);
                            count--;
//  If countConfirm is not equal to 1 this means the dataItem was not found therefore we throw the exception
                    } else {
                            throw new InvalidElementException("not in queue");
                    }

            }

        public boolean isEmpty() {
                return front == null && rear == null;
//      checks if the queue is empty, if s o return true if not return false
        }

        public int size() {
                return count;
//      return count which is either incrementing or decrementing which tells us the size of the queue
        }
        public String toString(){
                StringBuilder sAppend = new StringBuilder();
                DLinkedNode<T> curr = front;
                while (curr != null){
//  checks for null node if not then append the node.
                        sAppend.append(curr.getDataItem());
                        curr = curr.getNext();
// using a string builder turn our queue to toString by appending to a stringBuilder.
                }
                return sAppend.toString();
        }
        public DLinkedNode<T> getRear() {
                return rear;
//      return rear
        }
    }
