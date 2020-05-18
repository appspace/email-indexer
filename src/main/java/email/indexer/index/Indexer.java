package email.indexer.index;

import java.io.IOException;

public interface Indexer {

	/**
	 * Index content
	 */
	public Index buildIndex(String filename) throws IOException;

}
