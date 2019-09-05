package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex >= 0 && rightIndex < array.length && array.length != 0 && leftIndex < rightIndex) {
			int min = minArray(array);
			int max = maxArray(array);	
			int size = max - min + 1;
			
			Integer[] frequency = new Integer[size];
			for (int j = 0; j < frequency.length; j++) {
				frequency[j] = 0;
			}
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				frequency[array[i] - min] += 1;
			}
			
			int j = leftIndex;		
			for (int i = 0; i < frequency.length; i++) {
				while (frequency[i] > 0) {
					array[j] = i + min;
					j++;
					frequency[i] -= 1;
				}	
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
	
	private int minArray(Integer[] array) {
		int min = Integer.MAX_VALUE;
		for (Integer i : array) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	}

}
