package myTree;

import java.util.ArrayList;

public class Node implements ObserverI, SubjectI {

	int bNumber;
	ArrayList<String> courses= new ArrayList<>();
	public Node rightNode;
	public Node leftNode;

	public Node(Integer key, ArrayList<String> value) {
		bNumber = key;
		courses = value;
		rightNode = null;
		leftNode = null;
	}

	public int getbNumber() {
		return bNumber;
	}

	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}

	public ArrayList<String> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}

}
