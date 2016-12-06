package tp.pr3.mv.command;

import tp.pr3.mv.cpu.CPU;
import tp.pr3.mv.exceptions.MVTrap;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que se encarga de la interpretación de los diferentes comandos.
 */

abstract public class CommandInterpreter {
	// La cpu tiene que ser protected por si el comando quit se tiene que
	// ejecutar.
	protected static CPU cpu;
	@SuppressWarnings("unused")
	private static boolean terminado;

	public CommandInterpreter() {

	}

	// METODO QUE EJECUTARA CADA COMANDO INDIVIDUALMENTE.
	public abstract boolean executeCommand() throws MVTrap ;

	// METODO QUE CONFIGURA LA CPU PARA EL CORRECTO FUNCIONAMIENTO DEL COMANDO.
	public static void configureCommandInterpreter(CPU programa){
		cpu = programa;
	}

	// METODO QUE PARSEARA CADA COMANDO INDIVIDUALMENTE.
	public abstract CommandInterpreter parse(String cadena);

	public static boolean isQuit() {
		terminado = true;
		return false;
	}
}
