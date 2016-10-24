package atree.moopmetrics;

import java.util.Arrays;

public class Individual {
	private double[] values;

	public Individual(double[] values) {
		super();
		this.values = values;
	}
	public boolean isDominated(Individual drugi) {
		int temp = 0;
		int tempE = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] >= drugi.values[i])
				temp++;
			if (values[i] > drugi.values[i])
				tempE++;
		}
		if (temp == values.length && tempE > 0)
			return true;
		return false;
	}
    public String toString() {
    	return Arrays.toString(values);
    }
	public boolean isEqual(Individual drugi) {
		for (int i = 0; i < values.length; i++) {
			if (values[i] != drugi.values[i])
				return false;
		}
		return true;
	}
	public String toSpaceFormat() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < values.length; i++) {
			sb.append(""+values[i]).append(" ");
		}
		return sb.toString();
	}

}
