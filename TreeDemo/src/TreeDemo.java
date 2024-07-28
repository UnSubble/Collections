import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TreeDemo <T extends Comparable<T>> implements Iterable<T>{
	private Node root;

	private class Node implements Comparable<T>{
		public T val;
		public Node left;
		public Node right;
		
		public Node(T val) {
			this.val = val;
			left = null;
			right = null;
		}

		@Override
		public int compareTo(T o) {
			return val.compareTo(o);
		}
	}
	
	public void insert(T val) {
		if (root == null) {
			root =  new Node(val);
			return;
		}
		insert(root, val);
	}
	
	public void insertAll(Collection<T> vals) {
		for (T val : vals)
			insert(val);
	}
	
	public void insertAll(T[] vals) {
		for (T val : vals)
			insert(val);
	}
	
	private void insert(Node node, T val) {
		if (node.compareTo(val) > 0) {
			if (node.left == null) node.left = new Node(val);
			else insert(node.left, val);
		} else if (node.compareTo(val) < 0) {
			if (node.right == null) node.right = new Node(val);
			else insert(node.right, val);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public void remove(T val) {
		Node target = searchNode(val);
		if (target != null) {
			Node node = target.right == null ? target.left : target.right;
			if (target.right != null) {
				while (node.left != null) 
					node = node.left;
				remove(node, target);
			} else if (target.left != null) {
				while (node.right != null) 
					node = node.right;
				remove(node, target);
			} else if (target == root)
				root = null;
			else {
				Node parent = getParent(root, target);
				if (parent.left == target)
					parent.left = null;
				else
					parent.right = null;
			}
				
		}
			
	}
	
	private void remove(Node node, Node target) {
		Node parent = getParent(root, node);
		if (parent.left == node)
			parent.left = node.left;
		else
			parent.right = node.right;
		target.val = node.val;		
	}
	
	private Node getParent(Node node, Node target) {
		if (node.compareTo(target.val) > 0) {
			if (node.left == target)
				return node;
			return getParent(node.left, target);
		} else {
			if (node.right == target)
				return node;
			return getParent(node.right, target);
		}	
	}
	
	public int getDepth() {
		return root == null ? 0 : (getDepth(root) + 1);
	}
	
	private int getDepth(Node node) {
		int l = 0;
		int r = 0;
		if (node.left != null) 
			l = getDepth(node.left) + 1;
		if (node.right != null)
			r = getDepth(node.right) + 1;
		return Math.max(l, r);
	}
	
	public int getDepthOfValue(T val) {
		return search(val) ? getDepthOfValue(root, val, 1) : -1;
	}
	
	private int getDepthOfValue(Node node, T val, int d) {
		if (node.compareTo(val) > 0)
			return getDepthOfValue(node.left, val, d + 1);
		else if (node.compareTo(val) < 0)
			return getDepthOfValue(node.right, val, d + 1);
		return d;
	}
	
	public boolean search(T val) {
		return root == null ? false : (searchNode(val) != null);
	}
	
	private Node searchNode(T val) {
		return searchLeft(root, val);
	}
	
	private Node searchRight(Node node, T val) {
		if (node == null) 
			return null;
		if (node.compareTo(val) > 0)
			return searchLeft(node.left, val);
		if (node.compareTo(val) < 0)
			return searchRight(node.right, val);
		return node;
	}
	
	private Node searchLeft(Node node, T val) {
		if (node == null) 
			return null;
		if (node.compareTo(val) > 0)
			return searchLeft(node.left, val);
		if (node.compareTo(val) < 0)
			return searchRight(node.right, val);
		return node;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	@Override
	public Iterator<T> iterator() {
		return toList().iterator();
	}
	
	public List<T> preOrder() {
		List<T> list = new ArrayList<>();
		preOrder(root, list);
		return list;
	}
	
	public List<T> inOrder() {
		List<T> list = new ArrayList<>();
		toList(root, list);
		return list;
	}
	
	public List<T> postOrder() {
		List<T> list = new ArrayList<>();
		postOrder(root, list);
		return list;
	}
	
	private void preOrder(Node node, List<T> list) {
		list.add(node.val);
		if (node.left != null) preOrder(node.left, list);
		if (node.right != null) preOrder(node.right, list);
	}
	
	private void postOrder(Node node, List<T> list) {
		if (node.left != null) preOrder(node.left, list);
		if (node.right != null) preOrder(node.right, list);
		list.add(node.val);
	}
	
	public List<T> toList() {
		List<T> list = new ArrayList<>();
		toList(root, list);
		return list;
	}

	public Object[] toArray() {
		return toList().toArray();
	}
	
	private void toList(Node node, List<T> list) {
		if (node.left != null) toList(node.left, list);
		list.add(node.val);
		if (node.right != null) toList(node.right, list);	
	}
	
	@Override
	public String toString() {
		return toList().toString();
	}
}
