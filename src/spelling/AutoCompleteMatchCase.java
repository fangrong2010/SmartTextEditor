package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		word = word.toLowerCase();
		char[] wordArray = word.toCharArray();
		TrieNode curr = root;
		for(int i = 0; i < wordArray.length; i++){
			char c = wordArray[i];
			TrieNode next = curr.insert(c);
			if(next != null){ // newly created node
				if(i == wordArray.length-1){ // the last char in word
					next.setEndsWord(true); // this is a new word
					size++;
					return true;
				}
				else
					curr = next;
			}
			else{ // this char already exists in the level
				curr = curr.getChild(c);
				if(i == wordArray.length-1){ // the last char in the word
					if(curr.endsWord()) // this word already exist in the dictionary
						return false;
					else{
						curr.setEndsWord(true);
						size++;
						return true;
					}
				}				
			} //else
		} //for
				
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		s = s.toLowerCase();
		char[] sArray = s.toCharArray();
		TrieNode curr = root;
		for(int i = 0; i < sArray.length; i++){
			char c = sArray[i];
			TrieNode next = curr.getChild(c);
			if(next == null) // no link from sArray[i-1] to sArray[i]
				return false;
			else{
				curr = next;
			}
		}
		return curr.endsWord(); // check if the node correponding to the last char in s is a word
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions   	 
    	 List<String> predictions = new LinkedList<String>();
    	 if(numCompletions == 0)
    		 return predictions;
    	 List<TrieNode> descendents = new LinkedList<TrieNode>();
    	 int num = 0;
    	 prefix = prefix.toLowerCase();
		 char[] pArray = prefix.toCharArray();
 		 TrieNode curr = root;
 		 for(int i = 0; i < pArray.length; i++){
 			 char c = pArray[i];
 			 TrieNode next = curr.getChild(c);
 			 if(next == null) // no link from sArray[i-1] to sArray[i]
 				return predictions; // return an empty list
 			 else
 				curr = next;
 		 }
 		 descendents.add(curr);
 		 while(!descendents.isEmpty() && num < numCompletions){
 			 TrieNode node = descendents.remove(0);
 			 if(node.endsWord()){ // a word ends at node, add the word to the returned list
 				 predictions.add(node.getText());
 				 num++;
 				 if(num == numCompletions)
 					 return predictions;					 
 				 else // more words in need, add all its children to the queue
 					 descendents.addAll(node.getValidChildren());
 			 }
 			else{ // more words in need, add all its children to the queue
				 descendents.addAll(node.getValidChildren());
			}
 		 }   	 
 		 if(num < numCompletions){ // if not enough words can be found
 			 System.out.print("predictCompletions warning: "); 
 			 System.out.println(numCompletions + "words requested, but only" + num + "words predicted.");
 		 }
 		 return predictions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}