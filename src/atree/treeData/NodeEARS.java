package atree.treeData;

import java.util.ArrayList;

import atree.util.LineParserECJ;

public class NodeEARS implements Comparable<NodeEARS> {
	private NodeEARS parent;
	private ArrayList<NodeEARS> childrens;
	private long revisits;
	private long idGen;
	private double finess;
	public String chromo; //just for fast compare!!!
	public int ones1, ones2, ones3; //just for comparing speed
	private boolean pareto;
	private boolean revisited;
	public boolean isRevisited() {
		return revisited;
	}
	public void setRevisited(boolean revisited) {
		this.revisited = revisited;
	}
	public boolean isTmp() {
		return tmp;
	}
	public void setTmp(boolean tmp) {
		this.tmp = tmp;
	}

	private boolean tmp;

	private double x;

	public NodeEARS() {
		super();
		childrens = new ArrayList<NodeEARS>();
		revisits = 0;
		parent = null;
		pareto = false;
		tmp=false;
	}
	public void addRevisited()  {
		revisits++;
	}
	public boolean isPareto() {
		return pareto;
	}
	public void setPareto(boolean pareto) {
		this.pareto = pareto;
	}
	public NodeEARS(long idGen, String chromo, double x) {
		this();
		this.idGen = idGen;
		setChromo(chromo);
		this.x = x;
	}


	public NodeEARS getParent() {
		return parent;
	}
	public void addChild(NodeEARS c) {
		childrens.add(c);
	}
	public void setParent(NodeEARS parent) {
		this.parent = parent;
		if (parent!=null)
		parent.addChild(this);
	}

	public ArrayList<NodeEARS> getChildrens() {
		return childrens;
	}
	public void setChildrens(ArrayList<NodeEARS> childrens) {
		this.childrens = childrens;
	}
	public long getIdGen() {
		return idGen;
	}
	public void setIdGen(long idGen) {
		this.idGen = idGen;
	}

	public String getChromo() {
		return chromo;
	}
	public void setChromo(String chromo) {
		this.chromo = chromo;
		ones1=0; //just for fast compare!!!
		ones2=0;		
		ones3=0;
		for (int i=2; i<chromo.length();i=i+3) { //can skip 2 elements!!!
			 if (chromo.charAt(i-2)=='1') ones1++;
			 if (chromo.charAt(i-1)=='1') ones2++;
			 if (chromo.charAt(i)=='1') ones3++;
		}
	}

	public boolean isLeaf() {
		if (childrens.size()==0) return true;
		return false;
	}


	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public boolean isRnd() {
		return 	(parent==null);
	}
	public void setFit(double fitness)
	{
		this.finess = fitness;
	}
	public double getFit()
	{
		return this.finess;
	}
	@Override
	public int compareTo(NodeEARS o) {
        if (o.idGen==idGen) return 0;
        if (o.idGen>idGen) return -1;
        if (o.idGen==idGen) return -1;
        return 1;
	}
	public String getID() {
		return "("+getIdGen()+")";
	}
	public String getID(NodeEARS n) {
		if (n==null) return "(1)";
		return n.getID();
	}

	public String printChildrens() {
		StringBuffer sb= new StringBuffer();
		for (NodeEARS t:childrens) {
			sb.append(t.getID()).append(" ");
		}
		return sb.toString();
	}
	public String toString() {
		String t=getID()+" "+getID(parent)+" "+getChromo()+" "+getX()+" "+printChildrens()+" "+isPareto()+" "+isTmp();
		return t;
	}
	
	//Example: (-1,-1)(-1,-1)(5,0) 0000111010 0 0 0 0
	public static NodeEARS convertBarbaraFormat(String line, NodesEARS all) {
		String stepOne[] = line.split(" ");
		stepOne[0] = stepOne[0].replaceAll("\\(", "");
		String stepTwo[] = stepOne[0].trim().split("\\)");
		NodeEARS r = new NodeEARS();
		String stepThree[] = stepTwo[2].trim().split(",");
		r.setIdGen(Long.parseLong(stepThree[1]));
		r.setChromo(stepOne[1].trim());
		r.setX(Long.parseLong(stepOne[5].trim()));
		//set parent
		stepThree = stepTwo[0].trim().split(",");
		String key= "("+stepThree[1]+","+stepThree[0]+")";
		if (all.containsKey(key)) r.setParent(all.get(key));
		return r;
	}
	
	
	public static NodeEARS convert4String(String line, NodesEARS all, double epsilon) 
	{
		LineParserECJ lp = new LineParserECJ(line);
		NodeEARS r = new NodeEARS();
		NodeEARS p1,p2;
		p1=null;
		p2=null;
		String id[];
		String key;
		while (lp.getState()!=LineParserECJ.EOF) {
			switch (lp.getState()) {
			case LineParserECJ.ID:
				id = lp.getValues(",");
				r.setIdGen(Long.parseLong(id[0]));
				break;
			case LineParserECJ.P1:
				id = lp.getValues(",");
				key= "("+id[1]+","+id[0]+")";
				if (all.containsKey(key)) p1=all.get(key);
				break;
			case LineParserECJ.P2:
				id = lp.getValues(",");
				key= "("+id[1]+","+id[0]+")";
				if (all.containsKey(key)) p2=all.get(key);
				break;
			case LineParserECJ.IN:
				r.setChromo(lp.getValue().trim());
				break;
			case LineParserECJ.FITNESS:
				r.setFit(lp.getDoubleVaue());
				break;
			}
			lp.nextState();
		}

		double x_p1 = calcX(r, p1, epsilon);
		double x_p2 = calcX(r, p2, epsilon);
		if (x_p2<x_p1) { //most equal is parant2
			r.setParent(p2);
			r.setX(x_p2);
		} else {
			r.setParent(p1);
			r.setX(x_p1);	
		}
		
		//if (r.isRnd()) {
		//	System.out.println(line);
		//}
		return r;
	}
	
	//Example: p1(-1,-1) p2(-1,-1) id(1,0) in( 1 0 0 0 1 0 1 1 1 1) c0 m0 r0
	public static NodeEARS convert4String(String line, NodesEARS all, double epsilon[]) 
	{
		LineParserECJ lp = new LineParserECJ(line);
		NodeEARS r = new NodeEARS();
		NodeEARS p1,p2;
		p1=null;
		p2=null;
		String id[];
		String key;
		while (lp.getState()!=LineParserECJ.EOF) {
			switch (lp.getState()) {
			case LineParserECJ.ID:
				id = lp.getValues(",");
				r.setIdGen(Long.parseLong(id[1]));
				break;
			case LineParserECJ.P1:
				id = lp.getValues(",");
				key= "("+id[1]+","+id[0]+")";
				if (all.containsKey(key)) p1=all.get(key);
				break;
			case LineParserECJ.P2:
				id = lp.getValues(",");
				key= "("+id[1]+","+id[0]+")";
				if (all.containsKey(key)) p2=all.get(key);
				break;
			case LineParserECJ.IN:
				r.setChromo(lp.getValue().trim());
				break;
			case LineParserECJ.FITNESS:
				r.setFit(lp.getDoubleVaue());
				break;
			}
			lp.nextState();
		}

		double x_p1 = calcX(r, p1, epsilon);
		double x_p2 = calcX(r, p2, epsilon);
		if (x_p2<x_p1) { //most equal is parant2
			r.setParent(p2);
			r.setX(x_p2);
		} else {
			r.setParent(p1);
			r.setX(x_p1);	
		}
		
		//if (r.isRnd()) {
		//	System.out.println(line);
		//}
		return r;
	}
	
	private static double calcX(NodeEARS r, NodeEARS p, double[] epsilon) {
		if (p==null) return Integer.MAX_VALUE;
		String l[] = r.chromo.split(" ");
		double d1,d2;
		String lp[] = p.chromo.split(" ");
		double x=0;
		for (int i=0; i<l.length; i++) {
			d1 = Double.parseDouble(l[i]); 
			d2 = Double.parseDouble(lp[i]);
			if (Math.abs(d1-d2)>epsilon[i]) x++;
		}
		return x;
	}
	
	private static double calcX(NodeEARS r, NodeEARS p, double epsilon) {
		if (p==null) return Integer.MAX_VALUE;
		String l[] = r.chromo.split(" ");
		double d1,d2;
		double diff = 0;
		String lp[] = p.chromo.split(" ");
		double x=0;
		for (int i=0; i<l.length; i++) {
			d1 = Double.parseDouble(l[i]); 
			d2 = Double.parseDouble(lp[i]);
			diff += Math.abs(d1-d2);
			
		}
		if (diff>epsilon) x = diff;
		return x;
	}
	
}
