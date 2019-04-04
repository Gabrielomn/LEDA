package sorting.linearSorting;

import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        CountingSort sorter = new CountingSort();
        Integer[] arr = { 30, 28, 7, 29, 11, 26, 4, 22, 23,
                31 };
        sorter.sort(arr, 0 , arr.length -1 );


        System.out.println(Arrays.toString(arr));
    }
}
