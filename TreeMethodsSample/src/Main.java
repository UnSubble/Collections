class Tree<T extends Comparable<T>> {
	Tree<T> left;
	Tree<T> right;
	T val;
	Tree(T val) {
		this.val = val;
	}
}

public class Main {
	
	static <T extends Comparable<T>> void insert(Tree<T> root, T val) {
		if (root.val.compareTo(val) >= 0) {
			if (root.left == null) root.left = new Tree<>(val);
			else insert(root.left, val);
		} else {
			if (root.right == null) root.right = new Tree<>(val);
			else insert(root.right, val);
		}
	}
	
	static <T extends Comparable<T>> int count(Tree<T> root) {
		int n = 1;
		if (root.left != null) n += count(root.left);
		if (root.right != null) n += count(root.right);
		return n;
	}
	
	static <T extends Comparable<T>> int calcDepth(Tree<T> root) {
		if (root == null) return 0;
		return Math.max(calcDepth(root.left) + 1, calcDepth(root.right) + 1);
	}
	
	static <T extends Comparable<T>> boolean contains(Tree<T> root, T val) {
		if (root == null) return false;
		if (root.val.compareTo(val) > 0) return contains(root.left, val);
		if (root.val.compareTo(val) < 0) return contains(root.right, val);
		return root.val.equals(val);
	}
	
	static <T extends Comparable<T>> void printTreeSorted(Tree<T> root) {
		if (root.left != null) printTreeSorted(root.left);
		System.out.println(root.val);
		if (root.right != null) printTreeSorted(root.right);
	}
	
}
