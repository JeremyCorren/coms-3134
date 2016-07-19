
public class LinkedListTester {
	/**
     * Test the linked list. 
     */
    public static void main(String[] args) {
    	
    	System.out.println("*********************");
        System.out.println("*MyLinkedList Tester*");
        System.out.println("*********************\n");
       
    	MyLinkedList<Integer> lst = new MyLinkedList<>();
    	for(int i = 0; i < 5; i++)
    		lst.add(i);
    	for(int i = 20; i < 30; i++)
    		lst.add(0, i);
    	
    	lst.remove(0);
    	lst.remove(lst.size() - 1);
    	
    	System.out.println("List1: " + lst + "\n");

        
        
        // testing indexOf
        System.out.println("*** testing indexOf() ***"); 
        Integer k = 26;
        System.out.println("The element [" + k + "] is at index " + lst.indexOf(k) + "\n");
        
        // testing reverse
        System.out.println("*** testing reverse() ***");
        lst.reverse();
        System.out.println("List in reverse: " + lst + "\n");
        
        // testing removeDuplicates
        System.out.println("*** testing removeDuplicates() ***");
        for(int i = 8; i > 0; i--)
        	lst.add(i);
        System.out.println("List with duplicates: " + lst);
        
        lst.removeDuplicates();
        System.out.println("List sans duplicates: " + lst + "\n");
        
        
        // testing interleave()
        System.out.println("*** testing interleave() ***");
        MyLinkedList<Integer> lst2 = new MyLinkedList<>();
    	for(int i = 10; i < 25; i++)
            lst2.add(i);
    	System.out.println("List2 (other): " + lst2);
    	
    	lst.interleave(lst2);
    	System.out.println("Interleaved list: " + lst);
    }
}