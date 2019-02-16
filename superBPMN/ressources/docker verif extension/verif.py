#!/usr/bin/env python
# -*- coding: utf-8 -*- 
import sys

filename = "result.txt"

#sys.argv[0] le nom du script, le premier argument est à sys.argv[1], si longueur = 1 -> pas d'argument.
f = open(filename,'w+')

if len(sys.argv) == 1:
    f = open(filename,'w+')
    print ('pas d\'arguments', file=f)
    print ('00')

else : 
    if sys.argv[1].endswith('.bpmn'):
        print ('fichier avec extension .bpmn',file=f)
        print ('11')
    else:
        print ('pas un fichier avec extension .bpmn', file=f)
        print ('10')