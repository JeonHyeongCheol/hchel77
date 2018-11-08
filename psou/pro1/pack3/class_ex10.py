'''
Created on 2018. 11. 8.

클래스 오버라이딩(재정의)
'''

class Parent:
    def printData(self):
        pass
    
class Child1(Parent):
    def printData(self):
        print('Child1에서 오버라이드')
        
class Child2(Parent):
    def printData(self):
        print('Child2에서 오버라이딩')
        print('부모 메소드와 이름은 같으나 내용이 다른 메소드')
        
    def etc(self):
        print('Child2 고유 메소드')
        
c1 = Child1()
c1.printData()
print()
c2 = Child2()
c2.printData()

print('\n다형성---------------------------------------')
par = Parent()
par = c1
par.printData()
print()
par = c2
par.printData()
print()
abc = c1
abc.printData()
abc = c2
abc.printData()
abc.etc()

print()
plist = [c1, c2] # 클래스를 List에 넣을 수 있음.
for i in plist:
    i.printData() # 같은 메소드만 출력 가능!!!!!!!!! 다르면 for문 자체에서 돌릴 수 없음.