import java.util.Collection;
import java.util.Iterator;

public class StackDemo <E> implements Iterable<E>{
	
	private LinkedListDemo<E> list;
	private int count;
	
	public StackDemo() {
		count = 0;
		list = new LinkedListDemo<>();
	}
	
	public StackDemo(@SuppressWarnings("unchecked") E... values) {
		this();
		addAll(values);
		count = values.length;
	}
	
	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}
	
	public void add(E val) {
		list.add(val);
		count++;
	}
	
	public void addAll(E[] val) {
		for (E element : val) 
			add(element);
	}
	
	@SuppressWarnings("unchecked")
	public void addAll(Collection<E> val) {
		addAll((E[]) val.toArray());
	}

	public void push(E val) {
		add(val);
	}
	
	public E peek() {
		return list.getLastElement();
	}
	
	public E pop() {
		E temp = list.getLastElement();
		list.removeLast();
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
