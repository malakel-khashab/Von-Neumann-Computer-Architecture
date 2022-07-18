package processor;

public class ALU {
	static int flag=0;
	static int output;
	
	 public static void ALUimp(int input1,int input2,int ALUOp) {
		 switch(ALUOp) {
		 case 0b000:
			 output=input1 + input2;
			 break;
			 
		 case 0b001:
			 output=input1 - input2;
			 break;
			 
		 case 0b010:
			 output=input1 * input2;
			 break;
		 
		 case 0b011:
			 output=input1 & input2;
			 break;
			 
		 case 0b100:
			 output=input1 ^ input2;
			 break;
			 
		 case 0b101:
			 output=input1 << input2;
			 break;
			 
		 case 0b110:
			 output=input1 >> input2;
			 break;
		 }
		 if(output==0) {
			 flag=1;
		 }
		 else {
			 flag=0;
		 }
	 }

}
