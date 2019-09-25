package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		SingleLinkedListNode<T> aux = this.head;
		int cont = 0;
		while (!aux.isNIL()) {
			cont++;
			aux = aux.next;	
		}
		return cont;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null) {
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL()) {
				if (aux.data.equals(element)) {
					result = aux.data;
				}
				aux = aux.next;
			}			
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.head.data = element;
				this.head.next = new SingleLinkedListNode<T>();
			}
			else {
				SingleLinkedListNode<T> aux = this.head;
				while (!aux.isNIL()) {
					aux = aux.next;
				}
				aux.data = element;
				aux.next = new SingleLinkedListNode<T>();
			}			
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (!isEmpty()) {
				if (this.head.data.equals(element)) {
					this.head = this.head.next;
				} else {
					SingleLinkedListNode<T> prev = new SingleLinkedListNode<>();
					SingleLinkedListNode<T> aux = this.head;
					while (!aux.isNIL() && !aux.data.equals(element)) {
						prev = aux;
						aux = aux.next;
					}
					prev.next = aux.next;  
				}			
			}		
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		
		int i = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			array[i] = aux.data;
			i++;
			aux = aux.next;
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
