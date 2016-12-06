package tp.pr2.mv.command;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que parsea los comandos para entenderlos.
 */
public class CommandParser {
	private static CommandInterpreter[] commands = { new Step(), new Quit(),
			new Run(), new CommandPop(), new CommandPush(), new CommandWrite()};

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
