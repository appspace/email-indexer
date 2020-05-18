package email.indexer;

import email.indexer.index.BasicIndexer;
import email.indexer.index.Index;

public class DatabaseBuilder {

	public static void main(String[] args) {
		if (args.length==0) {
			System.out.println("Folder name is required. Example:");
			System.out.println("java DatabaseBuilder /emails");
			System.exit(-1);
		}
		String folder = args[0];
		System.out.println("Processing folder "+folder);
		
		try {
			BasicIndexer indexer = new BasicIndexer();
			Index index = indexer.indexFolder(folder);
			
		} catch (Exception e) {
			System.err.println("Unable to process folder");
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
}
