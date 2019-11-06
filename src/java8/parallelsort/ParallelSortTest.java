package java8.parallelsort;

import java.util.Arrays;

/**
 * parallelSort并行排序
 * @see Arrays
 */
public class ParallelSortTest
{
    public static void main(String[] args)
    {
        int[] array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Arrays.parallelSort(array, 0, 6);
        System.out.println(Arrays.toString(array));
        Arrays.parallelSort(array);
        System.out.println(Arrays.toString(array));
    }
}
