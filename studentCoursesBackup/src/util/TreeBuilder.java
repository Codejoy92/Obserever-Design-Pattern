package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import myTree.Node;

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
				node = this.insert(key, value);
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

	private boolean validateEntry(Integer key, Set<String> value) {
		// TODO Auto-generated method stub
		return true;
	}
}
