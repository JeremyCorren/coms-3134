/**
 * @author Jeremy Corren [jdc2189]
 * 
 * The SeparateChainingMap class represents a separate chaining hash table 
 * and implements the Map interface. Chains are implemented as linked lists.
 */

import java.util.*;

public class SeparateChainingMap<K extends Comparable<? super K>, V> implements Map<K, V> {
	
	private LinkedList<Pair<K, V>>[] theArray;
	private static final int initial_size = 10;
	
	private int keyCount;
	private int theSize;
	
	@SuppressWarnings("unchecked")
	public SeparateChainingMap() {
		
		theArray = (LinkedList<Pair<K, V>>[]) new LinkedList[initial_size];
		keyCount = 0;
		theSize = initial_size;
	}
	
	/**
     * Generates a hash code, assigned to elements in the hash table.
     */
	public int hashFunction(K key) {
		
		int code = (int) key.hashCode() % theSize;
		return Math.abs(code);
	}
	
	/**
     * Inserts a key/value pair into the hash table.
     * If a pair for a given key already exists, remove and replace with
     * new pair. Otherwise, add to the existing list.
     */
	public void put(K key, V value) {
			
		ensureCapacity();
		
		int index = hashFunction(key);
		Pair<K, V> pair = new Pair<K, V>(key, value);
		
		// no linked list exists for that index
		if(theArray[index] == null) {
			theArray[index] = new LinkedList<Pair<K, V>>();
			theArray[index].addFirst(pair);
			keyCount++;
		}
			
		ListIterator<Pair<K, V>> listIte = null;
		if(theArray[index] != null) {
			listIte = theArray[index].listIterator();
		}
		
		// adding to the list, checking for duplicates
		if(listIte != null) {
			while(listIte.hasNext()) {
				
				Pair<K, V> node = listIte.next();
				if(node.key.equals(key)) {
					listIte.remove();
					listIte.add(pair);
				}
				else {
					listIte.add(pair);
					keyCount++;
				}
			}
		}
	}
	
	/**
     * Returns a value given a key for some key/value pair in the table.
     */
	public V get(K key) {
		
		int index = hashFunction(key);
		ListIterator<Pair<K, V>> listIte = null;
		
		if(theArray[index] != null) {
			listIte = theArray[index].listIterator();
		}
				
		if(listIte != null) {
			while(listIte.hasNext()) {
				Pair<K, V> node = listIte.next();
				if(node.key.equals(key))
					return node.value;
			}	
		}
		
		return null;
	}
	
	/**
     * When the load factor exceeds 1, a new table of double size is created 
     * and elements from its predecessor are stored anew.
     */
	@SuppressWarnings("unchecked")
	private void ensureCapacity() {
		
        if(keyCount/theSize >= 1) {
        	
        	LinkedList<Pair<K, V>>[] doubleArray;
        	doubleArray = (LinkedList<Pair<K, V>>[]) new LinkedList[theSize];
        	
        	for(int i = 0; i < theSize; i++)
        		doubleArray[i] = theArray[i];
        	
        	int newCapacity = theSize*2;
        	theArray = (LinkedList<Pair<K, V>>[]) new LinkedList[newCapacity];
        	
        	for(int i = 0; i < theSize; i++)
        		theArray[i] = doubleArray[i];
        	
        	theSize = newCapacity;
        }
    }
}