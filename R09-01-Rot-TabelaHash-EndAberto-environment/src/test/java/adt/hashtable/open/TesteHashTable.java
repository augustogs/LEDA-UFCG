package adt.hashtable.open;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class TesteHashTable {

	protected AbstractHashtableOpenAddress<HashtableElement> table1;
	
	protected final int PROPOSED_SIZE = 10;

	@Before
	public void setUp() throws Exception {
		table1 = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);
		// o tamanho real utilizado vai ser PROPOSED_SIZE
		table1.insert(new HashtableElement(2)); // coloca no slot indexado com 2
		table1.insert(new HashtableElement(3)); // coloca no slot indexado com 3
		table1.insert(new HashtableElement(4)); // coloca no slot indexado com 4
		table1.insert(new HashtableElement(5)); // coloca no slot indexado com 5
	}

}
