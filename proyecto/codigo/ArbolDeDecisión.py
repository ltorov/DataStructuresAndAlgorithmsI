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

"""cambiamos la lectura de datos entonces hay que adaptar varias cosas :)
"""

filas = 135000  #Cambiar según la cantidad de datos

#metodo que lee y almacena los datos en una matriz que retorna
#Pensar en como excluir la primera fila y meterla como en un arreglo aparte para así tener las categorias.

def lecturaDeDatos (archivo):
    with open(archivo, encoding = 'utf-8') as archivocsv: #encoding es para que reconozca cualquier caracter
        data = [0]* filas
        lines = csv.reader(archivocsv, delimiter=',') #delimiter es el que lo separa por comas
        filasusadas = 0
        for line in lines:
            data [filasusadas] = line
            filasusadas = filasusadas+1
        return data, filasusadas

#Este metodo se usara para que no queden un montón de posiciones en 0, y tener una solo matriz
def createMatrix(archivo):
    rows, numrows = lecturaDeDatos (archivo)
    data = [0]*numrows
    for i in range (numrows):
        data [i] = rows [i]
    return data
    
"metodos auxiliares"
                
def is_numeric(value):
    return isinstance(value, int) or isinstance(value, float) 
#isinstance es un booleano que te dice si el valor que le pasas es del tipo que le pasas

#Esto lo que hace es crear un diccionario del ultimo objeto de las filas .
def class_counts(rows):
    dictionary = {}  # {} crea un diccionario
    for row in rows:
        dato = row[-1]   #row[-1] es el ultimo objeto de cada fila y es de tipo string.
       
        if dato not in dictionary:  #aqui crea nuevas llaves
            dictionary[dato] = 0
        dictionary[dato] += 1
    return dictionary

#Este metodo obtiene la primera fila, aquella que contiene todas las "categorias" en forma string y las separa y guarda en una lista.
def getLabels (rows):
    columns = rows[0] #lista con un sólo elemento
    a = columns [0] #string de ese elemento
    labels = a.split(';')
    return labels
    
    
"metodos de decisión"
    
#Question es un tipo de objeto que contiene las preguntas (columns) y los tipos de respuesta.
class Question:
    
    def __init__ (self, column, value):
        self.column = column
        self.value = value
        
    def match (self, data):
        a = data[self.column]
        if is_numeric(a):
            return a >= self.value
        else: 
            return a == self.value
    
#recibe data[] y un objeto de tipo Question para separar data[] en dos, según la pregunta.
def partition(rows,question):
    true_rows = []
    false_rows = []
    for i in rows:
        if question.match(i):
            true_rows.append(i)
        else:
            false_rows.append(i)
    return true_rows, false_rows  
 
#calcula la impureza de gini de cierta data.       
def gini (rows):
    counts = class_counts(rows)
    impurity = 1
    for i in counts: 
        prob = counts[i]/ float(len(rows))
        impurity -= prob**2 # ** is for exponents.
    return impurity

#calcula que tanto separa una pregunta.
def information_gain (left, right, current_uncertainty):
    a = float(len(left))/(len(left)+len(right))
    b = current_uncertainty - a * gini(left) - (1 - a) * gini(right)
    return b

# decide que pregunta es mejor para cada nodo de decision.
def decide_partition (rows):
    bestgain = 0 # empieza en cero pero se va cambiando.
    bestquestion = None # none es como null
    current_uncertainty = gini(rows)
    a = getLabels(rows)
    num_features = len(a) -1 # number of labels
    
    for i in range (num_features): 
        values = set([row[i] for row in rows]) #set guarda los valores y les quita los repetidos
        
        for j in values: 
            question = Question (i, j)
            true_rows, false_rows = partition(rows, question)
            
            if len(true_rows) == 0 or len(false_rows) == 0: 
                continue #si esta partición no hace nada, o no divide los datos, ir a la siguiente partición
                
            gain = information_gain(true_rows, false_rows, current_uncertainty)
            
            if gain > bestgain:
                bestgain = gain
                bestquestion = question
    print(bestgain)
    print(bestquestion)          
    return bestgain, bestquestion

"metodos de construcción del árbol de decisión"
#Una hoja es cuando ya no se puede dividir más la imformación
class Leaf:
    def __init__ (self, rows):
        self.predictions = class_counts(rows)
        # self.pureza = indice     Esto hay que pensarlo para que cada hoja tenga su certeza. Pasarlo como parametro.

#Un nodo de decision es aquel que divide la información en dos.      
class Decision_Node:
    def __init__ (self, question, true_branch, false_branch):
        self.question = question
        self.true_branch = true_branch
        self.false_branch = false_branch
    
#un metodo recursivo para construir el arbol que tiene como parada cuando se llega a una hoja.
def build_tree(rows):
    gain, question = decide_partition(rows)
    
    if gain == 0:
        return Leaf(rows)
    
    true_rows, false_rows = partition(rows, question)
    
    true_branch = build_tree(true_rows)
    
    false_branch = build_tree(false_rows)

    return Decision_Node(question, true_branch, false_branch)

#ahora, cuando se tiene el arbol de decisión creado, se usa para clasificar nueva data.
    
#este metodo retorna las predicciones de una hoja, es decir si tiene éxito o no.   
def classify(row, node):
    if isinstance(node,Leaf):
        return node.predictions
    if node.question.match(row):
        return classify (row, node.true_branch)
    else:
        return classify (row, node.false_branch)  

#este código imprime el arbol.
def print_tree(node, spacing=""):

    if isinstance(node, Leaf):
        print (spacing + "Predict", node.predictions)
        return

    print (spacing + str(node.question))

    print (spacing + '--> True:')
    print_tree(node.true_branch, spacing + "  ")

    print (spacing + '--> False:')
    print_tree(node.false_branch, spacing + "  ")
    

#Esto ya es para correr el código e idealmente imprimirlo
#Falta hacer que funcione :)
    
"aquí se lee el archivo"

archivo = os.path.expanduser('~/Desktop/Datos proyecto/Datos0.csv')  
data = createMatrix(archivo)
decide_partition(data)