package email.indexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import email.indexer.index.Location;
import email.indexer.searchtree.SearchTree;

public class Search {

	private final static String dbFile = "database.db";
	
	public static void main(String[] args) {
		if (args.length<1) {
			System.out.println("Search word is required. Example:");
			System.out.println("`java Search {searchWord}`");
			System.exit(-1);
		}
		
		try {
		    File file = new File(dbFile);
		    if (!file.exists()) {
		    	System.out.println("database.db file not found. Cannot open the index.");
		    	System.exit(-1);
		    }

		    if (!file.canRead()) {
		    	System.out.println("database.db exists but is not accessible. Cannot open the index.");
		    	System.exit(-1);
		    }
			
			SearchTree searchTree = (SearchTree) readObjectFromFile(file);
			String word = args[0];
			word = word.trim().toLowerCase();
			List<Location> locations = searchTree.getLocations(word);
			if (locations.isEmpty()) {
				System.out.println("Word `"+word+"` is not found in the documents.");
			} 
			for (Location loc : locations) {
				System.out.println(loc.getFileName()); 
				// Line numbers are also possible: loc.getLines()
	
			}
			System.exit(0);
		} catch (Exception e) {
			System.err.println("Unable to perform search");
			e.printStackTrace();
			System.exit(-1);
		}

	}

	private static Object readObjectFromFile(File file) {
	   try {
		   	FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
	        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	
	        Object obj = objectIn.readObject();
	
	        System.out.println("The Object has been read from the file");
	        objectIn.close();
	        return obj;
	
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}

}
