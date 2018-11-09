'''
Created on 2018. 11. 9.

기타 클래스 관련
'''
# class Singleton:
#     list = None
#     
# s1 = Singleton()
# s2 = Singleton()
# print(id(s1), id(s2))

class Singleton: # 싱글톤 패턴을 위한 원형 클래스
    inst = None
    def __new__(cls): # __new__ : 객체의 생성을 담당하는 메소드(Python System에서 가지고 있음).
        if cls.inst is None:
            cls.inst = object.__new__(cls)
        return cls.inst
    
    def aa(self):
        print('임의의 메소드')
        
        
class Sub(Singleton): # 싱글톤 메소드를 만든 후 상속 받으면 싱글톤으로 변함.
    pass

s1 = Sub()
s2 = Sub()
print(id(s1), id(s2))

s1.aa()
s2.aa()
print(id(s1.aa), id(s2.aa))

print('**' * 20)
class Test:
    data = "default"
    
i1 = Test()
i2 = Test()
print(i1.data, i2.data)
print()

Test.data = 'classData가 변경'
print(i1.data, i2.data)
print()

i1.__class__.data = '클래스 데이터가 Change' # __class__ : 자신을 생성한 클래스 객체 참조용 내장 속성. i1을 가지고 원형 클래스의 값을 변경.
print(i1.data, i2.data)

print()
i2.data = "i2의 데이터만 변경"
print(i1.data, i2.data)

print('-사용 가능한 멤버 고정 ----------------------------')
class Animal:
    __slots__ = ['name', 'age'] # 클래스 멤버변수 고정
    
    def printData(self):
        print(self.name, self.age)
        
m = Animal()
m.name = 'tiger'
m.age = 3
print(m.name + " " + str(m.age))

#m.eat = "동물" # slots는 파생클래스에 대해서 객체변수를 설정한거 말고는 사용 할 수 없게 만듬.
m.printData()