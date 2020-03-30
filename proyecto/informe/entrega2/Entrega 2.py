#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
@author: LuisaToroVillegas & GregorioPérezBernal
Este archivo lee los datos del archivo csv y lo organiza en un arreglo de arreglos.
"""
import csv
    
data = []
    
def lecturaDeDatos (archivo):
    with open(archivo, encoding = 'utf-8') as archivocsv: #encoding es para que reconozca cualquier caracter
        tie_reader = csv.reader(archivocsv, delimiter=',') #delimiter es el que lo separa por comas
        for line in tie_reader:
            data.append(line)
        print(data)

def search (data, a):
    for i in range (77):
        if (data [0],[i] == a):
            return i
        else:
            return "element not found" # la idea es to throw an exception
    
def accessPerson (data, p):
    return data [p]
    
def accessElement (data, p, value):
    b = search (data, value)
    return data [p][b]