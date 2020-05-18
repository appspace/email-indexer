package email.indexer.index;

public interface Indexer {

	/**
	 * Index content of a folder
	 */
	public Index indexFolder(String folderName) throws Exception;

}
