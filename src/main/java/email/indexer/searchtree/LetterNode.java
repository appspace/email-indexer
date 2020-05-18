package email.indexer.searchtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import email.indexer.index.Location;

public class LetterNode {
	
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
}
