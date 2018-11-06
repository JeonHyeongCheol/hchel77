'''
Created on 2018. 11. 6.

일급함수 : 함수 내에 함수 선언, 인자로 함수 전달, 반환값이 함수
'''
from builtins import filter

def func1(a, b):
    return a + b

func2 = func1
print(func2(3, 4))
print()

def func3(func): # func을 받음
    def func4(): 
        print('나는 내부함수~')
    func4() # func 출력
    return func # 받은 func의 값도 return(출력)

mbc = func3(func1) # 함수안에 함수를 씀. mbc는 func1를 가짐.
print(mbc(3,4))
print(id(func2), id(mbc)) # func1를 받는 func2, mbc는 주소 값이 같음.
print()

print('Lambda - 이름이 없는 한 줄 짜리 함수')
# 형식 : lambda 인자 ,,, : 표현식 // return 명령문 없이 결과를 반환

# 예전에 썼던 방식.
def hap(x, y):
    return x + y

print(hap(1, 2))
print()

# Lambda 방식
print((lambda x,y:x+y)(1,2))
g = lambda x, y: x * y
print(g(3,4))

print()
kbs = lambda a, su = 10: a + su
print(kbs(5))
print(kbs(5, 10))

sbs = lambda a, *tu, **di:print(a, tu, di)
sbs(1,2,3,4,5,m = 6, n = 7)
print()
li = [lambda a, b : a + b, lambda a, b : a * b] # 여러개의 형식을 만들어놓고 인덱스를 설정해 사용 가능.
print(li[0](3,4))
print(li[1](3,4))
print()

#filter(함수, 집합자료)
print(list(filter(lambda a:a < 5, range(10))))
print(list(filter(lambda a:a % 2, range(10)))) # 나머지를 리턴하는데 0는 false, 1는 true