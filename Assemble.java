import java.io.*;
import java.util.*; 

public class Assemble
{
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


    static void readSrc(String fname) throws IOException
    {
	FileReader fr = new FileReader(fname);
	BufferedReader br = new BufferedReader(fr);
	String buffer, operator, operand;
	buffer = operator = operand = "";
	StringTokenizer st = new StringTokenizer(buffer);
	String operators[] = {"HALT","ADD","SUB","MLT","DIV","ILOAD","LOAD","STOR","READ","WRITE","BR","BZ","BN","DUMP"};
	int index, opcode;
        
        while ((buffer = br.readLine()) != null)
        {
	    if( ( buffer.length() == 0) || ( buffer.charAt(0) == '#' ) )
		continue;            

	    st = new StringTokenizer(buffer);
	    operator = st.nextToken();
	    operand = st.nextToken(); 
	
	    if( (Integer.parseInt(operand) < 0) || (Integer.parseInt(operand) > 99) )
	    { 
	       System.out.println("Error: " + operand + " Operand out of range.");
	       System.exit(0);
	    }    
	
	    opcode = -1;
	    for(index = 0; index < operators.length; index++)
	    {
		if( operator.equals( operators[index] ) )
		{
                  // convert string operator to numeric form.
		  opcode = index;
		  break;
		}
	    }	    
	    
	    if(opcode == -1)
	    {
	    	System.out.println("Error: " + operator + " IS NOT VALID!");
		System.exit(0);
	    }

            System.out.println(pad(opcode,2) + pad(Integer.parseInt(operand),2));
        }
    }

    public static void main(String argv[]) throws IOException
    {
        if (argv.length != 1)
        {
            System.out.println("usage:  java Assemble INPUTFILE");
            System.exit(0);
        }

        readSrc(argv[0]);
    }
}
