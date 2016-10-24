package atree.experimet.ecj;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import atree.metrics.ATMetrics;
import atree.metrics.PrintAMetrics;
import atree.metrics.PrintStatATMetrics;
import atree.metrics.StatATMetrics;
import atree.treeData.Nodes;
import atree.util.Util;


public class ExperimentECJ_compare1dimm {
	//RastriginEEstatPSO_a1.stat
	//SpherePSOOut_a1.STAT
	public static String dir = "D:/My Documents/fax/Doktorat/Workplace/ecj/ec/EEstat/samples/ES/"; // on mac linux "/"
//	public static String dir = "D:\\My Documents\\fax\\Doktorat\\Workplace\\ecj\\ec\\EEstat\\samples\\"+pripona+"/"; // on mac linux "/"  F1_ElimEEstatRun1Dim2

	public static String analiza = "a";
	static String function;


	public static String[] mixrun;
	public static int[] mixrunID;
	public static int[] printrunID; //latex column name
	public static double epsilon[][];
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
	private static ArrayList<String> createXproblmTable(String fileName, int number_of_repetition, int type, int myX, boolean print, double epsilon1[]) {
		ArrayList<String> heads = new ArrayList<String>();
		ArrayList<ArrayList<String>> cols = new ArrayList<ArrayList<String>>();
		cols.add(PrintAMetrics.getInfoColumn());
		Nodes n;
		ATMetrics m;
		PrintAMetrics pm=null;
		String problem2;
		StatATMetrics sam=new StatATMetrics();
		int maxgeneration=3;
		String dimProblem = "Dim" + epsilon1.length;
		//long start = System.currentTimeMillis();
		// ---------------------------------------------------------
		for (int id = 1; id < (number_of_repetition+1); id++)
		{
			problem2 = dir + fileName + id + dimProblem + ".stat";
			System.out.println(problem2);
			n = new Nodes();
			n.createAll_ECJ(problem2, maxgeneration, epsilon1, false);
			
			if (type==Nodes.SCENARIO_OPTIMISTIC) 
			{
				n.transformInOptimisticParetoTree();
			}
			
			if (type==Nodes.SCENARIO_SEMI_OPTIMISTIC) 
			{
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

	private static void mutationMainTest(String problemX[], int x[],int number_of_test_repetition, double eps[][])
	{
		ArrayList<ArrayList<String>> cols2 = new ArrayList<ArrayList<String>>();
		cols2.add(PrintStatATMetrics.getDoubleInfoColumn());
		ArrayList<String> heads = new ArrayList<String>();
		long start = System.currentTimeMillis();
		int scenario_type=3; //normal
		String latexOutput;
		
		for (int i=0; i<problemX.length; i++)
		{
	      String dimProblem = "-Dim" + epsilon[i].length;
		  System.out.println("Start: "+(i+1)+"/"+problemX.length);
		  heads.add(""+function+dimProblem); //Column name in latex table
		  cols2.add(createXproblmTable(problemX[i],number_of_test_repetition,scenario_type,x[i],false, eps[i]));
		  System.out.println(i+" ("+((double)(System.currentTimeMillis()-start)/1000/60)+")"+problemX[i]);
		}
		
		latexOutput = PrintStatATMetrics.toLatex(heads, cols2, function);
		System.out.println(latexOutput);	
		
		try{
    		File file =new File("ExperimentECJ_compare1dimm"+function+".txt");
 
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
 
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(latexOutput + "\n\n");
    	        bufferWritter.close();
 
	        System.out.println("Done");
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		setArrays(dir, 1);
		double bord = 0.5;
		if (args.length > 0)
			function = args[0];
		else
			function = "F2";
		String problem_1 = "statfileDim2/" + function + "_ElimEEstatRun";   
		String problem_2 = "statfileDim5/" + function + "_ElimEEstatRun";
		String problem_3 = "statfileDim30/" + function + "_ElimEEstatRun";
		String problem_4 = "statfileDim100/" + function + "_ElimEEstatRun";
		epsilon = new double[4][];
		epsilon[0] = Util.generateEpsilonVector(2, bord); //for binary vector is any value less than 1 ok!
		epsilon[1] = Util.generateEpsilonVector(5, bord); //for binary vector is any value less than 1 ok!
		epsilon[2] = Util.generateEpsilonVector(30, bord); //for binary vector is any value less than 1 ok!
		epsilon[3] = Util.generateEpsilonVector(100, bord); //for binary vector is any value less than 1 ok!
		
		int number_of_test_repetition = 20;
		int x[] = new int[4];
		x[0] = 1; //X dimension-s is/are changed by epsilon
		x[1] = 1; //X dimension-s is/are changed by epsilon
		x[2] = 1; //X dimension-s is/are changed by epsilon
		x[3] = 1; //X dimension-s is/are changed by epsilon
		problemFiles = new String[4];
		problemFiles[0] = problem_1;
		problemFiles[1] = problem_2;
		problemFiles[2] = problem_3;
		problemFiles[3] = problem_4;
		mutationMainTest(problemFiles, x,number_of_test_repetition, epsilon);
 

	}

}
