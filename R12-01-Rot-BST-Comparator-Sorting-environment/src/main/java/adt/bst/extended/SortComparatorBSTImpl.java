package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	@Override
	public T[] sort(T[] array) {
		T[] result = null;
		if (array != null && array.length > 0) {
			cleanTree(this.root);
			for (T element : array) {
				this.insert(element);
			}
			result = this.order();
		}		
		return result;
	}
	
	private void cleanTree(BSTNode<T> node) {
		if (!node.isEmpty()) {
			cleanTree((BSTNode<T>) node.getLeft());
			cleanTree((BSTNode<T>) node.getRight());
			node.setData(null);
		}
	}
	
	@Override
	public T[] reverseOrder() {
		int size = this.size();
		List<T> list= new ArrayList<>(size);
		reverseOrder(list, this.root);
		return list.toArray((T[]) new Comparable[size]);
	}
	
	private void reverseOrder(List<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			reverseOrder(list, (BSTNode<T>) node.getRight());
			list.add(node.getData());
			reverseOrder(list, (BSTNode<T>) node.getLeft());
		}
	}
	
	@Override
	public BSTNode<T> search(T element) {
		return this.search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> result = new BSTNode.Builder().build();
		if (!node.isEmpty() && element != null) {
			if (this.comparator.compare(element, node.getData()) > 0) {
				result = search(element, (BSTNode<T>) node.getRight());				
			}
			else if (this.comparator.compare(element, node.getData()) < 0) {
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
		else if (this.comparator.compare(element, node.getData()) > 0) {
			insert(element, (BSTNode<T>) node.getRight());
		}
		else if (this.comparator.compare(element, node.getData()) < 0) {
			insert(element, (BSTNode<T>) node.getLeft());
		}
	}
	
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
