package processor;

public class ControlUnit {
	static int RegWrite;
	static int RegDest;
	static int Shift;
	static int ALUOp;
	static int ALUSrc;
	static int MemWrite;
	static int MemRead;
	static int Mem2Reg;
	static int jump;
	static int PCSrc;
	
	public static void ControlUnit(int opcode) {
		switch(opcode) {
		case 0:
			RegDest=0;
			PCSrc=0;
			RegWrite=1;
			ALUOp=0b000;
			ALUSrc=0;
			MemRead=0;
			MemWrite=0;
			Mem2Reg=1;
			jump=0;
			break;
		
		case 1:
			RegDest=0;
			PCSrc=0;
			RegWrite=1;
			ALUOp=0b001;
			ALUSrc=0;
			MemRead=0;
			MemWrite=0;
			Mem2Reg=1;
			jump=0;
			break;
			
		case 2:
			RegDest=0;
			PCSrc=0;
			RegWrite=1;
			ALUOp=0b010;
			ALUSrc=0;
			MemRead=0;
			MemWrite=0;
			Mem2Reg=1;
			jump=0;
			break;
			
		case 3:
			Shift=0;
			PCSrc=0;
			RegWrite=1;
			ALUOp=0b000;
			ALUSrc=1;
			MemRead=0;
			MemWrite=0;
			Mem2Reg=1;
			jump=0;
			break;
			
		case 4:
			Shift=0;
			RegDest=1;
			PCSrc=0;
			RegWrite=0;
			ALUOp=0b001;
			ALUSrc=0;
			MemRead=0;
			MemWrite=0;
			jump=1;
			break;
			
		case 5:
			RegDest=0;
			PCSrc=0;
			RegWrite=1;
			ALUOp=0b011;
			ALUSrc=0;
			MemRead=0;
			MemWrite=0;
			Mem2Reg=1;
			jump=0;
			break;
			
		case 6:
			Shift=0;
			PCSrc=0;
			RegWrite=1;
			ALUOp=0b100;
			ALUSrc=1;
			MemRead=0;
			MemWrite=0;
			Mem2Reg=1;
			jump=0;
			break;
			
		case 7:
			PCSrc=1;
			jump=0;
			break;
			
		case 8:
			Shift=1;
			PCSrc=0;
			RegWrite=1;
			ALUOp=0b101;
			ALUSrc=1;
			MemRead=0;
			MemWrite=0;
			Mem2Reg=1;
			jump=0;
			break;
			
		case 9:
			Shift=1;
			PCSrc=0;
			RegWrite=1;
			ALUOp=0b110;
			ALUSrc=1;
			MemRead=0;
			MemWrite=0;
			Mem2Reg=1;
			jump=0;
			break;
			
		case 10:
			Shift=0;
			PCSrc=0;
			RegDest=1;
			RegWrite=1;
			ALUOp=0b000;
			ALUSrc=1;
			MemRead=1;
			MemWrite=0;
			Mem2Reg=0;
			jump=0;
			break;
			
		case 11:
			Shift=0;
			PCSrc=0;
			//RegDest=1;
			ALUOp=0b000;
			ALUSrc=1;
			MemRead=0;
			MemWrite=1;
			jump=0;
			break;
		}
	}
	

}
