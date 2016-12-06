package tp.pr1.mv;

public class Instruction {
	
	public enum INS{
		PUSH, POP, DUP, FLIP, LOAD, STORE, ADD, SUB, MUL, DIV, OUT, HALT
	}
	
	private String instruccion;
	private int parametro;
	
	public Instruction(String ins){	
		this.instruccion = ins;	
	}
	
	public Instruction(String ins, int param){
		this.instruccion = ins;
		this.parametro = param;		
	}
}
