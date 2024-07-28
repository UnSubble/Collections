import java.util.Collection;
import java.util.Iterator;

public class LinkedListDemo<E> implements Iterable<E>{
	private class Node {
		Node prev;
		E val;
		Node next;
		Node(E val) {
			this.val = val;
		}
	}
	
	private class ListIterator implements Iterator<E> {
		private Node curr;
		
		public ListIterator(Node first) {
			this.curr = first;
		}
		
		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public E next() {
			Node temp = curr;
			curr = curr.next;
			return temp.val;
		}
		
	}
	
	private int count;
	private Node first;
	private Node last;
	
	public LinkedListDemo() {
		count = 0;
	}
	
	public LinkedListDemo(@SuppressWarnings("unchecked") E... val) {
		addAll(val);
		count = val.length;
	}
	
	public void setFirstElement(E val) {
		Node temp = new Node(val);
		if (count != 0)
			temp.next = first.next;
		else {	
			last = temp;
			count++;
		}
		first = temp;
	}
	
	public void setLastElement(E val) {
		Node temp = new Node(val);		
		if (count != 0) 
			temp.prev = last.prev;
		else {
			first = temp;
			count++;
		}
		last = temp;
	}
	
	private void setCountAndLast(Node curr, int c) {
		count += c;
		last = curr;
	}
	
	public void addAll(E[] val) {
		if (val == null || val.length == 0) return;
		setFirstElement(val[0]);
		Node curr = first;
		for (int i = 1; i < val.length; i++) {
			Node temp = new Node(val[i]);
			curr.next = temp;
			temp.prev = curr;
			curr = curr.next;
		}
		setCountAndLast(curr, val.length - 1);
		
	}
	
	public void addAll(Collection<E> col) {
		@SuppressWarnings("unchecked")
		E[] vals = (E[]) col.stream().toArray();
		addAll(vals);
	}
	
	public void add(E val) {
		if (count == 0) setFirstElement(val);
		else {
			Node temp = new Node(val);
			last.next = temp;
			temp.prev = last;
			setCountAndLast(last.next, 1);
		}	
	}
	
	public void addFirst(E val) {
		if (count == 0) {
			setFirstElement(val);
			return;
		}
		Node temp = new Node(val);
		if (first.prev != null)
			first.prev = temp;
		temp.next = first;
		first = temp;
		count++;
	}
	
	public void addLast(E val) {
		Node temp = new Node(val);
		last.next = temp;
		temp.prev = last;
		setCountAndLast(temp, 1);
	}
	
	public void add(int index, E val) {
		if (index == 0) {
			addFirst(val);
			return;
		} else if (index == count) {
			addLast(val);
			return;
		}
		Node node = first;
		while (index-- > 0) {
			node = node.next;
		}
		Node toAdd = new Node(val);
		Node temp = node.prev;
		node.prev.next = toAdd;
		node.prev = toAdd;
		toAdd.next = node;
		toAdd.prev = temp;
	}
	
	public void removeFirst() {
		Node temp = first;
		first = first.next;
		temp.next = null;
		count--;
	}
	
	public void removeLast() {
		Node temp = last;
		last = last.prev;
		last.next = null;
		temp.prev = null;
		count--;
	}
	
	public void remove(int index) {
		Node node = first;
		if (index == 0) {
			removeFirst();
			return;
		} else if (index == count - 1) {
			removeLast();
			return;
		} else if (index < count / 2) {
			while (index-- > 0) {
				node = node.next;
			}
			
		} else {
			node = last;
			index = count - index - 1;
			while (index-- > 0) {
				node = node.prev;
			}
		}
		if (node.prev != null)
			node.prev.next = node.next;
		if (node.next != null)
			node.next.prev = node.prev;
		node.next = null;
		node.prev = null;
		count--;
	}
	
	public E get(int index) {
		if (index > count || index < 0) throw new IndexOutOfBoundsException();
		if (index < count / 2) {
			Node curr = first;
			for (int i = 0; i < index; i++) {
				curr = curr.next;
			}
			return curr.val;
		}
		Node curr = last;
		for (int i = 0; i < count - index - 1; i++) {
			curr = curr.prev;
		}
		return curr.val;
	}
	
	public int indexOf(E val) {
		Node curr = first;
		int index = 0;
		while (curr != null) {
			if (curr.val.equals(val)) return index;
			curr = curr.next;
			index++;
		}
		return -1;
	}
	
	public int lastIndexOf(E val) {
		Node curr = last;
		int index = count - 1;
		while (curr != null) {
			if (curr.val.equals(val)) return index;
			curr = curr.prev;
			index--;
		}
		return -1;
	}
	
	public boolean contains(E val) {
		Node node = first;
		while (node != null) {
			if (node.val.equals(val))
				return true;
			node = node.next;
		}
		return false;
	}
	
	public int size() {
		return count;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public E getFirstElement() {
		return first.val;
	}
	
	public E getLastElement() {
		return last.val;
	}
	
	public void clear() {
		while (first != null) {
			first.prev = null;
			Node temp = first;
			first = first.next;
			temp.next = null;
		}
		last = null;
		count = 0;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ListIterator(first);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node curr = first;
		while (curr != null) {
			sb.append(curr.val);
			curr = curr.next;
			if (curr != null) sb.append(",");
		}
		return sb.append("]").toString();
	}
}
