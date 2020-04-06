## La complejidad de agregar un elementos en la posición n es de O (n) en el peor de los casos.


class Node:
    def __init__(self, actual, siguiente = None): #constructor
        self.actual = actual
        self.siguiente = siguiente
        
class ListaSimple():
    def __init__(self):
        self.first_Node = None
        self.size = 0
    
    def size(self):
        return self.size
    
    def get(self, index):
        if index < 0 or index >= self.size:
            raise Exception ("Index out of bound exception")
        else:
            EsteNodo = self.first_Node
       	    for j in range(index):
                   EsteNodo = EsteNodo.siguiente
                   return EsteNodo.actual

    def insertINDEX(self, element, index):
        if index == 0 and self.size==0:
            self.first_Node = Node (element)
        elif (self.size == 0 and index > 0) or index >self.size:
            raise Exception ("Index out of bound exception")
        else:
            EsteNodo = self.first_Node
            i = 0
            while i<index:
                EsteNodo = EsteNodo.siguiente
                i += 1
            EsteNodo.siguiente= Node (element, EsteNodo.siguiente.siguiente)
                    
    def insert (self, element):
        NuevoNodo = Node (element)
        if self.first_Node:
            EsteNodo = self.siguiente
            while (EsteNodo.siguiente):
                EsteNodo = EsteNodo.siguiente
            EsteNodo.siguiente = NuevoNodo
        else:
            self.first_Node = NuevoNodo
            
    def remove(self, index):
        if index > self.size or self.size == 0:
            raise Exception ("Index out of bound exception")
        else:
            EsteNodo = self.first_Node
            i = 0
            while i<index:
                EsteNodo = EsteNodo.siguiente
                i+= 1
            EsteNodo.siguiente = EsteNodo.siguiente.siguiente
   
    def contains(self, element):
        if (self.size == 0):
            return False
        else:
            EsteNodo = self.first_Node
            while (EsteNodo.siguiente != None):
                if (element == EsteNodo.actual):
                    return True
                    EsteNodo = EsteNodo.siguiente
                return False