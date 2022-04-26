'''
Created on 2018. 11. 8.

상속
'''

class Person:
    say = "난 사람이야~"
    nai = '20'
    __kor = 59 # 앞에 __ 해주면 private가 됨.
    
    def __init__(self, nai):
        print('Person 생성자')
        self.nai = nai
        
    def printInfo(self):
        print('나이:{}, 이야기:{}'.format(self.nai, self.say))
        
    def hello(self):
        print('안녕')
        print('hello에서 kor : ', self.__kor)
        
    @staticmethod
    def sbs(tel):
        print('sbs_tel : ', tel) # 다른 멤버와 상관없이 
        # self.hello # 멤버와 상관없는 독립적 수행할 때 사용.(self와 상관없ㅇㅣ..)
        
    @classmethod # 프로테드 부를때..?
    def mbc(cls):
        print('mbc_', cls.say, cls.nai, cls.__kor)
        
p = Person('22')
p.printInfo()
p.hello()
print('**' * 20)

class Employee(Person):
    say = "일하는 동물" # 만들어진 객체에 변수 먼저 확인.
    subject = "부가"
    
    def __init__(self):
        print('Employee 생성자')
        
    def EprintInfo(self):
        #printInfo() 이렇게하면 모듈의 함수를 찾는 것.
        self.printInfo()
        super().printInfo()

        
e = Employee()
print(e.say, e.nai, e.subject)
e.printInfo()
e.EprintInfo()
print("**" * 20)

class Worker(Person):
    def __init__(self, nai):
        print('Worker 생성자')
        self.nai = nai
    
    def WprintInfo(self):
        super().printInfo()
        
w = Worker('30')
w.WprintInfo()

print("~~" * 20)
class Programmer(Worker):
    def __init__(self, nai):
        print("Programmer 생성자")
        #super().__init__(nai) # Bound
        Worker.__init__(self, nai) # UnBound
        
    def printInfo(self):
        print("오바라이딩 메소드")
    
    def WprintInfo(self):
        self.printInfo() # PerSon까지 감.
        #print('Worker에서 kor : ', self._kor)
        
pr = Programmer(36)
pr.WprintInfo()

print()
a = 5
print(type(a))
print(Person.__bases__) # 현재 클래스의 부모 클래스 타입 확인
print(Programmer.__bases__)

pr.sbs('111-1111')
Person.sbs('222-2222')
pr.mbc()
Person.mbc()