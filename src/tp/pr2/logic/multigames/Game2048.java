package tp.pr2.logic.multigames;

import java.util.Random;
import java.util.Scanner;
import tp.pr2.control.Controller;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

class Game2048 {

	public static void main(String[] args)  {

		Scanner in = new Scanner(System.in);
		int size = 0, numIntial = 0, seed = 1000;
		
		//depende de los parametros que se pasen, ya que seed es opcional
		 
		Game game = null;
		Random random;
		try {
			if (args.length == 2) {
				System.out.println("****Game 2048****");
				size = Integer.parseInt(args[0]);
				numIntial = Integer.parseInt(args[1]);
				seed = new Random().nextInt(1000);
				game = new Game(size, numIntial, new Random(seed), GameType.ORIG);
			} else if (args.length == 3) {
				System.out.println("****Game 2048****");
				size = Integer.parseInt(args[0]);
				numIntial = Integer.parseInt(args[1]);
				seed = Integer.parseInt(args[2]);
				random = new Random(seed);
				game = new Game(size, numIntial, random, GameType.ORIG);
			}else {
				in.close();
				throw new NumberFormatException();
			}

			// creamos tablero y juego

			System.out.println(game.toString());
			Controller controller = new Controller(game, in);
			controller.run();
		} catch (NumberFormatException ne) {
			System.err.println("Argumentos incorrectos");
		}
	}
}
