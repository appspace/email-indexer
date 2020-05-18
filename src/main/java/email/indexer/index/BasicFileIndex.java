package email.indexer.index;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BasicFileIndex implements FileIndex {

	private String _name;
	
	private Map<String, HashSet<Integer>> _data = new HashMap<String, HashSet<Integer>>();
	
	public BasicFileIndex(String name) {
		if (name.contains(File.separator)) {
			name = name.substring(name.lastIndexOf(File.separator)+1);
		}
		_name = name;
	}
	
	@Override
	public String getName() {
		return _name;
	}

	@Override
	public Set<String> getWords() {
		return _data.keySet();
	}

	@Override
	public Set<Integer> getLocations(String word) {
		word = word.toLowerCase();
		if (!_data.containsKey(word)) {
			return Collections.emptySet();
		}
		return _data.get(word);
	}

	public void addWord(String word, Integer position) {
		word = word.toLowerCase();
		HashSet<Integer> positions;
		if (!_data.containsKey(word)) {
			positions = new HashSet<Integer>();
			_data.put(word, positions);
		} else {
			positions = _data.get(word);
		}
		positions.add(position);
	}

	@Override
	public boolean isEmpty() {
		return _data.isEmpty();
	}
	
}
