package email.indexer.index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InMemoryIndex implements Index {

	private Map<String, List<Location>> _locations = new HashMap<>();
	
	@Override
	public void addFileIndex(FileIndex fileIndex) {
		for (String word : fileIndex.getWords()) {
			Location location = new Location(fileIndex.getName(), fileIndex.getLocations(word));
			if (!_locations.containsKey(word)) {
				_locations.put(word, new ArrayList<>());
			} 
			_locations.get(word).add(location);
		}
	}

	@Override
	public List<Location> getLocations(String word) {
		if (word == null) {
			return Collections.emptyList();
		}
		word = word.trim().toLowerCase();
		if (!_locations.containsKey(word)) {
			return Collections.emptyList();
		}
		return _locations.get(word);
	}

	@Override
	public Set<String> getWords() {
		return _locations.keySet();
	}

	
}
