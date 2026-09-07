package assignments.sorting;

public class InsertionSort<T extends Comparable<T>>  extends SortingAlgorithm<T> {
    /**
     * post condition: 'array' is sorted in ascending order
     * @param array an array of integers
     */

    public void sort(T[] array) {
        for (int i = 1; i < array.length; i++)  {
            // loop starts at second index, because first is already sorted/fixed
           T currentValue = array[i];
           
           int j  = i - 1; 
           while (j >= 0 && array[j].compareTo(currentValue)>0) {
            array[j + 1] = array[j];
            j--; 

           }
        array[j +1] = currentValue;   
        }
    }
    public static void main(String[] args) {
        SortingAlgorithm.validate(new InsertionSort<Integer>());
        System.out.println("insertion sort has passed all tests.");
    }

}
