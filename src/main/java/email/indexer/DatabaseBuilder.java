package email.indexer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import email.indexer.index.BasicIndexer;
import email.indexer.index.Index;
import email.indexer.searchtree.InMemorySearchTree;
import email.indexer.searchtree.SearchTree;

public class DatabaseBuilder {

	private final static String dbFile = "database.db";
	
	public static void main(String[] args) {
		if (args.length<1) {
			System.out.println("Folder name is required. Example:");
			System.out.println("`java DatabaseBuilder /emails`");
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
			FileOutputStream fileOut = new FileOutputStream(dbFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(searchTree);
            objectOut.close();
            System.out.println("Folder is indexed! Use `java Search {search_word}` command to query the database.");
            System.exit(0);
		} catch (Exception e) {
			System.err.println("Unable to process folder");
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
}
