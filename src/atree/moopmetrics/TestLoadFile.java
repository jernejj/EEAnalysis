package atree.moopmetrics;

public class TestLoadFile {
	public static String dir = "/Users/matej/Documents/clanki2010/IEEETec/ResultData/"; // on
	// mac
	public static String problem10 = "knapsack_10_2";
	public static String problem100 = "knapsack_100_2";
	public static String analiza = "Analiza";
	public static String front = "NajdenaFronta";
	public static String resultsvega = "vega/";
	public static String resultsspea2 = "spea2/";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String f1=dir + resultsvega + problem100 + front + ((3-1)*30+2) + ".txt";
		String f2=dir + resultsspea2 + problem100 + front +((5-1)*30+4) + ".txt";
		IndividualList x1 = IndividualList.getAllBarbaraPareto(f1);
		System.out.println(x1);
		x1.toPlot(dir+"vega.txt");
		IndividualList x2 = IndividualList.getAllBarbaraPareto(f2);
		x2.toPlot(dir+"spea2.txt");
		System.out.println(x2);
		System.out.println("C(x1,x2)="+Metrics.C(x1, x2));
		System.out.println("C(x2,x1)="+Metrics.C(x2, x1));	

	}

}
