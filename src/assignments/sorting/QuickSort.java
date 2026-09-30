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
        if(start>=end){
            return;
        }

        // choose a pivot arbitrarily (chose last one)
        T pivot=array[end];

        ArrayList<T> leftPart = new ArrayList<T>();
        ArrayList<T> rightPart = new ArrayList<T>();

        // put everything in left or right depending on pivot
        for(int i=start;i<end;i++){
            if(array[i].compareTo(pivot) <= 0){
                leftPart.add(array[i]);
            }
            else {
                rightPart.add(array[i]);
            }
        }

        // put it all back in the array  left, then pivot, then right
        int index=start;
        for(int i=0;i<leftPart.size();i++){
            array[index]=leftPart.get(i);
            index++;
        }
        int pivotSpot = index;
        array[index]=pivot;
        index++;
        for(int i=0;i<rightPart.size();i++){
            array[index] = rightPart.get(i);
            index = index + 1;
        }

        // pivot is in the correct place, so sort left the left and right sides
        quickSort(array,start,pivotSpot-1);
        quickSort(array,pivotSpot+1,end);
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
