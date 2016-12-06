package tp.pr3.mv.command;

import tp.pr3.mv.ins.memory.Pop;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento del comando run
 */

public class CommandPop extends CommandInterpreter {

	public CommandPop() {
	}

	@SuppressWarnings("static-access")
	@Override
	// EJECUTA EL COMANDO POP
	public boolean executeCommand() {
		Pop pop = new Pop();
		return this.cpu.step(pop);
	}

	@Override
	// PARSEA EL COMANDO POP
	public CommandInterpreter parse(String cadena) {
		CommandInterpreter comando = null;
		if (cadena.equalsIgnoreCase("POP")) {

			comando = this;
		}
		return comando;
	}

}
