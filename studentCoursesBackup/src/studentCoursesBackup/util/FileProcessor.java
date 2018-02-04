package studentCoursesBackup.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;
import studentCoursesBackup.myTree.Node;
public class FileProcessor implements FileDisplayInterface {

	public LinkedHashMap<Integer, Set<String>> initialize(String datafile) throws FileNotFoundException {

		LinkedHashMap<Integer, Set<String>> dataMapProcessor = new LinkedHashMap<Integer, Set<String>>();

		if (null == datafile) {
			throw new FileNotFoundException("File Name is null");
		}

		File file = new File(datafile);
		if (!file.exists()) {
			throw new FileNotFoundException("Could not find File: " + datafile);
		}

		Scanner dataScanner = new Scanner(file);

		while (dataScanner.hasNextLine()) {
			String data = dataScanner.nextLine();
			if (null != data && (!data.trim().isEmpty())) {
				String[] splitData = data.split(":");
				int key = Integer.parseInt(splitData[0]);
				// if there is exisiting bnumber with course/courses
				Set<String> finalValue = new HashSet<String>();
				if (dataMapProcessor.containsKey(Integer.parseInt(splitData[0]))) {
					finalValue = dataMapProcessor.get(key);
					finalValue.add(splitData[1]);
					dataMapProcessor.put(key, finalValue);
				} else {
					finalValue.add(splitData[1]);
					dataMapProcessor.put(key, finalValue);
				}

			} else {
				// need to add custom exception
				System.out.println("no data in file");
				return null;
			}
		}
		dataScanner.close();
		return dataMapProcessor;
	}

	@Override
	public void display(Node root) {
		if(root!=null){
			display(root.leftNode);
			System.out.println(root.getbNumber()+":"+root.getCourses());
			display(root.rightNode);
		}
		
	}

}
