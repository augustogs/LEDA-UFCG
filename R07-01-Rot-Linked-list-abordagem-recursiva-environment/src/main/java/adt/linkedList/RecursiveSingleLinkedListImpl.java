package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

<<<<<<< HEAD

=======
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
	@Override
	public boolean isEmpty() {
		return (this.data == null);
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
<<<<<<< HEAD
		}
		else return this.size(next);
	}
	
	private int size(RecursiveSingleLinkedListImpl<T> node) {
		if (node.data == null) {
			return 0;
		}
		else return 1 + this.size(node.next);
=======
		} else {
			return 1 + this.next.size();
		}
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
	}

	@Override
	public T search(T element) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
=======
		T result = null;
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				result = this.data;
			} else {
				result = this.next.search(element);
			}
		}
		return result;
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data = element;
<<<<<<< HEAD
			this.next = new RecursiveSingleLinkedListImpl<>();
		} else {
			this.insert(node, element);
=======
			this.next = new RecursiveSingleLinkedListImpl<T>();
		} else {
			this.next.insert(element);
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
		}
	}

	@Override
	public void remove(T element) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
=======
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				this.data = next.data;
				this.next = next.next;
			} else {
				this.next.remove(element);
			}
		}
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
	}

	@Override
	public T[] toArray() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
=======
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
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
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
