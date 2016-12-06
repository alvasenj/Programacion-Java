package tp.pr2.mv.command;

import tp.pr2.mv.cpu.CPU;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que se encarga de la interpretación de los diferentes comandos.
 */

abstract public class CommandInterpreter {
	protected static CPU cpu;
	@SuppressWarnings("unused")
	private static boolean terminado;

	public CommandInterpreter() {

	}

	public abstract boolean executeCommand();

	public static void configureCommandInterpreter(CPU programa) {
		cpu = programa;
	}

	public abstract CommandInterpreter parse(String cadena);
	
	public static boolean isQuit() {
		terminado = true;
		return false;
	}
}
