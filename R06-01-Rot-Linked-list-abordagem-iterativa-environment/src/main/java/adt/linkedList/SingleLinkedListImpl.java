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
			aux = aux.getNext();	
		}
		return cont;
	}

	@Override
	public T search(T element) {
		T result = null;
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			if (aux.data.equals(element)) {
				result = aux.getData();
			}
			aux = aux.getNext();
		}
		
		return result;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.head.setData(element);
			this.head.setNext(new SingleLinkedListNode<T>());
		}
		else {
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL()) {
				aux = aux.getNext();
			}
			aux.setData(element);
			aux.setNext(new SingleLinkedListNode<T>());
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.head.data.equals(element)) {
				this.head.setNext(this.head.next);
			} else {
				SingleLinkedListNode<T> prev = new SingleLinkedListNode<>();
				SingleLinkedListNode<T> aux = this.head;
				while (!aux.isNIL() && !aux.data.equals(element)) {
					prev = aux;
					aux = aux.getNext();
				}
				prev.setNext(aux.next);  
			}			
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		
		SingleLinkedListNode<T> aux = this.head;
		int i = 0;
		while (!aux.isNIL()) {
			array[i] = aux.getData();
			i++;
			aux = aux.getNext();
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
