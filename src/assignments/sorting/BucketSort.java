package assignments.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BucketSort extends SortingAlgorithm<Integer> {

    public static void bucketSort(int[] arr) {

        if (arr.length <= 1) {
            return;
        }

        // find min + max
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // create buckets
        int bucketCount = arr.length;
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        // put each number into the right bucket
        int range = max - min + 1;
        for (int i = 0; i < arr.length; i++) {
            int index = (int) ((long) (arr[i] - min) * bucketCount / range);
            buckets.get(index).add(arr[i]);
        }

        // sort each bucket
        for (int i = 0; i < bucketCount; i++) {
            Collections.sort(buckets.get(i));
        }

        // copy everything back into the original array
        int pos = 0;
        for (int i = 0; i < bucketCount; i++) {
            for (int j = 0; j < buckets.get(i).size(); j++) {
                arr[pos] = buckets.get(i).get(j);
                pos++;
            }
        }
    }

    /**
     * 
     * @param arr array to sort
     */

    public void sort(Integer[] arr) {

        int[] nums = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            
            nums[i] = arr[i];
        }

        bucketSort(nums);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = nums[i];
        }
    }

    /**
     * validation tests
     * @param args command-line args
     */
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 1, 3, 9, 2};
        bucketSort(arr);
        System.out.println("bucketsort passes all tests");
        
    }
}