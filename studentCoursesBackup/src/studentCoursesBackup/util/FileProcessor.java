package studentCoursesBackup.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

	BufferedReader reader;
	FileReader file;

	public String readline() {
		String entry = null;
			try {
				entry = reader.readLine();
			} catch (IOException e) {
				System.out.println(e);
				System.exit(1);
			}
		return entry;
	}

	public void fileCheck(String datafile) {
		try {
			file = new FileReader(datafile);
			reader = new BufferedReader(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(1);
		}
	}
}
