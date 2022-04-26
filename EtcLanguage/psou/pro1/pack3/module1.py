'''
Created on 2018. 11. 7.

모듈 : 소스코드의 재사용을 가능하게 하며 모듈 단위로 관리된다.
'''
print(sum([1,3]))

import math
print(math.pi)

import sys
print('모듈경로 :', sys.path)
#sys.exit() # 프로그램의 강제 종료. 그 다음꺼 실행 안함.

import calendar
calendar.setfirstweekday(6) # 일요일이 0으로 설정.
calendar.prmonth(2018, 11)
del calendar

import os
print(os.getcwd()) # 현재 경로.
print(os.listdir('./'))  # 현재 파일명.

import random
print(random.random()) # 난수.

# 모듈에 멤버명을 바로 부를 수 있는 방법 2가지.
# 방법 1 : from 모듈명 import 멤버명 ← 으로 쓸 수 있는데 이렇게하면 멤버명을 그대로 사용 가능.
from random import randint
print(randint(1,10))

# 방법 2 : 메모리 낭비가 되지만 바로 사용 가능.
from random import * 
print(randrange(1,10))


print('종료')