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

		int maximo = this.max(array);
		Integer[] copia = Arrays.copyOf(array, array.length);
		int[] freq = new int[maximo + 1];
		for (int i = 0; i < maximo + 1; i++){
			freq[i] = 0;
		}

		for(int i = leftIndex; i <= rightIndex; i++){
			freq[array[i]]++;
		}

		for(int i = 1; i <= maximo; i++){
			freq[i] += freq[i-1];
		}

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
		for(int i = left; i < right; i++){
			if (arr[i] > maximo){
				maximo = arr[i];
			}
		}

		return maximo;
	}

	private int max(Integer[] arr){
		int maximo = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++){
			if (arr[i] > maximo){
				maximo = arr[i];
			}
		}
		if(maximo < 0){
			maximo = 0;
		}
		return maximo;
	}

}
