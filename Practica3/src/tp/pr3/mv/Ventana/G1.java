package tp.pr3.mv.Ventana;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class G1 extends JPanel{
	private JLabel b1,b2,b3;
	public G1(){
		super();
		InicializarG1();
	}

	private void InicializarG1() {
		// TODO Auto-generated method stub
		this.setLayout(new FlowLayout());
		TitledBorder titlePrograma = BorderFactory.createTitledBorder("Acciones");
		this.setBorder(titlePrograma);
		
		
		
		b1 = new JLabel();
		ImageIcon step = new ImageIcon("/tp/pr3/img/exit.png");
		b1.setIcon(step);
		this.add(b1);
		setSize(50,50);
		
		b2 = new JLabel();
		ImageIcon run = new ImageIcon("run.png");
		b2.setIcon(run);
		add(b2);
		setSize(50,50);
		
		b3 = new JLabel();
		ImageIcon salir = new ImageIcon("exit.png");
		b3.setIcon(salir);
		add(b3);
		setSize(50,50);
		
		
	}

}
