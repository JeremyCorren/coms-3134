/**
 * @author Jeremy Corren [jdc2189]
 * 
 * The AvlMap class represents represents an AVL tree
 * and implements the Map interface.
 */

public class AvlMap<K extends Comparable<K>, V> extends AvlTree<Pair<K,V>> implements Map<K,V> {
	
	/**
     * Insert a key/value pair into the tree.
     */
	public void put(K key, V value) {
		
		insert(new Pair<K,V>(key,value));
	}
	
	/**
     * Returns a value given a key for a key/value pair.
     */
	public V get(K key) {
		
		AvlNode<Pair<K,V>> found = find(new Pair<K, V>(key, null), root);
        if (found == null)
            return null;
        return found.element.value;
	}
	
	/**
     * Finds an item in a subtree by traversing the tree.
     */
	private AvlNode<Pair<K,V>> find(Pair<K,V> x, AvlNode<Pair<K,V>> t) {
		
        if(t == null)
            return null; 
            
        int compareResult = x.compareTo(t.element);
            
        if(compareResult < 0)
            return find(x, t.left);
        else if(compareResult > 0)
            return find(x, t.right);
        else
            return t;    // Match
    }
}