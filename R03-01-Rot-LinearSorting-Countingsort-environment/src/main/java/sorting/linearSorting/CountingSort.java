package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex >= 0 && rightIndex < array.length && array.length != 0 && leftIndex < rightIndex) {						
			int size = maxArray(array);
			
			Integer[] frequency = new Integer[size];	
			for (int i = 0; i < frequency.length; i++) {
				frequency[i] = 0;
			}
			
			for (int j = leftIndex; j <= rightIndex; j++) {
				frequency[array[j] - 1] += 1;
			}
			
			for (int i = leftIndex + 1; i < frequency.length; i++) {
				frequency[i] += frequency[i-1];
			}
			
			Integer[] arrayAux = new Integer[array.length];
			for (int j = rightIndex; j >= leftIndex; j--) {
				arrayAux[frequency[array[j] - 1] - 1] = array[j];
				frequency[array[j] - 1] -= 1;
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = arrayAux[i];
			}
			
		}
		
	}
	
	private int maxArray(Integer[] array) {
		int max = Integer.MIN_VALUE;
		for (Integer i : array) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

}
