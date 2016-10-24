package atree.test;

import atree.util.MonotoneGrayCode;

public class TestGray {
	public static void main(String a[]) {
		System.out.println("Polozaj:"+MonotoneGrayCode.getPos("0000000000", MonotoneGrayCode.all5));
		System.out.println("Polozaj:"+MonotoneGrayCode.getPos("0000001000", MonotoneGrayCode.all5));
		System.out.println("Polozaj:"+MonotoneGrayCode.getPos("1110111110", MonotoneGrayCode.all5));
		System.out.println("Polozaj:"+MonotoneGrayCode.getPos("1111111111", MonotoneGrayCode.all5));
	}

}
