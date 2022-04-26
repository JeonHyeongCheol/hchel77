'''
Created on 2018. 11. 9.

추상 클래스
'''

from abc import *

class AbstractClass(metaclass = ABCMeta):
    
    @abstractmethod
    def abcMethod(self):
        pass
    
    # 추상클래스안에 일반메소드도 가질 수 있음.
    
    def normalMethood(self):
        print('추상 클래스 내의 일반 메소드')
        

# parent = AbstractClass() # 추상클래스라 인스턴스 할 수 없음.
# TypeError: Can't instantiate abstract class AbstractClass with abstract methods abcMethod.

class Child1(AbstractClass):
    name = "난 Child1"
    
    def abcMethod(self):
        print('추상 메소드를  Child1에서 오버라이드 함.') # 오버라이드.
        
        
class Child2(AbstractClass):
    
    def abcMethod(self):
        print('추상 메소드를 Child2에서 오버라이드 함')
        
    def normalMethood(self):
        print('추상 클래스 내의 일반 메소드를 오버라이드 함')
        
c1 = Child1()
print(c1.name)
c1.abcMethod()
c1.normalMethood() # 자식한테 없지만 부모꺼에는 있기 때문에 부를 수 있음.
print()

c2 = Child2()
c2.abcMethod()
c2.normalMethood()
print()

aa = c1
print(aa.name)
aa.abcMethod()
aa.normalMethood()
print()

bb = c2
bb.abcMethod()
bb.normalMethood()
