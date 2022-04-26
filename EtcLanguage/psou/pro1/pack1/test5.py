'''
Created on 2018. 11. 5.

# tuple : List와 유사, 읽기 전용, 수정 X, 검색 속도 빠름.
'''
#t = 'a', 'b', 'c', 'd'
t = ('a', 'b', 'c', 'd')
print(t, len(t), t.count('a'), t.index('b'))
print()

p = (1, 2, 3)
#p[0] =  10 # 'tuple' object does not support item assignment, 튜플은 수정 불가능.
print(p)

q = list(p) # 형변환
q[0] = 10
p = tuple(q)
print(q)
print()

print('\n값 교환')
t1 = 10, 20
a, b = t1
b, a = a, b
t2 = a, b
print(t2)

print('***' * 10)
# set : 순서 X, 중복 X
a = {1, 2, 3, 1}
print(a, ' ', len(a)) # 중복값 사라짐.
#print(a[0]) #  'set' object does not support indexing, set은 인덱싱 지원 안함.
b = {3, 4}
print(a.union(b)) # 합집합
print(a.intersection(b)) # 교집합
print(a - b, a | b, a & b) # 차, 합, 교집합
b.add(5)
b.update({5, 6, 7}) # Set Type
b.update([8, 9]) # List Type
b.update((10, 11)) # Tuple Type
# b.update((10,)) # 튜플로 하나의 값을 추가하려면 뒤에 ,를 찍어줘야 됨. 안그러면 Err.
print(b)
print()

b.discard(7) # 값 삭제
b.remove(8) # 값 삭제
# 다시 삭제시 문제
b.discard(7) # 해당 값 없으면 통과(실행)
# b.remove(8) # 해당 값 없으면 에러(실행 멈춤)
print(b)
print()

li = [1,2,2,3,1,4]
s = set(li) # Set으로 중복 제거
li = list(s) # 다시 List 변환
print(li)

print('\ndict type - key:value')
mydic = dict(k1 = 1, k2 = 'abc', k3 = 5.6)
print(mydic) # key, value 형식이므로 슬라이싱 안됨.
dic = {'파이썬':'뱀','자바':'커피', '스프링':'용수철'} # json type 처리 할 때 많이 사용.
print(dic)
print(len(dic))
print(dic['자바'])
print(dic.get('자바')) # get으로 찾을 수 있음.
#print(dic['커피']) # Err, key에 의한 검색. 값으로 찾을 수 없음.
#print(dic[0]) # Err, 순서 X
dic['오라클'] = '예언자' # dict 값 추가
print(dic)
print('오라클' in dic) # 키 값이 있는지 없는지 확인. True
del dic['오라클'] # 삭제
print('오라클' in dic) # 키 값이 있는지 없는지 확인. False
print(dic)
print(dic.keys()) # 키 값을 따로 볼 수 있음.
print(dic.values()) # 값을 따로 볼 수 있음.
#dic.clear() # 전체 삭제
print(dic)