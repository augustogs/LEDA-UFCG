package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {
	
	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		if (array != null && array.length > 0) {
			int max = 0;
			int min = 0;
			for (int i = 0; i < array.length; i++) {
				if (array[i].compareTo(array[max]) > 0) {
					max = i;
				}
				if (array[i].compareTo(array[min]) < 0) {
					min = i;
				}
			}
			return auxGetOrderStatistics(array, k, 1, min, max);					
		}
		return null;
		
	}

	private T auxGetOrderStatistics(T[] array, int k, int qntdMin, int min, int max) {
		if (array.length == 1) {
			return null;
		}
		if (k == 1) {
			return array[min];
		}
		
		int nMin = max;
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(array[max]) < 0) {
				nMin = i;
			}
			
		}
		return auxGetOrderStatistics(array, k, qntdMin + 1, nMin, max);	
	}
	
	public static void main(String[] args) {
		Integer[] array = new Integer[] {3,2,1};
		System.out.println();
	}

}
