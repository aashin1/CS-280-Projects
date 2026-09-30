package assignments.sorting;
import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T>  {
    public MergeSort() {} //for javadocs

    /**
     * sorts the array with mergesort
     * @param array the array to sort
     */
    public void sort(T[] array) {
        mergeSort(array,0,array.length-1);
    }

    /**
     * mergesort
     * @param array the array
     * @param start start index
     * @param end end index
     */
    public void mergeSort(T[] array, int start, int end)
    {
        // base case, 0 or 1 items is already sorted
        if(start>=end){
            return;
        }

        // split the list in half
        int middle=(start+end)/2;

        // sort the left half and sort the right half
        mergeSort(array, start, middle);
        mergeSort(array, middle + 1, end);

        // cursors start at the smallest (leftmost) item in each half
        int leftCursor = start;
        int rightCursor = middle + 1;
        ArrayList<T> newList = new ArrayList<T>();

        // take the smaller of the two and move that cursor to the next item
        while(leftCursor <=middle && rightCursor <= end ){
            if(array[leftCursor].compareTo(array[rightCursor]) <= 0){
                newList.add(array[leftCursor]);
                leftCursor++;
            }
            else {
                newList.add(array[rightCursor]);
                rightCursor = rightCursor + 1;
            }
        }

        // one half is all added, so bring in the rest of the other half in order
        while(leftCursor<=middle){
            newList.add(array[leftCursor]);
            leftCursor++;
        }
        while(rightCursor<=end){
            newList.add(array[rightCursor]);
            rightCursor++;
        }

        // copy the new list back into the array
        for(int i=0;i<newList.size();i++){
            array[start+i]=newList.get(i);
        }
    }

    /**
     * Validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new MergeSort<Integer>());
        System.out.println("merge sort has passed all tests.");
    }
}
