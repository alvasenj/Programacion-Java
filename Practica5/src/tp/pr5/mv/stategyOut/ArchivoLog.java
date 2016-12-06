package tp.pr5.mv.stategyOut;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoLog {
	private File archivo;
	private FileWriter lector;
	int posicion = 0;

	// Constructora de la clase de FileOut que abre un archivo de salida y
	// permite la escritura en el.
	public ArchivoLog(String nombreArchivo) {
		this.archivo = new File(nombreArchivo);
		lector = null;
		// Intentamos escribir en el archivo.
		try {
			lector = new FileWriter(archivo);
		} catch (IOException e) {
			// Si existe cualquier problema, se muestra el error y se cierra el
			// programa.
			System.err.println("Error ejecutando OUT: " + e.getMessage());
			System.exit(2);
		}
	}

	// Metodo que escribe en el archivo de salida el caracter que le venga por
	// la cima de la pila.
	public void writeConsola(String cadena) {
		try {
			lector.write(cadena);
		} catch (IOException e) {
			System.err.println("Error en la ejecucion " + e.getMessage());
		}
	}

	// Metodo que cierra el archivo abierto en la constructora.
	public void cerrarArchivo() {
		try {
			this.lector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al cerrar el archivo out");
		}
	}

}