'''
Created on 2018. 11. 1.

집합형 자료 :str - 문자들의 집합(문자 묶음) : 순서O, 값변경 X
'''

s = "sequence"
print(len(s), s.count('e')) # len : 문자열 길이, .count('문자') : 문자 갯수 찾아줌
print(s.find('e'), s.find('e', 3), s.rfind('e')) # python 인덱스 시작 0, find : 문자 값에 처음 인덱스 값 리턴, rfind : 오른쪽 부터, ('e', 3) : 인덱스 3부터 찾는 것
print(s.startswith('s')) # S로 시작되는 확인(bool 값 리턴)
print()

ss = 'mbc'
print('mbc', id(ss), id('mbc')) # 그냥 출력하든, 문자를 그냥 입력하든  주소값은 같음
ss = 'abc' # 새로운 객체의 주소로 치환
print(ss, id(ss))

print("슬라이싱(집합형 자료의 일부를 참조)--------------------------")
print(s)
print(s[0], s[2:4], s[:3], s[3:]) # 인덱스로 슬라이싱 할 수 있음
print(s[-1], s[-4:-1], s[-3:]) # -는 뒤에서 부터 시작
print(s[::2]) # 2씩 뛰어서 출력

# s[1] = 'p' # 에러 : 값 변경 불가

sss = ' kbs mbc'
print(sss)
print(sss.strip()) # 공백 제거
# sss.lstrip(), sss.rstrip() : l은 왼쪽, r은 오른쪽 공백 자르는 것
s4 = sss.split(sep=' ')
print(s4)
print(':'.join(s4)) # 자른 후 합치기
print()

a = 'Life is too short'
b = a.replace('Life', 'Your leg') # 문자 치환
print(b)