package tp.pr3.mv.command;

import tp.pr3.mv.ins.memory.Push;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento del comando run
 */

public class CommandPush extends CommandInterpreter {
	private String numero;

	public CommandPush() {
	}

	public CommandPush(String numero) {
		this.numero = numero;
	}

	@SuppressWarnings("static-access")
	@Override
	// EJECUTA EL COMANDO PUSH
	public boolean executeCommand() {
		Push push = new Push(numero);
		return super.cpu.step(push);
	}

	@Override
	// PARSEA EL COMANDO PUSH
	public CommandInterpreter parse(String cadena) {
		CommandInterpreter comando = null;
		String[] words = cadena.split(" ");
		if (words[0].equalsIgnoreCase("PUSH")) {
			if (words.length == 2) {
				this.numero = words[1];
				comando = this;
			}
		}
		return comando;
	}
}
