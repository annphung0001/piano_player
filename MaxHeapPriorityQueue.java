package piano_player;

import java.util.Scanner;

import piano_player.pq_heap.SortedArrayPriorityQueue.ArrEntry;

public class MaxHeapPriorityQueue<K extends Comparable, V> extends SortedArrayPriorityQueue {
    ArrEntry<K, V>[] heapPQ;
    int size = 0;
    int defaultsize = 1000;

    // create a constructor
    public MaxHeapPriorityQueue() {
        heapPQ = new ArrEntry[defaultsize];
    }
    
    public ArrEntry<K, V>[] getHeapPQ() {
        return heapPQ;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean isEmpty() {
        return (getSize()==0);
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
    protected void swap(ArrEntry<K, V>[] heapPQ, int i, int j) {
        ArrEntry temp = heapPQ[i];
        heapPQ[i] = heapPQ[j];
        heapPQ[j] = temp;
    }

    // Moves the entry at index i higher, if necessary, to restore the heap
    // property.
    protected void upHeap(int i) {
        while (i > 0) {
            // continue until reaching root (or break statement)
            int p = parent(i);
            if (compare(heapPQ[i], heapPQ[p]) <= 0) {
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
            // although right may be greater
            if (hasRight(i) && compare(heapPQ[left(i)], heapPQ[right(i)]) < 0) {
                smallChildIndex = right(i);
                // set right child is the greater
            }
            if (compare(heapPQ[smallChildIndex], heapPQ[i]) <= 0) {
                break;
                // heap property has been restored
            } else {
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

    public ArrEntry<K, V> removeMax() {
        ArrEntry<K, V> arrEntry = heapPQ[0];
        heapPQ[0] = heapPQ[size - 1];
        downHeap(0);
        size--;
        return arrEntry;
    }

    public void printHeap(String space, int p) {
        if (p < size) {
            printHeap(space + "         ", left(p));
            System.out.println(space + heapPQ[p]);
            printHeap(space + "         ", right(p));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MaxHeapPriorityQueue<Integer, String> test = new MaxHeapPriorityQueue<>();
        test.insert(78, "Wendy");
        test.insert(56, "Ken");
        test.insert(100, "Anna");
        test.insert(89, "Peter");

        System.out.println("The heap I create:");
        test.printHeap("      ", 0);
        System.out.println("------------------------------------------");

        System.out.println("Enter your input here");
        for (int i = 1; i < 4; i++) {
            System.out.printf("Enter the student%d name: ", i);
            String name = sc.next();
            System.out.printf("Enter the student%d score: ", i);
            int score = sc.nextInt();
            test.insert(score, name);
            System.out.println("------------------------------------------");
            System.out.println("Insert:");
            test.printHeap("      ", 0);
        }

        while (test.size != 0) {
            test.removeMax();
            System.out.println("------------------------------------------");
            System.out.println("Remove min:");
            test.printHeap("      ", 0);
        }
        sc.close();
    }

}
