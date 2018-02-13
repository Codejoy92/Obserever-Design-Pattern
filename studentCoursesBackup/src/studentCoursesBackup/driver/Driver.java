package studentCoursesBackup.driver;

import java.io.FileNotFoundException;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;

public class Driver {

	private static FileProcessor fileProcessor;
	private static TreeBuilder treeBuilder;
	private static Node originalNode;
	private static Node backupNode1;
	private static Node backupNode2;

	public static void main(String[] args) throws FileNotFoundException {

		if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}") || null == args) {

			System.err.println("Error: Incorrect number of arguments. Program pass 5 argumnets.");
			System.exit(0);
		}
		fileProcessor = new FileProcessor();
		treeBuilder = new TreeBuilder();

		// insertion process starts
		String inputFileName = args[0];
		String data ="";
		fileProcessor.fileCheck(inputFileName);
		while (null!=data) {
			
			data = fileProcessor.readline();
			if (null != data && (!data.trim().isEmpty())) {
				String[] splitData = data.split(":");
				int bNumber = Integer.parseInt(splitData[0]);
				String course = splitData[1];
				// if its a new bnumber/node
				if (treeBuilder.validateEntry(bNumber)) {
					originalNode = treeBuilder.buildNode(bNumber, course);
					// cloning orignal node values to backup nodes and adding them to observer list
					try {
						backupNode1 = (Node) originalNode.clone();
						backupNode2 = (Node) originalNode.clone();
						backupNode1.setbNumber(bNumber);
						backupNode2.setbNumber(bNumber);
						backupNode1.getCourses().add(course);
						backupNode2.getCourses().add(course);
						originalNode.addObserver(backupNode1);
						originalNode.addObserver(backupNode2);
					//Following print statement can be useful while debugging entries in tree. uncomment it to use.
					//	System.out.println(originalNode.toString());
					} catch (CloneNotSupportedException e) {
						System.out.println("Problem with cloning");
						System.exit(0);
					}

					treeBuilder.insertInAllTrees(originalNode, backupNode1, backupNode2);
				} else {
					// if its an existing bnumber
					treeBuilder.addCourseToExisitingNode(bNumber, course);
				}
			}
		}
		// insertion process ends

		// Delete Process Start
		data = "";
		String deleteFileName = args[1];
		fileProcessor.fileCheck(deleteFileName);
		while (null!=data) {
			data = fileProcessor.readline();
			if (null != data && (!data.trim().isEmpty())) {
				String[] splitData = data.split(":");
				int bNumberDelete = Integer.parseInt(splitData[0]);
				String courseDelete = splitData[1];
				treeBuilder.deleteCourse(bNumberDelete, courseDelete);
			}
		}
		// Delete Process ends

		// storing process starts
		String outputFile1 = args[2];
		String outputFile2 = args[3];
		String outputFile3 = args[4];

		Results originalTreeResults = new Results();
		Results backupTreeResults1 = new Results();
		Results backupTreeResults2 = new Results();

		treeBuilder.printNodes(originalTreeResults, backupTreeResults1, backupTreeResults2);

		originalTreeResults.generateOutputFile(outputFile1);
		backupTreeResults1.generateOutputFile(outputFile2);
		backupTreeResults2.generateOutputFile(outputFile3);
		// storing process ends
	
		//display
		treeBuilder.displayTree();
	}

}
