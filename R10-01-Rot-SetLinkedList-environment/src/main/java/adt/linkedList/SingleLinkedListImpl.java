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
		int result = 0;
		if (!this.isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL()) {
				result += 1;
				aux = aux.next;
			}			
		}		
		return result;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null && !this.isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			
			boolean achou = false;
			while (!aux.isNIL() && !achou) {
				if (aux.data.equals(element)) {
					result = element;
					achou = true;
				}
				aux = aux.next;
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
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
		if (element != null && !this.isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			SingleLinkedListNode<T> previous = this.head;
			boolean removeu = false;
			
			while (!aux.isNIL() && !removeu) {
				if (aux.data.equals(element)) {
					aux.data = null;
					previous.next = aux.next;	
				}
				previous = aux;
				aux = aux.next;
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		if (!this.isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			int i = 0;
			while (!aux.isNIL() && i < array.length) {
				array[i++] = aux.data;
				aux = aux.next;
			}	
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
