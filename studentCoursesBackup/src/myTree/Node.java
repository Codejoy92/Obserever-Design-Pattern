package myTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node implements ObserverI, SubjectI {

	int bNumber;
	Set<String> courses= new HashSet<String>();
	public Node rightNode;
	public Node leftNode;

	public Node(Integer key, Set<String> value) {
		bNumber = key;
		courses = value;
		rightNode = null;
		leftNode = null;
	}

	public Node() {
	}

	public int getbNumber() {
		return bNumber;
	}

	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}

	public Set<String> getCourses() {
		return courses;
	}

	public void setCourses(Set<String> courses) {
		this.courses = courses;
	}

}
