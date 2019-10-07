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
<<<<<<< HEAD
		// TODO Implement the method
		throw new UnsupportedOperationException("Method not implemented");
=======
		if (isFull()) {
			throw new StackOverflowException();
		} else {
			this.top.insert(element);
		}
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
	}

	@Override
	public T pop() throws StackUnderflowException {
<<<<<<< HEAD
		// TODO Implement the method
		throw new UnsupportedOperationException("Method not implemented");
=======
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			T result = top.toArray()[top.size() - 1];
			this.top.removeFirst();
			return result;
		}
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
	}

	@Override
	public T top() {
<<<<<<< HEAD
		// TODO Implement the method
		throw new UnsupportedOperationException("Method not implemented");
=======
		T result = null;
		if (!isEmpty()) {
			result = this.top.toArray()[top.size() - 1];
		}
		return result;
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
	}

	@Override
	public boolean isEmpty() {
<<<<<<< HEAD
		// TODO Implement the method
		throw new UnsupportedOperationException("Method not implemented");
=======
		return this.size == this.top.size();
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
	}

	@Override
	public boolean isFull() {
<<<<<<< HEAD
		// TODO Implement the method
		throw new UnsupportedOperationException("Method not implemented");
=======
		return this.size == this.top.size();
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
	}

}
