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
	
	public Node delete(Map<Integer, String> deleteDataMap){
		
		for (Entry<Integer, String> entry : deleteDataMap.entrySet()) {
			Integer key = entry.getKey();
			String value = entry.getValue();
			boolean nodeSearch = true;
			Node current = root;
			while(nodeSearch){
				if(key < current.getbNumber()){				
					current = current.leftNode;
				}else if(key > current.getbNumber()){
					current = current.rightNode;
				} else {
					    if(current.getCourses().contains(value)) {
					    	current.getCourses().remove(value);
							}
					}
					nodeSearch = false;
				}
			}
		return root;	
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
	
/*	public Node searchNode(Integer key, String value) {
		Node node = new Node();
		boolean nodeSearch = true;
		Node current = root;
		while(nodeSearch){
			if(key < current.getbNumber()){				
				current = current.leftNode;
			}else if(key > current.getbNumber()){
				current = current.rightNode;
			} else if(key == current.getbNumber()){
				return node;
			}else {
				nodeSearch = false;
			}
			}
		return node;
		
	}*/
}
