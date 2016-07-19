
public class AvlMapTester {

	public static void main(String [] args) {
		
		AvlMap<String, Integer> m = new AvlMap<String, Integer>();
		m.put("cat", 2);
		m.put("cat", 3);
		m.put("frog", 6);
	
		System.out.println(m.get("cat"));
	}
}