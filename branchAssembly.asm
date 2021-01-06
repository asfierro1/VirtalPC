READ 50    #Enter in a non-zero number.
WRITE 50   #Print out the number.
LOAD 50   #Load the number entered in MEMORY[50] to the GPREG.
BZ 5    #Going to the very last instruction and stopping the program.
BR 0   #If not zero, then we go back all the way to the top.
HALT 45
