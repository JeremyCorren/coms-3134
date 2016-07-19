/**
 * @author Jeremy Corren [jdc2189]
 * 
 * TwoStackQueue class implements a queue built from two array stacks.
 * 
 * 2.1a -- Response to Quebert's mail problem: This strategy implements a 
 * queue in which the enqueue() method pushes items to the inbox stack, 
 * representing the end of the queue, and whose moveToOutbox() method pops items 
 * from the inbox and pushes them to the outbox, which represents the front of 
 * the queue. The dequeue() method pops items from the outbox and into the 
 * mailbag in the order they came in. 
 */

public class TwoStackQueue<T> implements Queue<T> {
	
	private ArrayStack<T> inbox; // inbox stack
	private ArrayStack<T> outbox; // outbox stack
	
	public TwoStackQueue() {
		inbox = new ArrayStack<>();
		outbox = new ArrayStack<>();
	}
	
	/**
	 * To enqueue items onto the back of the queue, push onto inbox stack.
	 * 	
	 * Runtime: O(1), since push() is O(1)
	 */	
	public void enqueue(T x) {
		inbox.push(x);
	}

	public int inboxSize() { 
		return inbox.topOfStack+1; 
	}
	
	public int outboxSize() {
		return outbox.topOfStack+1;
	}
	
	public void printInbox() {
		for (int i = 0; i < inboxSize(); i++)
			System.out.print(inbox.theArray[i] + " ");
	}
	
	public void printOutbox() {
		for(int i = 0; i < outboxSize(); i++)
			System.out.print(outbox.theArray[i] + " ");
	}
	
	/**
	 * Move items from top of inbox stack to the outbox stack.
	 * 	
	 * Runtime: O(N), since while loop is O(N) given N pushes to
	 * inbox stack, and pop() and push() are each O(1)
	 */
	public void moveToOutbox() {
		while(inboxSize() != 0) {
			T item = inbox.pop();
			outbox.push(item);
		}
	}
	
	/**
	 * Dequeue items from front of the TwoStackQueue.
	 * 
	 * @return item from outbox stack in the order they were pushed
	 * into the inbox stack.
	 * 
	 * Runtime: O(N), since moveToOutbox is O(N) and pop() is O(1)
	 */
	public T dequeue() {
		moveToOutbox();
				
		if(outboxSize() == 0)
			throw new IndexOutOfBoundsException("Attempted to dequeue an empty " 
		+ "stack");
		
		T result = outbox.pop();
		return result;
	}
	
	/**
	 * ArrayStack implementation adapted from Prof. Daniel Bauer.
	 * ArrayStack implements the stacks used to construct the
	 * front and back of the TwoStackQueue.
	 */
	private static class ArrayStack<T> {
		
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
	}
}