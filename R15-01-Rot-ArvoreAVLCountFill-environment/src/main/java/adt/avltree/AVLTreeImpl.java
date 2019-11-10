package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			int heightLeft = this.height((BSTNode<T>) node.getLeft());
			int heightRight = this.height((BSTNode<T>) node.getRight());
			result = heightLeft - heightRight;
		}
		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		
		if (balance > 1) {
			int balanceFilhoEsquerda = calculateBalance((BSTNode<T>) node.getLeft());
			if (balanceFilhoEsquerda < 0) {
				this.leftRotation((BSTNode<T>) node.getLeft());			
			}
			this.rightRotation(node);
		}
		else if (balance < -1) {
			int balanceFilhoDireita = calculateBalance((BSTNode<T>) node.getRight());
			if (balanceFilhoDireita > 0) {
				this.rightRotation((BSTNode<T>) node.getRight());
			}
			this.leftRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			rebalance(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
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
			this.rebalanceUp(node);
		} 
		else if (element.compareTo(node.getData()) > 0) {
			insert(element, (BSTNode<T>) node.getRight());
		}
		else if (element.compareTo(node.getData()) < 0) {
			insert(element, (BSTNode<T>) node.getLeft());
		}
	}
	
	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				this.rebalanceUp(node);	
				
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
				this.rebalanceUp(node);
				
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
	
	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> aux = Util.leftRotation(node);
		BSTNode<T> auxParent = (BSTNode<T>) aux.getParent();
		if (auxParent == null) {
			this.root = aux;
		} else {
			if (auxParent.getLeft().equals(node)) {
				auxParent.setLeft(aux);
			} else {
				auxParent.setRight(aux);
			}	
		}
	}
	
	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> aux = Util.rightRotation(node);
		BSTNode<T> auxParent = (BSTNode<T>) aux.getParent();
		if (auxParent == null) {
			this.root = aux;
		} else {
			if (auxParent.getLeft().equals(node)) {
				auxParent.setLeft(aux);
			} else {
				auxParent.setRight(aux);
			}	
		}
	}
}
