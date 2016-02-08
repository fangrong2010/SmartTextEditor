package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor
	public DictionaryLL(){
		this.dict = new LinkedList<String>();
	}


    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	word = word.toLowerCase();
//    	if(dict.size() == 0){
//    		dict.add(word);
//    		return true;
//    	}
//    	else{
//    		for(int i = 0; i < dict.size(); i++){
//    			int comp = word.compareTo(dict.get(i));
//    			if(comp < 0){ // if word lexicographically precedes dict.get(i)
//    				dict.add(i, word);
//    				return true;
//    			}
//    			else if(comp > 0) 
//    				continue;
//    			else // if word equals to dict.get(i), return false
//    				return false;
//    		}
//    	}
//    	dict.addLast(word); // word lexicographically follows the last word in dict
//    	return true;
    	if(dict.contains(word))
    		return false;
    	return dict.add(word);
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
//    	for(int i = 0; i < size(); i++){
//    		int comp = s.compareToIgnoreCase(dict.get(i));
//    		if(comp < 0){ 
//    			return false;
//    		}
//    		else if(comp > 0){
//    			continue;
//    		}
//    		else{
//    			return true;
//    		}
//    	}
//        return false; // comp lexicographically follows the last word in dict, return false
    	for(String word : dict){
    		int comp = word.compareToIgnoreCase(s);
    		if(comp == 0)
    			return true;
    	}
    	return false;
    }

    
}
