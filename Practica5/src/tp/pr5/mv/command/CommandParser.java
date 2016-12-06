package tp.pr5.mv.command;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

/**
 * Es la clase que parsea los comandos para entenderlos.
 */
public class CommandParser {
	// Array con los diferentes comandos que se pueden ejecutar (Steps y run no
	// hace falta) para parsearlos.
	private static CommandInterpreter[] commands = { new Step(), new Quit(),
			new Steps(), new Run(), new CommandPush(), new CommandPop(),
			new CommandWrite() };

	// METODO QUE ACCEDE AL PARSE DE CADA COMANDO INDIVIDUALMENTE Y DEVUELVE SI
	// ESTE ES EL PARSEADO O NO.
	public static CommandInterpreter parseCommand(String line) {
		CommandInterpreter comando = null;
		for (int i = 0; i < commands.length; i++) {
			if (comando == null) {
				comando = commands[i].parse(line);
			}
		}
		return comando;
	}
}
