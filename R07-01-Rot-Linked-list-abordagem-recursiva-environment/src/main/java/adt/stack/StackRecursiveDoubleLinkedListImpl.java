package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		} else {
			this.top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			T result = top.toArray()[top.size() - 1];
			this.top.removeFirst();
			return result;
		}
	}

	@Override
	public T top() {
		T result = null;
		if (!isEmpty()) {
			result = this.top.toArray()[top.size() - 1];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return this.size == this.top.size();
	}

	@Override
	public boolean isFull() {
		return this.size == this.top.size();
	}

}
