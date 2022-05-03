package piano_player.pq_heap;

public class SortedArrayPriorityQueue<K extends Comparable, V> implements PriorityQueueInterface {
    public static class ArrEntry<K, V> implements Entry<K, V>{
        private K key;
        private V value;

        public ArrEntry(K k, V v) {
            key = k;
            value = v;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
        
    }

    protected ArrEntry<K, V>[] array = new ArrEntry[1000];
    protected int size = 0;
    protected int defaultsize = 1000;

    // a constructor 
    public SortedArrayPriorityQueue() {
        array = new ArrEntry[defaultsize];
    }

    public boolean isEmpty() {
        return size == 0;
    }
    // insert an new entry
    public void insert(Entry entry) {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (compare((ArrEntry<K, V>)entry, array[i]) < 0) {
                    for (int j = size-1; j >= i; j--) {
                        array[j+1] = array[j];
                    }
                    array[i] = (ArrEntry<K, V>) entry;
                    break;
                } else {
                    array[size] = (ArrEntry<K, V>) entry;
                }
            }
            size++;
        } else {
            array[0] = (ArrEntry<K, V>) entry;
            size++;
        }
    }
    // insert an entry with 2 parameters
    public void insert(Object key, Object value) {
        ArrEntry arrEntry = new ArrEntry((K)key, (V)value);
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (compare(arrEntry, array[i]) < 0) {
                    for (int j = size-1; j >= i; j--) {
                        array[j+1] = array[j];
                    }
                    array[i] = arrEntry;
                    break;
                } else {
                    array[size] = arrEntry;
                }
            }
            size++;
        } else {
            array[0] = arrEntry;
            size++;
        }
    }
    // return min entry and remove it
    public Entry<K,V> removeMin() {
        if (isEmpty())
            return null;
        else {
            Entry<K,V> temp = (Entry<K,V>) array[0];
            for (int i = 0; i < size-1; i++) {
                array[i] = array[i+1];
            }
            size--;
            return temp;
        }
    }
    // return min entry but not remove it
    public Entry<K,V> min() {
        if (isEmpty())
            return null;
        else {
            return array[0];
        }
    }

    //  Method for comparing two entries according to key
    protected int compare(ArrEntry<K,V> a, ArrEntry<K,V> b) {
        return a.getKey().compareTo(b.getKey());
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }
    
}
