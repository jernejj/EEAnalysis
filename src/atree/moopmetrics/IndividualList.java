package atree.moopmetrics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import atree.treeData.Node;


public class IndividualList {
	private ArrayList<Individual> list;

	public IndividualList() {
		super();
		list = new ArrayList<Individual>();
	}
	public void add(Individual p) {
		list.add(p);
	}
	
	public ArrayList<Individual> getList() {
		return list;
	}
    public int getSize() {
    	return list.size();
    }
	public Individual get(int i) {
		return list.get(i);
	}
	public void toPlot(String filename) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(filename));
			for (Individual i: list) {
				pw.println(i.toSpaceFormat());
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Rezultati za problem E:/Diploma/Diploma/knapsack_10_2.txt
	 * 
	 * PARAMTERI: Verjetnost mutacije: 0.01 Verjetnost križanja: 0.8 Velikost
	 * turnirja: 2 K vrednost (izr. po enaèbi): 4 Velikost populacije: 10
	 * Velikost arhiva: 10 Število generacij: 1000 Število ovrednotenj: 20010
	 * 
	 * REZULTATI (podrobno): 1. Vrednosti: 360.0 370.0 Teze: 283.0 291.0 Bitni
	 * niz: 0000110111 Fitnes: 0.021771425375377775 (Stars1),(Stars2):
	 * (-1,-1)(-1,-1) (ID):(2,0)
	 * 
	 * 2....
	 * 
	 * Zanima nas samo NAJDENA FRONTA: naprej
	 */
	public static IndividualList getAllBarbaraPareto(String file) {
		IndividualList il = new IndividualList();
		double[] tmp;
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s;
			String stepOne[];
			boolean start=false;
			while ((s = br.readLine()) != null) {
				if (s.indexOf("NAJDENA FRONTA:") > -1) {
					start=true;
				} else {
				  if (start) {
				    s=s.trim();
				    if (s.length()<2) continue; //skip blank lines
					s = s.replaceAll("  ", " ");
					s = s.replaceAll("	", ",");
					stepOne = s.split(",");
				    if (stepOne.length<1) continue; //skip blank lines					
					tmp = new double[stepOne.length];
					for (int i=0; i<stepOne.length; i++) {
						tmp[i] = Double.parseDouble(stepOne[i]);
					}
					il.add(new Individual(tmp));
				  }	
				}
			}
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return il;
	}
	public String toString() {
		return list.toString();
	}
	
}
