package piano_player.pq_heap;

import piano_player.pq_heap.SortedArrayPriorityQueue.ArrEntry;

public class MinHeapPriorityQueue<K extends Comparable, V> extends SortedArrayPriorityQueue {

    ArrEntry<K, V>[] heapPQ;
    int size = 0;
    int defaultsize = 1000;

    public ArrEntry<K, V>[] getHeapPQ() {
        return heapPQ;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return size == 0;
    }
    
    // create a constructor
    public MinHeapPriorityQueue() {
        heapPQ = new ArrEntry[defaultsize];
    }

    protected int parent(int i) {
        return (i - 1) / 2;
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * i + 2;
    }

    protected boolean hasLeft(int i) {
        return left(i) < size;
    }

    protected boolean hasRight(int i) {
        return right(i) < size;
    }

    // Exchanges the entries at indices i and j of the array list. ∗/
    protected void swap(ArrEntry<K, V>[] heapPQ,int i, int j) {
        ArrEntry temp = heapPQ[i];
        heapPQ[i] = heapPQ[j];
        heapPQ[j] = temp;
    }

    // Moves the entry at index i higher, if necessary, to restore the heap property.
    protected void upHeap(int i) {
        while (i > 0) {
            // continue until reaching root (or break statement)
            int p = parent(i);
            if (compare(heapPQ[i], heapPQ[p]) >= 0) {
                break;
                // heap property verified
            } else {
                swap(heapPQ, i, p);
                // continue from the parent's location
                i = p; 
            }
        }
    }

    protected void downHeap(int i) {
        while (hasLeft(i)) {
            // continue to bottom (or break statement)
            int smallChildIndex = left(i);
            // although right may be smaller
            if (hasRight(i) && compare(heapPQ[left(i)], heapPQ[right(i)]) > 0) {
                smallChildIndex = right(i); 
                // right child is smaller
            }
            if (compare(heapPQ[smallChildIndex], heapPQ[i]) >= 0) {
                break;
                // heap property has been restored
            }else {
                swap(heapPQ, i, smallChildIndex);
                i = smallChildIndex;
                // continue at position of the child
            }
        }
    }

    public ArrEntry<K, V> insert(K key, V value) {
        ArrEntry<K, V> arrEntry = new ArrEntry<>(key, value);
        heapPQ[size] = arrEntry;
        upHeap(size);
        size++;
        return arrEntry;
    }

    public ArrEntry<K, V> removeMin() {
        ArrEntry<K, V> arrEntry = heapPQ[0];
        heapPQ[0] = heapPQ[size-1];
        downHeap(0);
        size--;
        return arrEntry;
    }
    
    public void printHeap(String space, int p) {
        if(p < size) {
            printHeap(space+ "         ", left(p));
            System.out.println(space + heapPQ[p]);
            printHeap(space+ "         ", right(p));
        }
    }
}