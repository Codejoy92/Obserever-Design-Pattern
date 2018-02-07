package studentCoursesBackup.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.TreeBuilder;

public class Driver {

	private static FileProcessor fileProcessor;
	private static TreeBuilder treeBuilder;
	private static Node originalNode;
	private static Node backupNode1;
	private static Node backupNode2;
	private static Map<Integer, String> dataMap = new HashMap<Integer, String>();
	private static Map<Integer, String> deleteDataMap = new HashMap<Integer, String>();
	
	public static void main(String[] args) throws FileNotFoundException {

		if (null == args || args.length != 5) {
			System.out.println("Invalid command line arguments, Must pass 5 text files");
			return;
		}
		fileProcessor = new FileProcessor();
		treeBuilder = new TreeBuilder();
		
		String inputFileName = args[0];
		fileProcessor.fileCheck(inputFileName);
		
		File file = new File(inputFileName);
		Scanner dataScanner = new Scanner(file);
		
		while (dataScanner.hasNextLine()) {

			String data = dataScanner.nextLine();
			if (null != data && (!data.trim().isEmpty())) {
				String[] splitData = data.split(":");
				int bNumber = Integer.parseInt(splitData[0]);
				String course = splitData[1];
				//if its a new bnumber/node
					if(treeBuilder.validateEntry(bNumber, course)) {
						originalNode = treeBuilder.buildNode(bNumber, course);
						//cloning orignal node values to backup nodes and adding them to observer list
						try {
							backupNode1 = (Node) originalNode.clone();
							backupNode2 = (Node) originalNode.clone();
							backupNode1.setbNumber(bNumber);
							backupNode2.setbNumber(bNumber);
							backupNode1.getCourses().add(course);
							backupNode2.getCourses().add(course);
							originalNode.addObserver(backupNode1);
							originalNode.addObserver(backupNode2);
						}catch(CloneNotSupportedException e) {
							System.out.println("Problem with cloning");
							e.printStackTrace();
							System.exit(0);
						}
					
						treeBuilder.insertInAllTrees(originalNode, backupNode1, backupNode2);
					}else {
						//if its an existing bnumber
						treeBuilder.addCourseToExisitingNode(bNumber, course);
					}
			} else {
				// need to add custom exception
				System.out.println("no data in file");
				System.exit(0);
			}
		}dataScanner.close();
		fileProcessor.display(treeBuilder.backupRoot1);
		
		String deleteFileName = args[1];
	/*	deleteDataMap = fileProcessor.initialize(deleteFileName);
		originalNode = treeBuilder.delete(deleteDataMap);*/
	//	fileProcessor.display(originalNode);
	//	fileProcessor.display(backupNode1);
		String outputFile1 = args[2];
		String outputFile2 = args[3];
		String outputFile3 = args[4];
		
		
	}

}
