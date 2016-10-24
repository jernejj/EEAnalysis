package atree.experimet;

import java.util.ArrayList;

import atree.metrics.ATMetrics;
import atree.metrics.PrintAMetrics;
import atree.metrics.PrintStatATMetrics;
import atree.metrics.StatATMetrics;
import atree.treeData.Node;
import atree.treeData.Nodes;


public class ExperimentIEEESmall {
	public static String dir = "/Users/matej/testdata/"; // on// mac
	public static String problem18 = "knapsack_18_2";
	public static String analiza = "Analiza";
	public static String front = "NajdenaFronta";
	public static String results = "vega/";
	double pm;
	double pc;
	int pop_size;
	int gen;
	public static String[] mixrun;
	public static int[] mixrunID;
	public static int[] printrunID;
	public static void setArrays(String subdir, int i, int offset) {
		mixrun = new String[i];
		for (int j=0; j<i;j++) mixrun[j] = subdir;
		mixrunID = new int[i];
		for (int j=0; j<i;j++) mixrunID[j] = j+1;
		printrunID = new int[i];
		for (int j=0; j<i;j++) printrunID[j] = j+1+offset;	
	}

	public static ArrayList<String> createXproblmTable(String pn,String results,String tableCaption,int from, int X, int type, int myX, boolean print) {
		StringBuffer sb = new StringBuffer();
		ArrayList<String> heads = new ArrayList<String>();
		ArrayList<ArrayList<String>> cols = new ArrayList<ArrayList<String>>();
		cols.add(PrintAMetrics.getInfoColumn());
		Nodes n;
		ATMetrics m;
		PrintAMetrics pm=null;
		String problem2;
		StatATMetrics sam=new StatATMetrics();
		//long start = System.currentTimeMillis();
		// ---------------------------------------------------------
		for (int id = from; id < (X+from); id++) {
			problem2 = dir + results + pn + analiza + id + ".txt";
			n = new Nodes();
			n.createAllBarbara(problem2, 290);
			n.setAllBarbaraPareto(dir + results + pn + front + id + ".txt");
			if (type==1) {
				n.transformInOptimisticParetoTree();
			}
			if (type==2) {
				n.transformInOptimisticPlusParetoTree();
			}
			heads.add("" + id);
			m = new ATMetrics(n.getInitTrees(), myX);
			pm = new PrintAMetrics(m);
			sam.add(m);
			cols.add(pm.getColumn());
			//System.out.print(id +"("+((double)(System.currentTimeMillis()-start)/1000/60)+") ");
		}
		PrintStatATMetrics statTAble = new PrintStatATMetrics(sam);
		if (print) System.out.println(pm.toLatex(heads, cols,tableCaption));
		// New table clear and print
		heads.clear();
		cols.clear();
		cols.add(PrintAMetrics.getInfoColumn());
		return statTAble.getDoubleColumn();

	}

	public static void createOverGenerationTable() {
		ArrayList<String> heads = new ArrayList<String>();
		ArrayList<ArrayList<String>> cols = new ArrayList<ArrayList<String>>();
		cols.add(PrintAMetrics.getInfoColumn());
		String rootPath = "/Users/matej/Documents/clanki2010/IEEETec/ResultData/";
		Nodes n;
		ATMetrics m;
		PrintAMetrics pm;
		String problem2;
		// ---------------------------------------------------------
		problem2 = rootPath + "knapsack_100_2_10Analiza.txt";
		// x=3-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2, 290);
		heads.add("{\\footnotesize$x=3$}");
		m = new ATMetrics(n.getInitTrees(), 3);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		// x=4-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2, 290);
		heads.add("{\\footnotesize$x=4$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		// x=5-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2, 290);
		heads.add("{\\footnotesize$x=5$}");
		m = new ATMetrics(n.getInitTrees(), 5);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		// 50-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2, 50);
		heads.add("{\\footnotesize$g=50$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		// 100-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2, 100);
		heads.add("{\\footnotesize$g=100$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		// 150-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2, 150);
		heads.add("{\\footnotesize$g=150$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		// 200-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2, 200);
		heads.add("{\\footnotesize$g=200$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		// 250-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2, 250);
		heads.add("{\\footnotesize$g=250$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		// 290-----------------------------
		n = new Nodes();
		n.createAllBarbara(problem2, 290);
		heads.add("{\\footnotesize$g=290$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());
		System.out
				.println(pm
						.toLatex(heads, cols,
								"Analyse of threshold $x$ and influence of generations over time in $x=4$"));
		// New table clear and print
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
		// ---------------------------------------------------------
		// 0.5-----------------------------
		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_5Analiza.txt");
		heads.add("{\\footnotesize$n(.5\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_5Analiza.txt");
		n.setAllBarbaraPareto(rootPath + "knapsack_100_2_5NajdenaFronta.txt");
		n.transformInOptimisticParetoTree();
		heads.add("{\\footnotesize$ o(.5\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_5Analiza.txt");
		n.setAllBarbaraPareto(rootPath + "knapsack_100_2_5NajdenaFronta.txt");
		n.transformInOptimisticPlusParetoTree();
		heads.add("{\\footnotesize$ s(.5\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		// 1-----------------------------
		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_10Analiza.txt");
		heads.add("{\\footnotesize$ n(1\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_10Analiza.txt");
		n.setAllBarbaraPareto(rootPath + "knapsack_100_2_10NajdenaFronta.txt");
		n.transformInOptimisticParetoTree();
		heads.add("{\\footnotesize$ o(1\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_10Analiza.txt");
		n.setAllBarbaraPareto(rootPath + "knapsack_100_2_10NajdenaFronta.txt");
		n.transformInOptimisticPlusParetoTree();
		heads.add("{\\footnotesize$s(1\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		// 2-----------------------------
		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_20Analiza.txt");
		heads.add("{\\footnotesize$n(2\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_20Analiza.txt");
		n.setAllBarbaraPareto(rootPath + "knapsack_100_2_20NajdenaFronta.txt");
		n.transformInOptimisticParetoTree();
		heads.add("{\\footnotesize$ o(2\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		n = new Nodes();
		n.createAllBarbara(rootPath + "knapsack_100_2_20Analiza.txt");
		n.setAllBarbaraPareto(rootPath + "knapsack_100_2_20NajdenaFronta.txt");
		n.transformInOptimisticPlusParetoTree();
		heads.add("{\\footnotesize$ s(2\\%)$}");
		m = new ATMetrics(n.getInitTrees(), 4);
		pm = new PrintAMetrics(m);
		cols.add(pm.getColumn());

		// ---------------------------------------------------------
		System.out
				.println(pm
						.toLatex(
								heads,
								cols,
								"Mutation parameter analyse on normal ($n$), optimistic ($o$) and semioptimistic ($s$) tree"));

	}

	public static void allScenariosForFirst10(String problem){
		ArrayList<ArrayList<String>> cols2 = new ArrayList<ArrayList<String>>();
		cols2.add(PrintStatATMetrics.getInfoColumn());
		ArrayList<String> heads = new ArrayList<String>();

		//createOverGenerationTable();
		//createOverMuatationTestTable();
		heads.add("$VEGA^{(opt)}$");
		cols2.add(createXproblmTable(problem, "vega/","Analyse of VEGA for $x=4$ (10 runs), $p\\_m=0.01$, $p\\_c=0.8$, $pop\\_size=250$, $g=290$",1,10,1,4,true));
		heads.add("$SPEA2^{(opt)}$");
		cols2.add(createXproblmTable(problem, "spea2/","Analyse of SPEA2 for $x=4$ (10 runs), $p\\_m=0.01$, $p\\_c=0.8$, $pop\\_size=250$, $g=290$",1,10,1,4,true));		
		
		heads.add("$VEGA^{(sem)}$");
		cols2.add(createXproblmTable(problem, "vega/","Analyse of VEGA for $x=4$ (10 runs), $p\\_m=0.01$, $p\\_c=0.8$, $pop\\_size=250$, $g=290$",1,10,2,4,true));
		heads.add("$SPEA2^{(sem)}$");
		cols2.add(createXproblmTable(problem, "spea2/","Analyse of SPEA2 for $x=4$ (10 runs), $p\\_m=0.01$, $p\\_c=0.8$, $pop\\_size=250$, $g=290$",1,10,2,4,true));		
		
		heads.add("$VEGA^{(all)}$");
		cols2.add(createXproblmTable(problem, "vega/","Analyse of VEGA for $x=4$ (10 runs), $p\\_m=0.01$, $p\\_c=0.8$, $pop\\_size=250$, $g=290$",1,10,3,4,true));
		heads.add("$SPEA2^{(all)}$");
		cols2.add(createXproblmTable(problem, "spea2/","Analyse of SPEA2 for $x=4$ (10 runs), $p\\_m=0.01$, $p\\_c=0.8$, $pop\\_size=250$, $g=290$",1,10,3,4,true));
		System.out.println(PrintStatATMetrics.toLatex(heads, cols2,"Statistic for (X=10)"));
		
	}
	public static void mutationTestFirst30(String problem){
		System.out.println("Start mutationTestFirst30()");
		ArrayList<ArrayList<String>> cols2 = new ArrayList<ArrayList<String>>();
		cols2.add(PrintStatATMetrics.getInfoColumn());
		ArrayList<String> heads = new ArrayList<String>();
		long start = System.currentTimeMillis();
		//createOverGenerationTable();
		//createOverMuatationTestTable();
		heads.add("$VEGA^{(0.005\\%)}$");
		cols2.add(createXproblmTable(problem, "vega/","Analyse of VEGA for $x=4$, $p\\_m=0.005$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",1,30,3,4,false));
		System.out.println("1 ("+((double)(System.currentTimeMillis()-start)/1000/60)+")"+problem+" ");

		heads.add("$SPEA2^{(0.005\\%)}$");
		cols2.add(createXproblmTable(problem, "spea2/","Analyse of SPEA2 for $x=4$, $p\\_m=0.005$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",1,30,3,4,false));		
		System.out.println("2 ("+((double)(System.currentTimeMillis()-start)/1000/60)+")"+problem+" ");
		
		heads.add("$VEGA^{(0.0075\\%)}$");
		cols2.add(createXproblmTable(problem, "vega/","Analyse of VEGA for $x=4$, $p\\_m=0.0075$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",31,30,3,4,false));
		System.out.println("3 ("+((double)(System.currentTimeMillis()-start)/1000/60)+")"+problem+" ");

		heads.add("$SPEA2^{(0.0075\\%)}$");
		cols2.add(createXproblmTable(problem, "spea2/","Analyse of SPEA2 for $x=4$, $p\\_m=0.0075$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",31,30,3,4,false));		
		System.out.println("4 ("+((double)(System.currentTimeMillis()-start)/1000/60)+")"+problem+" ");

		heads.add("$VEGA^{(0.01\\%)}$");
		cols2.add(createXproblmTable(problem, "vega/","Analyse of VEGA for $x=4$, $p\\_m=0.01$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",61,30,3,4,false));
		heads.add("$SPEA2^{(0.01\\%)}$");
		cols2.add(createXproblmTable(problem, "spea2/","Analyse of SPEA2 for $x=4$, $p\\_m=0.01$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",61,30,3,4,false));		

		heads.add("$VEGA^{(0.0125\\%)}$");
		cols2.add(createXproblmTable(problem, "vega/","Analyse of VEGA for $x=4$, $p\\_m=0.0125$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",91,30,3,4,false));
		heads.add("$SPEA2^{(0.0125\\%)}$");
		cols2.add(createXproblmTable(problem, "spea2/","Analyse of SPEA2 for $x=4$, $p\\_m=0.0125$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",91,30,3,4,false));		

		heads.add("$VEGA^{(0.015\\%)}$");
		cols2.add(createXproblmTable(problem, "vega/","Analyse of VEGA for $x=4$, $p\\_m=0.015$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",91,30,3,4,false));
		heads.add("$SPEA2^{(0.015\\%)}$");
		cols2.add(createXproblmTable(problem, "spea2/","Analyse of SPEA2 for $x=4$, $p\\_m=0.015$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",91,30,3,4,false));		

		heads.add("$VEGA^{(0.0175\\%)}$");
		cols2.add(createXproblmTable(problem, "vega/","Analyse of VEGA for $x=4$, $p\\_m=0.0175$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",121,30,3,4,false));
		heads.add("$SPEA2^{(0.0175\\%)}$");
		cols2.add(createXproblmTable(problem, "spea2/","Analyse of SPEA2 for $x=4$, $p\\_m=0.0175$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",121,30,3,4,false));		

		heads.add("$VEGA^{(0.02\\%)}$");
		cols2.add(createXproblmTable(problem, "vega/","Analyse of VEGA for $x=4$, $p\\_m=0.02$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",151,30,3,4,false));
		heads.add("$SPEA2^{(0.02\\%)}$");
		cols2.add(createXproblmTable(problem, "spea2/","Analyse of SPEA2 for $x=4$, $p\\_m=0.02$, $p\\_c=0.8$, $pop\\_size=200$, $g=250$",151,30,3,4,false));		

		System.out.println(PrintStatATMetrics.toLatex(heads, cols2,"Statistic (n=30) $p\\_m$=\\{0.5\\%, 0.75\\%, 1\\%, 1.25\\%, 1.75\\%, 2\\%\\}"));
		
	}

	public static void mutationMainTest(String[] mixrun,int[] mixrunID,int[] printrunID, String problemX, int x){
		ArrayList<ArrayList<String>> cols2 = new ArrayList<ArrayList<String>>();
		cols2.add(PrintStatATMetrics.getDoubleInfoColumn());
		ArrayList<String> heads = new ArrayList<String>();
		long start = System.currentTimeMillis();
		int MAXTEST = mixrun.length;
		for (int i=0; i<MAXTEST; i++) {
			System.out.println("Start: "+(i+1)+"/"+MAXTEST);
		  heads.add(""+printrunID[i]);
		  cols2.add(createXproblmTable(problemX, mixrun[i],"$x="+x+"$",((mixrunID[i]-1)*30+1),30,3,x,false));
		  System.out.println(i+" ("+((double)(System.currentTimeMillis()-start)/1000/60)+")"+problemX+" "+mixrun[i]);
		}
		System.out.println(PrintStatATMetrics.toLatex(heads, cols2,"EE test"));	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Scenario 100
		//mutationTestFirst30(); 
		//mutationMainTest(ExperimentIEEEmetricC.mixrun, ExperimentIEEEmetricC.mixrunID, ExperimentIEEEmetricC.printrunID,problem100,4);

		//setArrays(ExperimentIEEEmetricC.resultsvega, 9, 0);
		//mutationMainTest(mixrun, mixrunID, printrunID, problem100,4);

		//setArrays(ExperimentIEEEmetricC.resultsspea2, 9, 9);
		//mutationMainTest(mixrun, mixrunID, printrunID, problem10,4);

		//setArrays(ExperimentIEEEmetricC.resultsvega, 9, 0);
		//mutationMainTest(mixrun, mixrunID, printrunID);

		
		//Scenario 18
		setArrays("2_18/"+ExperimentIEEEmetricC.resultsspea2, 9, 9);
		mutationMainTest(mixrun, mixrunID, printrunID, problem18,3);

		setArrays("2_18/"+ExperimentIEEEmetricC.resultsvega, 9, 0);
		mutationMainTest(mixrun, mixrunID, printrunID, problem18,3);

	}

}
