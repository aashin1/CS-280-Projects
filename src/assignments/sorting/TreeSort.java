package assignments.sorting;

import java.util.Iterator;
import assignments.datastructures.BinarySearchTree;
import java.util.List;



/**
 * TreeSort, a sorting algorithm that runs in O(nlogn) time
 * works by first putting all items into a binary search tree
 * then it traverses the tree in order
 */

public class TreeSort<T extends Comparable<T>> {

    public void sort(List<T> list){
    // intialize a tree
    BinarySearchTree<T> tree = new BinarySearchTree<>(); 

    // add items to tree
        for (T item: list){
        tree.add(item);

        }
    
    // traverse tree in order

        Iterator<T> x = tree.inorder();
        int i = 0;
        while (x.hasNext()) {
            list.set(i++, x.next());
        }
        

    }

  /**
   * validation tests
   * @param args command-line args
   */  

  public static void main(String[] args){

    BinarySearchTree<Integer> tree = new BinarySearchTree<>();

    //add items, but out of order
    tree.add(4);
    tree.add(2);
    tree.add(6);
    tree.add(1);
    tree.add(3);

    // test inorder
    Iterator<Integer> iterator = tree.inorder();
    int[] expected = {1, 2, 3, 4, 6};
    for (int value : expected) {
        assert iterator.next() == value;
    }
    assert !iterator.hasNext();

    System.out.println("treesort passes all tests");

  }

}
