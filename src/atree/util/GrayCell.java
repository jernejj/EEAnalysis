package atree.util;

import java.util.ArrayList;

import atree.treeData.Node;


public class GrayCell {

	ArrayList<Node> elements;
	public static final int MUTATION=1; 
	public static final int CROSSOVER=2; 
	public static final int REPAIR=3;
	public static final int RANDOM=4; 
	public static final int EXPLOR=5; 
	public static final int EXPLIT=6; 
	public GrayCell() {
		super();
	}
	public void reset() {
	}
	public void add(Node n) {
		if (elements==null) elements = new ArrayList<Node>();
		elements.add(n);
		
	}
	/**
	 * @param argsê§
	 */
	public static void main(String[] args) {


	}

}
