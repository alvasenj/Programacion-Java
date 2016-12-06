package tp.pr4.mv.command;

import tp.pr4.mv.command.CommandInterpreter;
import tp.pr4.mv.command.Steps;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */
/**
 * Es la clase que define el funcionamiento del comando step n.
 */

public class Steps extends CommandInterpreter {
	private int veces;

	public Steps() {
	}

	public Steps(int veces) {
		this.veces = veces;
	}

	// EJECUTA EL COMANDO STEPS
	public boolean executeCommand() {
		boolean noError = true;
		int i = 0;
		while ((i <= this.veces - 1) && (noError) && (!cpu.isQuit())) {
			noError = cpu.step();
			i++;
		}
		return noError;
	}

	// PARSEA EL COMANDO STEPS
	public CommandInterpreter parse(String cadena) {
		CommandInterpreter comando = null;
		String[] words = cadena.split(" ");
		if (words.length == 2) {
			if ((words[0].equalsIgnoreCase("STEP"))
					&& (Integer.parseInt(words[1]) > 0)) {
				comando = new Steps(Integer.parseInt(words[1]));
			}

		}
		return comando;
	}

}
