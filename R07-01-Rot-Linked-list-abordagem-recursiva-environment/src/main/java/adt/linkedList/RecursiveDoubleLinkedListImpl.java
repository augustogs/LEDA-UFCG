package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}
	
	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<T>();
			if (this.previous == null) {
				this.previous = new RecursiveDoubleLinkedListImpl<T>();				
			}
			((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;	
		} else {
			next.insert(element);
		}
	}
	
	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				if ((this.previous.isEmpty() && this.next.isEmpty()) || this.next.isEmpty()) {
					this.data = null;
					this.next = null;
					this.previous = null;
				} else {
					RecursiveDoubleLinkedListImpl<T> nodeNext =  (RecursiveDoubleLinkedListImpl<T>) this.next;
					
					this.data = this.next.data;
					this.next = this.next.next;
					nodeNext.previous = this.previous;
				}
			} else {
				this.next.remove(element);
			}
		}
	}
	
	@Override
	public void insertFirst(T element) {
		if (isEmpty()) {
			this.insert(element);
		} else {
			RecursiveDoubleLinkedListImpl<T> oldNode = new RecursiveDoubleLinkedListImpl<T>();
			oldNode.data = this.data;
			oldNode.next = this.next;
			oldNode.previous = this;
			this.data = element;
			this.next = oldNode;		
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (this.next.isEmpty()) {
				this.data = null;
			} else {
				this.data = this.next.data;
				this.next = this.next.next;			
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.next.isEmpty()) {
				this.data = null;
				this.previous = new RecursiveDoubleLinkedListImpl<T>();
				this.previous.next = new RecursiveDoubleLinkedListImpl<T>();;
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
