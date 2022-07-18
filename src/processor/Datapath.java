package processor;

import java.io.IOException;

public class Datapath {


   
    public static IF_ID fetch(int pc) {
    	IF_ID stage1=new IF_ID();
    	int instruction =0;
    	instruction= Memory.memory[pc];
    		//System.out.println(instruction);
    		//decode(instruction);
    	stage1.instructionS1=instruction;
    	stage1.PCvalueS1=Main.pc++;
    	System.out.println();
    	System.out.println("1)FETCHING OF Instruction "+(pc+1));
    	System.out.println();
    	return stage1;
    }
    
    public static ID_EX decode1(IF_ID stage1) {
    	ID_EX stage2=new ID_EX();
    	stage2.pc2=stage1.PCvalueS1;
    	System.out.println("**IF_ID PIPELINE REGISTER INPUTS:**");
    	System.out.println("Instruction: "+stage1.instructionS1+ " , PCValue: "+stage1.PCvalueS1);
    	int opcode ;     // bits31:28
    	int R1 = 0;      // bits27:23
    	int valueR1 ;
        int valueR2 ;
        double shamt ;   // bits12:0
        int imm ;        // bits17:0
        int shiftoutput;
        int address = 0; // bits27:0
        int intermediatePC;
    	
        
        int R2 = 0;      // bit22:18
        int R3 = 0;      // bits17:13
        
        //R-Type
        
        
        int tempInst=stage1.instructionS1;
        tempInst= tempInst & 0b11110000000000000000000000000000;
        opcode = tempInst >>> 28;
        
        tempInst=stage1.instructionS1;
        imm=tempInst & 0b111111111111111111;
        if((tempInst << 14)<0) {
        	imm = tempInst | 0b11111111111111000000000000000000;
        	 tempInst=~(tempInst &  0b1111100000000000000000000000) ;
             R1 = (tempInst >> 23)*-1;
             
             tempInst= ~stage1.instructionS1;
             tempInst= tempInst & 0b11111000000000000000000;
             R2 = (tempInst >> 18)*-1;
             
             tempInst = ~stage1.instructionS1;
             tempInst= tempInst & 0b111110000000000000;
             R3 = (tempInst >>> 13)*-1;
             
        }
        else {
        tempInst = stage1.instructionS1 ;
        tempInst=(tempInst &  0b1111100000000000000000000000);
        R1 = tempInst >> 23;
       
        tempInst = stage1.instructionS1 ;
        tempInst= tempInst & 0b11111000000000000000000;
        R2 = tempInst >> 18;
      
        
        
        tempInst = stage1.instructionS1;
        tempInst= tempInst & 0b111110000000000000;
        R3 = tempInst >>> 13;
        }
        tempInst = stage1.instructionS1;
        tempInst= tempInst & 0b1111111111111;
        shamt = tempInst;
        //J-Type
        tempInst=stage1.instructionS1;
        address=tempInst&0b1111111111111111111111111111;
        
        
        
        ControlUnit.ControlUnit(opcode);
        
        if (R2==0) {
        	valueR1= RegisterFile.R0;
        }
        else {
        	 valueR1=RegisterFile.RegFileRead(R2);
        }
        
        if(ControlUnit.RegDest==0) {
        	if (R3==0) {
        		valueR2= RegisterFile.R0;
        	}
        	else {
        		valueR2= RegisterFile.RegFileRead(R3);
        	}
        }
        else {
        	if (R1==0) {
        		valueR2= RegisterFile.R0;
        	}
        	else {
        		valueR2= RegisterFile.RegFileRead(R1);
        	}
        } 
        
        if(ControlUnit.Shift==0) {
        	shiftoutput=imm;
        }
        else {
        	shiftoutput=(int) shamt;
        }
        
        intermediatePC=shiftoutput+Main.pc;
        
        
        stage2.opcode=opcode;
        stage2.ALUopS2=ControlUnit.ALUOp;
        stage2.ALUsrcS2=ControlUnit.ALUSrc;
        stage2.branchS2=ControlUnit.PCSrc;
        stage2.RegWriteS2=ControlUnit.RegWrite;
        stage2.jumpS2=ControlUnit.jump;
        stage2.MemorRegS2=ControlUnit.Mem2Reg;
        stage2.MemReadS2=ControlUnit.MemRead;
        stage2.MemWriteS2=ControlUnit.MemWrite;
        stage2.PCvalueS2=stage1.PCvalueS1;
        stage2.shiftS2=shiftoutput;
        stage2.addressS2=address;
        stage2.addressR1S2=R1;
        stage2.addressR2S2=R2;
        stage2.addressR3S2=R3;
        stage2.Datavalue1S2=valueR1;
        stage2.Datavalue2S2=valueR2;
        stage2.immPCS2=intermediatePC;
        
        // Printings
        System.out.println();
        System.out.println("2)DECODING OF Instruction "+ (stage2.pc2+1));
        System.out.println();
        System.out.println("Instruction Address is "+ address);
        System.out.println("opcode = "+opcode);
        System.out.println("rs = "+R2);
        System.out.println("rt = "+R3);
        System.out.println("rd = "+R1);
        System.out.println("shift amount = "+shamt);
        System.out.println("immediate = "+imm);
        System.out.println("address = "+address);
        System.out.println("value[rs] = "+valueR1);
        System.out.println("value[rt] = "+valueR2);
        System.out.println(); 
        //execute();
        return stage2;
    }
    public static ID_EX decode2(ID_EX stage2) {
    	System.out.println();
    	System.out.println("3)DECODING OF Instruction "+(stage2.pc2+1));
    	System.out.println();
    	return stage2;
    }
    
    
    public static EX_MEM execute1(ID_EX stage2) {
    	EX_MEM stage3=new EX_MEM();
    	stage3.pc3=stage2.pc2;
    	System.out.println("**ID_EX PIPELINE REGISTER INPUTS:**");
    	System.out.println("Opcode: "+ stage2.opcode+ " , ALUop: "+stage2.ALUopS2+ " , ALUSrc: "+stage2.ALUsrcS2+ " , Branch: " +stage2.branchS2+" , RegWrite: "+stage2.RegWriteS2 );
    	System.out.println();
    	System.out.println("Jump: "+stage2.jumpS2+ " , MemorReg: "+stage2.MemorRegS2+ " , MemRead: " +  stage2.MemReadS2+ " , MemWrite: "+ stage2.MemWriteS2+ " , PC: "+stage2.PCvalueS2 );
    	System.out.println();
    	System.out.println("Shift: "+ stage2.shiftS2+ " , Address: "+stage2.addressS2+ " , R1:"+ stage2.addressR1S2+ " , IntermediatePC: " +stage2.immPCS2 );
    	System.out.println();
    	int data2;
        
    	if(stage2.ALUsrcS2==0) {
    		data2=stage2.Datavalue2S2;
    	}
    	else {
    		data2=stage2.shiftS2;
    	}
    	ALU.ALUimp(stage2.Datavalue1S2, data2, stage2.ALUopS2);
    	
    	stage3.ALUoutS3=ALU.output;
    	stage3.MemorRegS3=stage2.MemorRegS2;
    	stage3.MemReadS3=stage2.MemReadS2;
    	stage3.MemWriteS3=stage2.MemWriteS2;
    	stage3.RegWriteS3=stage2.RegWriteS2;
    	stage3.addressR1S3=stage2.addressR1S2;
    	stage3.addressR2S3=stage2.addressR2S2;
    	stage3.addressR3S3=stage2.addressR3S2;
    	stage3.flagS3=ALU.flag;
    	stage3.branchS3=stage2.branchS2;
    	stage3.jumpS3=stage2.jumpS2;
    	stage3.immS3=stage2.immPCS2;
    	stage3.PCS3=stage2.PCvalueS2;
    	stage3.addressS3=stage2.addressS2;
    	System.out.println();
    	System.out.println("4)EXECUTING OF Instruction "+(stage3.pc3+1));
    	System.out.println();
    	
    	return stage3;
    }
    public static EX_MEM execute2(EX_MEM stage3) {
    	System.out.println();
    	System.out.println("5)EXECUTING OF Instruction "+(stage3.pc3+1));
    	System.out.println();
    	return stage3;
    }
    
    public static MEM_WB Mem(EX_MEM stage3) {
    	MEM_WB stage4=new MEM_WB();
    	stage4.pc4=stage3.pc3;
    	System.out.println("**EX_MEM PIPELINE REGISTER INPUTS:**");
    	System.out.println(" ALUout: "+stage3.ALUoutS3+ " , ALUFlag: "+stage3.flagS3+ " , Branch: " +stage3.branchS3+" , RegWrite: "+stage3.RegWriteS3 );
    	System.out.println();
    	System.out.println("Jump: "+stage3.jumpS3+ " , MemorReg: "+stage3.MemorRegS3+ " , MemRead: " +  stage3.MemReadS3+ " , MemWrite: "+ stage3.MemWriteS3+ " , PC: "+stage3.PCS3 );
    	System.out.println();
    	System.out.println("Branch: "+ stage3.branchS3+ " , Address: "+stage3.addressS3+ " , R1:"+ stage3.addressR1S3+ " , IntermediatePC: " +stage3.immS3 );
    	System.out.println();
    	int flagExec=stage3.flagS3 & stage3.jumpS3;
        int valueAddress;

        
        System.out.println("6)MEMORY OF Instruction "+(stage4.pc4+1));
    	valueAddress=Memory.ReadFromMemory(stage3.ALUoutS3, stage3.MemReadS3);
    	Memory.WriteToMemory(stage3.addressR1S3, stage3.ALUoutS3, stage3.MemWriteS3); //changed from valueR2 to data2
    	
    	//WriteBack();
    	stage4.ALUoutS4=stage3.ALUoutS3;
    	stage4.MemorRegS4=stage3.MemorRegS3;
    	stage4.MemoutS4=valueAddress;
    	stage4.RegWriteS4=stage3.RegWriteS3;
    	stage4.addressR1S4=stage3.addressR1S3;
    	stage4.addressR2S4=stage3.addressR2S3;
    	stage4.addressR3S4=stage3.addressR3S3;
    	stage4.flagS4=stage3.flagS3;
    	stage4.branchS4=stage3.branchS3;
    	stage4.jumpS4=stage3.jumpS3;
    	stage4.PCS4=stage3.PCS3;
    	stage4.immS4=stage3.immS3;
    	stage4.addressS4=stage3.addressS3;
    	stage4.flagExec=flagExec;
    	System.out.println();
    	
    	System.out.println();
    	return stage4;
    }
    
    public static void WriteBack(MEM_WB stage4) {
    	System.out.println("**MEM_WB PIPELINE REGISTER INPUTS:**");
    	System.out.println(" ALUout: "+stage4.ALUoutS4+ " , ALUFlag: "+stage4.flagS4+ " , Branch: " +stage4.branchS4+" , RegWrite: "+stage4.RegWriteS4 );
    	System.out.println();
    	System.out.println("Jump: "+stage4.jumpS4+ " , MemorReg: "+stage4.MemorRegS4+ " , MemOut: " +  stage4.MemoutS4+ " , PC: "+stage4.PCS4 );
    	System.out.println();
    	System.out.println("FlagExec: "+ stage4.flagExec+ " , Address: "+stage4.addressS4+ " , R1:"+ stage4.addressR1S4+ " , IntermediatePC: " +stage4.immS4 );
    	System.out.println();
    	int FinalOutput;
    	int masked=stage4.PCS4 & 0b11110000000000000000000000000000;
    	masked=masked | stage4.addressS4;
    	
    	if(stage4.flagExec==1) {
    		Main.pc = stage4.immS4;
    	}
    	else if(stage4.branchS4==1) {
    		stage4.PCS4=masked;
    		Main.pc=stage4.PCS4;
    	}
    	
    	if(stage4.addressR1S4==0) {
    		FinalOutput=RegisterFile.R0;
    	}
        
    	else if(stage4.MemorRegS4==0) {
    		
    		FinalOutput=stage4.MemoutS4;
    	}
    	else {
    		FinalOutput=stage4.ALUoutS4;
    	}
    	RegisterFile.RegFileWrite(FinalOutput,stage4.addressR1S4);

    	System.out.println();
    	System.out.println("7)WRITE BACK OF Instruction "+(stage4.pc4+1));
    	System.out.println();
    	if(stage4.RegWriteS4==1) {
    	System.out.println("!!!!!!!!!!!!!!!! REGISTER UPDATE!!!!!!!!!!!!!!!!");
    	if(stage4.addressR1S4!=0) {
    	System.out.println("Register["+stage4.addressR1S4+"] was updated to be equal =  "+ FinalOutput);
    	System.out.println();
    }
    	else{
    		if(stage4.addressR2S4==0 & stage4.addressR3S4==0 ) {
    		System.out.println("NOP CYCLE TO AVOID DATA HAZARD SO:");
    		System.out.println("Register["+stage4.addressR1S4+"] was updated to be equal =  "+ FinalOutput);
        	System.out.println();
    		}
    		else {
    			System.out.println("Register["+stage4.addressR1S4+"] was updated to be equal =  "+ FinalOutput);
    	    	System.out.println();
    		}
    	}
    }
    }
    
    public static void main (String[] args) throws IOException {
    	RegisterFile.generalRegisters[2]=2;
        RegisterFile.generalRegisters[3]=3;
        RegisterFile.generalRegisters[11]=32;
        Memory.memory[12]=3;
        Pipelining.Fillet_O_Neumann();
        System.out.println("=====REGISTER PRINTINGS AFTER LAST CLOCK CYCLE=====");
        for(int i=0;i<32;i++) {
        	System.out.println("R"+i+" VALUE IS: "+ RegisterFile.generalRegisters[i]);
        }
        System.out.println("=====MEMORY PRINTINGS AFTER LAST CLOCK CYCLE=====");
        System.out.println("**FROM INDEX 0 TO 1023=INSTRUCTIONS**");
        System.out.println("**FROM INDEX 1024 TO 2047=DATA**");
        for(int i=0;i<2048;i=i+5) {
        	for(int j=0;j<5;j++) {
        		if((i+j)<2048) {
        			System.out.print("M["+(i+j)+"] IS: "+ Memory.memory[i+j]+" , ");
                }	
        	}
        	
        	System.out.println();
        
    }
    
}
}
