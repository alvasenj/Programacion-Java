package tp.pr3.mv.Ventana;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class WindowMain extends JFrame{

	private Container panelPrincipal;
	private G1 panelBotones;
	//private G2 panelPrograma;
	//private G3 panelTres;
	
	public WindowMain(){
		panelPrincipal = this.getContentPane();  //Panel de la ventana
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setSize(500, 500);
		
		panelBotones = new G1();
		//panelPrograma = new G2();
		
		panelPrincipal.add(panelBotones,BorderLayout.NORTH);
		//panelPrograma.add(panelPrograma,BorderLayout.WEST);
		
		this.pack();       //la ventana se ajusta a las componentes que contiene
		this.setVisible(true);    //La hacemos visible
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);  
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WindowMain w = new WindowMain();
	}

}
