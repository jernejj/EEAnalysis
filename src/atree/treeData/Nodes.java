package atree.treeData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Nodes {
	private Hashtable<String, Node> ht;
	private ArrayList<Node> initTrees;
	private ArrayList<Node> paretoList;
	public static final int SCENARIO_NORMAL = 0;
	public static final int SCENARIO_OPTIMISTIC = 1;
	public static final int SCENARIO_SEMI_OPTIMISTIC = 2;
	public Hashtable<String, Node> getAllNodes() {
		return ht;
	}
	public boolean containsKey(Object key) {
		return ht.containsKey(key);
	}

	public ArrayList<Node> getInitTrees() {
		return initTrees;
	}

	public Node put(Node value) {
		if (value.getParent() == null)
			initTrees.add(value);
		return ht.put(value.getID(), value);
	}

	public Node get(String key) {
		return ht.get(key);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (Node n : ht.values()) {
			sb.append(n.toString()).append("\n");
		}
		return sb.toString();
	}

	public Nodes() {
		super();
		ht = new Hashtable<String, Node>();
		initTrees = new ArrayList<Node>();
		paretoList = new ArrayList<Node>();
	}

	public void createAllBarbara(String file) {
		createAllBarbara(file, Integer.MAX_VALUE);
	}

	/*
	 * Example
	 * 
	 * 10 5 10
	 * 
	 * Generacija št: 1 Navadna populacija: (-1,-1)(-1,-1)(0,0) 0101110001 0 0 1
	 * 2 (-1,-1)(-1,-1)(1,0) 0001110100 0 0 0 0 (-1,-1)(-1,-1)(2,0) 0000110111 0
	 * 0 1 2 (-1,-1)(-1,-1)(3,0) 0110000010 0 0 1 1 (-1,-1)(-1,-1)(4,0)
	 * 0100100110 0 0 1 2 (-1,-1)(-1,-1)(5,0) 0000111010 0 0 0 0
	 * (-1,-1)(-1,-1)(6,0) 1001000000 0 0 0 0 (-1,-1)(-1,-1)(7,0) 0000101110 0 0
	 * 0 0 (-1,-1)(-1,-1)(8,0) 1000110100 0 0 0 0 (-1,-1)(-1,-1)(9,0) 0100000011
	 * 0 0 1 1
	 * 
	 * Generacija št: 2 ...
	 */

	public void createAllBarbara(String file, int maxgen) {
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s;
			s = br.readLine();
			int popsize = Integer.parseInt(s);
			s = br.readLine();
			int gen = Integer.parseInt(s);
			if (maxgen < gen)
				gen = maxgen;
			s = br.readLine();
			int problemSize = Integer.parseInt(s);
			for (int i = 0; i <= gen; i++) {
				s = br.readLine(); // prazna
				s = br.readLine(); // Generacija št: 1
				s = br.readLine(); // Navadna populacija:
				for (int j = 0; j < popsize; j++) {
					put(Node.convertBarbaraFormat(br.readLine(), this));

				}

			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Rezultati za problem E:/Diploma/Diploma/knapsack_10_2.txt
	 * 
	 * PARAMTERI: Verjetnost mutacije: 0.01 Verjetnost križanja: 0.8 Velikost
	 * turnirja: 2 K vrednost (izr. po enaèbi): 4 Velikost populacije: 10
	 * Velikost arhiva: 10 Število generacij: 1000 Število ovrednotenj: 20010
	 * 
	 * REZULTATI (podrobno): 1. Vrednosti: 360.0 370.0 Teze: 283.0 291.0 Bitni
	 * niz: 0000110111 Fitnes: 0.021771425375377775 (Stars1),(Stars2):
	 * (-1,-1)(-1,-1) (ID):(2,0)
	 * 
	 * 2....
	 * 
	 * Zanima nas samo (ID):(2,0)
	 */
	public void setAllBarbaraPareto(String file) {
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s;
			String stepOne[];
			while ((s = br.readLine()) != null) {
				if (s.indexOf("(ID):") > -1) { // (ID):(2,0)
					s = s.replaceAll("\\(ID\\):", "");
					s = s.replaceAll("\\(", "");
					s = s.replaceAll("\\)", "");
					stepOne = s.split(",");
					String key = "(" + stepOne[1].trim() + "," + stepOne[0].trim() + ")";
					Node n = ht.get(key);
					if (n != null) {
						n.setPareto(true);
						this.paretoList.add(n);
					} else {
						System.out.println("Cant find:" + s);
					}
				}
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Only pareto parents and pareto inniduals survy 
	 */
	public void transformInOptimisticParetoTree() {
		for (Node n:paretoList) { //set all relavant
			while (n!=null) {
				n.setTmp(true);
				n=n.getParent();
			}
		}
		//Remove from initTrees
		for (int i=initTrees.size()-1;i>-1;i--) {
			if (!initTrees.get(i).isTmp()) initTrees.remove(i);
		}
		//Remove from tree
		for (Node n:paretoList) { //set all relavant
			while (n!=null) {
				for (int j=n.getChildrens().size()-1;j>=0;j--) {
					if (!n.getChildrens().get(j).isTmp()) {
						n.getChildrens().remove(j);
					}
				}
				n=n.getParent();
			}
		}
	}
	/*
	 * Only pareto  
	 */
	public void transformInOptimisticPlusParetoTree() {
		for (Node n:paretoList) { //set all relavant
			while (n!=null) {
				n.setTmp(true);
				n=n.getParent();
				if (n!=null) {
					for (Node nn:n.getChildrens()) {
						nn.setTmp(true);
					}
				}
			}
		}
		//Remove from initTrees
		for (int i=initTrees.size()-1;i>-1;i--) {
			if (!initTrees.get(i).isTmp()) initTrees.remove(i);
		}
		//Remove from tree
		for (Node n:paretoList) { //set all relavant
			while (n!=null) {
				for (int j=n.getChildrens().size()-1;j>=0;j--) {
					if (!n.getChildrens().get(j).isTmp()) {
						n.getChildrens().remove(j);
					}
				}
				n=n.getParent();
			}
		}
	}

	/**
	 * Skips comment or blank line
	 * 
	 * @param br
	 * @return
	 * @throws IOException 
	 */
	private String getNextLine(BufferedReader br) throws IOException {
		boolean comment=false;
		String s="";
		while ((s=br.readLine()) != null) {
			if (s.trim().length()==0) continue;
			
			break;
		}
		return s;
	}
	/**
	 * ECJ format
	 * Generation: 0
	 * p1(-1,-1) p2(-1,-1) id(0,0) in( 1 1 0 0 0 1 0 0 0 0) c0 m0 r0
	 * p1(-1,-1) p2(-1,-1) id(1,0) in( 1 0 0 0 1 0 1 1 1 1) c0 m0 r0
	 * ...
	 *  
	 * @param file
	 * @param maxgen
	 * @param useMaxGen calculate statistic for only first maxgen
	 */
	public void createAll_ECJ(String file, int maxgen, double epsilon[], boolean useMaxGen) {
		FileReader fr;
		int currGen=0;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while(null != (s = br.readLine())) {
				if (s.contains("Generation:")) {
					s=s.replaceFirst("Generation:", "");
					currGen = Integer.parseInt(s.trim());
					//System.out.println("Generation:"+currGen);
				}
				if (useMaxGen && (currGen > maxgen)) return;
				if (s.contains("id(")) { //new individual create node
					put(Node.convert4String(s, this,epsilon));	
				}
				
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ECJ format
	 * Generation: 0
	 * p1(-1,-1) p2(-1,-1) id(0,0) in( 1 1 0 0 0 1 0 0 0 0) c0 m0 r0
	 * p1(-1,-1) p2(-1,-1) id(1,0) in( 1 0 0 0 1 0 1 1 1 1) c0 m0 r0
	 * 
	 * Calculates the EE statistics with sum of diff on dimensions
	 *  
	 * @param file
	 * @param maxgen
	 * @param useMaxGen calculate statistic for only first maxgen
	 */
	public void createAll_ECJ(String file, int maxgen, double epsilon, boolean useMaxGen) 
	{
		FileReader fr;
		int currGen=0;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while(null != (s = br.readLine())) 
			{
				if (s.contains("Generation:"))
				{
					s=s.replaceFirst("Generation:", "");
					currGen = Integer.parseInt(s.trim());
					//System.out.println("Generation:"+currGen);
				}
				
				if (useMaxGen && (currGen > maxgen)) return;
				
				if (s.contains("id(")) { //new individual create node
					put(Node.convert4String(s, this,epsilon));	
				}
				
			}
			fr.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
