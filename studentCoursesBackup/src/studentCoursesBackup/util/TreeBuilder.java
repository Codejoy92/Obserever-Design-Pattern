package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;

public class TreeBuilder {

	public Node root, backupRoot1, backupRoot2;

	public TreeBuilder() {
		this.root = null;
		this.backupRoot1 = null;
		this.backupRoot2 = null;
	}

	/**
	 * This function creates a new Node object by setting b number and creating new
	 * arraylist of courses and returns that node to calling method
	 * 
	 * @param bnum
	 * @param courses
	 * @return
	 */
	public Node buildNode(int bnum, String course) {

		Node node = new Node();
		node.setbNumber(bnum);
		node.getCourses().add(course);
		return node;
	}

	/**
	 * This function inserts a new Node in the tree, its helper function for method
	 * insertInAllTrees
	 * 
	 * @param rootNode
	 * @param newNode
	 * @return
	 */
	private Node insert(Node rootNode, Node newNode) {

		if (null == rootNode) {
			rootNode = newNode;
			return rootNode;
		}
		Node current = rootNode;
		Node parent = null;

		while (true) {
			parent = current;
			if (newNode.getbNumber() < current.getbNumber()) {
				current = current.leftNode;
				if (current == null) {
					parent.leftNode = newNode;
					return rootNode;
				}
			} else {
				current = current.rightNode;
				if (current == null) {
					parent.rightNode = newNode;
					return rootNode;
				}
			}
		}
	}

	/**
	 * This function is used to insert nodes in respective tree i.e originalTree,
	 * backupTree1 and backupTree2
	 * 
	 * @param originalNode
	 * @param backupNode1
	 * @param backupNode2
	 */
	public void insertInAllTrees(Node originalNode, Node backupNode1, Node backupNode2) {
		root = insert(root, originalNode);
		backupRoot1 = insert(backupRoot1, backupNode1);
		backupRoot2 = insert(backupRoot2, backupNode2);
	}

	/**
	 * This function validates if the bnumber already exisits in the tree
	 * 
	 * @param bnum
	 * @return
	 */
	public boolean validateEntry(Integer bnum) {
		boolean nodeSearch = true;
		Node current = root;
		while (nodeSearch && null != root && null != current) {
			if (bnum < current.getbNumber()) {
				current = current.leftNode;
			} else if (bnum > current.getbNumber()) {
				current = current.rightNode;
			} else if (bnum == current.getbNumber()) {
				nodeSearch = false;
				return false; // bnumber already exisits
			} else {
				nodeSearch = false;
			}
		}
		return true;// unique bumber
	}

	/**
	 * This function is used to add course to an exsisting node in original tree and
	 * update backuptrees
	 * 
	 * @param bnumber
	 * @param courses
	 */
	public void addCourseToExisitingNode(Integer bnumber, String courses) {
		boolean nodeSearch = true;
		Node current = root;
		while (nodeSearch) {
			if (bnumber < current.getbNumber()) {
				current = current.leftNode;
			} else if (bnumber > current.getbNumber()) {
				current = current.rightNode;
			} else if (bnumber == current.getbNumber()) {
				if (!current.getCourses().contains(courses)) {
					current.getCourses().add(courses);
					int bnum = bnumber;
					current.update(bnum, courses);
				}
				nodeSearch = false;
			} else {
				nodeSearch = false;
			}
		}
	}

	/**
	 * This function is used to delete course from an exsisting node in original
	 * tree and update backuptrees
	 * 
	 * @param bNumberDelete
	 * @param courseDelete
	 */
	public void deleteCourse(int bNumberDelete, String courseDelete) {

		boolean nodeSearch = true;
		Node current = root;
		while (nodeSearch && null != root && null != current) {
			if (bNumberDelete < current.getbNumber()) {
				current = current.leftNode;
			} else if (bNumberDelete > current.getbNumber()) {
				current = current.rightNode;
			} else {
				if (current.getCourses().contains(courseDelete)) {
					current.getCourses().remove(courseDelete);
					current.notifyObservers(bNumberDelete, courseDelete);
				}
				nodeSearch = false;
			}

		}
	}

	/**
	 * This function is used to write all the tree values in output file
	 * 
	 * @param originalTreeResults
	 * @param backupTreeResults1
	 * @param backupTreeResults2
	 */
	public void printNodes(Results originalTreeResults, Results backupTreeResults1, Results backupTreeResults2) {
		addToResultList(root, originalTreeResults);
		addToResultList(backupRoot1, backupTreeResults1);
		addToResultList(backupRoot2, backupTreeResults2);
	}

	/**
	 * This function is helper function to printNodes for output value creation
	 * 
	 * @param rootNode
	 * @param result
	 */
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
