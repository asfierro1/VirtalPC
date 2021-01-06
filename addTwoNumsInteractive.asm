#
# VARIABLE MAP:
#
#  90:  first var
#  91:  second var
#  92:  sum

READ 90  # read 1st value to 90
LOAD 90  # load into GPREG
READ 91  # read 2nd value to 91
ADD  91  # add value from 91 to value in GPREG
STOR 92  # store sum (stored in GPREG) to 92
WRITE 92 # display sum
DUMP 99
HALT 99
