package atree.moopmetrics;



public class Metrics {
	/**
	 * Function C maps the order pair (x1,x2) to the interval [0..1]
	 * 
	 * @param x1
	 * @param x2
	 * @return 
	 */
	public static double C(IndividualList x1, IndividualList x2) {
		int countDominated=0;
		boolean dominated=false;
		for (int i = 0; i < x2.getSize(); i++) { 
			for (int ii = 0; ii < x1.getSize(); ii++) {
				if (x1.get(ii).isDominated(x2.get(i))) {
					dominated=true;
					//System.out.println(x1.get(ii)+" isDominated "+x2.get(i));
					break;
				}
			}
			if (dominated) {
				countDominated++;
				dominated=false;
			}
		}
		if (x2.getSize()==0) return 0;
		return ((double) countDominated)/x2.getSize();		
	}
}
