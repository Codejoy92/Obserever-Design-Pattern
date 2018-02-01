package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import myTree.Node;

public class TreeBuilder {

	public static Node root;

	public TreeBuilder() {
		this.root = null;
	}

	public void build(HashMap<Integer, ArrayList<String>> entries) {

		for (Map.Entry<Integer, ArrayList<String>> entry : entries.entrySet()) {
			Integer key = entry.getKey();
			ArrayList<String> value = entry.getValue();

			// Data validation
			boolean validEntry = validateEntry(key, value);

			if (validEntry) {
				this.insert(key, value);
			}
		}
	}

	public void insert(Integer key, ArrayList<String> value) {

		Node newNode = new Node(key, value);

		if (null == root) {
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		
		while(true){
			parent = current;
			if(key < current.getbNumber()){				
				current = current.leftNode;
				if(current==null){
					parent.leftNode = newNode;
					return;
				}
			}else{
				current = current.rightNode;
				if(current==null){
					parent.rightNode = newNode;
					return;
				}
			}
		}

	}

	private boolean validateEntry(Integer key, ArrayList<String> value) {
		// TODO Auto-generated method stub
		return false;
	}
}
