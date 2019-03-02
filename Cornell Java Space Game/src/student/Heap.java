package student;

import java.lang.reflect.Array;

import java.util.*;

// import Heap.VP;

/** An instance is a min-heap of distinct values of type V with
 *  priorities of type double. Since it's a min-heap, the value
 *  with the smallest priority is at the root of the heap. */
public class Heap<V> {

    /** Class Invariant:
     *   1. b[0..size-1] represents a complete binary tree. b[0] is the root;
     *      For each k, b[2k+1] and b[2k+2] are the left and right children of b[k].
     *      If k != 0, b[(k-1)/2] (using integer division) is the parent of b[k].
     *   
     *   2. For k in 0..size-1, b[k] contains the value and its priority.
     *   
     *   3. The values in b[0..size-1] are all different.
     *   
     *   4. For k in 1..size-1, (b[k]'s priority) >= (b[k]'s parent's priority).
     *   
     *   map and the tree are in sync, meaning:
     *   
     *   5. The keys of map are the values in b[0..size-1].
     *      This implies that this.size = map.getSze().
     *   
     *   6. if value v is in b[k], then map.get(v) returns k.
     */
    protected VP[] b;
    protected int size;
    protected HashMap<V, Integer> map;

    /** Constructor: an empty heap with capacity 10. */
    public Heap() {
        b= createVPArray(10);
        map= new HashMap<V, Integer>();
    }

    /** A VP object houses a Value and a Priority. */
    class VP {
        V value;           // The value
        double priority;   // The priority

        /** An instance with value v and priority p*/
        VP(V v, double p) {
            value= v;
            priority= p;
        }

        /** Return a representation of this VP object. */
        @Override public String toString() {
            return "(" + value + ", " + priority + ")";
        }
    }
 
    /** Insert v with priority p into the heap.
     *  Throw an illegalArgumentException if v is already in the heap.
     *  The expected time is logarithmic and the worst-case time is linear
     *  in the size of the heap. */
    public void insert(V v, double p) throws IllegalArgumentException {
        // TODO #1: Write this whole method. Note that bubbleUp is not implemented,
        // so calling it will have no effect (yet). The first tests of insert, using
        // test00Insert, ensure that this method maintains fields b and map properly,
        // without worrying about bubbling up. 
        
       //Testing procedure test00Insert should work. Look at its specification.
        
        // Do NOT call bubbleUp until the class invariant is true except
        //for the need to bubble op.
        // Calling bubbleUp is the last thing to be done.
    		if (map.containsKey(v)) {
    			throw new IllegalArgumentException("V is in the heap");
    		}
    		ensureSpace();
    		VP vp = new VP(v, p);
    		b[size] = vp;
    		map.put(v, size);
    		size += 1;
        bubbleUp(size-1);
    }


    /** If size = length of b, double the length of array b.
     *  The worst-case time is proportional to the length of b. */
    protected void ensureSpace() {
        //TODO #2. Any method that increases the size of the heap must call
        // this method first. 
        //
        // To make a copy of b, use a method in class java.util.Arrays
        // that will create the larger array and do the copying.
        //
        // If you write this method correctly AND method
        // insert calls this method appropriately, testing procedure
        // test10ensureSpace will not find errors. 
    		VP[] c = Arrays.copyOf(b,size*2);
    		if (size == b.length)
    			b = c;
    }

    /** Return the size of this heap.
     *  This operation takes constant time. */
    public int size() {
        return size;
    }

    /** Swap b[h] and b[k].
     *  Precondition: 0 <= h < heap-size, 0 <= k < heap-size. */
    void swap(int h, int k) {
        assert 0 <= h  &&  h < size;
        assert 0 <= k;
        assert k < size;
        //TODO 3: When bubbling values up and (later on) down, two values,
        // say b[h] and b[k], will have to be swapped. At the same time,
        // field VP has to be maintained.
        // In order to always get this right, use method swap for this.
        // Method swap is tested by testing procedure test13Swap --it will
        // find no errors if you write this method properly.
        // 
        // Read the A6 FAQs note about map.put(...).
        VP temp = b[h];
        b[h] = b[k];
        b[k] = temp;
        map.put(b[k].value, k);
        map.put(b[h].value, h);
    }

    /** Bubble b[k] up the heap to its right place.
     *  Precondition: Priority of every b[i] >= its parent's priority
     *                except perhaps for b[k]  AND
     *                0 <= k < size. */
    void bubbleUp(int k) {
        // TODO #4 As you know, this method should be called within insert in
        //  order to bubble a value up to its proper place, based on its priority.
        // Do not use recursion. Use iteration.
        // If this method is written properly, testing procedure
        // test15Insert_BubbleUp() will not find any errors.
        // Also, test17Insert_BubbleUpDupPriorities tests that two
        // nodes with the same priority are NOT swapped when bubbling up.
        assert 0 <= k  &&  k < size;
        int p = (k-1)/2;
        while(k > 0 && b[k].priority < b[p].priority) {
        		swap(p,k);
        		k = p;
        		p = (k-1)/2;
        }
    }

    /** Return the value of this heap with lowest priority. Do not
     *  change the heap. This operation takes constant time.
     *  Throw a NoSuchElementException if the heap is empty. */
    public V peek() {
        // TODO 5: Do peek. This is an easy one. 
        //         test20Peek() will not find errors if this is correct.
    		if(size == 0) 
    			throw new NoSuchElementException();
    		return b[0].value;
    }
    		
    
    /** Bubble b[k] down in heap until it finds the right place.
     *  If there is a choice to bubble down to both the left and
     *  right children (because their priorities are equal), choose
     *  the right child.
     *  Precondition: 0 <= k < size   and
     *                Each b[i]'s priority <= its childrens' priorities 
     *                except perhaps for b[k] */
    void bubbleDown(int k) {
        // TODO 6: We suggest implementing and using smallerChildOf, though
        //         you don't  have to. DO NOT USE RECURSION. Use iteration.
        //         Read the Piazza A6 FAQ note On testing whether a
        //         child b[k] exists.
        //
        //         When this method is correct, testing procedures
        //         test30Bubbledown and test31Bubbledown will not find errors.
        assert 0 <= k ;
        assert k < size;
        	while(2*k+1 < size) {
        		int c = smallerChildOf(k);
        		if(b[k].priority > b[c].priority) {
        			swap(k, c);
            		k = c;
        		}
        		else
        			return;
        }
    }
    
    /** Return the index of the smaller child of b[n].
     *  If the two children have the same priority, choose the right one.
     *  Precondition: left child exists: 2n+1 < size of heap */
    protected int smallerChildOf(int n) {
    		int rightChild = 2*n + 2;
    		int leftChild = 2*n + 1;
    		int index = 0;
    		if(2*n + 2 <size) {
    			if(b[leftChild].priority < b[rightChild].priority) 
    				index = leftChild;
    			else if(2*n + 2 <size) {
    				if(b[leftChild].priority >= b[rightChild].priority)
    					index = rightChild;
    			}
    		}
    		else {
    			index = leftChild;
    		}
    		return index;
    }

    /** Remove and return the element of this heap with lowest priority.
     *  The expected time is logarithmic and the worst-case time is linear
     *  in the size of the heap.
     *  Throw a NoSuchElementException if the heap is empty. */
    public V poll() {
        // TODO 7: When this method is written correctly, testing procedure
        //         test32Poll_BubbleDown_NoDups will not find errors.
        // 
        //         Note also testing procedure test40testDuplicatePriorities
        //         This method tests to make sure that when bubbling up or down,
        //         two values with the same priority are not swapped.
    	    if(size == 0) 
    	    		throw new NoSuchElementException();
        VP lowest = b[0];
        swap(0, size-1);
        map.remove(lowest.value);
        size -= 1;
        if (size > 0) {
        		bubbleDown(0);
        }
        return lowest.value;
    }

    /** Change the priority of value v to p.
     *  The expected time is logarithmic and the worst-case time is linear
     *  in the size of the heap.
     *  Throw an IllegalArgumentException if v is not in the heap. */
    public void changePriority(V v, double p) {
        // TODO  8: When this method is correctly implemented, testing procedure
        //          test50ChangePriority() won't find errors.
    		if (!map.containsKey(v)) 
    			throw new IllegalArgumentException();
    		int i = map.get(v);
    		double prev = b[i].priority;
    		b[i].priority = p;
    		if(p > (prev-1)/2) 
    			bubbleDown(i);
    		else if(p < (prev-1)/2)
    			bubbleUp(i);
    }

    /** Create and return an array of size n.
     *  This is necessary because generics and arrays don't interoperate nicely.
     *  A student in CS2110 would not be expected to know about the need
     *  for this method and how to write it. */
    VP[] createVPArray(int n) {
        return (VP[]) Array.newInstance(VP.class, n);
    }
}