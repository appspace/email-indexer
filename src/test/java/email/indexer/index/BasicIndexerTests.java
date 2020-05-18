package email.indexer.index;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class BasicIndexerTests {

	@Test
	public void testShouldCount() {
		BasicIndexer indexer = new BasicIndexer();
		assertFalse(indexer.shouldCount(null));
		assertFalse(indexer.shouldCount(""));
		assertFalse(indexer.shouldCount("   "));
		assertFalse(indexer.shouldCount("a"));
		assertFalse(indexer.shouldCount("3"));
		assertFalse(indexer.shouldCount("123464537345"));
		
		assertTrue(indexer.shouldCount("book"));
		assertTrue(indexer.shouldCount("books"));
	}
	
	@Test
	public void testListFiles() throws Exception {
		BasicIndexer indexer = new BasicIndexer();
		List<String> files = indexer.listFiles("src/test/resources");
		assertEquals(2, files.size());
	}
		
	@Test
	public void testSingleFile() {
		String filename = "src/test/resources/21891.txt";
		BasicIndexer indexer = new BasicIndexer();
		FileIndex index = indexer.buildIndex(filename);
		System.out.println(index.getWords());
		assertEquals(281, index.getWords().size());
	}
	
	@Test
	public void testSplitEmptyLine() {
		BasicIndexer indexer = new BasicIndexer();
		assertTrue(indexer.splitLine(null).isEmpty());
		assertTrue(indexer.splitLine("").isEmpty());
		assertTrue(indexer.splitLine(" ").isEmpty());
		assertTrue(indexer.splitLine("                ").isEmpty());
	}
	
	@Test
	public void testSplitLine() {
		BasicIndexer indexer = new BasicIndexer();
		List<String> result = indexer.splitLine("a b c");
		assertEquals(3, result.size());
		assertTrue(result.contains("a"));
		assertTrue(result.contains("b"));
		assertTrue(result.contains("c"));
		assertFalse(result.contains("d"));
	}

}
