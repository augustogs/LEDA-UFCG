package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (this.isFull()) {
			throw new HashtableOverflowException();
		} else {
			if (element != null) {
				int probe = 0;
				boolean inseriu = false;
				
				while (probe < this.capacity() && !inseriu) {
					int index = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
					if (this.table[index] == null || this.table[index] instanceof DELETED) {
						this.table[index] = element;
						this.elements++;
						inseriu = true;
					} else {
						this.COLLISIONS++;
					}
					probe++;
				}
			}		
		}
	}

	@Override
	public void remove(T element) {
		int index = indexOf(element);
		if (index >= 0) {
			this.table[index] = new DELETED();
			this.elements--;
		}
		
		
//		if (element != null && !this.isEmpty()) {
//			int probe = 0;
//			boolean removeu = false;
//			
//			while (probe < this.capacity() && !removeu) {
//				int index = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
//				if (this.table[index] != null && this.table[index].equals(element)) {
//					this.table[index] = new DELETED();
//					this.elements--;
//					removeu = true;
//				}
//				probe++;
//			}
//		}
	}

	@Override
	public T search(T element) {
		T result = null;
		
		int index = indexOf(element);
		if (index >= 0) {
			result = (T) this.table[index];
		}	
		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;
		
		if (element != null && !this.isEmpty()) {
			boolean achou = false;
			int probe = 0;
			
			while (probe < this.capacity() && !achou) {
				int index = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, probe);
				if (this.table[index] != null && this.table[index].equals(element)) {
					result = index;
					achou = true;
				}
				probe++;
			}			
		}	
		return result;
	}

}
