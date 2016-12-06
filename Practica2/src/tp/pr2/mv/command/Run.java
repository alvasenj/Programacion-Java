package tp.pr2.mv.command;


/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento del comando run
 */

public class Run extends CommandInterpreter {

	@Override
	public boolean executeCommand() {
		boolean noError = true;
		while((!cpu.programaTerminado()) && (noError)){
			noError = cpu.step();
		}
		return noError;
	}

	@Override
	public CommandInterpreter parse(String cadena) {
		CommandInterpreter comando = null;
		String[] words = cadena.split(" ");
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("RUN")) {
				comando = new Run();
			}
		}
		return comando;
	}
}
