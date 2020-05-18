package email.indexer.searchtree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import email.indexer.index.Location;

public class LetterNode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1546445218135624407L;

	private final Character _letter;
	
	private final List<Location> _locations;
	
	private final Map<Character, LetterNode> _children;
	
	public LetterNode(Character letter) {
		_letter = letter;
		_locations = new ArrayList<Location>();
		_children = new HashMap<>();
	}
	
	public LetterNode addChild(LetterNode child) {
		_children.put(child.getLetter(), child);
		return this;
	}
	
	public LetterNode addLocation(Location loc) {
		_locations.add(loc);
		return this;
	}
	
	public Character getLetter() {
		return _letter;
	}
	
	public List<Location> getLocations() {
		return _locations;
	}
	
	public Set<Character> getChildren() {
		return _children.keySet();
	}

	public LetterNode getChild(Character ch) {
		return _children.get(ch);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_children == null) ? 0 : _children.hashCode());
		result = prime * result + ((_letter == null) ? 0 : _letter.hashCode());
		result = prime * result + ((_locations == null) ? 0 : _locations.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LetterNode other = (LetterNode) obj;
		if (_children == null) {
			if (other._children != null)
				return false;
		} else if (!_children.equals(other._children))
			return false;
		if (_letter == null) {
			if (other._letter != null)
				return false;
		} else if (!_letter.equals(other._letter))
			return false;
		if (_locations == null) {
			if (other._locations != null)
				return false;
		} else if (!_locations.equals(other._locations))
			return false;
		return true;
	}
	
	
}
