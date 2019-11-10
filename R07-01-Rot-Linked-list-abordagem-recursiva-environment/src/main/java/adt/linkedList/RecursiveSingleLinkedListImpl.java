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
		}
		else return this.size(next);
	}
	
	private int size(RecursiveSingleLinkedListImpl<T> node) {
		if (node.data == null) {
			return 0;
		}
		else return 1 + this.size(node.next);
	}

	@Override
	public T search(T element) {
		T result = null;
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				result = this.data;
			} else {
				result = this.next.search(element);
			}
		}
		return result;

	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data = element;
			this.next = new RecursiveSingleLinkedListImpl<>();
		}else {
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				this.data = next.data;
				this.next = next.next;
			} else {
				this.next.remove(element);
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
		if (node.isEmpty()) {
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
