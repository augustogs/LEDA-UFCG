package adt.linkedList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import adt.linkedList.set.SetLinkedList;
import adt.linkedList.set.SetLinkedListImpl;

public class SetLinkedListTest {

	private SetLinkedList<Integer> conjunto;

	private SetLinkedListImpl<Integer> setInteger1 = new SetLinkedListImpl<Integer>();
	private SetLinkedListImpl<Integer> setInteger2 = new SetLinkedListImpl<Integer>();
	private SetLinkedListImpl<Integer> setIntegerUnion = new SetLinkedListImpl<Integer>();

	@Before
	public void setup() {
		this.conjunto = new SetLinkedListImpl<>();

		setInteger1.insert(1);
		setInteger1.insert(2);
		setInteger2.insert(0);
		setInteger2.insert(2);
		setInteger2.insert(1);

		setIntegerUnion.insert(1);
		setIntegerUnion.insert(2);
		setIntegerUnion.insert(0);
	}

	@Test
	public void testeUniao() {
		assertEquals(Arrays.toString(setIntegerUnion.toArray()),
				Arrays.toString((setInteger1.union(setInteger2)).toArray()));
	}

	@Test
	public void testeUniao02() {
		assertArrayEquals(new Integer[] {1,2}, setInteger1.toArray());
		assertArrayEquals(new Integer[] {0,2,1}, setInteger2.toArray());

		SetLinkedList<Integer> union = setInteger1.union(setInteger2);
		assertArrayEquals(new Integer[] {1,2,0}, union.toArray());
		
		//Adicao com repeticao no meio e diferente no fim. 
		setInteger1.insert(7);
		setInteger1.insert(11);
		setInteger1.insert(13);
		setInteger2.insert(7);
		setInteger2.insert(33);
		SetLinkedList<Integer> otherUnion = setInteger1.union(setInteger2);
		assertArrayEquals(new Integer[] {1,2,7,11,13,0,33}, otherUnion.toArray());
	
		// Sets vazios.
		SetLinkedList<Integer> set1 = new SetLinkedListImpl<Integer>();
		SetLinkedList<Integer> set2 = new SetLinkedListImpl<Integer>();
		assertArrayEquals(new Integer[] {}, (set1.union(set2)).toArray());

		// Um set com elementos e outro vazio.
		set2.insert(2);
		set2.insert(10);
		assertArrayEquals(new Integer[] {2,10}, (set1.union(set2)).toArray());

		// Uniao
		set1.insert(5);
		set1.insert(40);
		assertArrayEquals(new Integer[] {2,10,5,40}, (set2.union(set1)).toArray());
	}

	@Test
	public void testIntersection() {
		setInteger1.insert(5);
		setInteger1.insert(10);

		setInteger2.insert(10);
		setInteger2.insert(20);
		assertArrayEquals(new Integer[] { 1, 2, 5, 10 }, setInteger1.toArray());
		assertArrayEquals(new Integer[] { 0, 2, 1, 10, 20 }, setInteger2.toArray());

		SetLinkedList<Integer> intersection = setInteger1.intersection(setInteger2);
		assertArrayEquals(new Integer[] { 1, 2, 10 }, intersection.toArray());

		// Sets vazios.
		SetLinkedList<Integer> set1 = new SetLinkedListImpl<Integer>();
		SetLinkedList<Integer> set2 = new SetLinkedListImpl<Integer>();
		assertArrayEquals(new Integer[] {}, (set1.intersection(set2)).toArray());

		// Um set com elementos e outro vazio.
		set2.insert(2);
		set2.insert(10);
		assertArrayEquals(new Integer[] {}, (set1.intersection(set2)).toArray());

		// Sets com elementos diferentes
		set1.insert(5);
		set1.insert(40);
		assertArrayEquals(new Integer[] { 2, 10 }, set2.toArray());
		assertArrayEquals(new Integer[] { 5, 40 }, set1.toArray());
		
		assertArrayEquals(new Integer[] {}, (set1.intersection(set2)).toArray());
	}

	@Test
	public void testConcatenate() {
		setInteger1.insert(5);
		setInteger1.insert(10);

		setInteger2.insert(10);
		setInteger2.insert(20);
		assertArrayEquals(new Integer[] { 1, 2, 5, 10 }, setInteger1.toArray());
		assertArrayEquals(new Integer[] { 0, 2, 1, 10, 20 }, setInteger2.toArray());

		setInteger1.concatenate(setInteger2);
		assertArrayEquals(new Integer[] { 1, 2, 5, 10, 0, 20 }, setInteger1.toArray());
	}

	@Test
	public void testBasicOperations() {
		assertTrue(conjunto.isEmpty());
		conjunto.insert(2);
		assertEquals(1, conjunto.size());
		conjunto.insert(3);
		assertEquals(2, conjunto.size());

		// remocao de elemento inexistente.
		conjunto.remove(0);
		assertEquals(2, conjunto.size());

		// remocao de todos os elementos
		conjunto.remove(2);
		conjunto.remove(1);
		assertEquals(0, conjunto.size());

		// Insercoes
		conjunto.insert(5);
		conjunto.insert(9);
		conjunto.insert(11);

		// Testes de busca
		assertTrue(11 == conjunto.search(11));
		assertTrue(5 == conjunto.search(5));
		assertNull(conjunto.search(8));
		assertArrayEquals(new Integer[] { 5, 9, 11 }, conjunto.toArray());

		// Insercao de elemento existente
		conjunto.insert(11);
		assertArrayEquals(new Integer[] { 5, 9, 11, 11 }, conjunto.toArray());

		// Remocao de elementos duplicados
		conjunto.removeDuplicates();
		assertArrayEquals(new Integer[] { 5, 9, 11 }, conjunto.toArray());

		// Insercao de mais elementos repetidos.
		conjunto.insert(25);
		conjunto.insert(5);
		conjunto.insert(11);
		conjunto.insert(11);
		assertArrayEquals(new Integer[] { 5, 9, 11, 25, 5, 11, 11 }, conjunto.toArray());

		// Remocao de elementos duplicados
		conjunto.removeDuplicates();
		assertArrayEquals(new Integer[] { 5, 9, 11, 25 }, conjunto.toArray());
	}

}
