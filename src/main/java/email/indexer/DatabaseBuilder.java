package email.indexer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import email.indexer.index.BasicIndexer;
import email.indexer.index.Index;
import email.indexer.searchtree.InMemorySearchTree;
import email.indexer.searchtree.SearchTree;

public class DatabaseBuilder {
	
	public static void main(String[] args) {
		if (args.length<2) {
			System.out.println("Folder name to index and output DB file path is required. Example:");
			System.out.println("`java DatabaseBuilder /emails database.db`");
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
			FileOutputStream fileOut = new FileOutputStream(args[1]);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(searchTree);
            objectOut.close();
            System.out.println("Folder is indexed! Use `java Search " + args[1] + " {search_word}` command to query the database.");
            System.exit(0);
		} catch (Exception e) {
			System.err.println("Unable to process folder");
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
}
