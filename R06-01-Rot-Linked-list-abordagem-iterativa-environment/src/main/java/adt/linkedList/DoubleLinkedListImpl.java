package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());	
				this.head = newNode;
				this.last = newNode;	
			} else {
				DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.head;
				DoubleLinkedListNode<T> newNode = ((DoubleLinkedListNode<T>) this.head).previous;
				
				newNode.data = element;
				newNode.previous = new DoubleLinkedListNode<T>();
				newNode.next = auxHead;
				this.head = newNode;
			}			
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			this.head = this.head.next;
			if (this.head.isNIL()) {
				this.last = (DoubleLinkedListNode<T>) this.head;
			}
			((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());
		}
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
				this.head = newNode;
				this.last = newNode;
			} else {
				DoubleLinkedListNode<T> newNode = (DoubleLinkedListNode<T>) this.last.next;
				newNode.data = element;
				newNode.previous = this.last;
				newNode.next = new DoubleLinkedListNode<T>();
				this.last = newNode;
			}		
		}
	}
	
	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			if (this.head.data.equals(element)) {
				this.removeFirst();
			} else {
				DoubleLinkedListNode<T> prev = (DoubleLinkedListNode<T>) this.head;
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
				while (!aux.isNIL() && !aux.data.equals(element)) {
					prev = aux;
					aux = (DoubleLinkedListNode<T>) aux.next;
				}
				if (!aux.isNIL()) {
					((DoubleLinkedListNode<T>) aux.next).previous = prev;
					prev.next = aux.next;						
				}				
			}
			
			
			
//			DoubleLinkedListNode<T> prev = (DoubleLinkedListNode<T>) this.head;
//			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
//			while (!aux.isNIL() && !aux.data.equals(element)) {
//				prev = aux;
//				aux = (DoubleLinkedListNode<T>) aux.next;
//			}
//			((DoubleLinkedListNode<T>) aux.next).previous = aux.previous;
//			aux.previous.next = aux.next;
		}
	}
	
	@Override
	public void removeLast() {
		if (!this.last.isNIL()) {
			this.last = this.last.previous;
			if (this.last.isNIL()) {
				this.head = this.last;
			}
			this.last.next = new DoubleLinkedListNode<T>();
		}			

	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
