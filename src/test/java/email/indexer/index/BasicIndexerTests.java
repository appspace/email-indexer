package email.indexer.index;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class BasicIndexerTests {

	@Test
	public void testSingleFile() throws Exception {
		BasicIndexer indexer = new BasicIndexer();
		List<String> files = indexer.listFiles("src/test/resources");
		assertEquals(2, files.size());
	}
	
	
}
