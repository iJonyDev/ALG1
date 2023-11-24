/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EPD7;

import java.util.Arrays;

/**
 *
 * @author mgarciat[at]upo[dot]es
 * @see
 * http://faculty.simpson.edu/lydia.sinapova/www/cmsc250/LN250_Weiss/L16-QuickSort.htm#alg
 *
 */
public class QuickSort {

    public static void sort(int[] array) {
        QuickSort.sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int low, int high) {

        if (low < high) {
            int middle = QuickSort.partition(array, low, high);
            QuickSort.sort(array, low, middle - 1);
            QuickSort.sort(array, middle + 1, high);
        }
    }

    /**
     * Partitions the instances around a pivot. Used by quicksort and
     * kthSmallestValue.
     *
     * @param array the array of integers to be sorted
     * @param index the index into the array of integers
     * @param l the first index of the subset
     * @param r the last index of the subset
     *
     * @return the index of the middle element
     */
    private static int partition(int[] v, int low, int high) {
        int pivot = v[high];//pivot_item = a[low];
        int left = low;
        int right = high;
        while (left < right) {
            /* Move left while item < pivot */
            while (left < right && v[left] < pivot) {
                left++;
            }
            /* Move right while item > pivot */
            while (left < right && v[right] >= pivot) {
                right--;
            }
            if (left < right) {
                int aux = v[left];
                v[left] = v[right];
                v[right] = aux; //         SWAP(a, left, right);
            }
        }
        /* right is final position for the pivot */
        v[high] = v[left];
        v[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
//        int[] array = new int[]{4, 1, 3, 2, 3, 21, 43, 3, 34, 23, 12, 43, 100, 88, 324, 2, 42, 22, 1, 2, 5454, 3};
        int[] array = new int[]{4,3,2,1,5,-6,8,7,0,0};

        QuickSort.sort(array);
        System.out.println(Arrays.toString(array));
    }

}