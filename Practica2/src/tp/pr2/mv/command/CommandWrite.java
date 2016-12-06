package tp.pr2.mv.command;

import tp.pr2.mv.instructions.memory.Push;
import tp.pr2.mv.instructions.memory.Store;

public class CommandWrite extends CommandInterpreter {
	private String posicion;
	private String numero;

	public CommandWrite() {

	}

	public CommandWrite(String posicion, String n) {
		this.posicion = posicion;
		this.numero = n;
	}

	@Override
	public boolean executeCommand() {
		// TODO Auto-generated method stub
		boolean noError = true;
		if (Integer.parseInt(posicion) >= 0) {
			Push push = new Push(numero);
			this.cpu.step(push);
			Store store = new Store(posicion);
			this.cpu.step(store);
		} else
			noError = false;
		return noError;
	}

	@Override
	public CommandInterpreter parse(String cadena) {
		CommandInterpreter comando = null;
		String[] words = cadena.split(" ");
		if (words.length == 3) {
			if (words[0].equalsIgnoreCase("WRITE")) {
					comando = new CommandWrite(words[1], words[2]);
			}
		}
		return comando;
	}


}
