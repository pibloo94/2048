package tp.pr2.control.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import tp.pr2.exceptions.PlayException;
import tp.pr2.logic.multigames.Game;
import tp.pr2.util.MyStringUtils;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class SaveCommand extends Command{
	
	private static String nameSave = "save";
	private static String textSave = "Guarda un estado del juego.";
	private String fileName = null; //nombre del fichero
	private boolean filename_confirmed;
	public static String filenameInUseMsg = "The file already exists ; do you want to overwrite it ? (Y/N)";
	public static final String PlayException = " ERROR: Game unknown";

	/**
	 * constructor SaveCommand
	 */
	public SaveCommand() {
		super(nameSave,textSave);
	}
	
	/**
	 * constructor de la clase SaveCommand
	 * @param commandInfo de tipo String
	 * @param helpInfo de tipo String
	 */
	public SaveCommand(String commandInfo, String helpInfo, String fileName) {
		super(nameSave, textSave);
		this.fileName = fileName;
	}

	@Override
	public Command parse(String[] commandWords) {
		Command command = null;
		
		if (commandWords.length == 2 && commandWords[0].equals(nameSave) && comprobeFileName(commandWords[1])) {
			fileName = commandWords[1];
			command = new SaveCommand(nameSave, textSave, fileName);
		} else if (commandWords.length < 2 && commandWords[0].equals(nameSave)) {
			System.out.println("Save must be followed by a filename");
		} else if (commandWords.length > 2 && commandWords[0].equals(nameSave)) {
			System.out.println("Invalid filename: the filename contains spaces");
		}
		return command;
	}
	
	/**
	 * metodo que ejecuta el comando save
	 * @throws PlayException 
	 */
	public boolean execute(Game game) throws PlayException {		
		File fichero = null;
		FileWriter fw = null;
		BufferedWriter bfw = null;
		
		try {
			fichero = new File(fileName);
			if (fichero.exists()) {
				fichero =comprobeFileExist(fichero, fileName);
			}
			
			fw = new FileWriter(fichero); // preservar anteriores --> true, no preservar anteriores --> false
			bfw = new BufferedWriter(fw);

			game.store(bfw);

			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		} finally {
			try {
				if (fichero != null) {
					bfw.close();
					fw.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	/**This code supposes the following attribute declarations :
	* private boolean filename_confirmed;
	* public static final filenameInUseMsg
	* = "The file already exists ; do you want to overwrite it ? (Y/N)";
	*You may also need to add a throws clause to the declarations .
	*You may also need to add a throws clause to the declarations.
	*/

	/**
	 * metodo confirmFileNameStringForWrite
	 * @param filenameString de tipo String
	 * @param in de tipo Scanner
	 * @return String
	 * @throws PlayException 
	 * @throws FileException 
	 */
	private String confirmFileNameStringForWrite(String filenameString, Scanner in) throws PlayException {
		
		//llama a la funcion validFileName que crea un nuevo txt de nombre 
		String loadName = filenameString;
		filename_confirmed = false;
		
		while (!filename_confirmed) {
			if (MyStringUtils.validFileName(loadName)) {
				File file = new File(loadName);
				if (!file.exists())
					filename_confirmed = true;
				else {
					loadName = getLoadName(filenameString, in);
				}
			} else {
				// ADD SOME CODE HERE
				System.out.println("File name not valid"); 
			}
		}
		return loadName;
	}

	/**
	 * metodo getLoadName
	 * @param filenameString de tipo String
	 * @param in de tipo Scanner
	 * @return String
	 * @throws PlayException 
	 */
	public String getLoadName(String filenameString, Scanner in) throws PlayException  {
	    String newFilename = null;
	    boolean yesOrNo = false;
	    
	    while (!yesOrNo) {
			System.out.print(filenameInUseMsg + ": ");
			String[] responseYorN = in.nextLine().toLowerCase().trim().split("\\s+");
			if (responseYorN.length == 1) {
				switch (responseYorN[0]) {
				case "y":
					yesOrNo = true;
					// ADD SOME CODE HERE
					newFilename = filenameString;
					MyStringUtils.validFileName(newFilename);
					System.out.println("Game successfully saved to file; use load command to reload it.");
					break;
				case "n":
					// ADD SOME CODE HERE
					yesOrNo = true;
					System.out.print("Please enter another filename: ");
					filenameString = in.nextLine();
					newFilename = filenameString;
					if (MyStringUtils.validFileName(newFilename)) {
						confirmFileNameStringForWrite(newFilename, in);
					}
					break;
				default:
					// ADD SOME CODE HERE
					System.out.println("Please answe 'Y' or 'N'");
				}
			} else {
				// ADD SOME CODE HERE
				throw new PlayException(PlayException);
			}
		}
		return newFilename;
	}
	
	/**
	 * metodo comprobeFileExist
	 * @param file
	 * @param fileName
	 * @return file
	 * @throws PlayException
	 */
	public File comprobeFileExist(File file, String fileName) throws PlayException {
		
		fileName = getLoadName(fileName, new Scanner(System.in));
		file = new File(fileName);
		
		return file;
	}
}

