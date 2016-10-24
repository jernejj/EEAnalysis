package atree.experimet;

import java.util.ArrayList;
import java.util.Arrays;

import atree.metrics.ATMetrics;
import atree.metrics.PrintAMetrics;
import atree.metrics.PrintStatATMetrics;
import atree.metrics.StatATMetrics;
import atree.moopmetrics.IndividualList;
import atree.moopmetrics.Metrics;
import atree.treeData.Node;
import atree.treeData.Nodes;
import atree.util.MeanStDev;


public class ExperimentIEEEmetricC {
	public static String dir = "/Users/matej/Documents/clanki2010/IEEETec/ResultData/"; // on
	// mac
	public static String problem10 = "knapsack_10_2";
	public static String problem100 = "knapsack_100_2";
	public static String problem18 = "knapsack_18_2";
	public static String analiza = "Analiza";
	public static String front = "NajdenaFronta";
	public static String resultsvega = "vega/";
	public static String resultsspea2 = "spea2/";
	public static String[] mixrun= {resultsvega,resultsspea2,resultsvega,resultsspea2,resultsvega,resultsspea2};
	public static int[] mixrunID= {2,4,3,5,4,6};
	public static int[] printrunID= {2,13,3,14,4,15};

	double pm;
	double pc;
	int pop_size;
	int gen;
	public static int MAXTEST=9;
	public static String getHead(int col) {
		StringBuffer sb = new StringBuffer();
		sb.append("\\begin{table}[H] \n \\begin{center} \n \\begin{tabular}{@{}l@{\\hspace{1pt}}||");
		for (int i=1; i<=col;i++) {
			sb.append("@{\\hspace{1pt}}r@{\\hspace{1pt}}");
			if (i!=col)sb.append(" | ");
	   }
		sb.append(" | @{\\hspace{1pt}}r@{\\hspace{1pt}}");
	   return sb.append("}").toString();
	}
	public static String getTail() {
		StringBuffer sb = new StringBuffer();
		sb.append("\\end{tabular} \n \\caption{Comparison with the $C$ metrics} \n \\label{smalldatatable}\n \\end{center}\n\\end{table}");
		return sb.toString();
	}
	public static MeanStDev sumRow(MeanStDev st[][], int r) {
		ArrayList<Double> lista = new ArrayList<Double>();
		for (int i=0; i<st[r].length;i++)  {
			if (r==i) continue;
			lista.add(st[r][i].getMean());
		}
		return new MeanStDev(lista);
	}
	public static MeanStDev sumCol(MeanStDev st[][], int r) {
		ArrayList<Double> lista = new ArrayList<Double>();
		for (int i=0; i<st[r].length;i++)  {
			if (r==i) continue;
			lista.add(st[i][r].getMean());
		}
		return new MeanStDev(lista);
	}
	public static int countRow(MeanStDev st[][], int r) {
	//	ArrayList<Double> lista = new ArrayList<Double>();
		int sum=0;
		for (int i=0; i<st[r].length;i++)  {
			if (r==i) continue;
			if (st[r][i].getMean()>=st[i][r].getMean()) sum++;
		}
		return sum;
	}
	public static int countCol(MeanStDev st[][], int r) {
	//	ArrayList<Double> lista = new ArrayList<Double>();
		int sum=0;
		for (int i=0; i<st[r].length;i++)  {
			if (r==i) continue;
			//lista.add(st[i][r].getMean());
			if (st[i][r].getMean()<=st[r][i].getMean()) sum++;
		}
		return sum;
	}
	public static String calcCForMixValues(String[] mixrun,int[] mixrunID,int[] printrunID) {
		StringBuffer sb = new StringBuffer();
		StringBuffer st_line = new StringBuffer();
		int MAXTEST = mixrun.length;
		double C[][][] = new double[MAXTEST][MAXTEST][30]; 
		MeanStDev st[][] = new MeanStDev[MAXTEST][MAXTEST]; 
		ArrayList<Double> lista;
		IndividualList x1,x2;
		String f1, f2;
		for (int k=0; k<MAXTEST;k++) {
		for (int j=0; j<MAXTEST;j++) {
			if (j<=k) continue;
		for (int i=0; i<30;i++) {
			f1=dir + mixrun[k] + problem100 + front + (((mixrunID[k]-1)*30)+(i+1)) + ".txt";
			f2=dir + mixrun[j] + problem100 + front +(((mixrunID[j]-1)*30)+(i+1)) + ".txt";
			x1 = IndividualList.getAllBarbaraPareto(f1);
			x2 = IndividualList.getAllBarbaraPareto(f2);
			C[k][j][i] = Metrics.C(x1, x2);
			C[j][k][i] = Metrics.C(x2, x1);
			
		}
		}}
		for (int k=0; k<MAXTEST;k++) {
			for (int j=0; j<MAXTEST;j++) {
				lista = new ArrayList<Double>();
				if (j==k) continue;
				for (int i=0; i<30;i++) {
					lista.add(C[k][j][i]);
				}
				st[k][j] = new MeanStDev(lista);
			}
		}
		sb.append(getHead(MAXTEST)).append("\n");
		for (int k=1; k<=MAXTEST;k++) {
			sb.append(" & ").append(""+(printrunID[k-1]));
		}
		sb.append(" & ").append("\\#");
		sb.append("\\\\ \\hline\n");
		for (int k=0; k<MAXTEST;k++) {
			st_line = new StringBuffer(" & ");
			sb.append((printrunID[k])).append(" & ");
			for (int j=0; j<MAXTEST;j++) {
				if (k==j) {
					sb.append(" 0 ");
					st_line.append(" "); 
				}
				else {
					sb.append(st[k][j].toStringMean());
					st_line.append(st[k][j].toStringStDev());
				}
				if (j<(MAXTEST-1)) {
					sb.append(" & "); 
					st_line.append(" & "); 
				}

			}
			sb.append(" & ").append(countRow(st,k));
			sb.append("\\\\\n");
			sb.append(st_line);
			sb.append("\\\\ \\hline\n");
		}
		sb.append("\\#");
		for (int k=0; k<MAXTEST;k++) {
			sb.append(" & ").append(""+countCol(st,k));
		}
		sb.append("\\\\ \\hline\n");

		sb.append(getTail());
	   return sb.toString();
	}
	
	public static String calcCForMuatations(int offset, String subdir, String problemX) {
		StringBuffer sb = new StringBuffer();
		StringBuffer st_line = new StringBuffer();
		double C[][][] = new double[MAXTEST][MAXTEST][30]; 
		MeanStDev st[][] = new MeanStDev[MAXTEST][MAXTEST]; 
		ArrayList<Double> lista;
		IndividualList x1,x2;
		String f1, f2;
		for (int k=0; k<MAXTEST;k++) {
		for (int j=0; j<MAXTEST;j++) {
			if (j<=k) continue;
		for (int i=0; i<30;i++) {
			f1=dir + subdir + problemX + front + ((k*30)+(i+1)) + ".txt";
			f2=dir + subdir + problemX + front +((j*30)+(i+1)) + ".txt";
			x1 = IndividualList.getAllBarbaraPareto(f1);
			x2 = IndividualList.getAllBarbaraPareto(f2);
			C[k][j][i] = Metrics.C(x1, x2);
			C[j][k][i] = Metrics.C(x2, x1);
			
		}
		}}
		for (int k=0; k<MAXTEST;k++) {
			for (int j=0; j<MAXTEST;j++) {
				lista = new ArrayList<Double>();
				if (j==k) continue;
				for (int i=0; i<30;i++) {
					lista.add(C[k][j][i]);
				}
				st[k][j] = new MeanStDev(lista);
			}
		}
		sb.append(getHead(MAXTEST)).append("\n");
		for (int k=1; k<=MAXTEST;k++) {
			sb.append(" & ").append(""+(k+offset));
		}
		sb.append(" & ").append("\\#");
		sb.append("\\\\ \\hline\n");
		for (int k=0; k<MAXTEST;k++) {
			st_line = new StringBuffer(" & ");
			sb.append((offset+k+1)).append(" & ");
			for (int j=0; j<MAXTEST;j++) {
				if (k==j) {
					sb.append(" 0 ");
					st_line.append(" "); 
				}
				else {
					sb.append(st[k][j].toStringMean());
					st_line.append(st[k][j].toStringStDev());
				}
				if (j<(MAXTEST-1)) {
					sb.append(" & "); 
					st_line.append(" & "); 
				}
			}
			//sb.append(" & ").append(sumRow(st,k).toString());
			sb.append(" & ").append(countRow(st,k));
			sb.append("\\\\\n");
			sb.append(st_line);
			sb.append("\\\\ \\hline\n");
		}
		sb.append("\\#");
		for (int k=0; k<MAXTEST;k++) {
			//sb.append(" & ").append(""+sumCol(st,k).toString());
			sb.append(" & ").append(""+countCol(st,k));
		}
		sb.append("\\\\ \\hline\n");

		sb.append(getTail());
	   return sb.toString();
	}

	public static String calcArrayCForMuatations() {
		StringBuffer sb = new StringBuffer();
		double C[][][] = new double[6][6][30]; 
		IndividualList x1,x2;
		String f1, f2;
		for (int k=0; k<6;k++) {
		for (int j=0; j<6;j++) {
			if (j<=k) continue;
		for (int i=0; i<30;i++) {
			f1=dir + resultsspea2 + problem100 + front + ((k*30)+(i+1)) + ".txt";
			f2=dir + resultsspea2 + problem100 + front +((j*30)+(i+1)) + ".txt";
			x1 = IndividualList.getAllBarbaraPareto(f1);
			x2 = IndividualList.getAllBarbaraPareto(f2);
			C[k][j][i] = Metrics.C(x1, x2);
			C[j][k][i] = Metrics.C(x2, x1);
			
		}
		}
		}
		for (int k=0; k<6;k++) {
			for (int j=0; j<6;j++) {
				sb.append(Arrays.toString(C[k][j])).append("\n");
			}
		}
		
	   return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(calcCForMuatations(0, "2_18s/"+resultsspea2, problem18));
		//System.out.println(calcCForMuatations(0, "2_18/"+resultsvega, problem18));
		//System.out.println(calcCForMuatations(9, resultsspea2, problem100));
		//System.out.println(calcCForMuatations(9, "2_18/"+resultsspea2, problem18));
		//System.out.println(calcCForMixValues(mixrun,mixrunID,printrunID));
	}

}
