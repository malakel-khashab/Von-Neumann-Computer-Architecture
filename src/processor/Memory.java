package processor;

public class Memory {
	static int memory[]= new int[2048];
	
	public static void WriteToMemory(int data,int address,int SignalMemWrite) {
		if(SignalMemWrite==1) {
			if (address>=1024) {
				memory[address] = data;
				System.out.println("!!!!!!!!!!!!!!!! MEMORY UPDATE!!!!!!!!!!!!!!!!");
				System.out.println("Memory["+(address)+"] was updated to be equal=  "+ data);
				System.out.println();
			}
			else {
				memory[address+1024] = data;
				System.out.println();
				System.out.println("!!!!!!!!!!!!!!!!MEMORY UPDATE!!!!!!!!!!!!!!!!");
				System.out.println("Memory["+(address+1024)+"] was updated to be equal=  "+ data);
				System.out.println();
			}
			
		}
	}
	public static int ReadFromMemory(int address,int SignalMemRead) {
		if(SignalMemRead==1) {
			return memory[address];
		}
		return 0;
		
	}

}
