package tp.pr2.mv.command;
import tp.pr2.mv.instructions.memory.Pop;

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
		String[] words = cadena.split(" ");
		if (words.length == 1) {
			if (words[0].equalsIgnoreCase("POP")) {
				comando = new CommandPop();
			}
		}
		return comando;
	}

}