package tp.pr5.mv.controllers;

import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.views.WindowMain;

public class MemoryController {

	private Memory memoria;

	public MemoryController() {
		this.memoria = new Memory();
	}

	public void addCelda(int posicion, int valor) {
		memoria.store(posicion, valor);
	}

	public void memoryChange() {
	}

	public void addObs(WindowMain ventana) {
		memoria.addObs(ventana);
	}

}
