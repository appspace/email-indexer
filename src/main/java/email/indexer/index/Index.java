package email.indexer.index;

import java.util.List;
import java.util.Set;

public interface Index {

	public void addFileIndex(FileIndex fileIndex);

	public List<Location> getLocations(String word);
	
	public Set<String> getWords();
	
}
