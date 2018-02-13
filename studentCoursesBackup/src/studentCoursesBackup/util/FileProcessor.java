package studentCoursesBackup.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessor {

	Scanner scan;
	File file;
	
	/**
	 * This function is used to read contents of file line by line
	 * 
	 */
	public String readline() {
		String entry = null;
			try {
				if(scan.hasNextLine()) {
				entry = scan.next();
				if(entry.isEmpty())
					return null;
				}else {
					return null;
				}
			} catch (Exception e) {
				System.out.println(e);
				System.exit(1);
			}
		return entry;
	}

	/**
	 * This function validates the file's existence
	 * 
	 * @param datafile
	 */
	public void fileCheck(String datafile) {
		try {
			file = new File(datafile);
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(1);
		}
	}
}
