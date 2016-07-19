public class ArrayStack<T> {
	
	private static int default_size = 10;
	int topOfStack; // tracks end of array position
	private T[] theArray;
	    
	@SuppressWarnings("unchecked")
	public ArrayStack(){
		theArray = (T[]) new Object[default_size];
		topOfStack = -1;
	}
	    
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int size) {
		if (size > theArray.length) {
			T[] old = theArray;
	        size = size * 2;
	        theArray = (T[]) new Object[old.length * 2];
	        for (int i = 0; i < old.length; i++) 
	        	theArray[i] = old[i];
	        }
	    }
	    
	public void push(T x) {
		topOfStack++;
		ensureCapacity(topOfStack + 1);
		theArray[topOfStack] = x;
	}

	public T pop() {
		if (topOfStack == -1)
			throw new IndexOutOfBoundsException("Attempted to pop from "
	            		+ "empty stack!");
	        T result = theArray[topOfStack];
	        topOfStack--;
	        return result;
	    }

	public T top() {
		return theArray[topOfStack];
	}
}