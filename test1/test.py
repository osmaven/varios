__author__ = 'oventura'
__author__ = 'oventura'

class Person:
    def __init__(self,gender,name):
        self.gender = gender
        self.name = name
    def display(self):
        print("You're a ", self.gender ,", and you're name is: ", self.name)

class Animals:
    def eat(self):
        print('I can eat')
    def talk(self):
        print('I can talk')

class Cat(Animals):
    def talk(self):
        print('meow')
    def move(self):
        print('I can move')
class Dog(Animals):
    pass

class Worker:
    name=""
    hours=0
    earned=0
    workrate=1
    overworked=0
    def __init__(self,name):
       self.name = name
       self.hours=0
       self.earned=0
       self.workrate=1
       self.overworked=1
    def __init__(self,name,workrate):
        self.name = name
        self.workrate=workrate

    def display(self):
        self.set_earned()
        print("You're a  is: ", self.name, " and work", self.hours," and earned ",self.earned,"based on your workrate" ,self.workrate ," whit this overworked result",self.overworked)
    def set_hour(self,hoursw):
        self.hours=hoursw
        self.set_overworked()
    def set_overworked(self):
        if (self.hours - globvar) * 1.5 > 0:
            self.overworked=(self.hours - globvar) * 1.5
        else:
            self.overworked=0

    def set_workrate(self,wkrate):
        self.workrate=wkrate
    def set_earned(self):
        self.earned=self.hours*self.workrate



globvar = 40
people = Worker('Alex',1)
people.display()
hours = input("write your hours")

people.set_hour(hours)
people.display()
