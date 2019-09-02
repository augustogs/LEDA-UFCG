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
		Integer tamanho = maxArray(array);
		Integer[] frequencia = new Integer[tamanho];
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			frequencia[array[i]-1] += 1;
		}
		
		for (int i = leftIndex + 1; i < rightIndex; i++) {
			frequencia[i] = frequencia[i] + frequencia[i-1];
		}
		
	}
	
	private static Integer maxArray(Integer[] array) {
		Integer max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(max) < 0){
				max = array[i];
			}
		}
		return max;
	}

}
