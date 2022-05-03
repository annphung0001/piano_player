package piano_player.pq_heap;

public interface PriorityQueueInterface<K, V> {
    public int size();
    public boolean isEmpty();
    public void insert(Entry<K, V> entry); 
    public void insert(K key, V value); 
    public Entry<K, V> removeMin();
    public Entry<K, V> min();
}
