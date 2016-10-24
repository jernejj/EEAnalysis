package atree.experimet;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import atree.metrics.ATMetrics;
import atree.metrics.PrintAMetrics;
import atree.metrics.StatATMetrics;
import atree.treeData.Node;
import atree.treeData.Nodes;
import atree.util.Frequency;
import atree.util.GrayMetrics;
import atree.util.StartEnd;


public class GenMetricsEEVisitFequencyData {
	public static String dir = "/Users/matej/Documents/clanki2010/IEEETec/ResultData/"; // on
	// mac
	// /Users/matej/Documents/clanki2010/IEEETec/ResultData/knapsack_10_2NajdenaFronta1.txt
	// knapsack_10_2NajdenaFronta1.txt
	public static String problem10 = "knapsack_10_2";
	public static String problem18 = "knapsack_18_2";
	public static String analiza = "Analiza";
	public static String front = "NajdenaFronta";

	public static String getGNUPLOT(String n1, String exploreName,
			String exploitName) {
		return "plot \""
				+ n1
				+ "\" using 1:2:3 with image, "
				+ "\""
				+ exploreName
				+ "\" title 'Explore'  with points lc rgb \"#472171\" pointtype 2, \""
				+ exploitName
				+ "\" title 'Exploot'  with points lc rgb \"#29A291\" pointtype 3";
	}

	public static void createDataTable(int size, int x, String resultsdir,
			String problem, int id, int gen, int genStep, int X)
			throws FileNotFoundException {
		StringBuffer sb = new StringBuffer();
		GrayMetrics gm = new GrayMetrics(size);
		Nodes n;
		ATMetrics m;
		// ---------------------------------------------------------
		String problem2 = dir + resultsdir + problem + analiza + id + ".txt";
		n = new Nodes();
		n.createAllBarbara(problem2, gen);
		n.setAllBarbaraPareto(dir + resultsdir + problem + front + id + ".txt");
		// if (type==1) { n.transformInOptimisticParetoTree(); }
		// if (type==2) {n.transformInOptimisticPlusParetoTree();}
		// heads.add("" + id);
		m = new ATMetrics(n.getInitTrees(), 4);
		// Collection<Node> list = n.getAllNodes().values();
		for (Node node : n.getAllNodes().values()) {
			gm.add(node);
			System.out.println(node.getChromo());
		}
		String name1 = dir + resultsdir + problem + id + "Data.txt";
		String name2 = dir + resultsdir + problem + id + "Explore.txt";
		String name3 = dir + resultsdir + problem + id + "Exploit.txt";
		PrintWriter fw;
		fw = new PrintWriter(name1);
		fw.println(gm.getDataTableArea(0)); // all
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
		System.out.println(getGNUPLOT(name1, name2, name3));
		// System.out.println(gm.getDataTable());
		// System.out.println(gm.getExploit(2));
		// System.out.println(gm.getExplore(2));
	}

	public static void createDataTableCells(int size, String resultsdir,
			String problem, int id, int gen, int genStep, int X)
			throws FileNotFoundException {
		StringBuffer sb = new StringBuffer();
		GrayMetrics gm = new GrayMetrics(size);
		Nodes n;
		ATMetrics m;
		// ---------------------------------------------------------
		String problem2 = dir + resultsdir + problem + analiza + id + ".txt";
		n = new Nodes();
		n.createAllBarbara(problem2, gen);
		n.setAllBarbaraPareto(dir + resultsdir + problem + front + id + ".txt");
		// if (type==1) { n.transformInOptimisticParetoTree(); }
		// if (type==2) {n.transformInOptimisticPlusParetoTree();}
		// heads.add("" + id);
		m = new ATMetrics(n.getInitTrees(), X);
		// Collection<Node> list = n.getAllNodes().values();
		for (Node node : n.getAllNodes().values()) {
			if (node.getIdGen() <= gen) {
				gm.add(node);
				// System.out.println(node.getChromo());
			}
		}
		// String name1 = dir + resultsdir + problem + id + "Data.txt";
		String name2 = dir + resultsdir + problem + id + "Explore" + gen
				+ ".txt";
		String name3 = dir + resultsdir + problem + id + "Exploit" + gen
				+ ".txt";
		PrintWriter fw;
		// fw = new PrintWriter(name1);
		// fw.println(gm.getDataTableArea(0)); //all
		// fw.flush();
		// fw.close();
		fw = new PrintWriter(name2);
		fw.println(gm.getOptimizedEEDataTableArea(X, true,false));
		fw.flush();
		fw.close();
		fw = new PrintWriter(name3);
		fw.println(gm.getOptimizedEEDataTableArea(X, false,false));
		fw.flush();
		fw.close();
		System.out.println(name2);
		System.out.println(name3);
		// System.out.println(getGNUPLOT(name1, name2,name3));
		// System.out.println(gm.getDataTable());
		// System.out.println(gm.getExploit(2));
		// System.out.println(gm.getExplore(2));
	}

	public static void generateEEFrequencyGrafsData(int size, String resultsdir, String problem,
			int id, int gen, int genStep, int X, 	int type) throws FileNotFoundException {
		GrayMetrics gm = new GrayMetrics(size);
		Nodes n;
	//	ATMetrics m;
		// ---------------------------------------------------------
		String problem2 = dir + resultsdir + problem + analiza + id + ".txt";
		n = new Nodes();
		n.createAllBarbara(problem2, gen);
		n.setAllBarbaraPareto(dir + resultsdir + problem + front + id + ".txt");
		//m = new ATMetrics(n.getInitTrees(), X);
		// Collection<Node> list = n.getAllNodes().values();
		int sumCountNodes=0;
		int sumCountInNodes=0;
		for (Node node : n.getAllNodes().values()) {
			sumCountNodes++;
			if (node.getIdGen() <= gen) {
				gm.add(node);
				sumCountInNodes++;
			}
		}
		System.out.println("sumCountNodes:"+sumCountNodes);
		System.out.println("sumCountInNodes:"+sumCountInNodes);
		ArrayList<Frequency> tmp = new ArrayList<Frequency>();
		tmp.addAll(gm.getMaxValues(X,type));
		Collections.sort(tmp, new Frequency.CompareID());
		int sumAll=0;
		int testAll=0;
			
		for (Frequency f:tmp) {
			sumAll+=f.getCount();
			testAll+=f.describesElements();
		}
		System.out.println("testAll:"+testAll);
		System.out.println("sumAll:"+sumAll);
		System.out.println(tmp.size());
		System.out.println(tmp);
		int stevec=0;
		for (Frequency f:tmp) {
			stevec+=f.describesElements();
			//System.out.println(f.getId()+"\t"+stevec);
		}

		String name;
		String nameS;
		int noParts=4;
		PrintWriter fw;
		ArrayList<StartEnd> se=printPartsNumbers(tmp,noParts,X);
		int allByParts[][][];
		allByParts =new int[noParts][][];
		
		for (int i=0; i<se.size();i++) {
			allByParts[i]=gm.getOptimizedEEFrequencyShadow(X, se.get(i), type);
		}
		
		GrayMetrics.cleanShadow4Points(allByParts);
		for (int i=0; i<se.size();i++) {
			name = dir + resultsdir + problem + id + "t"+type+"Freq"+i+"g" + gen+ ".txt";
			nameS = dir + resultsdir + problem + id + "t"+type+"SFreq"+i+"g" + gen+ ".txt";
			//System.out.println(name);
			fw = new PrintWriter(name);
			fw.println(gm.getOptimizedEEFrequency(X, se.get(i), type));;
			fw.flush();
			fw.close();
			//System.out.println(nameS);
			fw = new PrintWriter(nameS);
			fw.println(GrayMetrics.getOptimizedEEFrequencyShadow(allByParts[i]));;
			fw.flush();
			fw.close();
			name = "ResultData/"+resultsdir + problem + id + "t"+type+"Freq"+i+"g" + gen+ ".txt";
			System.out.println("\""+name+"\" "+"\""+se.get(i).getStart()+"-"+se.get(i).getEnd()+"\""+" \\");
			nameS = "ResultData/"+resultsdir + problem + id + "t"+type+"SFreq"+i+"g" + gen+ ".txt";
			System.out.println("\""+nameS+"\""+" \\");

		}
		
	}
	
	public static void generateEEManualFrequencyGrafsData(int size, String resultsdir, String problem,
			int id, int gen, int genStep, int X, int type, ArrayList<StartEnd> manual ) throws FileNotFoundException {
		GrayMetrics gm = new GrayMetrics(size);
		Nodes n;
	//	ATMetrics m;
		// ---------------------------------------------------------
		String problem2 = dir + resultsdir + problem + analiza + id + ".txt";
		n = new Nodes();
		n.createAllBarbara(problem2, gen);
		n.setAllBarbaraPareto(dir + resultsdir + problem + front + id + ".txt");
		//m = new ATMetrics(n.getInitTrees(), X);
		// Collection<Node> list = n.getAllNodes().values();
		int sumCountNodes=0;
		int sumCountInNodes=0;
		for (Node node : n.getAllNodes().values()) {
			sumCountNodes++;
			if (node.getIdGen() <= gen) {
				gm.add(node);
				sumCountInNodes++;
			}
		}
		System.out.println("sumCountNodes:"+sumCountNodes);
		System.out.println("sumCountInNodes:"+sumCountInNodes);			
		String name;
		String nameS;
		int noParts=4;
		PrintWriter fw;
		//ArrayList<StartEnd> se=printPartsNumbers(tmp,noParts,X);
		int allByParts[][][];
		allByParts =new int[manual.size()][][];
		for (int i=0; i<manual.size();i++) {
			allByParts[i]=gm.getOptimizedEEFrequencyShadow(X, manual.get(i), type);
		}
		GrayMetrics.cleanShadow4Points(allByParts);
		for (int i=0; i<manual.size();i++) {
			name = dir + resultsdir + problem + id + "t"+type+"Freq"+i+"g" + gen+ ".txt";
			nameS = dir + resultsdir + problem + id + "t"+type+"SFreq"+i+"g" + gen+ ".txt";
			//System.out.println(name);
			fw = new PrintWriter(name);
			fw.println(gm.getOptimizedEEFrequency(X, manual.get(i), type));;
			fw.flush();
			fw.close();
			//System.out.println(nameS);
			fw = new PrintWriter(nameS);
			fw.println(GrayMetrics.getOptimizedEEFrequencyShadow(allByParts[i]));;
			fw.flush();
			fw.close();
			name = "ResultData/"+resultsdir + problem + id + "t"+type+"Freq"+i+"g" + gen+ ".txt";
			System.out.println("\""+name+"\" "+"\""+manual.get(i).getStart()+"-"+manual.get(i).getEnd()+"\""+" \\");
			nameS = "ResultData/"+resultsdir + problem + id + "t"+type+"SFreq"+i+"g" + gen+ ".txt";
			System.out.println("\""+nameS+"\""+" \\");

		}	
	}
	public static ArrayList<StartEnd> getManualAllStartEnds4_18(){
		ArrayList<StartEnd>  s = new ArrayList<StartEnd>();
		s.add(new StartEnd(1, 2));
		s.add(new StartEnd(3, 6));
		s.add(new StartEnd(7, 14));
		s.add(new StartEnd(15, 200));
		return s;
	}
	
	public static ArrayList<StartEnd> getManualStartEnds4_18(){
		ArrayList<StartEnd>  s = new ArrayList<StartEnd>();
		s.add(new StartEnd(1, 1));
		s.add(new StartEnd(2, 3));
		s.add(new StartEnd(4, 7));
		s.add(new StartEnd(8, 200));
		return s;
	}

	public static ArrayList<StartEnd> printPartsNumbers(ArrayList<Frequency> tmp, int parts, int x) {
		int testAll=0;
		//parts--;
		ArrayList<StartEnd> se= new ArrayList<StartEnd>();
		Collections.sort(tmp, new Frequency.CompareID());
		for (Frequency f:tmp) {
			testAll+=f.describesElements();
		}
		double part=testAll/parts;
		double inc=1;
		int start=0;
		int end=0;
		int stevec=0;
		for (Frequency f:tmp) {
			if (start==0) {
				start = 1;
				end = f.getId(); 
				stevec+=f.describesElements();
			} else {
			  if (stevec>=(part*inc)) {
				  inc++;
//				  System.out.println(stevec+" "+part+" "+start+", "+end);
				  System.out.println(start+", "+end);
				  se.add(new StartEnd(start, end));
				  start=end+1;
			  }
			  end = f.getId(); ;
			  stevec+=f.describesElements();
			}
		}
		  se.add(new StartEnd(start, end));
		 System.out.println(start+", "+end);	
		return se;
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
/*      Calculates and devides in 4 groups
		generateEEFrequencyGrafsData(18, "2_18/spea2/", problem18, 225, 100, 20, 3,1);
		generateEEFrequencyGrafsData(18, "2_18/spea2/", problem18, 225, 100, 20, 3,2);
		generateEEFrequencyGrafsData(18, "2_18/spea2/", problem18, 225, 100, 20, 3,3);
		generateEEFrequencyGrafsData(18, "2_18/vega/", problem18, 165, 100, 20, 3,1);
		generateEEFrequencyGrafsData(18, "2_18/vega/", problem18, 165, 100, 20, 3,2);
		generateEEFrequencyGrafsData(18, "2_18/vega/", problem18, 165, 100, 20, 3,3);
*/
		
		generateEEManualFrequencyGrafsData(18, "2_18/spea2/", problem18, 225, 100, 20, 3,1,GenMetricsEEVisitFequencyData.getManualAllStartEnds4_18());
		generateEEManualFrequencyGrafsData(18, "2_18/spea2/", problem18, 225, 100, 20, 3,2,GenMetricsEEVisitFequencyData.getManualStartEnds4_18());
		generateEEManualFrequencyGrafsData(18, "2_18/spea2/", problem18, 225, 100, 20, 3,3,GenMetricsEEVisitFequencyData.getManualStartEnds4_18());
		generateEEManualFrequencyGrafsData(18, "2_18/vega/", problem18, 165, 100, 20, 3,1,GenMetricsEEVisitFequencyData.getManualAllStartEnds4_18());
		generateEEManualFrequencyGrafsData(18, "2_18/vega/", problem18, 165, 100, 20, 3,2,GenMetricsEEVisitFequencyData.getManualStartEnds4_18());
		generateEEManualFrequencyGrafsData(18, "2_18/vega/", problem18, 165, 100, 20, 3,3,GenMetricsEEVisitFequencyData.getManualStartEnds4_18());
		
	}

}
