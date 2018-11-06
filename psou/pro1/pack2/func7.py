'''
Created on 2018. 11. 6.

장식자 : 
다른 함수를 감싼 함수다. 주 함수가 호출되면 그의 반환값이 장식자에게 전달 된다.
그러면 장식자는 포장된 함수로 교체하여 함수를 돌려준다. 
'''

def make2(fn):
    return lambda:"안녕 " + fn() 

def make1(fn):
    return lambda:"반가워 " + fn()

def hello():
    return "홍길동"

hi = make2(make1(hello)) # hi는 make2의 주소를 가짐.
print(hi())


@make2 # 함수 장식자, 함수를 정의한 후 @함수를 부르면 사용 가능. make1의 주소값을 가짐.
@make1 # hello2 주소값을 가짐.
def hello2():
    return "신기해"

print(hello2())
print()

hi2 = hello2() # 실행결과 치환. ()가 있으면 실행결과를 치환
print(hi2)
hi3 = hello2 # 함수주소 치환. ()가 없으면 주소를 치환.
print(hi3())
print()

# 재귀호출 : 함수가 자기자신을 호출.
def countDown(n):
    if n == 0:
        print('완료')
    else:
        print(n, end = ' ')
        countDown(n - 1) # 자기 자신 호출.
        
countDown(5)
print()

print('팩토리얼 , 5!')
def func(a):
    if a == 1: # a == 1이면 return 1 출력.
        return 1 # 마지막에 값에 1을 곱함.
    print(a)
    return a * func(a - 1) # 5 * 4, 4 * 3, 3 * 2 .....

re = func(5)
print('5! : ', re)