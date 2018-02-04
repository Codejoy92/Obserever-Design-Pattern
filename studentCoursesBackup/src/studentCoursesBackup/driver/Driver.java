package studentCoursesBackup.driver;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Set;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.TreeBuilder;

public class Driver {

	private static FileProcessor fileProcessor;
	private static TreeBuilder treeBuilder;
	private static Node node;
	private static LinkedHashMap<Integer, Set<String>> dataMap = new LinkedHashMap<Integer, Set<String>>();
	private static LinkedHashMap<Integer, Set<String>> deleteDataMap = new LinkedHashMap<Integer, Set<String>>();
	
	public static void main(String[] args) throws FileNotFoundException {

		if (null == args || args.length != 5) {
			System.out.println("Invalid command line arguments, Must pass 5 text files");
			return;
		}
		fileProcessor = new FileProcessor();
		treeBuilder = new TreeBuilder();
		node = new Node();
		
		String inputFileName = args[0];
		dataMap = fileProcessor.initialize(inputFileName);
		node = treeBuilder.build(dataMap);
		fileProcessor.display(node);
		
		String deleteFileName = args[1];
		deleteDataMap = fileProcessor.initialize(deleteFileName);
		node = treeBuilder.delete(deleteDataMap);
		fileProcessor.display(node);
		
	}

}
