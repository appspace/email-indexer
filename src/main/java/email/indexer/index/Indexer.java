package email.indexer.index;

import java.util.List;

public interface Indexer {

	/**
	 * Index content of a folder
	 */
	public List<FileIndex> indexFolder(String folderName) throws Exception;

}
