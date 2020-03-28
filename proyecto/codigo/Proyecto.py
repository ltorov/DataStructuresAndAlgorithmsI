#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
@author: LuisaToroVillegas & GregorioPérezBernal

Este archivo lee los datos del archivo csv y lo organiza en un arreglo de arreglos.

"""
import csv

def lecturaDeDatos (archivo):
    data = []
    with open(archivo, encoding = 'utf-8') as archivocsv: #encoding es para que reconozca cualquier caracter
        tie_reader = csv.reader(archivocsv, delimiter=',') #delimiter es el que lo separa por comas
        for line in tie_reader:
            data.append(line)
        print(data)