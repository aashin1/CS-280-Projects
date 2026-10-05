package assignments.sorting;
import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T>  {
    public MergeSort() {} //for javadocs

    /**
     * sorts the array with mergesort
     * @param array the array to sort
     */
    public void sort(T[] array) {
        // this "work" array is the only allocation in the whole logic, made once here
        T[] work = array.clone();

        mergeSort(array, work, 0, array.length - 1);
    }


    /**
     * mergesort
     * @param array the array
     * @param start start index
     * @param work buffer space the same size as array, which will be utilized during merging
     * @param end end index
     */
    public void mergeSort(T[] array, T[] work, int start, int end)
    {
        // base case, 0 or 1 items is already sorted
        if(start >= end){
            return;
        }

        // split the list in half
        int middle = (start + end) / 2;

        // sort the left half and sort the right half
        // pass the same array and buffer down with indices, rather than creating new subarrays for each half
        mergeSort(array, work, start, middle);
        mergeSort(array, work, middle + 1, end);

        // cursors start at the smallest (leftmost) item in each half
        int leftCursor = start;
        int rightCursor = middle + 1;

        // instead of creating a new list every merge, write it into the work array. workcursor starts at "start" so each merge uses its own slice of buffer
        int workCursor = start;

        // take smaller of two and move that cursor to next element
        while(leftCursor <= middle && rightCursor <= end ){
            if(array[leftCursor].compareTo(array[rightCursor]) <= 0){
                work[workCursor] = (array[leftCursor]);
                leftCursor++;
            }
            else {
                work[workCursor] = array[rightCursor];
                rightCursor++;
            }
            workCursor++;
        }

        // one half is all added, so bring in the rest of the other half in order
        while(leftCursor <= middle){
            work[workCursor] = array[leftCursor];
            workCursor++;
            leftCursor++;
        }

        while(rightCursor <= end){
            work[workCursor] = array[rightCursor];
            rightCursor++;
            workCursor++;
        }

        // copy the merged section from the work array back into the original array. only the references are copied, nothing new is made
        for(int i = start; i < end; i++){
            array[i] = work[i];
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

