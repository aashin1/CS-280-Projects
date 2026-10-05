package assignments.sorting;
import java.util.ArrayList;

public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T>  {
    public QuickSort() {} //for javadocs

    /**
     * sorts the array with quicksort
     * @param array the array to sort
     */
    public void sort(T[] array) {
        quickSort(array,0,array.length-1);
    }

    /**
     * quicksort
     * @param array the array
     * @param start start index
     * @param end end index
     */
    public void quickSort(T[] array, int start, int end)
    {
        // base case
        if(start >= end){
            return;
        }

        // choose a pivot arbitrarily (chose last one)
        // no new object is made when determing a pivot, it just acts as a reference
        T pivot = array[end];

        // in order to keep track of where lements smaller than the pivot shall go
        // instead of a seperate smaller list, a int index is made 

        int smallElementi = start - 1;

        // put everything in left or right depending on pivot
        for(int i = start; i< end; i++){
            if(array[i].compareTo(pivot) <= 0){
               smallElementi = smallElementi + 1;
               T temp = array[smallElementi];
               array[smallElementi] = array[i];
               array[i] = temp;
            }
        }

        // put pivot element into its final correct spot


        int pivotSpot = smallElementi + 1;
        T tempPivot = array[pivotSpot];
        array[pivotSpot] = array[end];
        array[end] = tempPivot;

  
        // pivot is in the correct place, so sort left the left and right sides

        quickSort(array, start, pivotSpot-1);
        quickSort(array, pivotSpot+1, end);
    
    }
    /**
     * Validation tests.
     * @param args command-line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate(new QuickSort<Integer>());
        System.out.println("quick sort has passed all tests.");
    }
}

