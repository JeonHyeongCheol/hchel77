'''
Created on 2018. 11. 7.

클래스 : OOP 지원 - 접근지정자 X, 메소드 오버로딩 X, interface X

'''
from builtins import isinstance
print('모듈의 멤버 중 클래스 연습')

# 원형클래스(맨 처음의 클래스) : 객체 생성이 되지 않은 맨 처음 클래스를 뜻함.
class TestClass: # 클래스 Header. 맨처음에 클래스를 만들면 prototype
    # 나머진 Body 영역.
    aa = 1 # 멤버 변수.
    
    def __init__(self): # 메소드, 메소드의 첫 인자는 Self, 자바에서의 This를 생각하면 됨.
        print('생성자')
    
    def __del__(self): # Python은 가비지컬렉터가 따로 있어 소멸자는 잘 사용하지 않음.
        print("소멸자")
        
    def printMsg(self): # 메소드
        name = '한국인'
        print(name)
        print("클래스내의 멤버 ", self.aa)
        
        
print(TestClass) # JAVA 처럼 new 없이 객체 생성.
print(TestClass.aa) # 원형 클래스 멤버를 호출.
#print(TestClass.printMsg()) # Self를 만족하지 못해 오류 남.
print()

test = TestClass() # 생성자 호출 후 객체 생성. 객체 주소값은 test에 들어감. 생성자 Overloading 안됨. Java에 TestClass test = new TestClass()와 동일.
print(id(test)) # 자동으로 생성자와 소멸자가 실행됨.
print(test.aa) # 클래스에 있는 멤버변수를 부름.
print()

#Bound Method Call
test.printMsg() # 객체변수(test)가 printMsg()에 괄호안에 들어감. 즉 printMsg(test)가 되는 것. test는 객체 주소 값을 들고 있음.
print()
# UnBound Methon Call
TestClass.printMsg(test) # 클래스로 메소드를 부를 때 객체변수를 꼭 주어야 함. 잘 사용 되지는 않음. 기본적으로 변수 설정 후 그 변수로 클래스 사용.
print()

print('클래스 타입 : ', isinstance(test, TestClass))
print()
print(id(test), id(TestClass))
print(type(test))