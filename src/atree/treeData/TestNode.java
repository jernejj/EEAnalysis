package atree.treeData;

import java.util.ArrayList;

public class TestNode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Nodes all = new Nodes();
		Node t = Node.convertBarbaraFormat("(-1,-1)(-1,-1)(5,0) 0000111010 1 1 1 2",all);
		System.out.println(t);
		all.put(t);
		t = Node.convertBarbaraFormat("(5,0)(-1,-1)(5,1) 0000111010 1 0 1 2",all);
		System.out.println(t);
		all.put(t);
	}

}
