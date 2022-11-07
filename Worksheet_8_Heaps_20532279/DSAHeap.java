
/*****************************************************
 * Author  : Vishmi Kalansooriya
 * Date    : 03/10/2021
 * Purpose : To  implemented  the DSAHeap class.
 */
import java.util.*;

public class DSAHeap {
    // Private inner class
    private class DSAHeapEntry {
        // Fields of the heap entry class
        private int priority;
        private Object value;

        // Parameter cunstructor.
        public DSAHeapEntry(int inPriority, Object inValue) {
            priority = inPriority;
            value = inValue;
        }
        // Default cunstructor

        public DSAHeapEntry() {
            priority = 0;
            value = 0;
        }

        // getters
        public int getPriority() {
            return priority;
        }

        public Object getValue() {
            return value;
        }
        // setters

        public void setPriority(int inPriority) {
            priority = inPriority;
        }

        public void setValue(int inValue) {
            value = inValue;
        }
    }

    // Fields of the DSAHeap class

    private DSAHeapEntry[] heap;
    private int count;
    private int max;
    private static final double UPPER_BOUNDARY = 0.70;
    private static final double LOWER_BOUNDARY = 0.40;

    // Parameter cunstructor
    public DSAHeap(int maxSize) {
        count = 0;
        heap = new DSAHeapEntry[maxSize];
        max = maxSize;

    }

    /**********************************************************
     * Name : isEmpty Import : None Export : NOne Purpose : To check whether the
     * heap is empty.
     */
    public boolean isEmpty() {
        return count == -1; // If the count (Nothing is inside the array) return -1. (an invalid number)
    }

    /**********************************************************
     * Name : isFull Import : None Export : NOne Purpose : To check whether the heap
     * is full.
     */
    public boolean isFull() {
        return count == heap.length; // the total number of elements the heap can have its max size.
    }

    /**********************************************************
     * Name : add Import : inPriority (int) inValue (object) Export : None Purpose :
     * To add elements to the heap
     * 
     */
    public void add(int inPriority, Object inValue) {
        DSAHeapEntry newEntry = new DSAHeapEntry(inPriority, inValue);

        if (getLoadFactor(count) <= UPPER_BOUNDARY) {

            heap[count] = newEntry;
            heap = trickleUp(count); // trickles up until the parent node is greater than child. (until the element
            // fits the position)
            count++; // count increases to the amount of elemnts inserted.

        } else {
            resize(heap.length * 2);
            heap[count] = newEntry;
            heap = trickleUp(count);
            count++;
        }

    }

    /**********************************************************
     * Name : remove Export : value (Object) Import : None Purpose : To remove
     * elements from the heap
     * 
     */
    public Object remove() {
        DSAHeapEntry temp;
        if (isEmpty()) {
            throw new IllegalArgumentException(" Sorry ! the heap is empty"); // throw an exception if the heap is empty

        }
        temp = heap[0]; // Copy the root elemnt into a temperory variable.
        heap[0] = heap[count - 1]; // get the element at the very end to the root element.
        trickleDown(0); // Trickle down until the last elemnt which was copied to the root element finds
                        // its true position

        return temp.getValue();
    }

    /**********************************************************
     * Name : trickleUp Export : value (Object) Import : None Purpose : To trickle
     * up through the heap.
     * 
     */
    public DSAHeapEntry[] trickleUp(int currIdx) {
        int parentIdx = (currIdx - 1) / 2;
        if (currIdx > 0) {
            if (heap[currIdx].getPriority() > heap[parentIdx].getPriority()) {
                DSAHeapEntry temp = heap[parentIdx];
                heap[parentIdx] = heap[currIdx];
                heap[currIdx] = temp;
                trickleUp(parentIdx);
            }
        }
        return heap;
    }

    /**********************************************************
     * Name : trickleDown Import : currIdx (int) Export : None Purpose : To trickle
     * the elements in the binary tree until the they meet the correct position.
     * 
     */

    public DSAHeapEntry[] trickleDown(int currIdx) {
        int lChildIdx = currIdx * 2 + 1; // to get the left child
        int rChildIdx = lChildIdx + 1; // To get the right Child.
        int largeIdx = 0;

        if (lChildIdx < count) {

            largeIdx = lChildIdx;

            if (rChildIdx < count) {
                if (heap[lChildIdx].getPriority() < heap[rChildIdx].getPriority()) {
                    largeIdx = rChildIdx;
                }
            }
            if (heap[largeIdx].getPriority() > heap[currIdx].getPriority()) {
                swap(heap, largeIdx, currIdx);
                trickleDown(largeIdx);
            }
        }
        return heap;

    }

    /**********************************************************
     * Name : swap Import : pList (DSAHeapEntry array) pMinIdx (int) currIdx(int)
     * Purpose : swap to elements according to their priority.ss
     * 
     */

    public DSAHeapEntry[] swap(DSAHeapEntry[] pList, int pMinIdx, int currIdx) {
        DSAHeapEntry temp = pList[pMinIdx];
        pList[pMinIdx] = pList[currIdx];
        pList[currIdx] = temp;

        return pList;
    }

    /***************************************************************************************
     * Name : heapify Import : None Export : None Purpose : To organize the array
     * into a heap incrementally by expanding the heap by adding one element at a
     * time.
     */
    public DSAHeapEntry[] heapify() {
        for (int i = ((count / 2) - 1); i > 0; i--) //
        {
            trickleDown(i);
        }
        return heap;
    }

    /***************************************************************************************
     * Name : heapSort Import : None Export : None Purpose : To sort the heap (to
     * return items according to the priority order)
     */

    public DSAHeapEntry[] heapSort() {
        // heapify the heap first.
        heapify(); // The zeroth item will be sorted
        for (int i = count - 1; i > 0; i--) // from the number of items in the heap down to 0
        // Populating the targetted array in reverse order. ( Because of a max heap).
        {
            swap(heap, 0, i); // Swap the entries according to the priority order.
            trickleDown(0);
        }

        return heap;
    }

    /***************************************************************************************
     * Name : displayHeap Import : None Export : None Purpose : To display the heap.
     */

    public void displayHeap() {
        for (int i = 0; i < count; i++) {
            System.out.println(heap[i].getPriority() + " " + heap[i].getValue());
            System.out.println();
        }
    }

    private int findNextPrime(int startVal) {
        boolean isPrime = false;
        int i = 0;
        int primeVal = 0;
        int rootVal = 0;
        if (startVal % 2 == 0)

        {
            primeVal = startVal - 1; // Because even numbers can never be prime numbers.
        } else {
            primeVal = startVal;
        }

        isPrime = false;
        do {
            primeVal = primeVal + 2;
            i = 3;
            isPrime = true;
            rootVal = (int) Math.sqrt((double) primeVal); // Limiting the search upto the squareroot of the
                                                          // entered value.
            do {
                if (primeVal % i == 0) {
                    isPrime = false;
                } else {
                    i = i + 2; // skipping the even numbers in the search process.
                }
            } while ((i <= rootVal) && (isPrime));

        } while (!isPrime); // There is always a prime number to be found

        return primeVal;
    }

    /**********************************************************************
     * Name : getLoadFactor Import : count (int) size (int) , Export : loadFactor
     * (double) Purpose : to calculate the load factor to se how full the array is.
     *****************************************************************************************/

    public double getLoadFactor(int count) {
        // imports the number of existing elements in the array and the size of the
        // array
        double loadFactor = count / heap.length;
        return loadFactor;

    }

    /**********************************************************************
     * Name : resize Import : newSize (int) size (int) , Export : heap
     * (DSAHeapEntrt[]) Purpose : to resize the heap according to the load factor.
     *****************************************************************************************/

    private DSAHeapEntry[] resize(int newSize) {
        int nextPrime = findNextPrime(newSize);
        heap = Arrays.copyOf(heap, nextPrime);

        return heap;
    }

}