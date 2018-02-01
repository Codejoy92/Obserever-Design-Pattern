package driver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import util.FileProcessor;
import util.TreeBuilder;

public class Driver {

	private static FileProcessor fileProcessor;
	private static TreeBuilder treeBuilder;
	private static HashMap<Integer, ArrayList<String>> dataMap = new HashMap<Integer, ArrayList<String>>();
	
	public static void main(String[] args) throws FileNotFoundException {

		if (null == args || args.length != 1) {
			System.out.println("Invalid command line arguments");
			return;
		}

		String inputFileName = args[0];
		dataMap = fileProcessor.initialize(inputFileName);
		treeBuilder = new TreeBuilder();
		treeBuilder.build(dataMap);
	}

}
