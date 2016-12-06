package tp.pr5.mv.command;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Es la clase que define el funcionamiento del comando step
 */

public class Step extends CommandInterpreter {

	@Override
	// EJECUTA EL COMANDO RUN
	public boolean executeCommand() {
		boolean ejecutado = cpu.step();
		return ejecutado;
	}

	@Override
	// PARSEA EL COMANDO STEP
	// Este método parsea tanto Step, como Steps o Run, pues estos dos son la
	// ejecucion repetida de Step
	public CommandInterpreter parse(String cadena) {
		CommandInterpreter comando = null;
		String[] words = cadena.split(" ");
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("STEP")) {
				comando = new Step();
			}
		}
		return comando;
	}

}
