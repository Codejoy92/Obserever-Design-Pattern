package studentCoursesBackup.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import studentCoursesBackup.myTree.Node;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	List<String> outputList = new ArrayList<>();

	public List<String> getOutputList() {
		return outputList;
	}

	public void setOutputList(List<String> outputList) {
		this.outputList = outputList;
	}

	@Override
	public void generateOutputFile(String outputFile) {

		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(outputFile));
			for (String output : outputList) {
				out.write(output);
				out.newLine();
			}
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("output file does not exisits");
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.out.println("error while writing into file");
			System.exit(1);
		}
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
