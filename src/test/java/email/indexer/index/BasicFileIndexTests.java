package email.indexer.index;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BasicFileIndexTests {

	@Test
	public void testMissingWordNotFound() {
		BasicFileIndex index = new BasicFileIndex("test");
		index.addWord("book", 3);
		index.addWord("book", 13);
		index.addWord("book2", 3);
		
		assertTrue(index.getLocations("book3").isEmpty());
	}

	@Test
	public void testResult() {
		BasicFileIndex index = new BasicFileIndex("test");
		index.addWord("book", 3);
		index.addWord("book", 13);
		index.addWord("book2", 3);
		
		assertEquals(2, index.getLocations("book").size());
		assertTrue(index.getLocations("book").contains(3));
		assertTrue(index.getLocations("book").contains(13));
		
		assertEquals(1, index.getLocations("book2").size());
		assertTrue(index.getLocations("book2").contains(3));
	}
	
	@Test
	public void testName() {
		BasicFileIndex index = new BasicFileIndex("test");
	
		assertEquals(index.getName(), "test");
	}

}
