#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
@author: LuisaToroVillegas & GregorioPérezBernal

Este código lee ciertos datos para crear un arbol de decisión. 
Luego con este arbol, es capaz de predecir bajo cierta certeza futuros datos.

Basamos el código en:
https://github.com/random-forests/tutorials/blob/master/decision_tree.py
"""
import csv
import os
import numpy as np
#import cProfile

filas = 135001 #leer este datos del archivo

def lecturaDeDatos (archivo):
    """Reads and stores the given dataset into a matrix
    """
    with open(archivo, encoding = 'utf-8') as archivocsv: #encoding es para que reconozca cualquier caracter
        data = [0]* filas
        lines = csv.reader(archivocsv, delimiter=';') #delimiter es el que lo separa por comas
        filasusadas = 0
        for line in lines:
            data[filasusadas] = line
            filasusadas = filasusadas+1
        datos = [0]*filasusadas
        labels = data[0]
        for i in range (filasusadas-1):
            datos[i] = data[i+1]
        return datos, filasusadas, labels
    
"METODOS AUXILIARES"
                
def isNumeric(value):
    """ Determines wether a given value is numeric
    """
    return isinstance(value, int) or isinstance(value, float) 
    #isinstance es un booleano que te dice si el valor que le pasas es del tipo que le pasas
    
def classCounts(rows):
    """ Counts how many success cases there are and stores it into a dictionary
    """
    dictionary = {}  # {} crea un diccionario
    for row in rows:
        dato = row[-1]   #row[-1] es el ultimo objeto de cada fila y es de tipo string.
        if dato not in dictionary:  #aqui crea nuevas llaves
            dictionary[dato] = 0
        dictionary[dato] += 1
    return dictionary

def getLabels (rows):
    """Accesses the first row of the matrix which corresponds to the labels.
    """
    return rows[0] 
    
"METODOS DE DECISIÓN"
    
class Question:
    """ An object type which contains the questions or labels and the given answers without repetition. Ignores caps.
    """
    def __init__ (self, column, value):
        self.column = column
        self.value = value
        
    def match (self, data):
        a = data[self.column]
        if isNumeric(a) :
            return a >= self.value
        elif np.char.isdigit(a):
            b = int(a)
            return b == self.value #Para booleanos con 0 y 1.
        else: 
            return a.upper == self.value.upper # Esto hace que ignore las mayusculas.  
        
    def toString (self):
        parameter = str(self.column)
        values = self.value
        comparison = " == "
        if isNumeric(values):
            comparison = " >= "
        else:
            comparison = " == "
        value = str(values)
        return parameter, comparison, value

def partition(rows,question):
    """Splits the matrix according to a given question, into a true matrix and a false matrix.
    """
    numtruerows = 0
    for row in rows:
        if question.match(row):
            numtruerows = numtruerows +1
    numfalserows = len(rows) - numtruerows       
    true_rows = [0] * numtruerows
    false_rows = [0] * numfalserows
    j = 0
    k = 0
    for row in rows:
        if question.match(row):
            true_rows[j] = row
            j = j+1
        else:
            false_rows[k] = row
            k=k+1
    return true_rows, false_rows  
       
def gini (rows):
    """Calculates the gini impurity of a given dataset.
    """
    counts = classCounts(rows)
    impurity = 1
    for i in counts: 
        prob = counts[i]/ float(len(rows))
        impurity -= prob**2 # ** is for exponents.
    return impurity

def informationGain (left, right, current_uncertainty):
    """Calculates the information gain of a given question.
    """
    a = float(len(left))/(len(left)+len(right))
    b = current_uncertainty - a * gini(left) - (1 - a) * gini(right)
    return b

def decidePartition (rows):
    """Tests all the possible partition by all the possible questions and decides which has more information gain.
    """
    bestgain = 0 # empieza en cero pero se va cambiando.
    bestquestion = None # none es como null
    currentUncertainty = gini(rows)
    a = getLabels(rows)
    numFeatures = len(a) -1 # number of labels
    for i in range (numFeatures): 
        values = set([row[i] for row in rows]) #set guarda los valores y les quita los repetidos
        for j in values: 
            question = Question(i, j) #Esto genera todas las preguntas posibles
            true_rows, false_rows = partition(rows, question)
            if len(true_rows) == 0 or len(false_rows) == 0: 
                continue #si esta partición no hace nada, o no divide los datos, ir a la siguiente partición
            gain = informationGain(true_rows, false_rows, currentUncertainty)
            if gain > bestgain:
                bestgain = gain
                bestquestion = question      
    return bestgain, bestquestion

"METODOS DE CONSTRUCCIÓN DEL ARBOL"
    
class Tree:
    """An object type which splits the dataset and builds a tree
    """
    def __init__(self, rows):
        self.gain, self.question = decidePartition(rows)
        if self.gain == 0:
            self.rows = rows
            self.true_branch = None
            self.false_branch = None
            self.miString = Tree.generateString(self)
        else:     
            true_rows, false_rows = partition(rows, self.question)
            self.true_branch = Tree(true_rows)
            self.false_branch = Tree(false_rows)
            self.miString = Tree.generateString(self)
     
    def generateString(self):
        parameter, comparison, values = self.question.toString()
        hijoTrue = self.HijoTrue
        return parameter + comparison + values + " -> " + hijoTrue.question.toString + "\n" + parameter + comparison + values + " -> " + self.HijoFalse.question.toString 
   
#def classify(row, node):
#    """Uses the decision tree to test new data.
#    """
#    if isinstance(node,Leaf):
#        return node.predictions
#    if node.question.match(row):
#        return classify (row, node.true_branch)
#    else:
#        return classify (row, node.false_branch)  

"LECTURA DE ARCHIVO"
archivo = os.path.expanduser('~/Desktop/Datos proyecto/lite.csv')  
#cProfile.run()
data,numFilas, labels =lecturaDeDatos(archivo)

"CONSTRUIR EL ARBOL"
