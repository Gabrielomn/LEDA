package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		int maximo = this.max(array, leftIndex, rightIndex);
		int minimo = this.min(array, leftIndex, rightIndex);
		Integer[] copia = Arrays.copyOf(array, array.length);
		int tamanho = maximo - minimo;
		int[] freq = new int[tamanho + 1];
		for (int i = 0; i <= tamanho; i++){
			freq[i] = 0;
		}
		for(int i = leftIndex; i <= rightIndex; i++){
			freq[array[i] - minimo]++;
		}
		System.out.println(Arrays.toString(freq));

		for(int i = 1; i < freq.length; i++){
			freq[i] += freq[i-1];
		}

		System.out.println(Arrays.toString(freq));

		for(int i = rightIndex; i >= leftIndex; i--){
			freq[array[i] - minimo]--;
			copia[freq[array[i] - minimo]] = array[i];
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

	private int min(Integer[] arr){
		int minimo = Integer.MAX_VALUE;

		for(int i = 0; i < arr.length; i++){
			if (minimo > arr[i]){
				minimo = arr[i];
			}
		}

		return minimo;
	}

	private int min(Integer[] arr, int left, int right){
		int minimo = Integer.MAX_VALUE;

		for(int i = left; i <= right; i++){
			if (minimo > arr[i]){
				minimo = arr[i];
			}
		}

		return minimo;
	}
}
