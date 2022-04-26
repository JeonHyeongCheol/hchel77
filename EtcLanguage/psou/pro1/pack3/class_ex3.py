'''
Created on 2018. 11. 7.

클래스 연습
'''

kor = 100

def abc():
    print('함수')

class My:
    kor = 90
    
    def abc(self):
        print('메소드')
    
    def show(self):
        #kor = 70
        # 원형 클래스 안에 있는 메소드는 지역변수 다음으로 글로발변수 참조. 멤버변수는 새로운 객체에 생성 되었을 때만 참조.
        print(kor) # 지역변수 값. 만약 지역변수 값이 없으면, 글로발 변수 값 참조.
        print(self.kor) # 생성객체 변수 값이 없으면 My에 멤버변수 값 참조.
        self.abc() # 멤버변수 값 참조.
        abc() # 글로발변수 값 참조.
        
my = My()
my.show()

