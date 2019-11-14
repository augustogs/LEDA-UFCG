package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.height(this.root);
	}

	private int height(BSTNode<T> node) {
		int resp = -1;
		
		if (!node.isEmpty()) {
			int heightLeft = this.height((BSTNode<T>) node.getLeft());
			int heightRight = this.height((BSTNode<T>) node.getLeft());
			resp = 1 + Math.max(heightLeft, heightRight);
		}
		return resp;
	}

	@Override
	public BSTNode<T> search(T element) {
		return this.search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> resp = new BSTNode.Builder().build();
		
		if (!node.isEmpty()) {
			if (element.compareTo(node.getData()) > 0) {
				resp = this.search(element, (BSTNode<T>) node.getRight());
			}
			else if (element.compareTo(node.getData()) < 0) {
				resp = this.search(element, (BSTNode<T>) node.getLeft());
			}
			else {
				resp = node;
			}
		}
		return resp;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(element, this.root);			
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder().parent(node).build());
			node.setRight(new BSTNode.Builder().parent(node).build());
		}
		else if (element.compareTo(node.getData()) > 0) {
			this.insert(element, (BSTNode<T>) node.getRight());
		}
		else if (element.compareTo(node.getData()) < 0) {
			this.insert(element, (BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return this.maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> resp = null;
		
		if (!node.isEmpty()) {
			if (node.getRight().isEmpty()) {
				resp = node;
			} else {
				this.maximum((BSTNode<T>) node.getRight());
			}
		}
		return resp;
	}

	@Override
	public BSTNode<T> minimum() {
		return this.minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> resp = null;
		
		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty()) {
				resp = node;
			} else {
				resp = this.minimum((BSTNode<T>) node.getLeft());
			}
		}
		
		return resp;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> resp = null;
		
		return resp;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
