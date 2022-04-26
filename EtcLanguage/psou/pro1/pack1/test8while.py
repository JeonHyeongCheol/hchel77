'''
Created on 2018. 11. 5.

while
'''
from builtins import int

a = 1
while a <= 5:
    print(a, end = ' ')
    a += 1

print()

colors = ['r', 'g', 'b']
print(colors[0])
a = 0

while a < len(colors): # color 크기 만큼 돎. 
    print(colors[a], end = ' ') # 값을 출력 해줌.
    a += 1

print()

while colors:
    print(colors.pop()) # pop은 추출. FIFO 방식.

print(len(colors)) # 값을 다 빼면 크기는 0이 됨.

print()

a = 0
while a < 10:
    a += 1
    if a == 5: continue
    if a == 7: break
    print(a)
else: 
    print('정상 종료 시 : while 수행') # 비정상 종료시 수행 하지 않음.

print('while 수행 후 : %d'%(a,))

print()

while True:
    a = int(input("확인할 숫자:"))
    if a == 0:
        print('종료')
        break
    elif a % 2 == 0:
        print('짝수')
        continue
    elif a % 2 == 1:
        print('홀수')
        continue