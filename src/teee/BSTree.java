package teee;

public class BSTree<T> {
	private Node root;
    private class Node {
        public Node left;
        public Node right;
        public int number;
        public int key;
        public T value;
        public Node(int key, T value, int number) {
            this.key = key;
            this.value = value;
            this.number = number;
        }
    }
    
    public T get(int key) {
        T result = null;
        Node node = root;
        while (node != null) {
            if (Integer.valueOf(key).compareTo(node.key) > 0) {
                node = node.right;
            }else if (Integer.valueOf(key).compareTo(node.key) < 0) {
                node = node.left;
            }else{
                result = node.value;
                break;
            }
        }
        return result;
    }
    
    public T getRecursive(int key) {
        return getValue(root, key);
    }

    private T getValue(Node root, int key) {
        if (root == null) {
        	return null;
        }
        int cmp = Integer.valueOf(key).compareTo(root.key);
        if (cmp > 0) {
        	return getValue(root.right, key);
        }
        else if (cmp < 0) {
        	return getValue(root.left, key);
        }
        else return root.value;
    }
    
    public void put(int key, T value) {
        root = put(root, key, value);
    }

    private Node put(Node x, int key, T value) {
        //如果节点为空，则创建新的节点，并返回
        //否则比较根据大小判断是左节点还是右节点，然后继续查找左子树还是右子树
        //同时更新节点的Number的值
        if (x == null) {
        	return new Node(key, value, 1);
        }
        int cmp = Integer.valueOf(key).compareTo(x.key);
        if (cmp < 0) {
        	x.left = put(x.left, key, value);
        }else if (cmp > 0) {
        	x.right = put(x.right, key, value);
        }else {
        	x.value = value;
        }
        x.number = size(x.left) + size(x.right) + 1;
        return x;
    }

    private int size(Node node) {
        if (node == null) {
        	return 0;
        }
        else return node.number;
    }
    
    public T getMax() {
        T maxItem = null;;
        Node s = root;
        while (s.right != null) {
            s = s.right;
        }
        maxItem = s.value;
        return maxItem;
    }

    public T getMin() {
        T minItem = null;
        Node s = root;
        while (s.left != null) {
            s = s.left;
        }
        minItem = s.value;
        return minItem;
    }
    
    public T getMaxRecursive() {
        return getMaxRecursive(root);
    }

    private T getMaxRecursive(Node root) {
        if (root.right == null) {
        	return root.value;
        }
        return getMaxRecursive(root.right);
    }

    public T getMinRecursive() {
        return getMinRecursive(root);
    }

    private T getMinRecursive(Node root) {
        if (root.left == null) {
        	return root.value;
        }
        return getMinRecursive(root.left);
    }
    
    //查找Floor(key)的值就是所有<=key的最大值，相反查找Ceiling的值就是所有>=key的最小值
    public T floor(int key) {
        Node x = floor(root, key);
        if (x != null) {
        	return x.value;
        }else {
        	return null;
        }
    }

    private Node floor(Node x, int key) {
        if (x == null) {
        	return null;
        }
        int cmp = Integer.valueOf(key).compareTo(x.key);
        if (cmp == 0) {
        	return x;
        }else if (cmp < 0) {
        	return floor(x.left, key);
        }else{
            Node right = floor(x.right, key);
            if (right == null) {
            	return x;
            }else {
            	return right;
            }
        }
    }
    
    //删除最小值
    public void delMin() {
        root = delMin(root);
    }

    private Node delMin(Node x) {
        if (x.left == null) {
        	return x.right;
        }
        x.left = delMin(x.left);
        x.number = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    public void Delete(int key) {
        root = delete(root, key);
    }

    private Node delete(Node x, int key) {
        int cmp = Integer.valueOf(key).compareTo(x.key);
        if (cmp > 0) {
        	x.right = delete(x.right, key);
        }else if (cmp < 0) {
        	x.left = delete(x.left, key);
        }else{
            if (x.left == null) {
            	return x.right;
            }else if (x.right == null) {
            	return x.left;
            }else{
                Node t = x;
                x = getMinNode(t.right);
                x.right = delMin(t.right);
                x.left = t.left;
            }
        }
        x.number = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node getMinNode(Node x) {
        if (x.left == null) {
        	return x;
        }
        else return getMinNode(x.left); 
    }
    
}
