package studentCoursesBackup.util;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import studentCoursesBackup.myTree.Node;


public class TreeBuilder {

	public static Node root, backupRoot1, backupRoot2;

	public TreeBuilder() {
		this.root = null;
		this.backupRoot1 = null;
		this.backupRoot2 = null;
	}

	public Node buildNode(int bnum, String course) {

		Node node = new Node();
			String value = course;
			node.setbNumber(bnum);
			node.getCourses().add(course);
		return node;
	}

	public Node insert(Node rootNode, Node newNode) {
		
		if (null == rootNode) {
			rootNode = newNode;
			return rootNode;
		}
		Node current = rootNode;
		Node parent = null;
		
		while(true){
			parent = current;
			if(newNode.getbNumber() < current.getbNumber()){				
				current = current.leftNode;
				if(current==null){
					parent.leftNode = newNode;
					return rootNode;
				}
			}else{
				current = current.rightNode;
				if(current==null){
					parent.rightNode = newNode;
					return rootNode;
				}
			}
		}

	}
	
	public boolean validateEntry(Integer key, String value) {
		boolean nodeSearch = true;
		Node current = root;
		while(nodeSearch && null!= root && null!= current){
			if(key < current.getbNumber()){				
				current = current.leftNode;
			}else if(key > current.getbNumber()){
				current = current.rightNode;
			} else if(key == current.getbNumber()){
						nodeSearch = false;
				    	return false; //course name already exisits 
				}else {
					nodeSearch = false;
					return false; //no such bnumber exisits
				}
			}
		return true;
	}

	public void addCourseToExisitingNode(Integer bnumber, String courses) {
		boolean nodeSearch = true;
		Node current = root;
		while(nodeSearch){
			if(bnumber < current.getbNumber()){				
				current = current.leftNode;
			}else if(bnumber > current.getbNumber()){
				current = current.rightNode;
			} else if(bnumber == current.getbNumber()){
				if(!current.getCourses().contains(courses)) {
					current.getCourses().add(courses);
					int bnum = bnumber;
					current.update(bnum,courses);
				}
				nodeSearch = false;
			}else {
				nodeSearch = false;
			}
			}
		
	}

	public void insertInAllTrees(Node originalNode, Node backupNode1, Node backupNode2) {
		root = insert(root, originalNode);
		backupRoot1 = insert(backupRoot1, backupNode1);
		backupRoot2 = insert(backupRoot2, backupNode2);
	}

	public void deleteCourse(int bNumberDelete, String courseDelete) {

			boolean nodeSearch = true;
			Node current = root;
			while(nodeSearch && null!= root){
				if(bNumberDelete < current.getbNumber()){				
					current = current.leftNode;
				}else if(bNumberDelete > current.getbNumber()){
					current = current.rightNode;
				} else {
					    if(current.getCourses().contains(courseDelete)) {
					    	current.getCourses().remove(courseDelete);
					    	current.notifyObservers(bNumberDelete, courseDelete);
							}
					    nodeSearch = false;
					}
					
				}
			}
	

	public void printNodes(Results originalTreeResults, Results backupTreeResults1, Results backupTreeResults2) {
		addToResultList(root, originalTreeResults);
		addToResultList(backupRoot1, backupTreeResults1);
		addToResultList(backupRoot2, backupTreeResults2);
	}
	
	private void addToResultList(Node rootNode, Results result) {
		 if (null != rootNode) {
			 	addToResultList(rootNode.leftNode, result);
			 	StringBuilder builder = new StringBuilder();
				builder.append(rootNode.getbNumber());
				builder.append(":");
				for (String courses : rootNode.getCourses()) {
					builder.append(" " + courses);
				}
				result.getOutputList().add(builder.toString());
				addToResultList(rootNode.rightNode, result);
	        }
	}

}

