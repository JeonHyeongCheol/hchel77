'''
Created on 2018. 11. 8.

다른 클래스의 부품으로 사용
'''

class PohamHandle:
    quantity = 0
    
    def leftTurn(self, quantity):
        self.quantity = quantity
        return "좌회전"
    
    def rightTurn(self, quantity):
        self.quantity = quantity
        return "우회전"