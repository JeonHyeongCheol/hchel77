'''
Created on 2018. 11. 8.

포함 : 로또 번호 출력
'''
import random

class LottoBall:
    def __init__(self, num):
        self.num = num
        
class LottoMachine:
    def __init__(self):
        self.ballList = []
        for i in range(1, 46):
            self.ballList.append(LottoBall(i)) # 포함관계
            
    def selectBall(self):
        for a in range(45):
            #print(self.ballList[a], end = ' ') # 섞기 전
            random.shuffle(self.ballList)
            #print(self.ballList[a], end = ' ') # 섞은 후
            return self.ballList[0:6] # 6개만 끄내기 위해..
            
class LottoUi:
    def __init__(self):
        self.machine = LottoMachine()
        
    def playLotto(self):
        input("로또를 수행하기 위해 아무키나 누르세요")
        selectBalls = self.machine.selectBall()
        for ball in selectBalls:
            print("%d"%(ball.num))
            
if __name__ == '__main__':
    LottoUi().playLotto()