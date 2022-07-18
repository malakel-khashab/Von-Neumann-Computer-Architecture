package processor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Pipelining {
	static int clockcycles;
	static IF_ID stage1;
	static ID_EX stage2;
	static EX_MEM stage3;
	static MEM_WB stage4;
	

	
    public static void Fillet_O_Neumann() throws IOException {
    	clockcycles=1;
    	Reader.readFile("test.txt");
    	int instructionsnumber=Reader.noofinst;
    	Queue<IF_ID> decode1=new LinkedList <IF_ID> () ;
    	Queue<ID_EX> decode2=new LinkedList <ID_EX> () ;
    	Queue<ID_EX> execute1=new LinkedList <ID_EX> () ;
    	Queue<EX_MEM> execute2=new LinkedList <EX_MEM> () ;
    	Queue<EX_MEM> memory=new LinkedList <EX_MEM> () ;
    	Queue<MEM_WB> writeback=new LinkedList <MEM_WB> () ;
    	
    	while(true) {
    		System.out.println("--------->>>> The Current Clock Cycle is :  "+ clockcycles);
    		
    		if(clockcycles%2==1) {
    			if(!writeback.isEmpty()) {

    				if(writeback.peek().branchS4==1 ||writeback.peek().flagExec==1 ) {
    					if(!execute2.isEmpty()) {
    	    				execute2.peek().addressR1S3=0;
    	    				execute2.peek().addressS3=0;
    	    				execute2.peek().ALUoutS3=0;
    	    				execute2.peek().immS3=0;
    	    				execute2.peek().MemorRegS3=0;
    	    				execute2.peek().branchS3=0;
    	    				execute2.peek().flagS3=0;
    	    				execute2.peek().jumpS3=0;
    	    				execute2.peek().MemorRegS3=0;
    	    				execute2.peek().MemReadS3=0;
    	    				execute2.peek().MemWriteS3=0;
    	    				execute2.peek().RegWriteS3=0;
    	    				execute2.peek().WriteDataipS3=0;
    	    			}
    	    			if(!decode2.isEmpty()) {
    	    				decode2.peek().addressR1S2=0;
    	    				decode2.peek().addressS2=0;
    	    				decode2.peek().ALUopS2=0;
    	    				decode2.peek().ALUsrcS2=0;
    	    				decode2.peek().branchS2=0;
    	    				decode2.peek().Datavalue1S2=0;
    	    				decode2.peek().Datavalue2S2=0;
    	    				decode2.peek().immPCS2=0;
    	    				decode2.peek().jumpS2=0;
    	    				decode2.peek().MemorRegS2=0;
    	    				decode2.peek().MemReadS2=0;
    	    				decode2.peek().MemWriteS2=0;
    	    				decode2.peek().PCvalueS2=0;
    	    				decode2.peek().RegWriteS2=0;	
    	    			}
    	    			if(!execute1.isEmpty()) {
    	    				execute1.peek().addressR1S2=0;
    	    				execute1.peek().addressS2=0;
    	    				execute1.peek().ALUopS2=0;
    	    				execute1.peek().ALUsrcS2=0;
    	    				execute1.peek().branchS2=0;
    	    				execute1.peek().Datavalue1S2=0;
    	    				execute1.peek().Datavalue2S2=0;
    	    				execute1.peek().immPCS2=0;
    	    				execute1.peek().jumpS2=0;
    	    				execute1.peek().MemorRegS2=0;
    	    				execute1.peek().MemReadS2=0;
    	    				execute1.peek().MemWriteS2=0;
    	    				execute1.peek().PCvalueS2=0;
    	    				execute1.peek().RegWriteS2=0;	
    	    			}
    	    			
    	    			if(!decode1.isEmpty()) {
       						decode1.peek().stop=1;
        					}
    	    			if(!memory.isEmpty()) {
    						memory.remove();
    					}
    				}
    				Datapath.WriteBack(writeback.remove());
    			}
    			if(!execute2.isEmpty()) {
    				memory.add(Datapath.execute2(execute2.remove()));
    			}
    			if(!decode2.isEmpty()) {
    				execute1.add(Datapath.decode2(decode2.remove()));
    			}
    			if(Main.pc< instructionsnumber) {
    				decode1.add(Datapath.fetch(Main.pc));
    				
    				}
    			
    		}
    			else {
    				if(!memory.isEmpty()) {
        				writeback.add(Datapath.Mem(memory.remove()));
    			}
    				if(!execute1.isEmpty()) {
        				execute2.add(Datapath.execute1(execute1.remove()));
    			}
    				if(!decode1.isEmpty()) {
    					decode2.add(Datapath.decode1(decode1.remove()));
    					if (writeback.peek()!=null) {
    					String type = checkHazard(decode2.peek(), execute2.peek(), writeback.peek());
    					if (type == "RR1a"||type ==  "RR2a"||type ==  "IR1a"||type == "IM1a"||type == "RR1b"||type =="RR2b"||type == "IR1b"||type =="IM1b") {
    						Main.pc=Main.pc-1;
    	    			if(!decode2.isEmpty()) {
    	    				decode2.peek().addressR1S2=0;
    	    				decode2.peek().addressR2S2=0;
    	    				decode2.peek().addressR3S2=0;
    	    				decode2.peek().addressS2=0;
    	    				decode2.peek().ALUopS2=0;
    	    				decode2.peek().ALUsrcS2=0;
    	    				decode2.peek().branchS2=0;
    	    				decode2.peek().Datavalue1S2=0;
    	    				decode2.peek().Datavalue2S2=0;
    	    				decode2.peek().immPCS2=0;
    	    				decode2.peek().jumpS2=0;
    	    				decode2.peek().MemorRegS2=0;
    	    				decode2.peek().MemReadS2=0;
    	    				decode2.peek().MemWriteS2=0;
    	    				decode2.peek().PCvalueS2=0;
    	    				decode2.peek().RegWriteS2=0;	
    	    			}}
    					
    					}
    					else if(execute2.peek()!=null){
    						String type = checkHazard(decode2.peek(), execute2.peek());
        					if (type == "RR1a"||type ==  "RR2a"||type ==  "IR1a"||type == "IM1a") {
        						Main.pc=Main.pc-1;
        	    			if(!decode2.isEmpty()) {
        	    				decode2.peek().addressR1S2=0;
        	    				decode2.peek().addressR2S2=0;
        	    				decode2.peek().addressR3S2=0;
        	    				decode2.peek().addressS2=0;
        	    				decode2.peek().ALUopS2=0;
        	    				decode2.peek().ALUsrcS2=0;
        	    				decode2.peek().branchS2=0;
        	    				decode2.peek().Datavalue1S2=0;
        	    				decode2.peek().Datavalue2S2=0;
        	    				decode2.peek().immPCS2=0;
        	    				decode2.peek().jumpS2=0;
        	    				decode2.peek().MemorRegS2=0;
        	    				decode2.peek().MemReadS2=0;
        	    				decode2.peek().MemWriteS2=0;
        	    				decode2.peek().PCvalueS2=0;
        	    				decode2.peek().RegWriteS2=0;	
        	    			}}
        					
    						
    					}
    			}
    				
    		}
    		if (writeback.isEmpty() && decode2.isEmpty() && decode1.isEmpty() && execute1.isEmpty() && execute2.isEmpty() && memory.isEmpty())
    			break;
    		clockcycles++;
    			
    		}
    		
    
    }
    public static void NOP() {
    	
    }
    
    public static String checkHazard(ID_EX stage2, EX_MEM stage3, MEM_WB stage4) {
    	String type;
    	switch (stage2.opcode) {
    	case 0: case 1: case 2: case 5: type="RR"; break;
    	case 3: type="II";break;
    	case 4: case 6: case 10: type= "IR"; break;
    	case 7: type="J"; break;
    	case 8: case 9: type="RI"; break;
    	default: type="IM";break;
    	}
    	if (type == "RR") {
    		if (stage2.addressR2S2== stage3.addressR1S3 && stage2.addressR2S2!=0) {
        		type= "RR1a";
        	}
        	else if (stage2.addressR2S2== stage4.addressR1S4 && stage2.addressR2S2!=0) {
        		type= "RR1b";
        	}
        	if (stage2.addressR3S2== stage3.addressR1S3 && stage2.addressR3S2!=0) {
        		type= "RR2a";
        	}
        	else if (stage2.addressR3S2== stage4.addressR1S4 && stage2.addressR3S2!=0) {
        		type= "RR2b";
        	}
    }
    	if (type =="IR" || type== "RI" ) {
    		if (stage2.addressR2S2== stage3.addressR1S3 && stage2.addressR2S2!=0) {
        		type= "IR1a";
        	}
        	else if (stage2.addressR2S2== stage4.addressR1S4 && stage2.addressR2S2!=0) {
        		type= "IR1b";
        	}
    	}
    	if(type== "IM") {
    		if (stage2.addressR1S2== stage3.addressR1S3 && stage2.addressR2S2!=0) {
        		type= "IM1a";
        	}
        	else if (stage2.addressR1S2== stage4.addressR1S4 && stage2.addressR2S2!=0) {
        		type= "IM1b";
        	}
    	}
    	return type;
    	}
    
    public static String checkHazard(ID_EX stage2, EX_MEM stage3) {
    	String type;
    	switch (stage2.opcode) {
    	case 0: case 1: case 2: case 5: type="RR"; break;
    	case 3: type="II";break;
    	case 4: case 6: case 10: type= "IR"; break;
    	case 7: type="J"; break;
    	case 8: case 9: type="RI"; break;
    	default: type="IM";break;
    	}
    	if (type == "RR") {
    		if (stage2.addressR2S2== stage3.addressR1S3 && stage2.addressR2S2!=0) {
        		type= "RR1a";
        	}
        	if (stage2.addressR3S2== stage3.addressR1S3 && stage2.addressR3S2!=0) {
        		type= "RR2a";
        	}
    }
    	if (type =="IR" || type== "RI" ) {
    		if (stage2.addressR2S2== stage3.addressR1S3 && stage2.addressR2S2!=0) {
        		type= "IR1a";
        	}
    	}
    	if(type== "IM") {
    		if (stage2.addressR1S2== stage3.addressR1S3 && stage2.addressR2S2!=0) {
        		type= "IM1a";
        	}
    	}
    	return type;
    	}
   
    
    }

