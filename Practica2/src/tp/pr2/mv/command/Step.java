package tp.pr2.mv.command;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento del comando step
 */

public class Step extends CommandInterpreter {

	@Override
	public boolean executeCommand() {
		System.out.println(cpu.escribirInstruccion());

		boolean ejecutado = cpu.step();

		if (!ejecutado) {
			System.out.println("Error en la ejecucion de la instruccion");
		}

		System.out.println(cpu);
		return ejecutado;
	}

	@Override
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
