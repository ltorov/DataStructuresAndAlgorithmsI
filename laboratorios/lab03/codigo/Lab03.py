#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Apr  5 15:00:57 2020

@author: LuisaToro & GregorioPérez

Recordar que para buscar según el curso se deben escribir como se decía en la guía del laboratorio, es decir:
"Fundamentos de Programación" ,  "Estructuras de Datos 1", y "Estructuras de Datos 2" o por sus respectivos códigos.

La lectura de datos la tomamos del código https://github.com/svegal/ST0245-032/blob/master/laboratorios/lab03/codigo/Punto1.py  
"""
import pandas as pd 

Fundamentos = pd.read_csv('~/Desktop/Fundamentos.csv')
Datos1 = pd.read_csv('~/Desktop/Datos1.csv')
Datos2 = pd.read_csv('~/Desktop/Datos2.csv')

Fundamentos = Fundamentos[["nombre", "código", "Cod. Materia", "Semestre","Nota Definitiva"]]
Datos1 = Datos1[["nombre", "código", "Cod. Materia", "Semestre", "Nota Definitiva"]]
Datos2 = Datos2[["nombre", "código", "Cod. Materia", "Semestre", "Nota Definitiva"]]


Fundamentos = Fundamentos.drop_duplicates(['código'],keep = 'last')
Datos1 = Datos1.drop_duplicates(['código'], keep = 'last')
Datos2 = Datos2.drop_duplicates(['código'], keep = 'last')

print (Fundamentos)
print (Datos1)
print (Datos2)

def BuscarCurso(materia, Semestre):
    curso = False
    if (materia == "Fundamentos de Programación" or materia == "ST0242"):
        for i in range (189): #fundamentos
            if (Semestre == str(Fundamentos.iloc[i][3])):
                print ("The final grade of the student " + str(Fundamentos.iloc[i][0]) + " in the course " 
                + materia + " is " + str(Fundamentos.iloc[i][4]))
                curso = True
    if ( materia == "Estructuras de Datos 1" or materia == "ST0245"):
        for i in range (163): #8fundamentos
            if (Semestre == str(Datos1.iloc[i][3])):
                print ("The final grade of the student " + str(Datos1.iloc[i][0]) + " in the course " + materia 
                + " is " + str(Datos1.iloc[i][4]))
                curso = True
   
    if (materia == "Estructuras de Datos 2" or materia == "ST0247"):
        for i in range (72): #fundamentos
            if (Semestre == str(Datos2.iloc[i][3])):
                print ("The final grade of the student " + str(Datos2.iloc[i][0])+ " in the course " + materia 
                + " is " + str(Datos2.iloc[i][4]))
                curso = True
    if (curso == False):
        print ("No student took the course " + materia + " in the semester " + Semestre)
    

def BuscarAlumno(Estudiante, Semestre):
    curso = False
    for i in range (189): #fundamentos
        if (str(Fundamentos.iloc[i][0])== Estudiante) and (str(Fundamentos.iloc[i][3]) == Semestre):
            print ("The final grade of the student " + Estudiante + " in the course Fundamentos de Programación is " 
            + str(Fundamentos.iloc[i][4]))
            curso = True
    for j in range (163):
        if (Estudiante == str(Datos1.iloc[j][0])) and (Semestre == str(Datos1.iloc[j][3])):
            print ("The final grade of the student " + Estudiante + " in the course Estructura de Datos 1 is " 
            + str(Datos1.iloc[j][4]))
            curso = True
    for k in range (72):
        if (Estudiante == str(Datos2.iloc[k][0])) and (Semestre == str(Datos2.iloc[k][3])):
            print ("The final grade of the student " + Estudiante + " in the course Estructura de Datos 2 is " 
            + str(Datos2.iloc[k][4]))
            curso = True
    if (curso == False):
        print ("The student didn't attend any courses that semester")
    

BuscarAlumno("Abel","20142")

BuscarCurso("Fundamentos de Programación","20142")
   
    