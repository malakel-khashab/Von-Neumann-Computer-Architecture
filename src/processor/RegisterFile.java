package processor;

public class RegisterFile {
	static int generalRegisters[]= new int[32];
	static final int R0= 0;
	static int data1=0;
	
	public static int RegFileRead(int read1) {
		return generalRegisters[read1];
		
	}
	public static void RegFileWrite(int writedata,int writeReg) {
		generalRegisters[writeReg]=writedata;
	}
}
