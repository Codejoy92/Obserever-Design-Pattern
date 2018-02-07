package studentCoursesBackup.util;

import java.io.File;
import java.io.FileNotFoundException;

import studentCoursesBackup.myTree.Node;
public class FileProcessor {

	public void fileCheck(String datafile) throws FileNotFoundException {

		if (null == datafile) {
			throw new FileNotFoundException("File Name is null");
		}

		File file = new File(datafile);
		if (!file.exists()) {
			throw new FileNotFoundException("Could not find File: " + datafile);
		}
	}
}
