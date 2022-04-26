'''
Created on 2018. 11. 5.

조건 판단문 if
'''

var = 2

# 들여쓰기 중요
if var >= 3:
    print('크구나')
    print('참')
else:
    print('거짓')

print('end1')


jumsu = 80

if jumsu >= 90:
    print('우수')
elif jumsu >= 70:
    print('보통')
else:
    print('저조')

print()

# python 입력받기 : input
#jumsu = int(input('점수입력 : ')) # int(), str()
# Python만에 장점!!!
if 90 <= jumsu <= 100:
    print('우수')
elif 70 <= jumsu <= 90:
    print('보통')
else:
    print('저조')

print()
    
names = ['tom', 'james', 'oscar']
if 'oscar' in names: # List에 들어있는 문자열 비교 가능.
    print('OK')
else:
    print('Fail')

print()

a = 3

if a > 5:
    re = a * 2
else:
    re = a + 2
    
print(re)

re = a * 2 if a > 5 else a + 2 # 한줄로 적용 가능
print(re)

a = 3
print((a + 2, a * 2)[a > 5]) # 조건이 뒤에 걸림. 조건이 참이면 1, 거짓이면 0, 튜플에 속성을 이용해 0, 1의 값을 리턴 할 수 있음.