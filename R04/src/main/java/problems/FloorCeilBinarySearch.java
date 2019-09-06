package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: - Algoritmo in-place (nao pode usar memoria extra a nao ser
 * variaveis locais) - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {
		
	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer result = null;
		if (array != null && array.length > 0) {
			result = floorBinarySearch(array, x, 0, array.length - 1);
		}
		return result;
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		Integer result = null;
		if (array != null && array.length > 0) {
			result = ceilBinarySearch(array, x, 0, array.length - 1);
		}
		return result;
	}
	
	//Metodo auxiliar do floor.
	private static Integer floorBinarySearch(Integer[] array, Integer x, int leftIndex, int rightIndex) {
		int middle = (leftIndex + rightIndex) / 2;
		if (leftIndex > rightIndex) {
			return array[rightIndex];
		}
		if (array[middle] > x) {
			return floorBinarySearch(array, x, leftIndex, middle - 1);
		}
		else if (array[middle] < x) {
			return floorBinarySearch(array, x, middle + 1, rightIndex);
		} 
		else {
			return array[middle];
		}			
	}

	//Metodo auxiliar do ceil.
	private Integer ceilBinarySearch(Integer[] array, Integer x, int leftIndex, int rightIndex) {
		int middle = (leftIndex + rightIndex) / 2;
		if (leftIndex > rightIndex) {
			return array[leftIndex];
		}
		if (array[middle] > x) {
			return ceilBinarySearch(array, x, leftIndex, middle - 1);
		}
		else if (array[middle] < x) {
			return ceilBinarySearch(array, x, middle + 1, rightIndex);
		} else {
			return array[middle];
		}		
	}

}
