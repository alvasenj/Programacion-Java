package tp.pr1.mv;

import java.util.Scanner;

public class CPU {
	
	private boolean execute = false;
	
	public CPU(){
		
	}
	
	public void initialize(){
		while(!execute){
			System.out.print("Escriba la instruccion a ejecutar: ");
			Scanner lectura = new Scanner(System.in);
			String leido = lectura.nextLine();
			if( leido.equalsIgnoreCase("HALT")){
				execute = true;
			}
			else{
				InstructionParser lectorInstruc = new InstructionParser();
				Instruction entendido = lectorInstruc.parse(leido);
				if (entendido != null){}
				else{}
			}
		}
	}

}
