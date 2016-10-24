package atree.util;

public class UtilTestMain {

	/**
	 * Just example test!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LineParserECJ lp = new LineParserECJ("p1(-1,-1) p2(-2,-2) id(1,0) in( 1 0 0 0 1 0 1 1 1 1) c1 m2 r3");
		while (lp.getState()!=LineParserECJ.EOF) {
			System.out.println(lp.getState() +" Value:"+lp.getValue());
			lp.nextState();
			
		}

	}

}
