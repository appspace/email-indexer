package email.indexer.searchtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import email.indexer.index.Location;

public class InMemorySearchTreeTests {

	@Test
	public void testBuild() {
		InMemorySearchTree tree = new InMemorySearchTree();
		Location loc1 = new Location("test1.txt", new HashSet(Arrays.asList(1, 2, 5)));
		Location loc2 = new Location("test2.txt", new HashSet(Arrays.asList(3, 5)));
		tree.addLocations("abc", Arrays.asList(loc1, loc2));
		
		assertTrue(tree.getLocations("ab").isEmpty());
		assertEquals(2, tree.getLocations("abc").size());
	}
	
	@Test
	public void testTokenizeWord() {
		InMemorySearchTree tree = new InMemorySearchTree();
		LinkedList<Character> result = tree.tokenizeWord("abc");
		assertEquals(new Character('a'), result.get(0));
		assertEquals(new Character('b'), result.get(1));
		assertEquals(new Character('c'), result.get(2));
	}
	
	@Test
	public void testAddNode() {
		InMemorySearchTree tree = new InMemorySearchTree();
		LetterNode nodeC = tree.findNode("abc");
		assertNotNull(nodeC);
		LetterNode nodeA = tree.findNode("a");
		assertNotNull(nodeA);
		assertEquals(1, nodeA.getChildren().size());
		
		LetterNode nodeB = nodeA.getChild('b');
		assertEquals(new Character('b'), nodeB.getLetter());
		assertEquals(new Character('c'), nodeA.getChild('b').getChild('c').getLetter());
	}
	
	@Test
	public void testSearchLocations() {
		InMemorySearchTree tree = new InMemorySearchTree();
		LetterNode nodeC = tree.findNode("book");
		assertNotNull(nodeC);
		nodeC.addLocation(new Location("test1.txt", new HashSet(Arrays.asList(1, 2, 5))));

		List<Location> locations = tree.getLocations("book");
		assertEquals(1, locations.size());
		assertEquals("test1.txt", locations.get(0).getFileName());

		locations = tree.getLocations("book2");
		assertEquals(0, locations.size());

	}
	
}
