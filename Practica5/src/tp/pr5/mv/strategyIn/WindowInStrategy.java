package tp.pr5.mv.strategyIn;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JTextArea;

public class WindowInStrategy implements InMethod {

	private InMethod metodoEntrada;
	private String texto = new String("");
	private char[] ArrayTexto = new char[200];
	private int contador = 0;

	// Constructora.
	public WindowInStrategy(InMethod entrada) throws IOException {
		metodoEntrada = entrada;
		loadText();
	}

	// Accedemos al texto y vamos sustituyendo cada letra si se ejecuta una
	// instruccion i,
	// si hemos llegado al final, devolvemos un -1.
	public int readChar() throws IOException {

		int devolver = -1;

		if ((contador == this.texto.length() || (metodoEntrada == null))) {
			devolver = -1;
		} else if (ArrayTexto[contador] != '\n') {
			devolver = ArrayTexto[contador];
			ArrayTexto[contador] = '*';
		} else {
			devolver = ArrayTexto[contador];
		}

		contador++;

		formarTexto();
		return devolver;
	}

	private void loadText() throws IOException {
		int valor = 0;
		int i = 0;
		if (this.metodoEntrada != null) {
			// Mientras que el metodo de FileIn no devuelva -1, seguimos
			// guardando el texto.
			while (valor != -1) {
				valor = metodoEntrada.readChar();
				char prueba = (char) valor;

				if (valor != -1) {
					texto = texto + prueba;
					ArrayTexto[i] = prueba;
					i++;
				}
			}

			// Utilizamos un array de char auxiliar para tener la dimension
			// exacta del texto
			// y asi no desperdiciar memoria.
			char[] ArrayAux = new char[texto.length()];

			i = 0;
			// Copiamos cada letra del texto en el Array auxiliar.
			while (i < texto.length()) {
				ArrayAux[i] = ArrayTexto[i];
				i++;
			}

			ArrayTexto = ArrayAux;
		}

	}

	// Metodo que forma el texto completo de entrada a partir de chars.
	private void formarTexto() {
		int i = 0;
		int longitud = texto.length();
		this.texto = "";
		while (i < longitud) {
			texto = texto + ArrayTexto[i];
			i++;
		}

	}

	// Metodo que agrega el texto de entrada al JTextArea que nos pasen.
	public void setTextoVentana(JTextArea informacion) {
		informacion.setText("");
		informacion.append(this.texto);
		if (this.metodoEntrada == null) {
			informacion.setBackground(Color.gray);
			informacion.setEditable(false);
		}
	}

}
