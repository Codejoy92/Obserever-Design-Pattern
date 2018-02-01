package driver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import util.FileProcessor;
import util.TreeBuilder;

public class Driver {

	private static FileProcessor fileProcessor;
	private static TreeBuilder treeBuilder;
	private static HashMap<Integer, Set<String>> dataMap = new HashMap<Integer, Set<String>>();
	
	public static void main(String[] args) throws FileNotFoundException {

		if (null == args || args.length != 1) {
			System.out.println("Invalid command line arguments");
			return;
		}
		fileProcessor = new FileProcessor();
		String inputFileName = args[0];
		dataMap = fileProcessor.initialize(inputFileName);
		System.out.println(dataMap);
		treeBuilder = new TreeBuilder();
		treeBuilder.build(dataMap);
	}

}
