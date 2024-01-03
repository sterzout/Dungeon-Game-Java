public class Mytester{
    public static void main(String[] args) {
        DLPriorityQueue<String> queue = new DLPriorityQueue<>();

        // Add some elements
        queue.add("B", 1.0);
        queue.add("A", 0.5);
        queue.add("C", 2.0);
        queue.add("E", 4.0);
        queue.add("D", 3.0);
        System.out.println(queue);
        System.out.println("Size: " + queue.size());
        System.out.println("Front: " + queue.getRear().getDataItem());

        // Update priority
        queue.updatePriority("B", 0.3);
        System.out.println("Front after priority update: " + queue.getRear().getDataItem());

        // Remove minimum
        String min = queue.removeMin();
        System.out.println("Removed minimum: " + min);
        System.out.println("Size after removal: " + queue.size());
        System.out.println("Front after removal: " + queue.getRear().getDataItem());

        // Try removing from empty queue
        try {
            queue.removeMin();
        } catch (EmptyPriorityQueueException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        // Try updating priority of nonexistent element
        try {
            queue.updatePriority("F", 1.0);
        } catch (InvalidElementException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
