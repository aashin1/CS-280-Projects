package assignments.sorting;

public class InsertionSort extends SortingAlgorithm {
    /**
     * post condition: 'array' is sorted in ascending order
     * @param array an array of integers
     */

    public void sort(Integer[] array) {
        for (int i = 1; i < array.length; i++)  {
            // loop starts at second index, because first is already sorted/fixed
           int currentValue = array[i];
           
           int j  = i - 1; 
           while (j >= 0 && array[j] > currentValue) {
            array[j + 1] = array[j];
            j--; 

           }
        array[j +1] = currentValue;   
        }
    }
    public static void main(String[] args) {
        SortingAlgorithm.validate(new InsertionSort());
        System.out.println("insertion sort has passed all tests.");
    }

}
