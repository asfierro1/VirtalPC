READ 50   #Have the user enter in a new value to add to the total sum.
LOAD 50   #Load the GPREG  with the current value of [50].
BZ 12     #Go down to the bottom to do average, if GPREG = 0.
LOAD 51   #Load up [51] to begin our sum.
ADD 50    #GPREG = GPREG + [50].
STOR 51   #Store my new total sum into [51].
WRITE 51  #Display my new total sum.

ILOAD 1  #Load an intital one into GPREG for counter.
ADD 52   #Increase by counter by one.
STOR 52  #Store the new count in [52].
WRITE 52 #Display the count for confirmation.

BR 0     #User is not finished yet. Go back up!
LOAD 51  #GPREG is loaded with the current sum from [51].
DIV 52   #Divide the GPREG by by counter in [52].
STOR 53   #Store the average into [53].
WRITE 53  #Display the average.
HALT 99  #End Program.
