package tp.pr2.mv.command;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento del comando quit
 */

public class Quit extends CommandInterpreter {

	@Override
	public boolean executeCommand() {
		cpu.setTerminado();
		return true;
	}

	@Override
	public CommandInterpreter parse(String cadena) {
		CommandInterpreter comando = null;
		String words[] = cadena.split(" ");
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("QUIT")) {
				comando = new Quit();
			}
		}
		return comando;
	}

}
