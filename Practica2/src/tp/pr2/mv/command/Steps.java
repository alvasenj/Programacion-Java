package tp.pr2.mv.command;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento del comando step n.
 */

public class Steps extends CommandInterpreter {
	private int veces;

	public Steps(int veces) {
		this.veces = veces;
	}

	public boolean executeCommand() {
		boolean noError = false;
		int i = 0;
		while ((i < this.veces) && (noError)) {
			cpu.step();
			i++;
		}
		return noError;
	}

	public CommandInterpreter parse(String cadena) {
		CommandInterpreter comando = null;
		String[] words = cadena.split(" ");
		if (words.length == 2) {
			if (words[0].equalsIgnoreCase("STEP")
					&& (Integer.parseInt(words[1]) > 0)) {
				comando = new Steps(Integer.parseInt(words[1]));
			}
		}
		return comando;
	}
}
