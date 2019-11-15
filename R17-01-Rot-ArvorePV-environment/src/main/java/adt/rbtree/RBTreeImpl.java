package adt.rbtree;

import java.util.ArrayList;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return this.blackHeight((RBNode<T>) this.root);
	}

	private int blackHeight(RBNode<T> node) {
		int resp = 0;
		if (!node.isEmpty()) {
			int blackHeightLeft = blackHeight((RBNode<T>) node.getLeft());
			int blackHeightRight = blackHeight((RBNode<T>) node.getRight());		
			if (node.getColour() == Colour.BLACK) {
				resp = 1 + Math.max(blackHeightLeft, blackHeightRight);
			} else {
				resp = Math.max(blackHeightLeft, blackHeightRight);
			}
		}		
		return resp;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfredNodes((RBNode<T>) this.root);
	}

	private boolean verifyChildrenOfredNodes(RBNode<T> node) {
		boolean resp = true;
		
		if (!node.isEmpty()) {
			if (node.getColour() == Colour.RED) {
				Colour colorChildrenLeft = ((RBNode<T>) node.getLeft()).getColour();
				Colour colorChildrenRight = ((RBNode<T>) node.getRight()).getColour(); 
				
				if (colorChildrenLeft == Colour.RED || colorChildrenRight == Colour.RED) {
					resp = false;
				} else {
					resp = verifyChildrenOfredNodes((RBNode<T>) node.getLeft()) && verifyChildrenOfredNodes((RBNode<T>) node.getRight());
				}		
			}
		}	
		return resp;
	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		return verifyBlackHeight((RBNode<T>) this.root);
	}

	private boolean verifyBlackHeight(RBNode<T> node) {
		boolean resp = true;
		
		if (!node.isEmpty()) {
			if (verifyBlackHeight((RBNode<T>) node.getLeft()) != verifyBlackHeight((RBNode<T>) node.getRight())) {
				resp = false;
			}
		}
		return resp;

	}

	@Override
	public void insert(T value) {
		if (value != null) {
			this.insert(value, (RBNode<T>) this.root);
		}
	}
	
	private void insert(T value, RBNode<T> node) {
		if (node.isEmpty()) {
			node.setData(value);
			node.setColour(Colour.RED);
			node.setLeft(new RBNode<T>());
			node.setRight(new RBNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);		
			this.fixUpCase1(node);
		} else {
			if (value.compareTo(node.getData()) > 0) {
				this.insert(value, (RBNode<T>) node.getRight());
			}
			else if (value.compareTo(node.getData()) < 0) {
				this.insert(value, (RBNode<T>) node.getLeft());
			}
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		int size = this.size();
		List<RBNode<T>> list = new ArrayList<RBNode<T>>(size);
		this.rbPreOrder((ArrayList<RBNode<T>>) list, (RBNode<T>) this.root);
			
		RBNode<T>[] resp = new RBNode[size];
		for (int i = 0; i < list.size(); i++) {
			resp[i] = list.get(i);
		}
		return resp;
		
	}
	
	private void rbPreOrder(ArrayList<RBNode<T>> list, RBNode<T> node) {
		if (!node.isEmpty()) {
			list.add(node);
			rbPreOrder(list, (RBNode<T>) node.getLeft());
			rbPreOrder(list, (RBNode<T>) node.getRight());
		}	
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(this.root)) {
			node.setColour(Colour.BLACK);
		} else {
			this.fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		Colour colorParent = ((RBNode<T>) node.getParent()).getColour();
		if (colorParent == Colour.RED) {
			this.fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();
		RBNode<T> uncle = this.getUncle(node);
		
		if (uncle.getColour() == Colour.RED) {
			uncle.setColour(Colour.BLACK);
			parent.setColour(Colour.BLACK);
			grandParent.setColour(Colour.RED);
			this.fixUpCase1(grandParent);
		} else {
			this.fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();

		if (!isLeftChild(node) && isLeftChild(parent)) {
			Util.leftRotation(parent);
			next = (RBNode<T>) node.getLeft();
		} else if (isLeftChild(node) && !isLeftChild(parent)) {
			Util.rightRotation(parent);
			next = (RBNode<T>) node.getRight();
		}
		this.fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();

		parent.setColour(Colour.BLACK);
		grandParent.setColour(Colour.RED);
		if (isLeftChild(node)) {
			Util.rightRotation(grandParent);
		} else
			Util.leftRotation(grandParent);
	}
	
	private boolean isLeftChild(RBNode<T> node) {
		return node.getParent().getLeft().equals(node);
	}
	
	private RBNode<T> getUncle(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> uncle;
		if (isLeftChild(parent)) {
			uncle = (RBNode<T>) parent.getParent().getRight();			
		} else {
			uncle = (RBNode<T>) parent.getParent().getLeft();			
		}
		return uncle;
	}
}
