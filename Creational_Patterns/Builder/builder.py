from abc import ABC, abstractmethod
from typing import Optional

class Pizza:
    def __init__(self):
        self.size=None
        self.pepperoni=None
        self.cheese=None
        self.mushrooms=None
        
    def __str__(self):
        toppings=[]
        
        if self.cheese:
            toppings.append("cheese")
        if self.pepperoni:
            toppings.append("pepperoni")
        if self.mushrooms:
            toppings.append("mushrooms")
            
        toppings_str="".join(toppings) if toppings else "no toppings"
        return f"{self.size} with toppings {toppings_str}"

# Abstract Builder Interface
class PizzaBuilderInterface(ABC):
    
    @abstractmethod
    def set_size(self,size):
        pass
    
    @abstractmethod
    def add_cheese(self):
        pass
    
    @abstractmethod
    def add_pepperoni(self):
        pass
    
    @abstractmethod
    def add_mushrooms(self):
        pass
    
    @abstractmethod
    def build(self):
        pass
    
class PizzaBuilder(PizzaBuilderInterface):
    
    def __init__(self):
        self.pizza=Pizza()
    
    def set_size(self, size):
        self.pizza.size = size
        return self
        
    def add_cheese(self):
        self.pizza.cheese=True
        return self
    
    def add_pepperoni(self):
        self.pizza.pepperoni = True
        return self
    
    def add_mushrooms(self):
        self.pizza.mushrooms = True
        return self
    
    def build(self):
        return self.pizza
    

pizza1 = (PizzaBuilder()
             .set_size("Large")
             .add_cheese()
             .add_pepperoni()
             .build())
    
print(pizza1)  # Output: Large pizza with cheese, pepperoni
    
    # Build another pizza with different configuration
pizza2 = (PizzaBuilder()
             .set_size("Medium")
             .add_cheese()
             .add_mushrooms()
             .build())
    
print(pizza2) 