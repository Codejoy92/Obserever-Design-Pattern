package driver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import myTree.Node;
import util.FileProcessor;
import util.TreeBuilder;

public class Driver {

	private static FileProcessor fileProcessor;
	private static TreeBuilder treeBuilder;
	private static Node node;
	private static LinkedHashMap<Integer, Set<String>> dataMap = new LinkedHashMap<Integer, Set<String>>();
	
	public static void main(String[] args) throws FileNotFoundException {

		if (null == args || args.length != 1) {
			System.out.println("Invalid command line arguments");
			return;
		}
		fileProcessor = new FileProcessor();
		node = new Node();
		String inputFileName = args[0];
		dataMap = fileProcessor.initialize(inputFileName);
		//System.out.println(dataMap);
		treeBuilder = new TreeBuilder();
		node = treeBuilder.build(dataMap);
		fileProcessor.display(node);
	}

}
