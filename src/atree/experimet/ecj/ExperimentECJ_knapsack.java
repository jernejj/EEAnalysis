package atree.experimet.ecj;

import java.util.ArrayList;

import atree.metrics.ATMetrics;
import atree.metrics.PrintAMetrics;
import atree.metrics.PrintStatATMetrics;
import atree.metrics.StatATMetrics;
import atree.treeData.Nodes;
import atree.util.Util;


public class ExperimentECJ_knapsack {
	//public static String dir = "test_cases/ecj/knapsack_10_1/"; // on mac linux "/"
	//public static String problem_1 = "EEstatKnapsack_10_1_";
	public static String dir = "test_cases/ecj/Sphere_dim2/"; // on mac linux "/"
	public static String problem_1 = "SphereEEstat_2_1_";
	public static String analiza = "a";

	private double pm;
	private double pc;
	private int pop_size;
	private int gen;
	public static String[] mixrun;
	public static int[] mixrunID;
	public static int[] printrunID;
	public static double epsilon[];
	public static void setArrays(String subdir, int i, int offset) {
		mixrun = new String[i];
		for (int j=0; j<i;j++) mixrun[j] = subdir;
		mixrunID = new int[i];
		for (int j=0; j<i;j++) mixrunID[j] = j+1;
		printrunID = new int[i];
		for (int j=0; j<i;j++) printrunID[j] = j+1+offset;	
	}

	private static ArrayList<String> createXproblmTable(String pn,String results,String tableCaption,int from, int X, int type, int myX, boolean print) {
		ArrayList<String> heads = new ArrayList<String>();
		ArrayList<ArrayList<String>> cols = new ArrayList<ArrayList<String>>();
		cols.add(PrintAMetrics.getInfoColumn());
		Nodes n;
		ATMetrics m;
		PrintAMetrics pm=null;
		String problem2;
		StatATMetrics sam=new StatATMetrics();
		int maxgeneration=10;
		//long start = System.currentTimeMillis();
		// ---------------------------------------------------------
		for (int id = from; id < (X+from); id++) {
			problem2 = dir + pn + analiza + id + ".stat";
			System.out.println(problem2);
			n = new Nodes();
			n.createAll_ECJ(problem2, maxgeneration, epsilon, false);
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

	private static void mutationMainTest(String[] mixrun,int[] mixrunID,int[] printrunID, String problemX, int x){
		ArrayList<ArrayList<String>> cols2 = new ArrayList<ArrayList<String>>();
		cols2.add(PrintStatATMetrics.getDoubleInfoColumn());
		ArrayList<String> heads = new ArrayList<String>();
		long start = System.currentTimeMillis();
		int MAXTEST = mixrun.length;
		int number_of_test_repetition=1;
		int scenario_type=3; //normal
		for (int i=0; i<MAXTEST; i++) {
			System.out.println("Start: "+(i+1)+"/"+MAXTEST);
		  heads.add(""+printrunID[i]);
		  cols2.add(createXproblmTable(problemX, mixrun[i],"$x="+x+"$",((mixrunID[i]-1)*30+1),number_of_test_repetition,scenario_type,x,false));
		  System.out.println(i+" ("+((double)(System.currentTimeMillis()-start)/1000/60)+")"+problemX+" "+mixrun[i]);
		}
		System.out.println(PrintStatATMetrics.toLatex(heads, cols2,"EE test"));	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setArrays(dir, 1, 0);
		epsilon = Util.generateEpsilonVector(10, 0.1); //for binary vector is any value less than 1 ok!
		mutationMainTest(mixrun, mixrunID, printrunID, problem_1,2);


	}

}
