package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BTNode<T> right = node.getRight();
		
		right.setParent(node.getParent());
		node.setParent(right);
		
		BTNode<T> aux = right.getLeft();
		node.setRight(aux);
		right.setLeft(node); 
		
		if (aux != null) {
			aux.setParent(node);
		}		
		return (BSTNode<T>) right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BTNode<T> left = node.getLeft();
		
		left.setParent(node.getParent());	
		node.setParent(left);	
		
		BTNode<T> aux = left.getRight();
		node.setLeft(aux);	
		left.setRight(node);
		
		if (aux != null) {
			aux.setParent(node);
		}
		
		return (BSTNode<T>) left;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
