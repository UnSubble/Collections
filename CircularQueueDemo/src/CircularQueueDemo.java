import java.util.Collection;
import java.util.Iterator;

public class CircularQueueDemo <E> implements Iterable<E>{
	
	private LinkedListDemo<E> list;
	private int count;
	private int capacity;
	private int index;
	private int removeIndex;
	
	public CircularQueueDemo() {
		this(100);
	}
	
	public CircularQueueDemo(int capacity) {
		index = -1;
		removeIndex = 0;
		count = 0;
		list = new LinkedListDemo<>();
		this.capacity = capacity;
	}
	
	public CircularQueueDemo(int capacity, @SuppressWarnings("unchecked") E... values) {
		this(capacity);
		if (values.length > capacity)
			throw new IndexOutOfBoundsException();
		addAll(values);
		count = values.length;
	}
	
	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}
	
	public void add(E val) {
		if (list.size() == capacity)
			throw new IndexOutOfBoundsException("The queue is full");
		index = Math.min(list.size(), index + 1) % capacity;
		removeIndex = index % (list.size() == 0 ? 1 : list.size());
		list.add(index, val);
		count = Math.min(count + 1, capacity);
	}
	
	public void addAll(E[] val) {
		for (E element : val) 
			add(element);
	}
	
	@SuppressWarnings("unchecked")
	public void addAll(Collection<E> val) {
		if (count + val.size() > capacity)
			throw new IndexOutOfBoundsException();
		addAll((E[]) val.toArray());
	}

	public void offer(E val) {
		add(val);
	}
	
	public E peek() {
		return list.get(removeIndex);
	}
	
	public E poll() {
		E temp = list.get(removeIndex);
		list.remove(removeIndex);
		count--;
		return temp;
	}
	
	public boolean contains(E val) {
		return list.contains(val);
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public void clear() {
		list.clear();
		count = 0;
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
}
