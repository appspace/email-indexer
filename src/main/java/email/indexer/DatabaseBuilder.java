package email.indexer;

import email.indexer.index.BasicIndexer;
import email.indexer.index.Index;
import email.indexer.searchtree.InMemorySearchTree;
import email.indexer.searchtree.SearchTree;

public class DatabaseBuilder {

	private final static String dbFile = "database.db";
	
	public static void main(String[] args) {
		if (args.length<1) {
			System.out.println("Folder name is required. Example:");
			System.out.println("java DatabaseBuilder /emails");
			System.exit(-1);
		}
		String folder = args[0];
		System.out.println("Processing folder "+folder);
		
		try {
			BasicIndexer indexer = new BasicIndexer();
			Index index = indexer.indexFolder(folder);
			SearchTree searchTree = new InMemorySearchTree();
			for (String word : index.getWords()) {
				searchTree.addLocations(word, index.getLocations(word));				
			}
			
		} catch (Exception e) {
			System.err.println("Unable to process folder");
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
}
