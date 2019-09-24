package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();	
		newNode.setData(element);
		newNode.setNext(this.getHead());
		newNode.previous = new DoubleLinkedListNode<T>();
		((DoubleLinkedListNode<T>) this.head).setPrevious(newNode);
		this.setHead(newNode); 
	}

	@Override
	public void removeFirst() {
		if (!this.head.isNIL()) {
			this.setHead(this.head.getNext());
			if (this.head.isNIL()) {
				this.last = (DoubleLinkedListNode<T>) this.head;
			}	
			((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());
		}
	}
	
	@Override
	public void insert(T element) {
	}
	

	@Override
	public void removeLast() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
