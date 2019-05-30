package adt.heap;

import java.util.Arrays;
import java.util.List;

public class random {
    public static void main(String[] args) {
        String frase = "Gabriel thaynnara joao lucas";




        String[] array = frase.split(" ");;
        List<String> list = Arrays.asList(array);

        list.forEach(n -> System.out.println(n));
        int n = 0;
        List<String> nomes = list.join();
    }
}
