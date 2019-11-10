package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		if (!this.isEmpty()) {
			SingleLinkedListNode<T> aux = this.getHead();
			while (!aux.isNIL()) {
				SingleLinkedListNode<T> auxNext = aux.getNext();
				SingleLinkedListNode<T> auxPrevious = auxNext;
				
				while (!auxNext.isNIL()) {
					if (aux.getData().equals(auxNext.getData())) {
						auxNext.setData(null);
						auxPrevious.setNext(auxNext.getNext());
					}
					auxPrevious = auxNext;
					auxNext = auxNext.getNext();
				}
				aux = aux.getNext();
			}
		}
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		SetLinkedList<T> result = new SetLinkedListImpl<T>();
		
		SingleLinkedListNode<T> auxHead = this.getHead();
		while (!auxHead.isNIL()) {
			result.insert(auxHead.getData());
			auxHead = auxHead.getNext();
		}
		
		SingleLinkedListNode<T> auxOtherHead = ((SetLinkedListImpl<T>) otherSet).getHead();
		while (!auxOtherHead.isNIL()) {
			result.insert(auxOtherHead.getData());
			auxOtherHead = auxOtherHead.getNext();
		}	
		result.removeDuplicates();
		return result;
	}
	
	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		SetLinkedList<T> result = new SetLinkedListImpl<T>();
		
		if (!this.isEmpty() && !otherSet.isEmpty()) {	
			SingleLinkedListNode<T> auxHead = this.getHead();	
			while (!auxHead.isNIL()) {	
				SingleLinkedListNode<T> auxOtherHead = ((SetLinkedListImpl<T>) otherSet).getHead();
				boolean achou = false;
				
				while (!auxOtherHead.isNIL() && !achou) {
					if (auxHead.getData().equals(auxOtherHead.getData())) {
						result.insert(auxHead.getData());
						achou = true;
					}
					auxOtherHead = auxOtherHead.getNext();
				}
				auxHead = auxHead.getNext();
			}
		}
		this.removeDuplicates();
		return result;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {	
		SingleLinkedListNode<T> auxHead = this.getHead();
		SingleLinkedListNode<T> previous = this.getHead();
		while (!auxHead.isNIL()) {
			previous = auxHead;
			auxHead = auxHead.getNext();
		}
		SingleLinkedListNode<T> auxOtherHead = ((SetLinkedListImpl<T>) otherSet).getHead();
		previous.setNext(auxOtherHead);
		this.removeDuplicates();
	}

}
