package email.indexer.index;

import java.io.Serializable;
import java.util.Set;

public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5088826189317992295L;

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
