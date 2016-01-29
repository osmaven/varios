__author__ = 'oventura'
from __future__ import print_function

import sys
from operator import add

from pyspark import SparkContext

 sc = SparkContext('local', 'test')

def makePlural(word):
    return word + 's'
def pluralLengths(word):
    return len(word)
def wordsRDD(word):
    return List(word,1)



def main(argv):
    otro = 'cat'
    valor = makePlural(otro)
    print(valor)
if __name__ == "__main__":
    main(sys.argv[1:])


s  =sc.parallelize(map (makePlural,animals))
 import re

def tokenize(line):
    /*line='!!!!123A/456_B/789C.123A'*/
    aux =line.lower().replace("[*!\\.]", "")
    return sc.parallelize(re.split('\W+',aux)).filter (lambda x: len(x)>0)

prueba =tokenize('A quick brown fox jumps over the lazy dog.')
x = Array['ala@ala.com', 'bala@bala.com']

stopwords = sc.textFile("/Users/oventura/Downloads/cs100/lab3/stopwords.txt", 4)
x.remove()



line='!!!!123A/456_B/789C.123A'
aux =line.lower().replace("[*!\\.]", "")
aux2=sc.parallelize(re.split('\W+',aux))
aux3= aux2.filter (lambda x: len(x)>0)


def MakeDict(Lista):
    kk = {}
    x = 0
    for i in Lista:
        x = x+1
        if kk.get(i) > 0:
            kk[i] = kk.get(i)+1
        else:
            kk[i] = 1
    return (kk,x)


def MakeDicttf(Lista):

    dicionario=Lista[0]
    total = Lista[1]
    for i in dicionario:
        print(dicionario.get,"-",total)
        dicionario[i] = float(dicionario.get)/total
    return Lista

def MakeDict(Lista):
    kk = {}
    total = len(Lista)
    for i in Lista:
        if kk.get(i) > 0:
            kk[i] = kk.get(i)+1
        else:
            kk[i] = 1
    for h in kk:
        kk[h] = float(kk.get(h))/total
    return kk

print(kk.get,"-",total)