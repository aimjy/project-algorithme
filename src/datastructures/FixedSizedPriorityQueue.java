package datastructures;

import java.util.*;
/**
 * 
 * FixedSizePriorityQueue<T> is a class responsible for maintaining in order the 'elementsLeft' smallest items.
 * 
 * @author Leen De Baets
 *
 */
public class FixedSizedPriorityQueue extends PriorityQueue<ComparableSimpleEntry> {
    
    /**
     * Determines the remaining free spots in this queue
     */
    private int elementsLeft;

    /**
     * Constructs a new FixedSizedPriorityQueue with a capacity of elementsLeft
     * @param elementsLeft
     */
    public FixedSizedPriorityQueue(int elementsLeft) {
        super();
        this.elementsLeft = elementsLeft;
    }

    /**
     * Overrides the add of a PriorityQueue
     * 
     * - If capacity has not been reached -> delegate the add to the PriorityQueue and decrease the capacity
     * - Else -> Check with the item with the highest distance (should be at the front of the priority queue). If item to be added is smaller
     *           then remove top from queue and add new item. Else do not add item.
     * 
     * @param e The ComparableSimpleEntry contains as key a double (representing e.g., the distance) and as value an object (e.g., a movie). 
     * The key should be used to determine if an element is added or not when the capacity of the fixed sized priority queue is reached.
     * @return
     */
    @Override
    public boolean add(ComparableSimpleEntry e) {
    	if(this.elementsLeft > 0) {
            this.elementsLeft--;
            this.offer(e);
        }
        else {
            if(e.compareTo(this.peek()) > 0) {
                this.poll();
                this.offer(e);
            }
            else return false;
        }
        return true;
    }


    @Override
    public String toString() {
            //TODO: Delete exception and implement here
            // Do this in such way that the first element printed is the most important one (e.g. the movie with the smallest distances (key))
            String terug = "";
            ArrayList<ComparableSimpleEntry> lijst = new ArrayList<>();
            int lengte = this.size();
            for(int j = 0; j<lengte; j++) {
                lijst.add(this.poll());
            }
            for(int i = lijst.size()-1; i>=0; i--) {
                this.offer(lijst.get(i));
                terug = terug.concat(lijst.get(i).getValue().toString());
                terug = terug.concat("\n");
            }
            return terug;
        } 
    }
