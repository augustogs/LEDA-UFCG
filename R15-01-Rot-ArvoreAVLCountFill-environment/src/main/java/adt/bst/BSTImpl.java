package adt.bst;

import java.util.ArrayList;
import java.util.List;

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

	protected int height(BSTNode<T> node) {
		int result = -1;
		if (!node.isEmpty()) {
			int heightLeft = height((BSTNode<T>) node.getLeft());
			int heightRight = height((BSTNode<T>) node.getRight());
			result = 1 + Math.max(heightLeft, heightRight);
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		return this.search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> result = new BSTNode.Builder().build();
		if (!node.isEmpty() && element != null) {
			if (element.compareTo(node.getData()) > 0) {
				result = search(element, (BSTNode<T>) node.getRight());				
			}
			else if (element.compareTo(node.getData()) < 0) {
				result = search(element, (BSTNode<T>) node.getLeft());
			} else {
				result = node;
			}
		}
		return result;
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
			insert(element, (BSTNode<T>) node.getRight());
		}
		else if (element.compareTo(node.getData()) < 0) {
			insert(element, (BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return this.maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if (!node.isEmpty()) {
			if (node.getRight().isEmpty()) {
				result = node;
			} else {
				result = maximum((BSTNode<T>) node.getRight());
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> minimum() {
		return this.minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty()) {
				result = node;
			} else {
				result = minimum((BSTNode<T>) node.getLeft());
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;

		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty())
				result = minimum((BSTNode<T>) node.getRight());
			else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && parent.getData().compareTo(node.getData()) < 0) {
					node = parent;
					parent = (BSTNode<T>) node.getParent();
				}
				result = parent;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;

		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty())
				result = maximum((BSTNode<T>) node.getLeft());
			else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && parent.getData().compareTo(node.getData()) > 0) {
					node = parent;
					parent = (BSTNode<T>) node.getParent();
				}
				result = parent;
			}
		}
		return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);

			} else if (temFilho(node)) {
				if (node.getParent() != null) {
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}

					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
			} else {
				T sucessorNode = sucessor(node.getData()).getData();
				remove(sucessorNode);
				node.setData(sucessorNode);
			}
		}
	}
	
	private boolean temFilho(BSTNode<T> node) {
		return (node.getLeft().isEmpty() && !node.getRight().isEmpty()
				|| node.getRight().isEmpty() && !node.getLeft().isEmpty());
	}

	@Override
	public T[] preOrder() {
		int size = this.size();
		List<T> list = new ArrayList<T>(size);
		preOrder(list, this.root);
		return list.toArray((T[]) new Comparable[size]);
	}

	private void preOrder(List<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			list.add(node.getData());
			preOrder(list, (BSTNode<T>) node.getLeft());
			preOrder(list, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] order() {
		int size = this.size();
		List<T> list= new ArrayList<>(size);
		order(list, this.root);
		return list.toArray((T[]) new Comparable[size]);
	}

	private void order(List<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			order(list, (BSTNode<T>) node.getLeft());
			list.add(node.getData());
			order(list, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] postOrder() {
		int size = this.size();
		List<T> list= new ArrayList<>(size);
		postOrder(list, this.root);
		return list.toArray((T[]) new Comparable[size]);
	}

	private void postOrder(List<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			postOrder(list, (BSTNode<T>) node.getLeft());
			postOrder(list, (BSTNode<T>) node.getRight());
			list.add(node.getData());
		}
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
