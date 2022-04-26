'''
Created on 2018. 11. 8.

클래스의 포함관계
'''
from pack3.handleClass import PohamHandle

class PohamCar:
    turnShow = "정지"
    
    def __init__(self, ownerName):
        self.ownerName = ownerName
        self.handle = PohamHandle # PohamHandle 참조.
        
    def TurnHandle(self, q):
        if q > 0:
            self.turnShow = self.handle.rightTurn(q)
        elif q < 0:
            self.turnShow = self.handle.leftTurn(q)
        else:
            self.turnShow = "직진"
        
if __name__ == "__main__": 
    tom = PohamCar('tom')
    tom.TurnHandle(10)
    print(tom.ownerName + "의 회전량은 " + tom.turnShow + str(tom.handle.quantity))
    
    james = PohamCar('james')
    james.TurnHandle(-20)
    print(james.ownerName + "의 회전량은 " + james.turnShow + str(james.handle.quantity))
