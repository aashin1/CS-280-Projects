package assignments.datastructures;
import java.util.Iterator;
import adt.Tree;
import adt.Set;



public class TreeSet<T extends Comparable<T>> implements Set<T> {
    private BinarySearchTree<T> items;

    /**
     * intaliaze a new set
     */
    public TreeSet() {
        this.items = new BinarySearchTree<>();
    }
/**
 * compute the number of items in this set
 * @return the number of items
 */
    public int length() {
        return this.items.length();
    }

    /**   
     * check if set conatins a given value
     * @param value the value to look for
     * @return true iff the set contains value
     */

    public boolean contains(T value){
        return this.items.contains(value);
    }
    /**   
     * add item to set, if it is not already present
     * @return true iff the valuewas added, false if the set had already contained it
     */

    public boolean add(T value){
        if (this.contains(value)){
            return false;
        }
        this.items.add(value);
        return true;
    }

    /**
     * remove item from set, if it is present
     * @param value to remove
     * @return true iff the value was removed, false if the set did not contain it
     */

    public boolean remove(T value){
        return this.items.remove(value);
    }

    /**
     * iterate over items in this set in ascending order
     * @return a new iterator starting at the smallest item
     */
    public Iterator<T> iterator(){
        return this.items.iterator();
    }

    /**
     * run validation tests
     * @param args command-line args
     */

    public static void main(String[] args){
        Set.validate(new TreeSet<>());
        System.out.println("treeset passes all tests");
    }
}
