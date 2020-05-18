package email.indexer.searchtree;

import java.io.Serializable;
import java.util.List;

import email.indexer.index.Location;

public interface SearchTree extends Serializable {

	public void addLocations(String word, List<Location> locations);

	public List<Location> getLocations(String word);
}
