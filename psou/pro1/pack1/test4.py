'''
Created on 2018. 11. 5.

집합형 자료형 : list - 순서 O, 수정 O
'''
a = [1, 2, 3]
b = [10, a, 20.5, True, '문자열']
print(a, id(a))
print(b, id(b))
print()

f = ['엄마', '아빠', '나', '여동생']
print(f, len(f), f[2])
f.append('남동생') # 추가
f.remove('나') # 삭제
f.insert(0, '할아버지') # 추가
f.extend(['삼촌', '조카']) # 추가
f += ['이모', '고모'] # 추가
print(f)
print(f.index('남동생')) # 값 찾기.
print('엄마' in f, '나' in f) # True, false.
# Python에서 많이쓰는 것이 자료형.

print('\n 슬라이싱------')
aa = [1,2,3,4,5]
print(aa[0], ' ', aa[0:3], aa[-1], aa[::2])

aa = [1,2,3,['a', 'b', 'c'], 4, 5]
aa[0] = 100 # 수정가능.
print(aa)
aa[3][0] = 'good'
print(aa)
print(aa[0], aa[3])
print(aa[3], aa[:2])
print(aa[3][2])
print()

aa.remove(4) # 값에 의한 삭제.
del aa[4] # 순서에 의한 삭제.
print(aa)

aa[3].remove('c')
del aa[3][0]
print()

aa2 = [3,1,5,2,4]
print(aa2)
aa2.sort()
print(aa2) # sort한 것으로 데이터값이 바뀜.
aa2.sort(reverse=True) # 내림차순.
print(aa2)
aa3 = sorted(aa2)
print(aa3)
print()

aa4 = ['123', '34', '234']
aa4.sort() # sorted는 치환 가능. 그냥은 그 변수에서 해줘야 함.
print(aa4) # 문자열이기 때문에 이렇게 1,2,3 순으로 나옴.
aa4.sort(key = int) # 키 값을 줌으로써 문자열을 int로 정렬함.
print(aa4)
print()

print('/n복사')
name = ['tom', 'james', 'oscar']
name2 = name # 주소치환 - 얕은 복사, 주소가 같음.
print(name, name2, id(name), id(name2))

name[0] = 'john'
print(name,name2)

import copy
name3 = copy.deepcopy(name) # 깊은 복사, 주소값 다름.
name[0] = 'page'
print(name, name3, id(name), id(name3))
print()

sbs = [10, 20, 30]
sbs.append(40) # Stack : LIFO
print(sbs)
sbs.pop() # .pop()을 이용해 값을 뺄 수 있음.
sbs.pop()
print(sbs)

sbs = [10, 20, 30]
sbs.append(40) # queue : FIFO
sbs.pop()
sbs.pop()
print(sbs)