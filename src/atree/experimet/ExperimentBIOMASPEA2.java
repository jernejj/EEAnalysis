package atree.experimet;

import java.util.ArrayList;

import atree.metrics.ATMetrics;
import atree.metrics.PrintAMetrics;
import atree.treeData.Node;
import atree.treeData.Nodes;


public class ExperimentBIOMASPEA2 {
	public static void createOverGenerationTable() {
		ArrayList<String> heads = new ArrayList<String>();
		ArrayList<ArrayList<String>> cols = new ArrayList<ArrayList<String>>();
		cols.add(PrintAMetrics.getInfoColumn());
		String rootPath = "/Users/matej/Documents/clanki2010/IEEETec/ResultData/";
		Nodes n;
		ATMetrics m;
		PrintAMetrics pm;
		String problem2;
		//---------------------------------------------------------
		problem2 = rootPath + "knapsack_100_2_10Analiza.txt";
		//x=3-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2,290);
		heads.add("{\\footnotesize$x=3$}");
		m = new ATMetrics(n.getInitTrees(), 3);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//x=4-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2,290);
		heads.add("{\\footnotesize$x=4$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//x=5-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2,290);
		heads.add("{\\footnotesize$x=5$}");
		m = new ATMetrics(n.getInitTrees(), 5);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//50-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2,50);
		heads.add("{\\footnotesize$g=50$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//100-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2,100);
		heads.add("{\\footnotesize$g=100$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//150-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2,150);
		heads.add("{\\footnotesize$g=150$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//200-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2,200);
		heads.add("{\\footnotesize$g=200$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//250-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2,250);
		heads.add("{\\footnotesize$g=250$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//290-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2,290);
		heads.add("{\\footnotesize$g=290$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		System.out.println(pm.toLatex(heads, cols,"Analyse of threshold $x$ and influence of generations over time in $x=4$"));
		//New table clear and print
		heads.clear();
		cols.clear();
		cols.add(PrintAMetrics.getInfoColumn());		

	}
	public static void createOverMuatationTestTable() {
		ArrayList<String> heads = new ArrayList<String>();
		ArrayList<ArrayList<String>> cols = new ArrayList<ArrayList<String>>();
		cols.add(PrintAMetrics.getInfoColumn());
		String rootPath = "/Users/matej/Documents/clanki2010/IEEETec/ResultData/";
		Nodes n;
		ATMetrics m;
		PrintAMetrics pm;
		//---------------------------------------------------------
		//0.5-----------------------------
		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_5Analiza.txt");
		heads.add("{\\footnotesize$n(.5\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		
		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_5Analiza.txt");
		n.setAllBarbaraPareto(rootPath +"knapsack_100_2_5NajdenaFronta.txt");
		n.transformInOptimisticParetoTree();		
		heads.add("{\\footnotesize$ o(.5\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_5Analiza.txt");
		n.setAllBarbaraPareto(rootPath +"knapsack_100_2_5NajdenaFronta.txt");
		n.transformInOptimisticPlusParetoTree();		
		heads.add("{\\footnotesize$ s(.5\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		//1-----------------------------
		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_10Analiza.txt");
		heads.add("{\\footnotesize$ n(1\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		
		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_10Analiza.txt");
		n.setAllBarbaraPareto(rootPath +"knapsack_100_2_10NajdenaFronta.txt");
		n.transformInOptimisticParetoTree();		
		heads.add("{\\footnotesize$ o(1\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_10Analiza.txt");
		n.setAllBarbaraPareto(rootPath +"knapsack_100_2_10NajdenaFronta.txt");
		n.transformInOptimisticPlusParetoTree();		
		heads.add("{\\footnotesize$s(1\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		//2-----------------------------
		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_20Analiza.txt");
		heads.add("{\\footnotesize$n(2\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		
		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_20Analiza.txt");
		n.setAllBarbaraPareto(rootPath +"knapsack_100_2_20NajdenaFronta.txt");
		n.transformInOptimisticParetoTree();		
		heads.add("{\\footnotesize$ o(2\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_20Analiza.txt");
		n.setAllBarbaraPareto(rootPath +"knapsack_100_2_20NajdenaFronta.txt");
		n.transformInOptimisticPlusParetoTree();		
		heads.add("{\\footnotesize$ s(2\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
	
		//---------------------------------------------------------		
		System.out.println(pm.toLatex(heads, cols,"Mutation parameter analyse on normal ($n$), optimistic ($o$) and semioptimistic ($s$) tree"));

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		createOverGenerationTable();
		createOverMuatationTestTable();
		
	}

}
