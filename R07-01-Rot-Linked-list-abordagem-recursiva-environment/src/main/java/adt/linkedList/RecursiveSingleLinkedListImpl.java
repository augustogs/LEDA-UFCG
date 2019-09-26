package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	@Override
	public boolean isEmpty() {
		return (this.data == null);
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				result = this.data;
			} else {
				result = next.search(element);
//				result = search(element, this.next);
			}
		}
		return result;
	}
	
	private T search(T element, RecursiveSingleLinkedListImpl<T> node) {
		if (node.data == null) {
			return null;
		}
		else if (node.data.equals(element)) {
			return node.data;
		} else {
			return search(element, node.next);
		}
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data = element;
			this.next = new RecursiveSingleLinkedListImpl<T>();
		} else {
			this.next.insert(element);
//			insert(element, this.next);
		}
	}
	
	//auxiliar recursivo.
	private void insert(T element, RecursiveSingleLinkedListImpl<T> node) {
		if (node.data == null) {
			node.data = element;
			node.next = new RecursiveSingleLinkedListImpl<T>();
		} else {
			insert(element, node.next);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && this.data != null) {
			if (this.data.equals(element)) {
				this.data = next.data;
				this.next = next.next;
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[this.size()];
		
		if (!isEmpty()) {
			return toArray(array, this, 0);
		} else {
			return array;			
		}	
	}

	private T[] toArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int i) {
		if (node.data == null) {
			return array;
		} else {
			array[i] = node.data;
			return toArray(array, node.next, i + 1);			
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
