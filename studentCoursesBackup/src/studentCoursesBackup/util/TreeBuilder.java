package studentCoursesBackup.util;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import studentCoursesBackup.myTree.Node;


public class TreeBuilder {

	public static Node root;

	public TreeBuilder() {
		this.root = null;
	}

	public Node build(LinkedHashMap<Integer, Set<String>> dataMap) {

		Node node = new Node();
		for (Entry<Integer, Set<String>> entry : dataMap.entrySet()) {
			Integer key = entry.getKey();
			Set<String> value = entry.getValue();

			// Data validation
			boolean validEntry = validateEntry(key, value);

			if (validEntry) {
				node = insert(key, value);
			}
		}
		return node;
	}

	public Node insert(Integer key, Set<String> value) {

		Node newNode = new Node(key, value);

		if (null == root) {
			root = newNode;
			return root;
		}
		Node current = root;
		Node parent = null;
		
		while(true){
			parent = current;
			if(key < current.getbNumber()){				
				current = current.leftNode;
				if(current==null){
					parent.leftNode = newNode;
					return root;
				}
			}else{
				current = current.rightNode;
				if(current==null){
					parent.rightNode = newNode;
					return root;
				}
			}
		}

	}
	
	public Node delete(LinkedHashMap<Integer, Set<String>> deleteDataMap){
		
		for (Entry<Integer, Set<String>> entry : deleteDataMap.entrySet()) {
			Integer key = entry.getKey();
			Set<String> value = entry.getValue();
			boolean nodeSearch = true;
			Node current = root;
			while(nodeSearch){
				if(key < current.getbNumber()){				
					current = current.leftNode;
				}else if(key > current.getbNumber()){
					current = current.rightNode;
				} else {
					Set<String> courses = current.getCourses();
					for (String s : value) {
					    if(courses.contains(s)) {
							courses.remove(s);
							}
					}
					nodeSearch = false;
				}
			}
		}
		
		return root;		
	}

	private boolean validateEntry(Integer key, Set<String> value) {
		// TODO Auto-generated method stub
		return true;
	}
}
