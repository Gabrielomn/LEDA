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

		if(array == null || array.length == 0 || leftIndex < 0 || rightIndex >= array.length){
			return;
		}

		int maximo = this.max(array, leftIndex, rightIndex);
		int minimo = this.min(array, leftIndex, rightIndex);
		Integer[] copia = Arrays.copyOf(array, array.length);
		int[] freq = this.contaFrequencia(array, leftIndex, rightIndex, maximo, minimo);

		freq = this.somaAcumulada(freq);

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
		if(maximo < 0){
			maximo = 0;
		}

		return maximo;
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

	private int[] contaFrequencia(Integer[] arr, int leftIndex, int rightIndex, int maximo, int minimo){

		int tamanho = maximo - minimo;
		int[] freq = new int[tamanho + 1];

		for (int i = 0; i <= tamanho; i++){
			freq[i] = 0;
		}

		for(int i = leftIndex; i <= rightIndex; i++){
			freq[arr[i] - minimo]++;
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
