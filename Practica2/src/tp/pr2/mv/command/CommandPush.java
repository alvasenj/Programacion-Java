package tp.pr2.mv.command;
import tp.pr2.mv.instructions.memory.Push;


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
				comando = new CommandPush(words[1]);
			}
		}
		return comando;
	}
}