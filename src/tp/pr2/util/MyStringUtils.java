package tp.pr2.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class MyStringUtils {
	
	/**
	 * metodo repeat
	 * @param elmnt de tipo String
	 * @param length de tipo int
	 * @return String
	 */
	public static String repeat(String elmnt, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += elmnt;
		}
		return result;
	}

	/**
	 * metodo centre
	 * @param text de tipo String
	 * @param len de tipo int 
	 * @return String
	 */
	public static String centre(String text, int len){
		String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
		float mid = (out.length()/2);
		float start = mid - (len/2);
		float end = start + len;
		return out.substring((int)start, (int)end);
	}

	/**
	 * metodo validFileName
	 * @param filename
	 * @return boolean
	 */
	public static boolean validFileName(String filename) {
		File file = new File(filename);
		
		if (file.exists()) {
			return canWriteLocal(file);
		} else {
			try {
				file.createNewFile();
				file.delete();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * metodo canWriteLocal
	 * @param file
	 * @return boolean
	 */
	public static boolean canWriteLocal(File file) {

		// works on Windows
		try {
			new FileOutputStream(file, true).close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}
