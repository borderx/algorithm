package teee;

public class RedBlackTree<T> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;

	class Node {
	    public Node left;
	    public Node right;
	    public int key;
	    public T value;
	    public int number;
	    public boolean color;
	    public Node(int key, T value,int number, boolean color)
	    {
	        this.key = key;
	        this.value = value;
	        this.number = number;
	        this.color = color;
	    }
	}

	private boolean isRed(Node node){
	    if (node == null) return false;
	    return node.color == RED;
	}
	
	public T Get(int key) {
	    return getValue(root, key);
	}

	private T getValue(Node node, int key) {
	    if (node == null) {
	    	return null;
	    }
	    int cmp = Integer.valueOf(key).compareTo(node.key);
	    if (cmp == 0) {
	    	return node.value;
	    }else if (cmp > 0) {
	    	return getValue(node.right, key);
	    }
	    else return getValue(node.left, key);
	}
	
	private Node rotateLeft(Node h) {
	    Node x = h.right;
	    //将x的左节点复制给h右节点
	    h.right = x.left;
	    //将h复制给x右节点
	    x.left = h;
	    x.color = h.color;
	    h.color = RED;
	    return x;
	}
	
	private Node rotateRight(Node h) {
	    Node x = h.left;
	    h.left = x.right;
	    x.right = h;
	    x.color = h.color;
	    h.color = RED;
	    return x;
	}
	
	private Node flipColor(Node h) {
		h.left.color = BLACK;
		h.right.color = BLACK;
		h.color = RED;
		return h;
	}
	
	public void put(int key, T value) {
	    root = put(root, key, value);
	    root.color = BLACK;
	}

	private Node put(Node h, int key, T value) {
	    if (h == null) {
	    	return new Node(key, value, 1, RED);
	    }
	    int cmp = Integer.valueOf(key).compareTo(h.key);
	    if (cmp < 0) {
	    	h.left = put(h.left, key, value);
	    }else if (cmp > 0) {
	    	h.right = put(h.right, key, value);
	    }else {
	    	h.value = value;
	    }

	    //平衡化操作
	    if (isRed(h.right) && !isRed(h.left)) {
	    	h = rotateLeft(h);
	    }
	    if (isRed(h.left) && isRed(h.left.left)) {
	    	h = rotateRight(h);
	    }
	    if (isRed(h.left) && isRed(h.right)) {
	    	h = flipColor(h);
	    }

	    h.number = size(h.left) + size(h.right) + 1;
	    return h;
	}

	private int size(Node node) {
	    if (node == null) {
	    	return 0;
	    }
	    return node.number;
	}
	
}
