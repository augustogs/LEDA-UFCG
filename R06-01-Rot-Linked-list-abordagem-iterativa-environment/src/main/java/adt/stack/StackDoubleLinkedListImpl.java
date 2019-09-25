package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException();
		} else {
			this.top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T result = null;
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		} else {
			result = this.top.toArray()[top.size() - 1];
		}
		return result;
	}

	@Override
	public T top() {
		T result = null;
		if (!this.isEmpty()) {
			result = this.top.toArray()[top.size() - 1];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.size == this.top.size();
	}

}
