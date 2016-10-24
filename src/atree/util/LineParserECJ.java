package atree.util;

public class LineParserECJ {
	public static final int BOF=0;
	public static final int EOF=-1;
	public static final int P1=1;
	public static final int P2=2;
	public static final int ID=3;
	public static final int IN=4;
	public static final int CROSS=5;
	public static final int MUTAT=6;
	public static final int REPAIR=7;
	public static final int FITNESS=8;
	
	private String line;
	private String value;
	int state; 
	int pos;
	int len;
	boolean start, end;
	public int getState() {
		return state;
	}
	public String getValue() {
		return value;
	}
	public int getIntValue() {
		return Integer.parseInt(value);
	}
	public double getDoubleVaue()
	{
		return Double.parseDouble(value);
	}
	public String[] getValues(String seperator) {
		return value.split(seperator);
	}
	public LineParserECJ(String l) {
		line =l;
		len = line.length();
		state=BOF;
		pos=0;
		nextState();
	}
	//Example: p1(-1,-1) p2(-1,-1) id(1,0) in( 1 0 0 0 1 0 1 1 1 1) c1 m0 r0
	public void nextState() {
		if (len==pos) {
			state = EOF;
			value="";
			return;
		}
		switch (state) {
		 case  BOF: 
			for (; pos<len; pos++) {
				if (line.charAt(pos)=='(') {
					state= P1;
					pos++;
					value="";
					while (line.charAt(pos)!=')') {
						value=value+line.charAt(pos);
						pos++;
					}
					break;
				}
			};
			break;
		 case  P1: 
			for (; pos<len; pos++) {
				if (line.charAt(pos)=='(') {
					state= P2;
					pos++;
					value="";
					while (line.charAt(pos)!=')') {
						value=value+line.charAt(pos);
						pos++;
					}
					break;
				}
			};
			break;			
		 case  P2: 
			for (; pos<len; pos++) {
				if (line.charAt(pos)=='(') {
					state= ID;
					pos++;
					value="";
					while (line.charAt(pos)!=')') {
						value=value+line.charAt(pos);
						pos++;
					}
					break;
				}
			};
			break;
		 case  ID: 
			for (; pos<len; pos++) {
				if (line.charAt(pos)=='(') {
					state= IN;
					pos++;
					value="";
					while (line.charAt(pos)!=')') {
						value=value+line.charAt(pos);
						pos++;
					}
					break;
				}
			};
			break;	
		 case IN: 
		 case CROSS:
		 case MUTAT:
				for (; pos<len; pos++) {
					if (line.charAt(pos)=='c') {
						state= CROSS;
						pos++;
						value="";
						while ((pos<len) && (line.charAt(pos)!=' ')) {
							value=value+line.charAt(pos);
							pos++;
						}
						break;
					}
					if (line.charAt(pos)=='m') {
						state= MUTAT;
						pos++;
						value="";
						while ((pos<len) && (line.charAt(pos)!=' ')) {
							value=value+line.charAt(pos);
							pos++;
						}
						break;
					}
					if (line.charAt(pos)=='r') {
						state= REPAIR;
						pos++;
						value="";
						while ((pos<len) && (line.charAt(pos)!=' ')) {
							value=value+line.charAt(pos);
							pos++;
						}
						break;
					}
				};
				break;	
		 case REPAIR:
		 case FITNESS:
			 for (; pos<len; pos++) {
					if (line.charAt(pos)=='(') {
						state= FITNESS;
						pos++;
						value="";
						while (line.charAt(pos)!=')') {
							if(line.charAt(pos)=='.')
							{
								pos++;
							}
							else if(line.charAt(pos)==',')
							{
								value += '.';
								pos++;
							}
							else
							{
								value=value+line.charAt(pos);
								pos++;
							}
						}
						break;
					}
				};
				break;
		}
		
	}
}
