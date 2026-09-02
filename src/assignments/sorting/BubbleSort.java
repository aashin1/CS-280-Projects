package assignments.sorting;

/** 
 * bubble sort for arrays - swaps adjancent elements over an array until fully sorted 
 */

public class BubbleSort extends SortingAlgorithm {


    /**
    sort an array in place with bubble sort

    post condition: 'array' is sorted in ascending order


    @param array an array of integers
    */
    public void sort(Integer[] array){
        for (int k = array.length; k>=2; k--) {  // k is the length of the sub array we are looping through in the inner loop
            for (int i = 0; i < k-1; i++) {
                if (array[i] > array[i+1]) {
                    // swap logic .. swaps adjacent items in array  
                    swap(array, i, i+1);
                }
            }
        }    
    }    
    private void swap(Integer[] array, int i, int j) {
        /**
         * swap two elements within an array
         * 
         * @param array the array to swap values in 
         * @param i the first index to swap
         * @param j the second index to swap 
         */

        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;

    }

    /**
     * validation tests
     * @params args command-line args
     * 
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new BubbleSort());
        System.out.println("bubble sort has passed all tests.");
    }
}

 




