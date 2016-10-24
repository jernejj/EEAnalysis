package atree.metrics;

import java.util.ArrayList;

import atree.treeData.Node;
import atree.treeData.Nodes;


public class TestAMetrics {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String problem = "2_10_5g.txt";
		// String problem="2_100_290g.txt";
		String rootPath = "/Users/matej/Documents/clanki2010/bioma-latex/ResultData/2_10/";
		String pot = rootPath + "Analiza_" + problem;
		Nodes n = new Nodes();
		n.createAllBarbara(pot);
		n.setAllBarbaraPareto(rootPath +"NajdenaFronta_2_10_5g.txt");
		n.transformInOptimisticParetoTree();
		System.out.println(n);
		for (Node ini : n.getInitTrees()) {
			ArrayList<Node> lista = new ArrayList<Node>();
			lista.add(ini);
			System.out.println(ini);
			ATMetrics m = new ATMetrics(lista, 2);
			PrintAMetrics pm = new PrintAMetrics(m);
			pm.printToScreen();
		}

	}

}
