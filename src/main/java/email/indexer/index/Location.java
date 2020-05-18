package email.indexer.index;

import java.util.Set;

public class Location {

	private final String _fileName;
	
	private final Set<Integer> _lines;
	
	public Location(String fileName, Set<Integer> lines) {
		_fileName = fileName;
		_lines = lines;
	}

	public String getFileName() {
		return _fileName;
	}

	public Set<Integer> getLines() {
		return _lines;
	}

	
}
