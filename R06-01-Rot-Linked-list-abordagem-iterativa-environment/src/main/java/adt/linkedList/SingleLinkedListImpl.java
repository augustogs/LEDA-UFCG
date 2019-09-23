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
		while (aux != null) {
			if (!aux.isNIL()) {
				cont++;
			}
			aux = aux.next;				
		}
		return cont;
	}

	@Override
	public T search(T element) {
		T result = null;
		SingleLinkedListNode<T> aux = this.head;
		while (aux != null) {
			if (aux.data == element) {
				result = aux.data;
			}
			aux = aux.next;
		}
		
		return result;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<>());
		if (isEmpty()) {
			this.head = newNode;
		}
		else {
			SingleLinkedListNode<T> aux = this.head;
			while (aux.next != null) {
				aux = aux.next;
			}
			aux.next = newNode;
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.head.data == element) {
				this.head = this.head.next;
			} else {
				SingleLinkedListNode<T> prev = new SingleLinkedListNode<>();
				SingleLinkedListNode<T> aux = this.head;
				while (aux != null && aux.data != element) {
					prev = aux;
					aux = aux.next;
				}
				prev.next = aux.next;  
			}
			
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Comparable[this.size()];
		int i = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (aux != null) {
			if (!aux.isNIL()) {
				array[i] = aux.data;
				i++;
			}
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
