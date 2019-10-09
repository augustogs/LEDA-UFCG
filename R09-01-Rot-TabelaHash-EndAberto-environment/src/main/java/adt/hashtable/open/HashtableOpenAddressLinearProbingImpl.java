package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
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
               int index = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
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
   }

   @Override
   public T search(T element) {
      T result = null;

      int index = indexOf(element);
      if (index >= 0) {
         result = element;
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
            int index = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
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
