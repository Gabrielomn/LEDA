package recursao;

import java.util.Arrays;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		int result = 0;
		result += array[array.length - 1];

		return result + calcularSomaArray(Arrays.copyOfRange(array,0, array.length - 2));
	}
	public long calcularFatorial(int n) {
		if(n == 1){
			return 1;
		}else{
			return n*calcularFatorial(n-1);
		}
	}

	public int calcularFibonacci(int n) {
		if(n == 1 || n == 2){
			return 1;
		}else{
			return calcularFibonacci(n -1) + calcularFibonacci( n -2);
		}
	}

	public int countNotNull(Object[] array) {
		if (array.length == 0){
			return 0;
		}else{
			if (array[array.length - 1] == null){
				return 1 + countNotNull(Arrays.copyOfRange(array, 0, array.length -2));
			}else{
				return countNotNull(Arrays.copyOfRange(array, 0, array.length -2));
			}
		}
	}

	public long potenciaDe2(int expoente) {
		if (expoente == 0){
			return 1;
		}else{
			return 2*potenciaDe2(expoente - 1);
		}

	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		if(n == 0){
			return 0;
		}else{
			return progressaoAritmetica(termoInicial + razao, razao, n -1);
		}
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		if(n == 0){
			return 1;
		}
		else{
			return progressaoGeometrica(termoInicial * razao, razao, n-1);
		}
	}
	
	
}
