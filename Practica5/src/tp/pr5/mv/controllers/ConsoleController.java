package tp.pr5.mv.controllers;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.exceptions.MVTrap;
import tp.pr5.mv.observers.ConsoleObserver;

public class ConsoleController {

	private CPU computador;

	public ConsoleController(CPU computador) {
		this.computador = computador;
	}

	public void step() {
		computador.step();
	}

	public void run() {
		while (!computador.isQuit()) {
			computador.step();
		}
	}

	public void quit() {
		computador.detenerMaquina();
	}
	
	public boolean isQuit(){
		return this.computador.isQuit();
	}

	public void closeASM() {
		this.computador.cerrarArchivo();
	}

	public void loadProgram(ProgramMV programa) {
		computador.loadProgram(programa);
	}

	public void configurarComando() {
		CommandInterpreter.configureCommandInterpreter(computador);
	}

	public boolean ejecutarComando(CommandInterpreter comando) throws MVTrap {
		comando.executeCommand();
		return computador.isQuit();
	}

	public void addObs(ConsoleObserver o) {
		this.computador.addObs(o);
	}
}
