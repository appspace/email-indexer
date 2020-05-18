package email.indexer.index;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BasicIndexer {

	public void indexFolder(String folderName) throws Exception {
		List<String> fileNames = listFiles(folderName);
		for (String fileName : fileNames) {
			
		}
	}

	protected List<String> listFiles(String folderName) throws Exception {
		List<String> fileNames = new ArrayList<String>();
		File folder = new File(folderName);
		if (!folder.exists()) {
			throw new Exception("Folder "+folderName+" does not exist!");
		}
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				fileNames.add(file.getAbsolutePath());
			}			
		} else {
			fileNames.add(folder.getAbsolutePath());
		}
		return fileNames;
	}
	
	
	
}
