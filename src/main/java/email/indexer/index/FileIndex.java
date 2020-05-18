package email.indexer.index;

import java.util.Set;

public interface FileIndex {
	
	/**
	 * Get index name
	 * @return
	 */
	public String getName();
	
	/**
	 * Get all words in index
	 * @return
	 */
	public Set<String> getWords();
	
	/**
	 * Get locations for given word
	 * @param word
	 * @return
	 */
	public Set<Integer> getLocations(String word);
	
	/**
	 * 
	 * @return true if index is empty
	 */
	public boolean isEmpty();
}
