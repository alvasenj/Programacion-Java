package tp.pr3.mv.Ventana;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class G2 extends JPanel {
	public G2(){
	super();
	this.setLayout(new FlowLayout());
	TitledBorder titlePrograma = BorderFactory.createTitledBorder("Programa");
	this.setBorder(titlePrograma);
	}
}

