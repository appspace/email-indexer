package email.indexer.searchtree;

import java.util.LinkedList;
import java.util.List;

import email.indexer.index.Location;

public class InMemorySearchTree implements SearchTree {

	LetterNode root = new LetterNode('0');
	
	@Override
	public List<Location> getLocations(String word) {
		LetterNode node = findNode(word);
		return node.getLocations();
	}

	@Override
	public void addLocations(String word, List<Location> locations) {
		LetterNode node = findNode(word);
		locations.stream().forEach(loc-> node.addLocation(loc));

	}

	protected LetterNode findNode(String word) {
		List<Character> letters = tokenizeWord(word);
		LetterNode currentNode = root;
		LetterNode previousNode = root;
		for (Character nextLetter : letters) {
			currentNode = previousNode.getChild(nextLetter);
			if (currentNode == null) {
				currentNode = new LetterNode(nextLetter);
				previousNode.addChild(currentNode);
			}
			previousNode = currentNode;
		}
		return currentNode;
	}

	protected LinkedList<Character> tokenizeWord(String word) {
		LinkedList<Character> result = new LinkedList<>();
		for (int index = 0; index < word.length(); index++) {
			result.add(new Character(word.charAt(index)));
		}
		return result;
	}
	
}
