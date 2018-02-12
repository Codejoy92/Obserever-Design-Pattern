package studentCoursesBackup.myTree;

import java.util.ArrayList;

public class Node implements ObserverI, SubjectI, Cloneable {

	int bNumber;
	private ArrayList<String> courses = new ArrayList<String>();;
	private ArrayList<Node> backupNodesList = new ArrayList<Node>();
	public Node rightNode;
	public Node leftNode;
	public OperationEnum enumKey;
	
	public Node(Integer key, ArrayList<String> value) {
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

	public ArrayList<String> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}

	public ArrayList<Node> getBackupNodesList() {
		return backupNodesList;
	}

	public void setBackupNodesList(ArrayList<Node> backupNodesList) {
		this.backupNodesList = backupNodesList;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Node();
	}

	@Override
	public void removeObserver(Node observerRemove) {
		backupNodesList.remove(observerRemove);

	}

	@Override
	public void addObserver(Node observerAdd) {
		backupNodesList.add(observerAdd);

	}

	@Override
	public void notifyObservers(String course, OperationEnum enumValue) {
		update(course, enumValue);
	}

	@Override
	public void update(String course, OperationEnum enumValue) {
		if(enumKey.INSERT== enumValue) {
		for (Node backupNodes : backupNodesList) {
			backupNodes.getCourses().add(course);
		}
		}else if(enumKey.DELETE== enumValue) {
			for (Node backupNodes : backupNodesList) {
				backupNodes.getCourses().remove(course);
			}			
		}

	}

	@Override
	public String toString() {
		return "Node [bNumber=" + bNumber + ", courses=" + courses + ", backupNodesList=" + backupNodesList
				+ ", rightNode=" + rightNode + ", leftNode=" + leftNode + ", enumKey=" + enumKey + "]";
	}
	

}
