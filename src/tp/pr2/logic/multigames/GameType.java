package tp.pr2.logic.multigames;

import tp.pr2.exceptions.PlayException;
import tp.pr2.rules.*;

/**
 * @author Manuel Monforte Escobar y Pablo Agudo Brun
 */

public enum GameType {
	
	ORIG("2048,original version", "original", new Rules2048()), 
	FIB("2048,fibonacci version", "fib", new RulesFib()), 
	INV("2048,inverse version", "inverse", new RulesInverse());

	private String userFriendlyName;
	private String parameterName;
	private GameRules correspondingRules;
	public static final String PlayException = "Game unknown";

	private GameType(String friendly, String param, GameRules rules) {
		userFriendlyName = friendly;
		parameterName = param;
		correspondingRules = rules;
	}

	// precondition : param string contains only lower case characters
	// used in PlayCommand and Game, in parse method and load method, respectively
	public static GameType parse(String param) throws PlayException {
		for (GameType gameType : GameType.values()) {
			if (gameType.userFriendlyName.equals(param) || gameType.parameterName.equals(param)) {
				return gameType;
			}
		}
		
		throw new PlayException(PlayException);
	}

	// used in PlayCommand to build help message, and in parse method exception msg
	public static String externaliseAll () {
		String s = "";
		
		for (GameType type : GameType.values()) {
			s = s + " " + type.parameterName + ",";
		}
		
		return s.substring(1, s.length() - 1);
	}

	// used in Game when constructing object and when executing play command
	public GameRules getRules() {
		return correspondingRules;
	}

	// used in Game in store method --> USAR PARA STORE EN GAME
	public String externalise() {
		return parameterName;

	}

	// used PlayCommand and LoadCommand, in parse methods
	// in ack message and success message, respectively
	public String toString() {
		return userFriendlyName;
	}


}
