package tp.pr5.mv.command;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Es la clase que define el funcionamiento del comando quit
 */

public class Quit extends CommandInterpreter {

	@Override
	// EJECUTA EL COMANDO QUIT
	public boolean executeCommand() {

		cpu.detenerMaquina();
		return true;
	}

	@Override
	// PARSEA EL COMANDO QUIT
	public CommandInterpreter parse(String cadena) {
		CommandInterpreter comando = null;
		if (cadena.equalsIgnoreCase("QUIT")) {
			comando = new Quit();
		}
		return comando;
	}

}
