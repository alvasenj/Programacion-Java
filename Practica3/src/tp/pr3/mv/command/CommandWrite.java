package tp.pr3.mv.command;

import tp.pr3.mv.exceptions.MVTrap;
import tp.pr3.mv.ins.memory.Push;
import tp.pr3.mv.ins.memory.Store;

/**
 @author Javier Druet
 @author Álvaro Asenjo 
 */

/**
 * Es la clase que define el funcionamiento del comando run
 */

public class CommandWrite extends CommandInterpreter {
	private String numero;
	private String posicion;

	public CommandWrite() {
	}

	public CommandWrite(String posicion, String n) {
		this.numero = n;
		this.posicion = posicion;
	}

	@SuppressWarnings("static-access")
	@Override
	// EJECUTA EL COMANDO POP
	public boolean executeCommand() throws MVTrap {
		boolean noError = true;
		if(Integer.parseInt(this.posicion) >= 0){
		Push push = new Push(this.numero);
		this.cpu.step(push);
		Store store = new Store(this.posicion);
		this.cpu.step(store);
		} else {
			//SI LA DIRECCION DONDE ESCRIBIR ES NEGATIVA, SALTARA UNA EXCEPCION.
			noError = false;
			throw new MVTrap("Error ejecutando WRITE " + this.posicion
					+ ": dirección incorrecta " + this.posicion);
		}
		
		return noError;
	}

	@Override
	// PARSEA EL COMANDO POP
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
