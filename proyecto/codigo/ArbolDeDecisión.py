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
#import cProfile

filas = 135001

#Pensar en como excluir la primera fila y meterla como en un arreglo aparte para así tener las categorias.

def lecturaDeDatos (archivo):
    """Reads and stores the given dataset into a matrix
    Input : the csv file containing the data.
    """
    with open(archivo, encoding = 'utf-8') as archivocsv: #encoding es para que reconozca cualquier caracter
        data = [0]* filas
        lines = csv.reader(archivocsv, delimiter=';') #delimiter es el que lo separa por comas
        filasusadas = 0
        for line in lines:
            data[filasusadas] = line
            filasusadas = filasusadas+1
        filasusadas -= 1
        datos = [0]*filasusadas
        labels = data[0]
        for i in range (filasusadas):
            datos[i] = data[i+1]
        return datos, filasusadas, labels
    
    
"METODOS AUXILIARES"

def is_number(s):
    """ Determines wether a given value is numeric.
    Input : a value
    """
    try:
        float(s)
        return True
    except ValueError:
        pass
 
    try:
        import unicodedata
        unicodedata.numeric(s)
        return True
    except (TypeError, ValueError):
        pass
 
    return False

def classCounts(rows):
    """ Counts how many success cases there are and stores it into a dictionary
    Input : the dataset organized into a matrix
    """
    dictionary = {}  # {} crea un diccionario
    for row in rows:
        dato = row[-1]   #row[-1] es el ultimo objeto de cada fila y es de tipo string.
        if dato not in dictionary:  #aqui crea nuevas llaves
            dictionary[dato] = 0
        dictionary[dato] += 1
    return dictionary

def mejorValue (rows, columnNum):
    """ Counts how many success cases there are and stores it into a dictionary
    Input : the dataset organized into a matrix and the column number.
    """
    dictionary = {}
    for row in rows:
        dato = row[columnNum]
        if dato not in dictionary:
            dictionary [dato] = 0
        dictionary [dato] += int(row[-1])
    
    mejorValue = None
    mejorExito = 0
    for data in dictionary:
        if (dictionary[data] >= mejorExito):
            mejorExito = dictionary[data]
            mejorValue = data
        
    return mejorValue
    
"METODOS DE DECISIÓN"
    
class Question:
    """ An object type which contains the questions or labels and the given answers without repetition. Ignores caps.
    Input : the column number and a value that column may take
    """
    def __init__ (self, column, value):
        self.column = column
        self.value = value
        
    def match (self, data):
        """ Determines wether to treat a value as a number (>=) or as a string (==) and says wether the value in the question meets that parameter.
        Input : 
        """
        a = data[self.column]
        if isinstance(a, int) or isinstance(a, float) or is_number(a):
            return a >= self.value
        else: 
            return a == self.value
        
#Este metodo es importante para imprimir el arbol, hay que buscar que genere el string necesario  
#"Welcome" -> "To"        
    def toString (self):
        """ Organizes the Question data into a string.
        """
        parameter = str(self.column)
        values = self.value
        comparison = " == "
        if isinstance(values, int) or isinstance(values, float) or is_number(values):
            comparison = " >= "
        else:
            comparison = " == "
        value = str(values)
        return parameter, comparison, value

def partition(rows,question):
    """Splits the matrix according to a given question, into a true matrix and a false matrix.
    Input : the dataset organized into a matrix, a value of type Question
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
    Input : the dataset organized into a matrix
    """
    counts = classCounts(rows)
    impurity = 1
    for i in counts: 
        prob = counts[i]/ float(len(rows))
        impurity -= prob**2 # ** is for exponents.
    return impurity

def informationGain (left, right, current_uncertainty):
    """Calculates the information gain of a given question.
    Input: the data separated into two matrixes: left (true) and right (false), and the gini index of the node.
    """
    a = float(len(left))/(len(left)+len(right))
    b = current_uncertainty - a * gini(left) - (1 - a) * gini(right)
    return b


def decidePartition (rows, labels, questionsused):
    """Tests all the possible partition by all the possible questions and decides which has more information gain.
    Input : the dataset organized into a matrix, an array containing the data labels or column headers and an array of the questions previously used.
    """
    bestgain = 0
    bestquestion = None
    currentUncertainty = gini(rows)
    numFeatures = len(labels) -1 # number of labels
    
    for i in range(1, numFeatures):
        value = mejorValue(rows, i)
        question = Question (i, value)
        if question in questionsused:
            continue
        truerows, falserows = partition(rows, question)
        gain = informationGain(truerows, falserows, currentUncertainty)
        if gain >= bestgain: 
            bestgain = gain
            bestquestion = question
    if bestquestion!= None:
        questionsused.append(bestquestion)    
    return bestgain, bestquestion, questionsused

"METODOS DE CONSTRUCCIÓN DEL ARBOL"
    
class Tree:
    """An object type which splits the dataset and builds a tree
    """
    def __init__(self, rows, labels, questionsused, limite = 5):
        self.labels = labels
        self.questionsused = questionsused
        self.gain, self.question, self.questionsused = decidePartition(rows, labels, self.questionsused)
        if self.gain == 0 or limite == 0:
            self.rows = rows
            self.hijoTrue = None
            self.hijoFalse = None
        else:     
            true_rows, false_rows = partition(rows, self.question)
            self.hijoTrue = Tree(true_rows, self.labels, questionsused, limite-1)
            self.hijoFalse = Tree(false_rows, self.labels, questionsused, limite-1)
            self.miString = Tree.generateString(self)
            
    def generateString(self):
        parameter, comparison, values = self.question.toString()
        parameterString = self.labels[int(parameter)]
        hijos = [self.hijoTrue, self.hijoFalse]
        for hijo in hijos:
            if (hijo != None):
                parameterHijo, comparisonHijo, valuesHijo = hijo.question.toString()
                parameterHijoString = self.labels[int(parameterHijo)]
                string = " \" " + parameterString + comparison + values + " \" " +  " -> " + " \" " + parameterHijoString + comparisonHijo + valuesHijo + " \" "
                print(string)
    
        
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
archivo = os.path.expanduser('~/Desktop/Datos proyecto/datos0.csv')  
#cProfile.run()
data,numFilas,labels =lecturaDeDatos(archivo)
"CONSTRUIR EL ARBOL"

questionsused = []
tree = Tree(data, labels, questionsused)