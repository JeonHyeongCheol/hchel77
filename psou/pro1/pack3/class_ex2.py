'''
Created on 2018. 11. 7.

클래스 연습
'''
hi = "hello" # 전역변수

class Car:
    #pass # pass 하면 아무것도 없는 빈털털이 클래스
    
    handle = 1 # 멤버변수.
    speed = 0
    
    def __init__(self, speed, name): # 객체 들어오는 변수 설정. # self : 객체변수의 주소값임.
        self.speed = speed
        self.name = name # 별도의 객체 만들어짐. Car클래스에는 만들어지지 않음.
        
    def showData(self):
        km = '킬로미터' # 지역변수.
        msg = '속도' + str(self.speed) + km
        return msg
    
print(Car.handle, Car.speed) # 원형 클래스에 값이 있음.
# 원형클래스에는 멤버변수에 있는 값만 추가 시킬 수 있고, 원형클래스 타입을 이용하는 새로운 객체는 새로운 변수를 추가 할 수 있음.
#print(Car.name) # 하지만 원형 클래스에 name이 없기 때문에 오류 남.
    
car1 = Car(50, "tom")
print(car1.handle, car1.name, car1.speed) # 먼저 car1 클래스에 있는 객체 값을 먼저 확인 후 그 다음 원형 클래스의 값을 참조. 
car1.color = "검정"
print(car1.color) # car1 클래스에 color를 추가 시킴.
#print(Car.color) # 원형 클래스에 없기  때문에 오류.
print()

print('----------------------------------------------------------------------------------------')
car2 = Car(20, 'james')
print(car2.handle, car2.name, car2.speed)
#print(car2.color) # car2에는 컬러가 없음. car1에만 있음. 각 객체마다 변수가 다를 수 있음.
print()

print(id(Car), id(car1), id(car2))
print()
print(car1.__dict__) # 객체변수.__dict : 변수 객체의 대한 변수 값을 볼 수 있음.
print(car2.__dict__)
print()

print('car1 method : ' + car1.showData()) # return 값이 있기 때문에 print로 해야 함.

print('car2 method : ' + car2.showData())
print()

car1.speed = 77
car2.speed = 88
print('car1 method : ' , car1.showData())
print('car2 method : ' , car2.showData())
print('car1 speed : ' , car1.speed)
print('car2 speed : ' , car2.speed)
print('Car speed : ' , Car.speed)
