'''
Created on 2018. 11. 6.

내장 함수
'''

print(sum([3,5,7])) # List 집합형 자료면 List, Tuple, Set에 왠만하면 넣어 줄 것.
print(sum((3,5,7))) # Tuple
print(sum({3,5,7})) # Set
print(bin(8)) # 2진수
print(int(1.7), float(5), str(5) + '오') # 정수, 실수, 문자열
print(round(1.2), round(1.7)) # 반올림
a = 10
b = eval('a + 5') # eval : 문자열을 변수로 변환 시킴..?
print(b)
print()

print(dir(__builtins__)) # 내장 함수 종류를 할 수 있음
print()

import math # 모듈명
print(math.pi) # Pie
print(math.ceil(1.2), math.ceil(1.6)) # 근사치 중 큰 값
print(math.floor(1.2), math.floor(1.6)) # 근사치 중 작은 값
print()

b_list = [True, 1, False]
print(all(b_list)) # all : 모두 참이면 참, 아니면 False
print(any(b_list)) # any : 하나가 참이면 참, 전부 거짓일 때 거짓
print()

b_list2 = [1,3,2,5,7,6]
re = all(a < 10 for a in b_list2) # 모든 숫자가 10 미만인지 확인
print('모든숫자가 10 미만이냐 ?', re)
re = all(a < 3 for a in b_list2) # 모든 숫자중 3 미만인지 확인
print('모든숫자중  3 미만이 있냐 ?', re)
print()

x = [10, 20, 30]
y = ['m', 'n']
for i in zip(x, y): # x,y에 대해 짝을 이루어줌.
    print(i)
    
