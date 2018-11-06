'''
Created on 2018. 11. 6.

변수의 생존범위
'''

player = "전국대표" # 전역변수 - 모듈의 어디서든 공유

def func():
    name = "홍길동" # 지역 변수 - 함수 내에서만 유효
    print(name, player)
    
print(player)
#print(name)

func()
print(func)

print("**" * 20)

# Local → Global 순으로 찾음.
# 여러번 해보기..안하면 지식이 로컬 되는 수가 있다...
a = 10; b = 20; c = 30
print('출력1) a:{}, b:{}, c:{}' .format(a, b, c))

def foo():
    a = 40  # foo 수준의 a, b의 지역변수
    b = 50
    def bar():
        #c = 70 # bar 수준의 c의 지역변수
        global c # global이라고 쓰면 전역변수로 바뀜. 이 라인 이후에 실행되는 코드만 먹힘. 그전에 똑같은 변수로 있으면 그전에 코드는 전껄로 먹힘.
        nonlocal b # bar 지역변수는 아니지만 그 상위에 있는 함수에 지역변수가 됨.
        print('출력2) a:{}, b:{}, c:{}' .format(a, b, c))
        c = 70 # 실행하면서 객체를 만들지 않음. 실행하면서 한번 읽기 때문에 지역을 참조함. 하지만 밑에 있어서 참조 할 수 없어 오류남.
        b = 90
    bar()
    a = 80 # 지역 변수
    print('출력3) a:{}, b:{}, c:{}' .format(a, b, c))
    
foo()
print('출력4) a:{}, b:{}, c:{}' .format(a, b, c))
print()

g = 1

def f1():
    global g
    a = g
    g = 2 # 없을 땐 문제가 없지만 있는 순간 지역변수로 됨.
    return a

print(f1())

