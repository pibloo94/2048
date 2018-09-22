package tp.pr2.util;

public class MyMathsUtil {
	
	/** 
	 * metodo nextFib
	 * convert from long to int since we will not need to use large numbers
	 * @param previous de tipo int
	 * @return int
	 */
	public static int nextFib(int previous) {
		double phi = (1 + Math.sqrt(5)) / 2;
		return (int) Math.round(phi * previous);
	}
}