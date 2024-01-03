public class FindShortestPath {
    public static void main(String[] args) {
        try {
//  create the try and catch through assigning the new dungeon file, queue (hexagon class), current and add the current
//  with 0 priority along with marking current as enqueued.
            if (args.length < 1) throw new Exception("No input file specified");
            String dungeonFileName = args[0];
            Dungeon newDungeon = new Dungeon(dungeonFileName);

            DLPriorityQueue<Hexagon> newQueue = new DLPriorityQueue<>();
            Hexagon current = newDungeon.getStart();
            newQueue.add(current, 0);
            current.markEnqueued();
            while (!newQueue.isEmpty() && !current.isExit()) {
//  while the queue is not empty and not the exit continue
                current = newQueue.removeMin();
                current.markDequeued();
//  remove the min, and mark current as dequeued
                if (current.isExit()) {
                    break;
//  if current is exit break out of the while loop
                }
                boolean dragonOrNot = false;
                for (int i = 0; i < 6; i++) {
//  go through all the neighbour nodes to see if they are dragons or if the current cell is also a dragon, if so
//  dragonOrNot is true
                    if (current.getNeighbour(i) != null) {
                        if (current.isDragon() || current.getNeighbour(i).isDragon()) {
                            dragonOrNot = true;
                        }
                    }
                }
                if (!dragonOrNot) {
//  As long as there is no neighbouring dragon nodes, proceed.
                    for (int i = 0; i < 6; i++) {
                        if (current.getNeighbour(i) != null && !current.getNeighbour(i).isMarkedDequeued() && !current.getNeighbour(i).isWall()) {
                            int D = 1 + current.getDistanceToStart();
//  create D as the distance from current node to start plus 1
                            if (current.getNeighbour(i).getDistanceToStart() > D) {
                                current.getNeighbour(i).setDistanceToStart(D);
                                current.getNeighbour(i).setPredecessor(current);
//  set the predecessor equal to our current node
                                if (current.getNeighbour(i).isMarkedEnqueued()) {
                                    double distance = current.getNeighbour(i).getDistanceToStart() + current.getNeighbour(i).getDistanceToExit(newDungeon);
                                    newQueue.updatePriority(current.getNeighbour(i), distance);
//  if the current neighbour is enqueued then we update priority with the neighbour cell and the two distances combined
//  (neighbour to start distance and the neighbour to exit distance.
                                }
                                if (!current.getNeighbour(i).isMarkedEnqueued()) {
                                    double distance = current.getNeighbour(i).getDistanceToStart() + current.getNeighbour(i).getDistanceToExit(newDungeon);
                                    newQueue.add(current.getNeighbour(i), distance);
//  Otherwise we add the neighbour if it is not enqueued
                                }
                            }
                        }
                    }
                }
            }
//  down below are the exceptions --> InvalidDungeonCharacterException, EmptyPriorityQueueException,
//  InvalidNeighbourIndexException, InvalidElementException and Exception e.
        } catch (InvalidDungeonCharacterException e) {
            System.out.println("InvalidDungeonCharacterException");
        } catch (EmptyPriorityQueueException e) {
            System.out.println("EmptyPriorityQueueException");

        } catch (InvalidNeighbourIndexException e) {
            System.out.println("InvalidNeighbourIndexException");

        } catch (InvalidElementException e) {
            System.out.println("InvalidElementException");
        } catch (Exception e) {
            System.out.println("Exception e");
        }
    }
}