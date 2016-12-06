package tp.pr5.mv.controllers;

import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.views.WindowMain;

public class OperandStackController {

	private OperandStack pila;

	public OperandStackController() {
		this.pila = new OperandStack();
	}

	public void addCima(int numero) {
		this.pila.guardarEntero(numero);
	}

	public void deleteCima() {
		this.pila.eliminarCima();
	}

	public void operandStackChange() {

	}

	public void addObs(WindowMain ventana) {
		this.pila.addObs(ventana);
	}
}
