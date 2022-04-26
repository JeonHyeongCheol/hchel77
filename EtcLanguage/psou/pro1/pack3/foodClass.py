'''
Created on 2018. 11. 8.

냉장고에 들어갈 음식 객체
'''
class FoodData:
    def __init__(self, irum, expiry_date): # 포함관계에서는 생성사 __init__을 써야 함.
        self.irum = irum
        self.expiry_date = expiry_date