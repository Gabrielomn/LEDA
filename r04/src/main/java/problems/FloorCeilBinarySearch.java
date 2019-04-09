package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if(array== null){
			return null;
		}
		int indice = buscaBinaria(array, x, 0 ,array.length) - 1;
		if(indice < 0 || indice > array.length){
			return null;
		}
		if (array[indice + 1] == x){
			return array[indice + 1];
		}else{
			return array[indice];
		}
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		if(array== null){
			return null;
		}
		int indice = buscaBinaria(array, x, 0 ,array.length);
		if(indice >= array.length || indice < 0){
			return null;
		}
		return array[indice];
	}

	public int buscaBinaria(Integer[] array, Integer x, int left, int right){
		if(left >= right){
			return left;
		}
		if (left < 0){
			return 0;
		}

		int meio = (left + right)/2;
		if(x.compareTo(array[meio]) == 0){
			return meio;
		}
		else if(x.compareTo(array[meio]) > 0){
			return buscaBinaria(array, x,meio + 1,right);
		}else{
			return buscaBinaria(array, x,left, meio);
		}


	}

}
