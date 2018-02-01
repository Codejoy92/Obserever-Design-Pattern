package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileProcessor {

	
	 public HashMap<Integer, ArrayList<String>> initialize(String datafile) throws FileNotFoundException {
		
		HashMap<Integer, ArrayList<String>> dataMapProcessor = new HashMap<Integer, ArrayList<String>>();
		
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
				if(null == splitData) {
					//need to add custom exception
					System.out.println("no data in file");
					return null;
				}
				//if there is exisiting bnumber with course/courses
				ArrayList<String> finalValue = new ArrayList<>();
				if(dataMapProcessor.containsKey(splitData[0])) {
					finalValue = dataMapProcessor.get(splitData[0]);
					finalValue.add(splitData[1]);
					dataMapProcessor.put(Integer.parseInt(splitData[0]), finalValue);
				}else{
					finalValue.add(splitData[1]);
					dataMapProcessor.put(Integer.parseInt(splitData[0]), finalValue);
				}
			
			}
		}
		dataScanner.close();
		return dataMapProcessor;
	}

}
