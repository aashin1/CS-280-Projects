package assignments.sorting;

public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
/**
 * post condition: array is sorted in ascending order
 * @param array an array of integers
 */

    public void sort(T[] array) {
        for (int i = 0; i <array.length - 1; i++) { // this is the position we are currently filling in (i)
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex])<0) {
                    minIndex = j;
                }
            }
            T temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }

    }
    public static void main(String[] args) {
        SortingAlgorithm.validate(new SelectionSort<Integer>());
        System.out.println("selection sort has passed all tests.");
    }
}