import sys

filename = "result.txt"
if len(sys.argv) == 1:
    f = open(filename,'w+')
    print >>f, 'whatever'
    print 0

else : 
    print 1