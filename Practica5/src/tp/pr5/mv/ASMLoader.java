package tp.pr5.mv;

import java.io.DataInputStream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.exceptions.ASMSyntaxErrorException;
import tp.pr5.mv.exceptions.MVTrap;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

/**
 * @author Javier Druet
 * @author Alvaro Asenjo
 * 
 */

// clase para cargar archivos ASM.
public class ASMLoader {

	// metodo para cargar archivos ASM.
	public static ProgramMV loadASMFile(String file) throws IOException,
			ASMSyntaxErrorException {
		// Añadimos un parseador de instrucciones.

		ProgramMV programaCargado = new ProgramMV();

		FileInputStream archivo = new FileInputStream(file);
		// CREAMOS EL OBJETO DE ENTRADA
		DataInputStream lectura = new DataInputStream(archivo);
		// CREAMOS EL ALMACENADOR DE LECTURA
		BufferedReader bff = new BufferedReader(new InputStreamReader(lectura));

		String lineatxt;

		boolean instruccionEncontrada = false;

		// MIENTRAS QUE NO LLEGUEMOS A LA ULTIMA LINEA DEL ARCHIVO, SEGUIREMOS
		// COGIENDO DE LINEA EN LINEA
		while ((lineatxt = bff.readLine()) != null) {
			instruccionEncontrada = false;
			String instruccion = new String();
			int contador1 = 0;

			// RECORREMOS LETRA A LETRA LA LINEA CORRESPONDIENTE, PARA IR
			// FORMANDO UN STRING CON UNA INSTRUCCION.
			for (int i = 0; i < lineatxt.length(); i++) {
				String letra = lineatxt.substring(contador1, contador1 + 1);
				if (letra.equalsIgnoreCase(";")) {
					// Si reconoce un punto y coma, significa que es un
					// comentario, y podemos saltar a la siguiente linea;
					i = lineatxt.length();
				} else {
					// SI NO ES PUNTO Y COMA, AÑADIMOS LA LETRA A LA PALABRA.
					instruccionEncontrada = true;
					instruccion = instruccion + letra;
					contador1++;
				}
			}

			// Despues de leer toda la linea, si instruccionEncontrada vale
			// true, parseamos la instruccion.
			if (instruccionEncontrada) {
				Instruction ins = null;
				try {
					ins = InstructionParser.parse(instruccion);
				} catch (MVTrap e) {
					// SI SALTA LA EXCEPCION MVTRAP NO HACE NADA AQUI, PUES
					// SEGUN EL METODO DE EJECUCION, SE TRATARA EN EL MAIN
					// DE UNA MANERA U OTRA.
				}
				if (ins != null) {
					// Se habra parseado bien, y la añadiremos al programa.
					programaCargado.addInstruction(ins);
				} else {
					// SI NO SE HA PARSEADO BIEN, HAY UN ERROR EN EL ARCHIVO DE
					// TEXTO, Y SE NOTIFICARA MEDIANTE UNA EXCEPCION.
					// CERRAMOS EL ARCHIVO Y EL BUFFER Y LANZAMOS LA EXCEPCION.
					bff.close();
					archivo.close();
					throw new ASMSyntaxErrorException(
							"Error en el programa. Linea: " + instruccion);
				}
			}
		}
		// SI TODO HA IDO BIEN, AL TERMINAR DE LEER EL ARCHIVO, SE CIERRA TODO Y
		// SE DEVUELVE EL PROGRAMA YA INICIALIZADO.
		bff.close();
		archivo.close();
		return programaCargado;
	}
}
