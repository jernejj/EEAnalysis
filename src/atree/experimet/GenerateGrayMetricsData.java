package atree.experimet;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import atree.metrics.ATMetrics;
import atree.metrics.PrintAMetrics;
import atree.metrics.StatATMetrics;
import atree.treeData.Node;
import atree.treeData.Nodes;
import atree.util.GrayMetrics;


public class GenerateGrayMetricsData {
	public static String dir = "/Users/matej/Documents/clanki2010/IEEETec/ResultData/"; // on
	// mac                      /Users/matej/Documents/clanki2010/IEEETec/ResultData/knapsack_10_2NajdenaFronta1.txt
	//     knapsack_10_2NajdenaFronta1.txt
	public static String problem10 = "knapsack_10_2";
	public static String problem18 = "knapsack_18_2";
	public static String analiza = "Analiza";
	public static String front = "NajdenaFronta";
	public static String getGNUPLOT(String n1, String exploreName, String exploitName) {
		return "plot \""+n1+"\" using 1:2:3 with image, " +
				"\""+exploreName+"\" title 'Explore'  with points lc rgb \"#472171\" pointtype 2, \""+
				exploitName+"\" title 'Exploot'  with points lc rgb \"#29A291\" pointtype 3";
	}
	public static void createDataTable(int size, int x, String resultsdir, String problem, int id, int gen, int genStep, int X) throws FileNotFoundException {
		StringBuffer sb = new StringBuffer();
		GrayMetrics gm = new GrayMetrics(size);
		Nodes n;
		ATMetrics m;
		// ---------------------------------------------------------
		String problem2 = dir + resultsdir + problem + analiza + id + ".txt";
		n = new Nodes();
		n.createAllBarbara(problem2, gen);
		n.setAllBarbaraPareto(dir + resultsdir + problem + front + id + ".txt");
		//	if (type==1) { n.transformInOptimisticParetoTree(); }
		//	if (type==2) {n.transformInOptimisticPlusParetoTree();}
		//	heads.add("" + id);
		m = new ATMetrics(n.getInitTrees(), 4);
		//Collection<Node> list = n.getAllNodes().values();
		for (Node node:n.getAllNodes().values()) {
			gm.add(node);
			System.out.println(node.getChromo());
		}
		String name1 = dir + resultsdir + problem + id + "Data.txt";
		String name2 = dir + resultsdir + problem + id + "Explore.txt";
		String name3 = dir + resultsdir + problem + id + "Exploit.txt";
		PrintWriter fw;
		fw = new PrintWriter(name1);
		fw.println(gm.getDataTableArea(0)); //all
		fw.flush();
		fw.close();
		fw = new PrintWriter(name2);
		fw.println(gm.getExplore(X));
		fw.flush();
		fw.close();
		fw = new PrintWriter(name3);
		fw.println(gm.getExploit(X));
		fw.flush();
		fw.close();
		System.out.println(getGNUPLOT(name1, name2,name3));
		//System.out.println(gm.getDataTable()); 
		//System.out.println(gm.getExploit(2)); 
		//System.out.println(gm.getExplore(2)); 
	}
	public static void createDataTableCells(int size, String resultsdir, String problem, int id, int gen, int genStep, int X) throws FileNotFoundException {
		StringBuffer sb = new StringBuffer();
		GrayMetrics gm = new GrayMetrics(size);
		Nodes n;
		ATMetrics m;
		// ---------------------------------------------------------
		String problem2 = dir + resultsdir + problem + analiza + id + ".txt";
		n = new Nodes();
		n.createAllBarbara(problem2, gen);
		n.setAllBarbaraPareto(dir + resultsdir + problem + front + id + ".txt");
		//	if (type==1) { n.transformInOptimisticParetoTree(); }
		//	if (type==2) {n.transformInOptimisticPlusParetoTree();}
		//	heads.add("" + id);
		m = new ATMetrics(n.getInitTrees(), X);
		//Collection<Node> list = n.getAllNodes().values();
		for (Node node:n.getAllNodes().values()) {
			if (node.getIdGen()<=gen) {
			gm.add(node);
			//System.out.println(node.getChromo());
			}
		}
		//String name1 = dir + resultsdir + problem + id + "Data.txt";
		String name2 = dir + resultsdir + problem + id + "Explore"+gen+".txt";
		String name3 = dir + resultsdir + problem + id + "Exploit"+gen+".txt";
		String name22 = dir + resultsdir + problem + id + "SExplore"+gen+".txt"; //S for Shadow
		String name33 = dir + resultsdir + problem + id + "SExploit"+gen+".txt"; //S for Shadow
		PrintWriter fw;

		fw = new PrintWriter(name2);
		fw.println(gm.getOptimizedEEDataTableArea(X,true,false));
		fw.flush();
		fw.close();
		fw = new PrintWriter(name3);
		fw.println(gm.getOptimizedEEDataTableArea(X,false,false));
		fw.flush();
		fw.close();
		fw = new PrintWriter(name22);
		fw.println(gm.getOptimizedEEDataTableArea(X,true,true));
		fw.flush();
		fw.close();
		fw = new PrintWriter(name33);
		fw.println(gm.getOptimizedEEDataTableArea(X,false,true));
		fw.flush();
		fw.close();
		System.out.println(name2);
		System.out.println(name3);
		System.out.println(name22);
		System.out.println(name33);		//System.out.println(getGNUPLOT(name1, name2,name3));
		//System.out.println(gm.getDataTable()); 
		//System.out.println(gm.getExploit(2)); 
		//System.out.println(gm.getExplore(2)); 
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//createDataTableCells(18,"2_18/vega/",problem18,165, 75, 20,3);
		//createDataTableCells(18,"2_18s/spea2/",problem18,225, 75, 20,3);
		createDataTableCells(18,"2_18/spea2/",problem18,225, 25, 20,3);
		createDataTableCells(18,"2_18/spea2/",problem18,225, 50, 20,3);
		createDataTableCells(18,"2_18/spea2/",problem18,225, 75, 20,3);
		createDataTableCells(18,"2_18/spea2/",problem18,225, 100, 20,3);
		createDataTableCells(18,"2_18/vega/",problem18,165, 25, 20,3);
		createDataTableCells(18,"2_18/vega/",problem18,165, 50, 20,3);
		createDataTableCells(18,"2_18/vega/",problem18,165, 75, 20,3);
		createDataTableCells(18,"2_18/vega/",problem18,165, 100, 20,3);
	}

}
