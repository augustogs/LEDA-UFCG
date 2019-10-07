package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
<<<<<<< HEAD
		lista1 = null;
		lista2 = null;
		lista3 = null;
=======
		lista1 = new RecursiveDoubleLinkedListImpl<>();
		lista2 = new RecursiveDoubleLinkedListImpl<>();
		lista3 = new RecursiveDoubleLinkedListImpl<>();
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
	}
<<<<<<< HEAD
=======
	
	@Test
	public void testeInsertRemove() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeLast();
		((DoubleLinkedList<Integer>) lista1).insertFirst(5);
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		((DoubleLinkedList<Integer>) lista1).insert(6);
		Assert.assertArrayEquals(new Integer[] {4,5,6}, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).remove(5);
		Assert.assertArrayEquals(new Integer[] {4,6}, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] {4}, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).remove(6);
		Assert.assertArrayEquals(new Integer[] {4}, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).remove(4);
		Assert.assertArrayEquals(new Integer[] {}, lista1.toArray());
	}
>>>>>>> f18c998a04623bfa6991a0bf88e16e456443ed55

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
	}
}