package atree.experimet.ears;

import java.util.ArrayList;

import atree.metrics.ATMetrics;
import atree.metrics.PrintAMetrics;
import atree.metrics.PrintStatATMetrics;
import atree.metrics.StatATMetrics;
import atree.treeData.NodesEARS;


public class ExperimentEARS {
	//RastriginEEstatPSO_a1.stat
	//SpherePSOOut_a1.STAT
	public static String dir = "test_cases/ears/"; // on mac linux "/"
	public static String problem_1 = "DESphere";
	//public static String problem_2 = "SphereEEstatPSO_a";
	public static String analiza = "";


	public static String[] mixrun;
	public static int[] mixrunID;
	public static int[] printrunID; //latex column name
	public static double epsilon;
	public static String problemFiles[];
	
	public static void setArrays(String subdir, int i) {
		mixrun = new String[i];
		for (int j=0; j<i;j++) mixrun[j] = subdir;
		mixrunID = new int[i];
		for (int j=0; j<i;j++) mixrunID[j] = j+1;
		printrunID = new int[i];
		for (int j=0; j<i;j++) printrunID[j] = j+1;	
	}
	//  cols2.add(createXproblmTable(problemX[i],number_of_test_repetition,scenario_type,x,false));
	private static ArrayList<String> createXproblmTable(String fileName, int number_of_repetition, int type, int myX, boolean print) {
		ArrayList<String> heads = new ArrayList<String>();
		ArrayList<ArrayList<String>> cols = new ArrayList<ArrayList<String>>();
		cols.add(PrintAMetrics.getInfoColumn());
		NodesEARS n;
		ATMetrics m;
		PrintAMetrics pm=null;
		String problem2;
		StatATMetrics sam=new StatATMetrics();
		int maxgeneration=10000;
		//long start = System.currentTimeMillis();
		// ---------------------------------------------------------
		for (int id = 1; id < (number_of_repetition+1); id++) {
			problem2 = dir + fileName + id + ".csv";
			System.out.println(problem2);
			n = new NodesEARS();
			n.createAll_EARS(problem2, maxgeneration, epsilon, false);
			if (type==NodesEARS.SCENARIO_OPTIMISTIC) {
				n.transformInOptimisticParetoTree();
			}
			if (type==NodesEARS.SCENARIO_SEMI_OPTIMISTIC) {
				n.transformInOptimisticPlusParetoTree();
			}
			heads.add("" + id);
			m = new ATMetrics(n.getInitTrees(), myX);
			pm = new PrintAMetrics(m);
			sam.add(m);
			cols.add(pm.getColumn());
	
		}
		PrintStatATMetrics statTAble = new PrintStatATMetrics(sam);
		if (print) System.out.println(pm.toLatex(heads, cols,fileName));
		// New table clear and print
		heads.clear();
		cols.clear();
		cols.add(PrintAMetrics.getInfoColumn());
		return statTAble.getDoubleColumn();

	}

	private static void mutationMainTest(String problemX[], int x,int number_of_test_repetition){
		ArrayList<ArrayList<String>> cols2 = new ArrayList<ArrayList<String>>();
		cols2.add(PrintStatATMetrics.getDoubleInfoColumn());
		ArrayList<String> heads = new ArrayList<String>();
		long start = System.currentTimeMillis();
		int scenario_type=3; //normal
		for (int i=0; i<problemX.length; i++) {
			System.out.println("Start: "+(i+1)+"/"+problemX.length);
		  heads.add(""+problemX[i]); //Column name in latex table
		  cols2.add(createXproblmTable(problemX[i],number_of_test_repetition,scenario_type,x,false));
		  System.out.println(i+" ("+((double)(System.currentTimeMillis()-start)/1000/60)+")"+problemX[i]);
		}
		System.out.println(PrintStatATMetrics.toLatex(heads, cols2,"EE test"));	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setArrays(dir, 1);
		epsilon = 0.01; //for binary vector is any value less than 1 ok!
		int number_of_test_repetition =1;
		int x=1; //X dimension-s is/are changed by epsilon
		problemFiles = new String[1];
		problemFiles[0] = problem_1;
		//problemFiles[1] = problem_2;
		mutationMainTest(problemFiles,x,number_of_test_repetition);
 

	}

}
