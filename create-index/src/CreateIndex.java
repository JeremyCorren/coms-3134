/**
 * @author Jeremy Corren [jdc2189]
 * 
 * The CreateIndex class indexes the words contained in a text file.
 */

import java.io.*;
import java.util.*;

public class CreateIndex {
	
	private HashMap<String, MyLinkedList<Integer>> hm;
	private int lineCount;
	
	public CreateIndex() {
		
		hm = new HashMap<>();
	}
	
	/**
     * Reads in a text file line by line and extracts each word.
     * Inserts individual words into map as keys; the values are given
     * by the line of the text file in which the word is found.
     */
	public HashMap<String, MyLinkedList<Integer>> read(File file) throws FileNotFoundException {
		
		Scanner in = new Scanner(file);
		
		while(in.hasNextLine() == true) {
			String line = in.nextLine();
			String[] arr = line.split(" ");
						
			for(String str : arr) {
				
				if(hm.containsKey(str)) {
					MyLinkedList<Integer> lines = hm.get(str);
					lines.add(lineCount);
					hm.put(str, lines);
				}
				
				else {
					MyLinkedList<Integer> lines = new MyLinkedList<Integer>();
					lines.add(lineCount);
					hm.put(str, lines);
				}
			}
			lineCount++;
		}
		in.close();
		return hm;
	}
	
	/**
     * Returns a list containing all of the line numbers on which a
     * given word (key) is found.
     */
	public MyLinkedList<Integer> getIndex(String word) {
		
		if(hm.containsKey(word))
			return hm.get(word);
		else
			return null;
	}
	
	/**
     * Prints the map as a collection of key/value pairs.
     */
	public static void print(HashMap<String, MyLinkedList<Integer>> map) {
		
		for(String key : map.keySet())
			System.out.println(key + " " + map.get(key));
	}
	
	/**
     * Tester class: reads a text file in from the command-line.
     */
	public static void main(String[] args) throws FileNotFoundException {
		
		CreateIndex c = new CreateIndex();
		
		HashMap<String, MyLinkedList<Integer>> m = c.read(new File(args[0]));
		
		CreateIndex.print(m);
	}
	
}