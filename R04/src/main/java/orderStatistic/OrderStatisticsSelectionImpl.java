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
		if (array != null && array.length > 0 && k > 0) {
			int iMin = 0;
			int iMax = 0;	
			for (int i = 0; i < array.length; i++) {
				if (array[i].compareTo(array[iMax]) > 0) {
					iMax = i;
				}
				if (array[i].compareTo(array[iMin]) < 0) {
					iMin = i;
				}
			}
			return auxGetOrderStatistics(array, k, 1, iMin, iMax);					
		} else {
			return null;
		}
		
	}	
	
	private T auxGetOrderStatistics(T[] array, int k, int qntdMinimos, int iMin, int iMax) {
		if (qntdMinimos == k) {
			return array[iMin];
		}
		if (qntdMinimos == array.length) {
			return null;
		}
		int lastMin = iMax;
		for (int i = 0; i < array.length; i++) {
			if (array[i].compareTo(array[lastMin]) < 0) {
				if (array[i].compareTo(array[iMin]) > 0) {
					lastMin = i;
				}
			}
		}
		return auxGetOrderStatistics(array, k, qntdMinimos + 1, lastMin, iMax);
	}
	
	public static void main(String[] args) {
		Integer[] array = new Integer[] {5,4,1,7};
		Integer num = new OrderStatisticsSelectionImpl<Integer>().getOrderStatistics(array, 2);
		System.out.println(num);
	}
	
}
