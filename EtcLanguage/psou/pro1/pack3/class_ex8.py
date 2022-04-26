'''
Created on 2018. 11. 8.

상속 : 자원의 재활용. 다형성을 구사
'''
# Python은 다중 상속 가능.
class Animal:
    def __init__(self):
        print('Animal 생성자')
        
    def move(self):
        print('움직이는 생물')
        
class Dog(Animal): # 상속
    def __init__(self):
        print('Dog 생성자')
        
    def my(self):
        print('나는  Dog이야~~~')
    
dog1 = Dog()
dog1.my()
dog1.move()
print()

class Horse(Animal):
    pass

horse = Horse()
horse.move(
    )
