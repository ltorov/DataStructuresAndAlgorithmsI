#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
@author: LuisaToroVillegas & GregorioPérezBernal
This code reads certain data to create a decision tree.
Then with this tree, it's able to predict future data with some certainty.

Uses the following webpage to graph the tree:
    http://www.webgraphviz.com
"""
import csv
import os
import cProfile

"READING AND STORING DATASET"
#O(n)
def importData (file):
    """Reads and stores the given dataset into a matrix
    
    :param file: the csv file containing the data.
    :return: a triple of dataFinal which is the matrix, usedRows an integer of the length of dataFinal and an array called labels
    """
    with open(file, encoding = 'utf-8') as filecsv: #encoding es para que reconozca cualquier caracter
        data = [0]* 135001
        lines = csv.reader(filecsv, delimiter=';') #delimiter es el que lo separa por comas
        usedRows = 0
        for line in lines:
            data[usedRows] = line
            usedRows = usedRows + 1
        usedRows -= 1
        dataFinal = [0]*usedRows
        labels = data[0]
        for i in range (usedRows):
            dataFinal[i] = data[i+1]
        return dataFinal, usedRows, labels
    
"AUXILIARY METHODS"
#O(1)
def is_number(value):
    """ Determines wether a given value is numeric.
    
    :param value : a value of any type
    :return : returns a boolean
    """
    try:
        float(value)
        return True
    except ValueError:
        pass
    try:
        import unicodedata
        unicodedata.numeric(value)
        return True
    except (TypeError, ValueError):
        pass
 
    return False
#O(n)
def classCounts(rows):
    """ Counts how many success cases and failure cases there are and stores it into a dictionary
    
    :param rows: the dataset organized into a matrix
    :return: a dictionary
    """
    dictionary = {}  # creates a dictionary
    for row in rows:
        dato = row[-1]   #row[-1] is the last object of the row
        if dato not in dictionary:  #creates new keys
            dictionary[dato] = 0
        dictionary[dato] += 1 #counts every time said key appears.
    return dictionary
#O(n)
def countSuccess (rows):
    """ Counts how many success cases and failure cases there are, stores it into a dictionary, and returns the number of successes.
    
    :param rows: the dataset organized into a matrix
    :return: success which is an integer that counts the amount of success cases in the matrix.
    """
    dictionary = {}  # creates a dictionary
    for row in rows:
        dato = row[-1]   #row[-1] is the last object of the row
        if dato not in dictionary:  #creates new keys
            dictionary[dato] = 0
        dictionary[dato] += 1 #counts every time said key appears.
    success = 0
    for data in dictionary:
        if (int(data) == 1):
            success = int(dictionary[data]) #counts every time the key "1" or success appears.
    return success
#O(n)
def bestValue (rows, columnNum):
    """ Counts how many success cases there are according to a given value a column can take, stores it into a dictionary, and returns the value that has the most success cases.

    :param rows: the dataset organized into a matrix
    :param columnNum : the column number
    :return: bestValue which may be any given value type
    """
    dictionary = {} #creates a dictionary
    for row in rows:
        dato = row[columnNum] # stores all possible values a given column can take
        if dato not in dictionary:
            dictionary [dato] = 0
        dictionary [dato] += int(row[-1])
    bestValue = None
    bestSuccess = 0
    for data in dictionary: # determines which is the value that has most success cases.
        if (dictionary[data] >= bestSuccess): 
            bestSuccess = dictionary[data]
            bestValue = data
    return bestValue
    
"DECISION METHODS"
    
class Question:
    """ An abstract object type which contains the questions or labels and the given answers without repetition. Ignores caps.
    """
    #O(1)
    def __init__ (self, column, value):
        """ Construct a new Question object
        
        :param column: the column number
        :param value: a value of any given type
        """
        self.column = column
        self.value = value
    #O(1)   
    def match (self, row):
        """ Determines wether to treat a value as a number (>=) or as a string (==) and says wether the value in the question meets that parameter.
        
        :param row: a row or subject of the dataset
        :return: returns wether a row or subject meets a certain condition.
        """
        value = row[self.column] # retrieves the value in the given column of the row
        if isinstance(value, int) or isinstance(value, float) or is_number(value): 
            return value >= self.value  #if the value is a number
        else: 
            return value == self.value #if the value is a string
        
#O(1)
    def toString (self):
        """ Organizes the Question data into a string.
        
        :return: triple of parameter or column number in a string, comparison and value
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
#O(n)
def partition(rows, question):
    """Splits the matrix according to a given question, into a true matrix and a false matrix.

    :param rows: the dataset organized into a matrix
    :param question: an object of type Question
    :return: true_rows and false_rows, which are created by the matrix being divided into two matrixes according to the given condition
    """
    numtruerows = 0
    for row in rows: #counts the number of rows that follow a given question or condition
        if question.match(row):
            numtruerows = numtruerows +1
    numfalserows = len(rows) - numtruerows       
    true_rows = [0] * numtruerows #creates a new matrix of the size n*m, with n the rows that follow the condition and m the columns
    false_rows = [0] * numfalserows #creates a new matrix of the size n*m, with n the rows that don't follow the condition and m the columns
    j = 0
    k = 0
    for row in rows:
        if question.match(row):
            true_rows[j] = row #inputs the rows that apply to the true matrix
            j = j+1
        else:
            false_rows[k] = row #inputs the rows that apply to the false matrix
            k=k+1
    return true_rows, false_rows  
#O(1)      
def gini (rows):
    """Calculates the gini impurity of a given dataset.

    :param rows: the dataset organized into a matrix
    :return: gini impurity
    """
    counts = classCounts(rows) #classCounts returns a dictionary with the number of successes and the number of failures.
    impurity = 1
    for i in counts: # counts has two keys, the loop is done twice.
        prob = counts[i]/ float(len(rows))
        impurity -= prob**2 # ** is for exponents.
    return impurity
#O(1)
def informationGain (left, right, current_uncertainty):
    """Calculates the information gain of a given question.
   
    :param left: a matrix of the rows that follow the condition.
    :param right: a matrix of the rows that don't follow the condition.
    :param current_uncertainty: the gini of the decision node which split the data into the two matrices.
    :return: infoGain which is the information gain of a given question.
    """
    a = float(len(left))/(len(left)+len(right))
    infoGain = current_uncertainty - a * gini(left) - (1 - a) * gini(right)
    return infoGain
#O(n^2)
def decidePartition (rows, labels, questionsused):
    """Tests all the possible partition by all the possible questions and decides which has more information gain.

    :param rows: the dataset organized into a matrix
    :param labels: an array containing the data labels or column headers
    :param questionsused: an array of the questions previously used
    :return: a triple of bestgain which is the information gain of the question decided to use to split the data, bestquestion which is the question decided, and questionsused an array of the questions previously used on the branch.
    """
    bestgain = 0
    bestquestion = None
    currentUncertainty = gini(rows)
    numFeatures = len(labels) -1 # number of labels
    
    for i in range(1, numFeatures): #loops through every columns
        value = bestValue(rows, i) #calculates the value that has most success cases.
        question = Question (i, value) #creates a question with the column and the value.
        if question in questionsused: #if the question has already been used in the branch, it skips it.
            continue
        truerows, falserows = partition(rows, question) #splits the value with the question.
        gain = informationGain(truerows, falserows, currentUncertainty) #calculates the information gain of the partition
        if gain >= bestgain: 
            bestgain = gain
            bestquestion = question
    if bestquestion!= None: #if a question is indeed created and used to split the data, it is stores in the array of used questions.
        questionsused.append(bestquestion)    
    return bestgain, bestquestion, questionsused

"TREE CONSTRUCTION METHODS"
    
class Tree:
    """An object type which splits the dataset and builds a tree
    """
    #O(2^n)
    def __init__(self, rows, labels, questionsused, limit = 7):
        """ Construct a new Tree object recursively that creates the tree by creating nodes and leafs, connected throught "children".
       
        :param rows: the dataset organized into a matrix
        :param labels: an array containing the data labels or column headers
        :param questionsused: an array of the questions previously used
        :param limit: recursion depth.
        """
        self.labels = labels #an array containing the column headers or labels.
        self.questionsused = questionsused #an array that stores the questions used in a branch to avoid repetition
        self.limit = limit #an integer that controls the recursion depth
        self.gain, self.question, self.questionsused = decidePartition(rows, labels, self.questionsused) 
        if self.gain == 0 or self.limit == 0: #the leafs, created when no question can improve the gini or when the recursion depth is reached.
            self.rows = rows
            self.trueSon = None #its a leaf so it has no "children"
            self.falseSon = None
            self.prediction = prediction(rows) #the probability of success.
            self.myString = Tree.generateString(self) #creates a string to print the gini index and probability of success of that leaf.
        else:     
            true_rows, false_rows = partition(rows, self.question)
            self.trueSon = Tree(true_rows, self.labels, questionsused, limit-1) #the true child, or child that abides the condition
            self.falseSon = Tree(false_rows, self.labels, questionsused, limit-1) #the false child, or child that doesn't abide the condition
            self.myString = Tree.generateString(self)  # creates a string to print the tree.
            self.prediction = 0
    #O(1)   
    def generateString(self):
        """ Organizes the tree into strings to print it and print the connection between nodes. Leaves are printed with their given gini index and probability of success. 
        According to the probability of success, the leaves are printed with a color scale.
        Red for failure, yellow for more than 50%, green for more than 70% and blue for more than 90%.
        """
        parameter, comparison, values = self.question.toString() #stores the question of the "father" into three strings
        parameterString = self.labels[int(parameter)] #translates the column into a column header or label
        children = [self.trueSon, self.falseSon] #array of the two children
        for child in children: # loops through the two children to avoid repetition in code.
            if (child != None):
                parameterChild, comparisonChild, valuesChild = child.question.toString() #stores the question of the "child" into three strings
                parameterChildString = self.labels[int(parameterChild)]
                if (child == self.trueSon): #gives the branch or connection representation a name
                    branch = "[ label = \"True\" ]" 
                else:
                    branch = "[ label = \"False\" ]"
                string = "\"" + parameterString + comparison + values + "\"" +  " -> " + "\"" + parameterChildString + comparisonChild + valuesChild + "\"" + branch
                #print(string)
        color = "[color = orangered]" #color for failure, red.
        if self.trueSon == None or self.falseSon == None: #if its a leaf.
            if self.prediction >= 50:
                color = "[color=gold1]" #yellow
            if self.prediction >= 70: 
                color = "[color=greenyellow]" #green
            if self.prediction >= 90:
                color = "[color=\"0.650 0.200 1.000\"]" #blue
            
            string = "\"" + parameterString + comparison + values + "\"" + " -> " + "\"" + " Gini: " + str(round(gini(self.rows), 2)) + ", Probability of success : " + str(round(self.prediction, 2)) + "%" +  "\"" 
            colorString = "\"" + " Gini: " + str(round(gini(self.rows), 2)) + ", Probability of success : " + str(round(self.prediction, 2)) + "%" +  "\"" + color
            #print (string)
            #print(colorString)
#O(n)
def prediction(rows):
    """ Calculates the probability of success with three decimal points.

    :param rows: the dataset organized into a matrix
    :return: prediction which is the probabily of success of any given leaf or Tree object with infogain == 0 or that has reached its recursion depth.
    """
    dictionary = classCounts(rows)
    success = 0
    fail = 0
    if '1' in dictionary:
        success = dictionary ['1']
    if '0' in dictionary:
        fail = dictionary ['0']
    total = success + fail
    prediction = round((success / total),5) *100 #determines decimal points.
    return prediction
#O(m)       
def classify(tree, row):
    """A recursive method that uses the decision tree to test each person

    :param tree: the tree build with the training data
    :param row: the dataset to be tested organized into a matrix.
    return: 1 or 0 according to whether the prediction is successful or not.
    """
    if tree.gain == 0 or tree.limit == 0: #if the node is a leaf, stopping condition
        if tree.prediction >= 50: #if the probability of success is more than 50%, then its considered as successfull.
            string = str(round (tree.prediction, 2)) +  "Successfull"
            #print(string)
            return 1
        else: #else, its not successfull.
            string =  str(round(tree.prediction, 2)) +  "Unsuccessfull"
            #print(string)
            return 0
    if tree.question.match(row): #if its a decision node, then determine wether the row or student meets the condition of the question.
        return classify (tree.trueSon, row) #recursion if the row meets the condition
    else:
        return classify (tree.falseSon, row) #recursion if the row doesn't meet the condition
#O(n*m)
def runClassify(tree, dataTest):
    """Loops through all the test dataset and calculates the accuracy of the tree.
    
    :param tree: the tree build with the training data
    :param dataTest: the testing dataset organized into a matrix
    :return: the accuracy of the tree used to predict
    """
    correct = 0
    total = 0
    for row in dataTest:
        prediction = int(classify (tree, row))
        actual = int(row[-1])
        if prediction == actual:
            correct += 1
        total += 1
    percentageAccurate = correct / total * 100
    return percentageAccurate   

#O(n)
def errorMatrix(tree, dataTest):
    """Goes through the results and calculates the Error matrix, showing how the prediction data is distributed.
    
    :param tree: the tree build with the training data
    :param dataTest: the testing dataset organized into a matrix
    """
    eMatrix = [[0,0],[0,0]]
    for row in dataTest:
         prediction = int(classify (tree, row))
         actual = int(row[-1])
         if prediction == 0 and actual == 0:
             eMatrix[1][1] += 1
         if prediction == 0 and actual == 1:
             eMatrix[1][0] += 1
         if prediction == 1 and actual == 0:
             eMatrix[0][1] += 1
         if prediction == 1 and actual == 0:
             eMatrix[0][0] += 1
    print (eMatrix)            

"READ AND IMPORT DATASETS"

archivoTrain = os.path.expanduser('datos5.csv')  
archivoTest = os.path.expanduser('test4.csv')
data,numFilas,labels = importData(archivoTrain)
dataTest, numFilasTest, labelsTest = importData(archivoTest)

"BUILD TREE"

questionsused = []
print("ratio = fill;")
print ("node [style=filled];")
print("\"Probability < 50% \" [color=salmon2]")
print("\"Probability >= 50 % \" [color=darkorange1]")
print ("\"Probability >= 70% \" [color=greenyellow]")
print ("\"Probability >= 90% \" [color=\"0.650 0.200 1.000\"]")

tree = Tree(data, labels, questionsused)

"CLASSIFY NEW DATA"
#runClassify(tree, dataTest)
#errorMatrix (tree, dataTest)

"TESTING TIME"

#cProfile.run('importData(archivoTrain)')
#cProfile.run('Tree(data, labels, questionsused)')
#cProfile.run('runClassify(tree,dataTest)')  