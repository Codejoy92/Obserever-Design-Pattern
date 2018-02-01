package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileProcessor {

	
	 public HashMap<Integer, Set<String>> initialize(String datafile) throws FileNotFoundException {
		
		HashMap<Integer, Set<String>> dataMapProcessor = new HashMap<Integer, Set<String>>();
		
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
				String[] splitData= data.split(":");
				int key = Integer.parseInt(splitData[0]);
				if(null == splitData) {
					//need to add custom exception
					System.out.println("no data in file");
					return null;
				}
				//if there is exisiting bnumber with course/courses
				Set<String> finalValue = new HashSet<String>();
				if(dataMapProcessor.containsKey(Integer.parseInt(splitData[0]))) {
					finalValue = dataMapProcessor.get(key);
					finalValue.add(splitData[1]);
					dataMapProcessor.put(key, finalValue);
				}else{
					finalValue.add(splitData[1]);
					dataMapProcessor.put(key, finalValue);
				}
			
			}
		}
		dataScanner.close();
		return dataMapProcessor;
	}

}
