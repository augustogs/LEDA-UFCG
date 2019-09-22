package linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return (this.head == null);
	}

	@Override
	public int size() {
		int result = 0;
		SingleLinkedListNode<T> aux = this.head;
		while (aux.next != null) {
			aux = aux.next;
			result++;
		}
		return result;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (!isEmpty()) {
			if (this.head.data == element) {
				result = element;
			}
			else {
				SingleLinkedListNode<T> aux = this.head;
				while (aux.next != null) {
					if (aux.next.data == element) {
						result = aux.next.data;
					}
					aux = aux.next;
				}
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> node = new SingleLinkedListNode<T>(element, null);
		if (isEmpty())
			this.head = node;
		else {
			SingleLinkedListNode<T> aux = this.head;
			while (aux.next != null) {
				aux = aux.next;
			}
			aux.next = node;
		}
	}

	@Override
	public void remove(T element) {
		if (this.head.data == element) {
			this.head = this.head.next;
		} else {
			SingleLinkedListNode<T> prev = null;
			SingleLinkedListNode<T> aux = this.head;
			while (aux != null && aux.data != element) {
				prev = aux;
				aux = aux.next;
			}
			prev.next = aux.next;
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Comparable[this.size()];
		SingleLinkedListNode<T> aux = this.head;
		int i = 0;
		while (aux.next != null) {
			array[i] = aux.next.data;
			aux = aux.next;
			i++;
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
