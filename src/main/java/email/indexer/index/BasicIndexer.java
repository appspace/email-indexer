package email.indexer.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BasicIndexer implements Indexer {

	public List<FileIndex> indexFolder(String folderName) throws Exception {
		List<String> fileNames = listFiles(folderName);
		
		List<FileIndex> indexes = fileNames
			.parallelStream()
			.map(fileName -> buildIndex(fileName))
			.filter(index -> index.isEmpty())
			.collect(Collectors.toList());
		
		return indexes;
	}

	protected FileIndex buildIndex(String fileName) {
		BasicFileIndex index = new BasicFileIndex(fileName);
		BufferedReader fileReader = null;
		try {
			int lineNumber = 1;
			fileReader = new BufferedReader(new FileReader(fileName));
			String line = fileReader.readLine();
			while (line!=null) {
				if (shouldProcess(line)) {
					List<String> words = splitLine(line);
					for (String word : words) {
						if (shouldCount(word)) {
							word = word.toLowerCase().trim();
							index.addWord(word, lineNumber);
						}
					}
				}
				line = fileReader.readLine();
				lineNumber++;
			}
		} catch (Exception e) {
			System.err.println("Unable to process file "+fileName);
			e.printStackTrace();
		} finally {
			try {
				if (fileReader!=null) fileReader.close();
			} catch (Exception e) {
				// Silently swallowing as we are not interested in this
			}
		}
		return index;
	}
	
	
	protected List<String> splitLine(String line) {
		if (line==null) {
			return Collections.emptyList();
		}
		line = line.trim().toLowerCase();
		if (line.length()==0) {
			return Collections.emptyList();
		}

		return Arrays.asList(line.split(" "))
				.stream()
				.map(element -> element.trim())
				.filter(element -> element.length()>0)
				.collect(Collectors.toList());
	}

	/**
	 * 
	 * @param word
	 * @return true if the word should be accounted for
	 */
	protected boolean shouldCount(String word) {
		if (word==null) {
			return false;
		}
		word = word.trim();
		if (word.length()<2) {
			return false;
		}
		return word.matches("[a-zA-Z]*");
	}
	
	/**
	 * Returns true if the line should be processed
	 * @param line
	 * @return
	 */
	private boolean shouldProcess(String line) {
		return true;
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
