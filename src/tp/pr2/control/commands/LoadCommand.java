package tp.pr2.control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import tp.pr2.exceptions.PlayException;
import tp.pr2.logic.multigames.Game;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public class LoadCommand extends Command {
	
	private static String nameLoad = "load";
	private static String textLoad = "Carga un estado del juego.";
	private static String firstLine =  "This file stores a saved 2048 game";
	private String fileName = null; //nombre del fichero

	/**
	 * constructor LoadCommand
	 */
	public LoadCommand() {
		super(nameLoad,textLoad);
	}
	
	/**
	 * constructor de la clase LoadCommand
	 * @param commandInfo de tipo String
	 * @param helpInfo de tipo String
	 */
	public LoadCommand(String commandInfo, String helpInfo, String fileName) {
		super(nameLoad, textLoad);
		this.fileName = fileName;
	}
	
	@Override
	public Command parse(String[] commandWords) {
		Command command = null;

		if (commandWords.length == 2 && commandWords[0].equals(nameLoad) && comprobeFileName(commandWords[1])) {
			fileName = commandWords[1];
			command = new LoadCommand(nameLoad, textLoad, fileName);
		} else if (commandWords.length < 2 && commandWords[0].equals(nameLoad)) {
			System.out.println("Load must be followed by a filename");
		} else if (commandWords.length > 2 && commandWords[0].equals(nameLoad)) {
			System.out.println("Invalid filename: the filename contains spaces");
		}
		return command;
	}

	/**
	 * metodo que ejecuta el comando load
	 * @throws IOException 
	 * @throws PlayException 
	 */
	public boolean execute(Game game) throws PlayException {
		File fichero = null;
		FileReader fr = null;
		BufferedReader bfr = null;
		String cadena = null;
		boolean exist = false;
		
		try {
			fichero = new File(fileName);
			if (fichero.exists()) {
				fr = new FileReader(fichero);
				bfr = new BufferedReader(fr);
				cadena = bfr.readLine();
				
				if (cadena.equals(firstLine)) {
					game.load(bfr);
					exist = true;
				}
				bfr.close();
				fr.close();
			}
			else {
				throw new IOException();
			}
			return exist;
		} catch (IOException ioe) {
			System.out.println("ERROR E/S: " + "Fichero no Existente");
			return exist;
		}
	}
}

