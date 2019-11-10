package problems;

/**
 * Dado dois arrays ordenados em ordem crescente, encontrar a k-esima estatistica de ordem 
 * da uniao ordenada deles. 
 * 
 * Restricoes:
 * - os arrays nao possuem elementos em comum e nem repetidos
 * - k eh um numero compreendido entre 1 e array1.length + array2.length
 * - caso o k-esima estatistica de ordem nao exista, o metodo deve retornar null
 * - voce nao pode usar memoria extra
 * - seu algoritmo deve ter complexidade O(array1.length + array2.length). 
 * - voce nao pode usar nenhum metodo pronto de manipulacao de arrays, exceto length.
 * 
 * @author adalbertocajueiro
 *
 */
public class OrderStatisticsSortedUnion<T extends Comparable<T>> {
	
	public T statisticsOrder(T[] array1, T[] array2, int k) {
		T result = null;
		if (k == 1) {
			if (array1.length == 0) {
				result = array2[0];
			}
			if (array2.length == 0) {
				result = array1[0];
			}		
			if (array1.length > 0 && array2.length > 0) {				
				if(array1[0].compareTo(array2[0]) < 0) {
					result = array1[0];
				} else {
					result = array2[0];
				}				
			}
		}
		
		if (k > array1.length + array2.length) {
			result = null;
		}	
		else {
			int cont = 1;
			int i = 0;
			int j = 0;
			if (j < array1.length && i < array2.length) {
				while (cont < k && j < array1.length && i < array2.length) {
					if (array1[j].compareTo(array2[i]) > 0) {
						result = array1[j];
						i++;
					} else {
						result = array2[i];
						j++;
					}
					cont++;
				}
			}
			
			while (i < array1.length) { 
				if (cont < k) {
					if (result == null) {
						result = array1[0];
					}
					if (array1[i].compareTo(result) > 0) {
						result = array1[i];
						cont++;
					}
				}
				i++;
			}
			
			while (j < array2.length) {
				if (cont <  k) {
					if (result == null) {
						result = array2[0];
					}
					if (array2[j].compareTo(result) > 0) {
						result = array2[j];
						cont++;
					}
				}
				j++;
			}					
		}
		return result;
	}

}
