import java.io.*;

public class VPCrte
{
    static final int MEMSIZ = 100;

    static final int HALT  = 0;
    static final int ADD   = 1;
    static final int SUB   = 2;
    static final int MLT   = 3;
    static final int DIV   = 4;
    static final int ILOAD = 5;
    static final int LOAD  = 6;
    static final int STOR  = 7;
    static final int READ  = 8;
    static final int WRITE = 9;
    static final int BR    = 10;
    static final int BZ    = 11;
    static final int BN    = 12;
    static final int DUMP  = 13;
 
    static int MEMORY[] = new int[MEMSIZ];

    static int PCREG;
    static int IRREG;
    static int GPREG;
    
    static boolean debug = false;

    static void readToMemory(String fname) throws IOException
    {
        FileReader fr = new FileReader(fname);
        BufferedReader br = new BufferedReader(fr);
	String buffer = ""; int index = 0;
	
	while( (buffer = br.readLine()) != null  )
        {
            if (debug)
            {      
	   	System.out.println("readToMemory: [" + index +"] = (" + buffer + ")");
            }
            
	    MEMORY[index] = Integer.parseInt(buffer);
	    index += 1;; 
        }
    }

    public static String pad(int n, int w)
    {
        String machineCode = "";
        machineCode = Integer.toString(n);
        int machineCodeLength = machineCode.length();

        int remainingZeros = w  - machineCodeLength;
        for(int i = remainingZeros; i > 0; i--)
            machineCode = "0" + machineCode;

        return machineCode;
    }
    
    public static void dumpMemory()
    {
        System.out.println("===================================================================");
        int c=0;

        System.out.println("PCREG = " + pad(PCREG, 4));
        System.out.println("IRREG = " + pad(IRREG,4));
        System.out.println("GPREG = " + pad(GPREG, 4) + "\n");
         
        System.out.println("MEMORY:     0     1     2     3     4     5     6     7     8     9");
        System.out.println("    ---------------------------------------------------------------");
        
        for (int i=0; i<MEMSIZ; i++)
        {
            if ((i%10) == 0)
            {
                System.out.print("     " + c + "|");
                c++;
            }
            
            System.out.print("  " + pad(MEMORY[i], 4));
            
            if (((i+1)%10) == 0)
                System.out.println();
        }
        System.out.println();
        System.out.println("===================================================================");
    }
       
    public static void runProg() throws IOException
    {
	int opcode, operand;
	opcode = 99; operand = 0;
	PCREG = 0;
        
	while(opcode != HALT)
        {
            IRREG = MEMORY[PCREG];        
	    BufferedReader input = new BufferedReader
                           (new InputStreamReader(System.in));
	    String inputFromUser = "";

	    opcode = (IRREG / 100);
	    operand = (IRREG % 100); 		
            
            if (debug)   
            {
		System.out.print("runProg: MEMORY[" + pad(PCREG,2) + "] = " + 
		pad(IRREG,4) + ", opcode = " + pad(opcode,2) + ", operand = " 
		+ pad(operand,2) + ", GPREG = " + pad(GPREG,4) + " (");
		
		switch(opcode)
		{
		   case(HALT):
		     System.out.print("HALT)\n");
		     break;
		   case(ADD):
		     System.out.print("ADD)\n");
		     break;
		   case(SUB):
		     System.out.print("SUB)\n");
		     break;
		   case(MLT):
		     System.out.print("MLT)\n");
		     break;
		   case(DIV):
		     System.out.print("DIV)\n");
		     break;
		   case(ILOAD):
		     System.out.print("ILOAD)\n");
		     break;
		   case(LOAD):
		     System.out.print("LOAD)\n");
		     break;
		   case(STOR):
		     System.out.print("STOR)\n");
		     break;
		   case(READ):
		     System.out.print("READ)\n");
		     break;
		   case(WRITE):
		     System.out.print("WRITE)\n");
		     break;
		   case(BR):
		     System.out.print("BR)\n");
		     break;
		   case(BZ):
		     System.out.print("BZ)\n");
		     break;
		   case(BN):
		     System.out.print("BN)\n");
		     break;
		   case(DUMP):
		     System.out.print("DUMP)\n");
		     break;
		}
            }

	    switch(opcode)
	    {
	       case ADD:
		    GPREG += MEMORY[operand];
		    break;
	       case SUB:
		    GPREG -= MEMORY[operand];
		    break;
	       case MLT:
		    GPREG *= MEMORY[operand];
		    break;
	       case DIV:
		    GPREG /= MEMORY[operand];
		    break;
	       case ILOAD:
		    GPREG = operand;
		    break;
	       case LOAD:
		    GPREG = MEMORY[operand];
		    break;
	       case STOR:
		    MEMORY[operand] = GPREG;
		    break;
	       case READ:
		    System.out.print("["+ operand +"]? ");
		    inputFromUser = input.readLine();
		    MEMORY[operand] = Integer.parseInt(inputFromUser);
		    break;
	       case WRITE:
		    System.out.println("["+ operand + "] -> " + MEMORY[operand]);
		    break;
	       case BR:
		    PCREG = operand;
		    continue;
	       case BZ:
		    if(GPREG == 0) 
		    { 
		      PCREG = operand;
		      continue;
		    }
		    break;
	       case BN:
		    if(GPREG != 0)
		    { 
		      PCREG = operand;
		      continue;
		    }
		    break;
	       case DUMP:
		    dumpMemory();
		    break;
	    }
	    PCREG += 1;
        }
    }
    
    public static void main(String argv[]) throws IOException
    {
        if (argv.length == 0) 
        {
            System.out.println("usage: java VPCrte FILENAME.exe [ debug ]");
            System.exit(0);
        } 
        
        if ((argv.length == 2) && (argv[1].equals("debug"))) debug = true;

        readToMemory(argv[0]);

        if (debug) dumpMemory();
        
        runProg();
    }
}
