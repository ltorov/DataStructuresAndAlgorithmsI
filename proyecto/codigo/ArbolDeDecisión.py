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
data = []

def lecturaDeDatos (archivo):
    with open(archivo, encoding = 'utf-8') as archivocsv: #encoding es para que reconozca cualquier caracter
        tie_reader = csv.reader(archivocsv, delimiter=',') #delimiter es el que lo separa por comas
        for line in tie_reader:
            data.append(line)
        print(data)
        
        #Hay que aprender como "conectar" dos clases :) para usar aquí los datos importados

#metodos que son importantes : Objeto "nodo de decisión" , 
#Calcular impuresa o gini, calcular information gain, partir los datos en dos,
#las preguntas "obtenerlas de los datos",  

#metodos auxiliares
def is_numeric(value):
    
    return isinstance(value, int) or isinstance(value, float) 
#isinstance es un booleano que te dice si el valor que le pasas es del tipo que le pasas

def class_counts(rows):
    counts = {}  # diccionario
    for row in rows:
        label = row[-1]
        if label not in counts:
            counts[label] = 0
        counts[label] += 1
    return counts


#metodos de decisión
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
    
def partition(rows,question):
    true_rows = []
    false_rows = []
    for i in rows:
        if question.match(i):
            true_rows.append(i)
        else:
            false_rows.append(i)
    return true_rows, false_rows  
        
def gini (rows):
    counts = class_counts(rows)
    impurity = 1
    for i in counts: 
        prob = counts[i]/ float(len(rows))
        impurity -= prob**2 # ** is for exponents.
    return impurity

def information_gain (left, right, current_uncertainty):
    a = float(len(left))/(len(left)+len(right))
    b = current_uncertainty - a * gini(left) - (1 - a) * gini(right)
    return b

def decide_partition (rows):
    bestgain = 0 # empieza en cero pero se va cambiando.
    bestquestion = None # none es como null
    current_uncertainty = gini(rows)
    num_features = len(rows[0]) -1 # number of columns or information types.
    
    for i in range (num_features): 
        values = set([row[i] for row in rows]) #este metodo guarda los valores y les quita los repetidos
        
        for j in values: 
            question = Question (i, j)
            true_rows, false_rows = partition(rows, question)
            
            if len(true_rows) == 0 or len(false_rows) == 0: 
                continue #si esta partición no hace nada, o no divide los datos, ir a la siguiente partición
                
            gain = information_gain(true_rows, false_rows, current_uncertainty)
            
            if gain > bestgain:
                bestgain = gain
                bestquestion = question
                
    return bestgain, bestquestion

# ahora, a construir el arbol
class Leaf:
    def __init__ (self, rows):
        self.predictions = class_counts(rows)
        
class Decision_Node:
    def __init__ (self, question, true_branch, false_branch):
        self.question = question
        self.true_branch = true_branch
        self.false_branch = false_branch
    
def build_tree(rows):
    gain, question = decide_partition(rows)
    
    if gain == 0:
        return Leaf(rows)
    
    true_rows, false_rows = partition(rows, question)
    
    true_branch = build_tree(true_rows)
    
    false_branch = build_tree(false_rows)

    return Decision_Node(question, true_branch, false_branch)

# ahora, cuando se tiene el arbol de decisión creado, se usa para clasifica nueva data.
    
def classify(row, node):
    if isinstance(node,Leaf):
        return node.predictions
    if node.question.match(row):
        return classify (row, node.true_branch)
    else:
        return classify (row, node.false_branch)
    
def print_tree(node, spacing=""):

    if isinstance(node, Leaf):
        print (spacing + "Predict", node.predictions)
        return

   
    print (spacing + str(node.question))

   
    print (spacing + '--> True:')
    print_tree(node.true_branch, spacing + "  ")

   
    print (spacing + '--> False:')
    print_tree(node.false_branch, spacing + "  ")
    
archivo = os.path.expanduser('~/Desktop/Datos proyecto/Datos0.csv')  
   
lecturaDeDatos(archivo)
my_tree = build_tree(data)

archivo2 = os.path.expanduser('~/Desktop/Datos proyecto/Test0.csv')
 
lecturaDeDatos(archivo2)
classify(data[0], my_tree)