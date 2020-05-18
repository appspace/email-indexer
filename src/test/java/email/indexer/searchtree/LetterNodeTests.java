package email.indexer.searchtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import email.indexer.index.Location;

public class LetterNodeTests {

	@Test
	public void testAddLocation() {
		LetterNode node = new LetterNode('a');
		Location loc = new Location("test.txt", new HashSet(Arrays.asList(1, 2, 5)));
		node.addLocation(loc);
		assertEquals(new Character('a'), node.getLetter());
		assertEquals(1, node.getLocations().size());
		assertEquals(loc, node.getLocations().get(0));
	}

	@Test
	public void testAddChildrenFlat() {
		LetterNode nodeA = new LetterNode('a');
		LetterNode nodeB = new LetterNode('b');
		LetterNode nodeC = new LetterNode('c');
		nodeA.addChild(nodeB);
		nodeA.addChild(nodeC);

		assertEquals(2, nodeA.getChildren().size());
		assertTrue(nodeA.getChildren().contains(new Character('b')));
		assertTrue(nodeA.getChildren().contains(new Character('c')));
		
		assertEquals(nodeB, nodeA.getChild('b'));
		assertEquals(nodeC, nodeA.getChild('c'));
	}
	
}
