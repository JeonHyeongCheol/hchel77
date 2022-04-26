'''
Created on 2018. 11. 8.

def는 define!

냉장고에 음식 담기
'''
# 포함관계의 다른 모습.

from pack3.foodClass import FoodData

class Fridge:
    isOpened = False
    foods =[]
    
    def open(self):
        self.isOpened = True
        print('냉장고 문 열기')
        
    def close(self):
        self.isOpened = False
        print('냉장고 문 닫기')
        
    def put(self, thing):
        if self.isOpened:
            self.foods.append(thing)
            print("냉장고 속에 음식이 들어감")
        else:
            print('냉장고 문이 닫혀 있어 음식을 담을 수 없음')
    
    def list(self):
        for f in self.foods:
            print('-', f.irum, f.expiry_date)
            
f = Fridge()
apple = FoodData('사과', '2018-12-31') # 포함관계 일종.
f.put(apple)
f.open()
f.put(apple)
f.close()

print()
f = Fridge()
cider = FoodData('사이다', '2019-12-31') # 포함관계 일종.
f.open()
f.put(cider
      )
f.close()