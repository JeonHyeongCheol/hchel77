'''
Created on 2018. 11. 1.

@author: kitcoop

큰 따옴표, 작은따옴표  3개는 여러 줄 주석
'''

# 한 줄 주석.

v1 = '안녕 파이썬'
v1 = 5
print(v1)

# 띄어쓰기하면 블럭 생김

v2 = 20.5
v3 = v2 # 주소 치환
print(v1, v2, v3)

print(id(v1), id(v2), id(v3)) # id : 주소 확인

print(v1 is v2, v1 == v2) # is : 주소 값을 비교해 boolean으로 리턴, == 는 값을 비교
print(v2 is v3, v2 == v3)
print()

print(1000 is 10 ** 3) # 주소 값 비교
print(1000 == 10 ** 3) # 값 비교
print(1000, id(1000))
print(10 ** 3, id(10 ** 3))
print()

A = 1; a = 2
print(A, a, id(A), id(a)) # 대문자, 소문자 구분함

import keyword
# 키워드 목록
print('키워드 목록 : \n', keyword.kwlist) # Keyword 목록 : 변수명으로 쓸 수 없음
print()

print(10, oct(10), hex(10), bin(10)) # oct(옥타) : 8진수, hex(헥사) : 16진수
print(10, 0o12, 0xa, 0b1010) # Desimal(데시말)
print()

print('type(자료형)')
print(3, type(3)) # 정수
print(3.5, type(3.5)) # 실수
print(3 + 4j, type(3 + 4j)) # 복소수
print(True, type(True)) # bool
print('abc', type('abc')) # 문자열
print()

print((1,), type((1,)))
print([1], type([1]))
print({1}, type({1}))
print({'key':1}, type({'key':1}))

print()
a = 2
print(isinstance(a, int)) # isinstance : 타입 확인시
print(isinstance(a, float))

