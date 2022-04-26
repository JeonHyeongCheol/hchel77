'''
Created on 2018. 11. 8.

다중 상속
'''

class Tiger:
    data = "호랑이 세상"
    
    def Cry(self):
        print('어흥')
        
    def Eat(self):
        print('맹수는 고기를 먹음')


class Lion:
    def Cry(self):
        print('으르렁')
        
    def Hobby(self):
        print('백수의 왕은 게임을 즐김')
        
class Liger1(Tiger, Lion):
    pass

li1 = Liger1()
print(li1.data)
li1.Cry()
li1.Eat()
li1.Hobby()

print()

class Liger2(Lion, Tiger):
    data = "라이거 만세"
    
    def Hobby(self):
        print('라이거는 초원걷기를 좋아함')
        
    def ShowHobby(self):
        self.Hobby()
        super().Hobby()
        
li2 = Liger2()
li2.Cry()
li2.ShowHobby()
print(li2.data)