__author__ = 'oventura'
#!/usr/bin/python

import sys, getopt , array,csv

def main(argv):
    inputfile = ''
    outputfile = ''
    try:
        opts, args = getopt.getopt(argv,"hi:o:",["ifile="])
    except getopt.GetoptError:
        print 'test.py -i <inputfile>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'matrix.py -i <inputfile>'
            sys.exit()
        elif opt in ("-i", "--ifile"):
            inputfile = arg
    print 'Input file is "', inputfile

if __name__ == "__main__":
    main(sys.argv[1:])
    fichero = str(sys.argv[2])
    fich_list = open(fichero, 'r')
    a = [[str(x) for x in ln.split()] for ln in fich_list]

    print a
    print(type(a))
    '''
    x = csv.reader(a)
    list(x)
    b = [str(i[0]) for i in a]
    print b,type(b)
    for i in a:
         print i,"es i",type(i),"-"
         h=(i[0].split())
         print h
'''
    f = open(fichero, "r")
    lines = f.read().split("\n") # "\r\n" if needed
    cols = [None]
    for line in lines:
        if line != "": # add other needed checks to skip titles
           cols.append(line.split(","))
    for row in cols:
        print row[1]
        '''
        print cols,type(cols),cols[row][1]
        '''
    p = cols[1]
    print p[1]


def loadMarvelNames():
    marvelNames = {}
    with open("/Users/oventura/Downloads/marvel-names.txt") as f:
        for line in f:
            fields = line.split(' ')
            marvelNames[int(fields[0])] = fields[1]
    return marvelNames

val nameDict = sc.broadcast(loadMarvelNames())