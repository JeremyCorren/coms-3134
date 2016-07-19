
public class StackQueueTester {
	
	public static void main(String[] args) {

		TwoStackQueue<Integer> mailbox = new TwoStackQueue<>();
		for(int i = 1; i < 16; i++)
			mailbox.enqueue(i);
		
		if(mailbox.inboxSize() != 0) {
			System.out.println("Printing inbox: ");
			mailbox.printInbox();
		}
		
		System.out.println("\nPrinting dequeues: ");
		
		for(int i = 1; i < 16; i++)
			System.out.print(mailbox.dequeue() + " ");		
	}
}