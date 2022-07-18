package processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Reader {
	static int location = 0;
	static int noofinst=0;
	
	public static void readFile(String path) throws IOException{
	        String currentLine = "";
	        FileReader fileReader= new FileReader(path);
	        try (BufferedReader br = new BufferedReader(fileReader)) {
				while ((currentLine = br.readLine()) != null) {
					int interpret=interpret(currentLine);
				    Memory.memory[location]=  interpret; 
				    noofinst++;
				    //System.out.println(Main.memory[location]);
				    location++;
				}
			}
	       
	    }
	
	 public static int interpret(String instruc) {
		 int inst[]= new int[2];;
		 String[] arrOfInstruction = instruc.split(" ");
		 String rs;
		 int R2;
		 String rt;
		 int R3;
		 String rd;
		 int R1;
		 int shiftamount = 0;
		 int immediate;
		 int addressj;
		 int opcode ;
		 int instruction;
		 switch (arrOfInstruction[0]) {
	    	case "ADD": opcode=0b0000; break;
	    	case "SUB": opcode=0b0001; break;
	    	case "MUL": opcode=0b0010; break;
	    	case "MOVI": opcode=0b0011; break;
	    	case "JEQ": opcode=0b0100; break;
	    	case "AND": opcode=0b0101; break;
	    	case "XORI": opcode=0b0110; break;
	    	case "JMP": opcode=0b0111; break;
	    	case "LSL": opcode=0b1000; break;
	    	case "LSR": opcode=0b1001; break;
	    	case "MOVR": opcode=0b1010; break;
	    	default: opcode =0b1011;//MOVM
		 }
//		 System.out.println("My opcode is : "+ opcode);
		 if (arrOfInstruction[0].equals("ADD")||arrOfInstruction[0].equals("SUB")||arrOfInstruction[0].equals("MUL")||arrOfInstruction[0].equals("AND")) {
			 rs=arrOfInstruction[2];
			 R2 = Integer.parseInt(rs.replace("R"," ").trim());
		 
			 rt=arrOfInstruction[3];
			 R3 = Integer.parseInt(rt.replace("R"," ").trim());

			 
			 rd=arrOfInstruction[1];
			 R1 = Integer.parseInt(rd.replace("R"," ").trim());

			 instruction = opcode;
			 instruction = instruction <<5;
			 instruction += R1;
			 instruction=instruction <<5;
			 instruction+= R2;
			 instruction=instruction <<5;
			 instruction+= R3;
			 instruction=instruction <<13;
		 }
		 else if (arrOfInstruction[0].equals("JEQ")||arrOfInstruction[0].equals("XORI")||arrOfInstruction[0].equals("MOVR")||arrOfInstruction[0].equals("MOVM")) {
			 rd=arrOfInstruction[1];
			 R1 = Integer.parseInt(rd.replace("R"," ").trim());
			 
			 rs=arrOfInstruction[2];
			 R2 = Integer.parseInt(rs.replace("R"," ").trim());
			 
			 immediate= Integer.parseInt(arrOfInstruction[3]);
			 instruction = opcode;
			 instruction = instruction <<5;
			 instruction += R1;
			 instruction=instruction <<5;
			 instruction+= R2;
			 instruction=instruction <<18;
			 instruction+= immediate;
			 
		 }
		 else if (arrOfInstruction[0].equals("MOVI")) {
			 rd=arrOfInstruction[1];
			 R1 = Integer.parseInt(rd.replace("R"," ").trim());
			 immediate= Integer.parseInt(arrOfInstruction[2]);
			 instruction = opcode;
			 instruction = instruction << 5;
			 instruction += R1;
			 instruction=instruction <<5;
			 instruction=instruction <<18;
			 
			 instruction+= immediate;	

		 }
		 else if (arrOfInstruction[0].equals("LSL")||arrOfInstruction[0].equals("LSR")) {
			 rd=arrOfInstruction[1];
			 R1 = Integer.parseInt(rd.replace("R"," ").trim());
			 
			 rs=arrOfInstruction[2];
			 R2 = Integer.parseInt(rs.replace("R"," ").trim());
		
			 if (arrOfInstruction[3]!= null) {
				 shiftamount= Integer.parseInt(arrOfInstruction[3]);
			 }
	
			 instruction = opcode;
			 instruction = instruction <<5;
			 instruction += R1;
			 instruction=instruction <<5;
			 instruction += R2;
			 instruction=instruction <<5;
			 instruction=instruction <<13;
			 instruction+= shiftamount;	

		 }
		 else {
			 addressj= Integer.parseInt(arrOfInstruction[1]);
		 
			 instruction = opcode;
			 instruction = instruction <<28;
			 instruction +=addressj;
			 
		 
		 }
		 //Datapath.opcode=opcode;
		
		 return instruction;
	    
	 }
	 
	 public static void main(String[] args) throws IOException {
		 readFile("test.txt");
	 }
}
