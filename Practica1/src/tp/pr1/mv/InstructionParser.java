package tp.pr1.mv;

public class InstructionParser {
	
	private String[] listaIns = {
			"PUSH", "POP", "DUP", "FLIP", "LOAD", "STORE", "ADD", "SUB", "MUL", "DIV", "OUT", "HALT"
	};
	
	
	public Instruction parse(String s){
		Instruction ins = null;
		
		String[] words = s.split(" ");
		
		for(int i=0; i< listaIns.length; i++){
			if(words[0].equalsIgnoreCase(listaIns[i])){
				if(words.length==1){
					ins = new Instruction(s);
				}
				else ins = new Instruction(words[0], Integer.parseInt(words[1]));
			}
		}
		return ins;
	}

}
