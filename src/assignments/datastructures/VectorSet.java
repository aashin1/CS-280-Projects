package assignments.datastructures;

import java.util.Iterator;

import adt.Set;

/**
 * 
 * VectorSet, works by 
 * @param <T>
 */

public class VectorSet<T> implements Set<T> {
    private Vector<T> items;

    /**
     * intialiaze a new set
     */
    public VectorSet() {
        this.items = new Vector<>();
        
    }

    /**   
     * compute number of items in this collection
     * @return int, number of items
     */

    public int length(){
        return this.items.length();
    }
    
    /**
     * Check if the set contains a given value.
     * @param value the value to look for
     * @return true iff the set contains value
     */

    public boolean contains(T value){
        return this.items.contains(value);
    }

    /**
     * Add an item to the set, if it is not already present.
     * @param value the value to add
     * @return true iff the value was added, false if the set already contained it
     */

    public boolean add(T value){
        if (this.contains(value)){
            return false;
        }
        this.items.push(value);
        return true;
    }

    /**    
     *  Remove an item from the set, if it is present.
     * @param value the value to remove
     * @return true iff the value was removed
     */

    public boolean remove(T value){
        for (int i =0; i < this.items.length(); i++){
            if (value.equals(this.items.at(i))){
                T last = items.pop();
                if (i < this.items.length()){
                    this.items.set(i, last);
                }
                return true;
            }
        }
        return false;
    }

     /**
     * Iterate over the items in this set
     * @return a new iterator starting at the first item
     */

    public Iterator<T> iterator() {
        return this.items.iterator();
    }

    /**
     * run validation tests
     * @param args command line args
     */
       public static void main(String[] args) {
       Set.validate(new VectorSet<>());
       System.out.println("vectorset passes all tests.");
   }

}
