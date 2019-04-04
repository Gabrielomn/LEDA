package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {


		if (array == null || array.length == 0 || leftIndex < 0 || rightIndex > array.length){
			return;
		}

		int maximo = this.max(array, leftIndex, rightIndex);
		Integer[] copia = Arrays.copyOf(array, array.length);
		int[] freq = this.contaFrequencia(array, leftIndex, rightIndex, maximo);

		freq = this.somaAcumulada(freq);

		for(int i = rightIndex; i >= leftIndex; i--){
			freq[array[i]]--;
			copia[freq[array[i]]] = array[i];
		}

		for(int i = leftIndex; i <= rightIndex; i++){
			array[i] = copia[i];
		}
	}


	private int max(Integer[] arr, int left, int right){
		int maximo = Integer.MIN_VALUE;
		for(int i = left; i <= right; i++){
			if (arr[i] > maximo){
				maximo = arr[i];
			}
		}

		if(maximo < 0){
			maximo = 0;
		}
		return maximo;
	}

	private int[] contaFrequencia(Integer[] arr, int leftIndex, int rightIndex, int maximo){

		int[] freq = new int[maximo + 1];

		for (int i = 0; i < maximo + 1; i++){
			freq[i] = 0;
		}

		for(int i = leftIndex; i <= rightIndex; i++){
			freq[arr[i]]++;
		}

		return freq;

	}

	private int[] somaAcumulada(int[] arr){
		int[] saida = new int[arr.length];
		saida[0] = arr[0];
		for(int i = 1; i < saida.length; i++){
			saida[i] = arr[i] + saida[i-1];
		}

		return saida;
	}

}
