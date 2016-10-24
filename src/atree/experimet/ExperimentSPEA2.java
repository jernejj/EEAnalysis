package atree.experimet;

import java.util.ArrayList;

import atree.metrics.ATMetrics;
import atree.metrics.PrintAMetrics;
import atree.treeData.Node;
import atree.treeData.Nodes;


public class ExperimentSPEA2 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> heads = new ArrayList<String>();
		ArrayList<ArrayList<String>> cols = new ArrayList<ArrayList<String>>();
		cols.add(PrintAMetrics.getInfoColumn());
		String rootPath = "/Users/matej/Documents/clanki2010/bioma-latex/ResultData/";
		String problem1 = rootPath + "2_10/Analiza_2_10_5g.txt";
		Nodes n = new Nodes();
		n.createAllBarbara(problem1,50);
//		heads.add("$\\tau^{(2)}$");
		ATMetrics m = new ATMetrics(n.getInitTrees(), 2);
		PrintAMetrics pm = new PrintAMetrics(m);
//		cols.add(pm.getColumn());
		//---------------------------------------------------------
		String problem2 = rootPath + "2_100/Analiza_2_100_290g.txt";
		n = new Nodes();
		n.createAllBarbara(problem2,50);
		//-------x=2
		heads.add("$\\tau^{(3)}$");
		m = new ATMetrics(n.getInitTrees(), 3);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=3
		heads.add("$\\tau^{(4)}$");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=5
		heads.add("$\\tau^{(5)}$");
		m = new ATMetrics(n.getInitTrees(), 5);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=10
		heads.add("$\\tau^{(6)}$");
		m = new ATMetrics(n.getInitTrees(), 6);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=20
		heads.add("$\\tau^{(10)}$");
		m = new ATMetrics(n.getInitTrees(), 10);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//Print
		System.out.println(pm.toLatex(heads, cols,"Analyse of problem $n=2$, $m=100$ after $50$ generations"));
		//New table clear and print
		heads.clear();
		cols.clear();
		cols.add(PrintAMetrics.getInfoColumn());
		//100
		n = new Nodes();
		n.createAllBarbara(problem2,100);
		//-------x=2
		heads.add("$\\tau^{(3)}$");
		m = new ATMetrics(n.getInitTrees(), 3);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=3
		heads.add("$\\tau^{(4)}$");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=5
		heads.add("$\\tau^{(5)}$");
		m = new ATMetrics(n.getInitTrees(), 5);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=10
		heads.add("$\\tau^{(6)}$");
		m = new ATMetrics(n.getInitTrees(), 6);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=20
		heads.add("$\\tau^{(10)}$");
		m = new ATMetrics(n.getInitTrees(), 10);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//Print
		System.out.println(pm.toLatex(heads, cols,"Analyse of problem $n=2$, $m=100$ after $100$ generations"));
		//New table clear and print
		heads.clear();
		cols.clear();
		cols.add(PrintAMetrics.getInfoColumn());
		//150
		n = new Nodes();
		n.createAllBarbara(problem2,150);
		//-------x=2
		heads.add("$\\tau^{(3)}$");
		m = new ATMetrics(n.getInitTrees(), 3);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=3
		heads.add("$\\tau^{(4)}$");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=5
		heads.add("$\\tau^{(5)}$");
		m = new ATMetrics(n.getInitTrees(), 5);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=10
		heads.add("$\\tau^{(6)}$");
		m = new ATMetrics(n.getInitTrees(), 6);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=20
		heads.add("$\\tau^{(10)}$");
		m = new ATMetrics(n.getInitTrees(), 10);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//Print
		System.out.println(pm.toLatex(heads, cols,"Analyse of problem $n=2$, $m=100$ after $150$ generations"));
		//New table clear and print
		heads.clear();
		cols.clear();
		cols.add(PrintAMetrics.getInfoColumn());		
		//200
		n = new Nodes();
		n.createAllBarbara(problem2,290);
		//-------x=2
		heads.add("$\\tau^{(3)}$");
		m = new ATMetrics(n.getInitTrees(), 3);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=3
		heads.add("$\\tau^{(4)}$");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=5
		heads.add("$\\tau^{(5)}$");
		m = new ATMetrics(n.getInitTrees(), 5);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=10
		heads.add("$\\tau^{(6)}$");
		m = new ATMetrics(n.getInitTrees(), 6);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=20
		heads.add("$\\tau^{(10)}$");
		m = new ATMetrics(n.getInitTrees(), 10);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//Print
		System.out.println(pm.toLatex(heads, cols,"Analyse of problem $n=2$, $m=100$ after $290$ generations"));
		//New table clear and print
		heads.clear();
		cols.clear();
		cols.add(PrintAMetrics.getInfoColumn());		
/*
 		String problem3 = rootPath + "2_100/Analiza_2_100_100g.txt";
		n = new Nodes();
		n.createAllBarbara(problem3);
		//-------x=2
		heads.add("$\\tau^{(3)}$");
		m = new ATMetrics(n.getInitTrees(), 3);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=3
		heads.add("$\\tau^{(4)}$");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=5
		heads.add("$\\tau^{(5)}$");
		m = new ATMetrics(n.getInitTrees(), 5);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=10
		heads.add("$\\tau^{(6)}$");
		m = new ATMetrics(n.getInitTrees(), 6);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=20
		heads.add("$\\tau^{(10)}$");
		m = new ATMetrics(n.getInitTrees(), 10);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		
		
		//Print
		System.out.println(pm.toLatex(heads, cols));
 */
		n = new Nodes();
		n.createAllBarbara(problem1,50);
//		heads.add("$\\tau^{(2)}$");
		 m = new ATMetrics(n.getInitTrees(), 2);
		 pm = new PrintAMetrics(m);
//		cols.add(pm.getColumn());
		//---------------------------------------------------------
		 problem2 = rootPath + "2_100/Analiza_2_100_290g.txt";
		n = new Nodes();
		n.createAllBarbara(rootPath + "2_100/Analiza_2_100_290g.txt",290);
		n.setAllBarbaraPareto(rootPath +"2_100/NajdenaFronta_2_100_290g.txt");
		n.transformInOptimisticParetoTree();
		//-------x=2
		heads.add("$\\tau^{(3)}$");
		m = new ATMetrics(n.getInitTrees(), 3);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=3
		heads.add("$\\tau^{(4)}$");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=5
		heads.add("$\\tau^{(5)}$");
		m = new ATMetrics(n.getInitTrees(), 5);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=10
		heads.add("$\\tau^{(6)}$");
		m = new ATMetrics(n.getInitTrees(), 6);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//-------x=20
		heads.add("$\\tau^{(10)}$");
		m = new ATMetrics(n.getInitTrees(), 10);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		//Print
		System.out.println(pm.toLatex(heads, cols,"Optimistic tree of problem $n=2$, $m=100$ after $290$ generations"));
		heads.clear();
		cols.clear();
		cols.add(PrintAMetrics.getInfoColumn());
		//-------------------------
		
		 problem2 = rootPath + "2_100/Analiza_2_100_290g.txt";
			n = new Nodes();
			n.createAllBarbara(problem2,290);
			n.setAllBarbaraPareto(rootPath +"2_100/NajdenaFronta_2_100_290g.txt");
			n.transformInOptimisticPlusParetoTree();
			//-------x=2
			heads.add("$\\tau^{(3)}$");
			m = new ATMetrics(n.getInitTrees(), 3);
			pm = new PrintAMetrics(m);
			cols.add(pm.getColumn());
			//-------x=3
			heads.add("$\\tau^{(4)}$");
			m = new ATMetrics(n.getInitTrees(), 4);
			pm = new PrintAMetrics(m);
			cols.add(pm.getColumn());
			//-------x=5
			heads.add("$\\tau^{(5)}$");
			m = new ATMetrics(n.getInitTrees(), 5);
			pm = new PrintAMetrics(m);
			cols.add(pm.getColumn());
			//-------x=10
			heads.add("$\\tau^{(6)}$");
			m = new ATMetrics(n.getInitTrees(), 6);
			pm = new PrintAMetrics(m);
			cols.add(pm.getColumn());
			//-------x=20
			heads.add("$\\tau^{(10)}$");
			m = new ATMetrics(n.getInitTrees(), 10);
			pm = new PrintAMetrics(m);
			cols.add(pm.getColumn());
			//Print
			System.out.println(pm.toLatex(heads, cols,"Optimistic plus tree of problem $n=2$, $m=100$ after $290$ generations"));
			heads.clear();
			cols.clear();
			cols.add(PrintAMetrics.getInfoColumn());	
	
		
		}

}
