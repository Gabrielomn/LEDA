package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if(leftIndex >= rightIndex){
			return;
		}

		if(leftIndex < 0){
			leftIndex = 0;
		}
		if(rightIndex > array.length -1){
			leftIndex = 0;
		}


		int[] volta = partition(array,leftIndex,rightIndex);
		sort(array, leftIndex, volta[0] - 1);
		sort(array, volta[1]+1, rightIndex);
	}

	public int[] partition(T[] array, int leftIndex, int rightIndex){


		T pivot = array[leftIndex];
		int low = leftIndex;
		int high = rightIndex;
		int i = leftIndex;
		while(i <= rightIndex){
			if(i > high){
				break;
			}
			if(array[i].compareTo(pivot) < 0){
				Util.swap(array, i, low);
				i++;
				low++;
			}else if(array[i].compareTo(pivot) > 0) {
				Util.swap(array, i, high);
				high--;
			}else{
				i++;
			}

		}

		int[] newArray = new int[2];
		newArray[0] = low;
		newArray[1] = high;
		return newArray;
	}
}
