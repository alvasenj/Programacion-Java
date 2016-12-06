package tp.pr4.mv.stategyOut;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JTextArea;

public class WindowOutStrategy implements OutMethod{
	
	private String texto = "";
	private OutMethod metodoSalida;

	//Constructora.
	public WindowOutStrategy(String nombreArchivo){
		//Esta constructora tiene que ser con el nombre del Archivo, pues si no, da problemas.
		if(nombreArchivo != null){
		this.metodoSalida = new FileOut(nombreArchivo);
		}
	}

	
	//Metodo que llama a escribir en el archivo y en el texto de salida.
	public void writeChar(char c) throws IOException {
		// TODO Auto-generated method stub
		if(metodoSalida != null){
		texto = texto + c;
		metodoSalida.writeChar(c);
		}
	}

	//Metodo que cierra el archivo abierto.
	public void cerrarArchivo() {
		// TODO Auto-generated method stub
		if(metodoSalida != null){
		metodoSalida.cerrarArchivo();
		}
	}

	//Metodo que agrega el texto de entrada al JTextArea que nos pasen.
	public void setTextoVentana(JTextArea j){
		j.setText("");
		j.append(texto);
		if(metodoSalida == null){
			j.setBackground(Color.gray);
			j.setEditable(false);
		}
	}
}
