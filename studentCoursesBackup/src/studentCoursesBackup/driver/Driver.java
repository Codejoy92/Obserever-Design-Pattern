package studentCoursesBackup.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

		if (null == args || args.length != 5) {
			System.out.println("Invalid command line arguments, Must pass 5 text files");
			return;
		}
		fileProcessor = new FileProcessor();
		treeBuilder = new TreeBuilder();
		
	//insertion process starts
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
		//insertion process ends
		
		//Delete Process Start
		String deleteFileName = args[1];
		fileProcessor.fileCheck(deleteFileName);	
		File deleteFile = new File(deleteFileName);
		Scanner delteDataScanner = new Scanner(deleteFile);
		
		while (delteDataScanner.hasNextLine()) {
			String deleteData = delteDataScanner.nextLine();
			if (null != deleteData && (!deleteData.trim().isEmpty())) {
					String[] splitData = deleteData.split(":");
					int bNumberDelete = Integer.parseInt(splitData[0]);
					String courseDelete = splitData[1];
					treeBuilder.deleteCourse(bNumberDelete, courseDelete);
			}else {
				System.out.println("no data in file");
				System.exit(0);
			}
		}dataScanner.close();
		//Delete Process ends

		//storing process starts
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
    	
		//storing process ends
	}

}
