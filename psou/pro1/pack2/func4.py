'''
Created on 2018. 11. 6.

인수 키워드 매핑
'''


def ShowGugu(start = 1, end = 5): #초기치 값을 줄 수 있음. 초기 값은 함수 부를 때 수정 가능.
    for dan in range(start, end + 1):
        print(str(dan) + '단 출력', end = ' ')
        
ShowGugu(2, 3)
print()
ShowGugu(2) # 2단 부터 시작
print()
ShowGugu(start = 2, end = 3)
print()
ShowGugu(end = 3, start = 2)
print()
ShowGugu(3, end = 6)
# ShowGugu(start = 3, 6) # 이런식으로는 안됨. 두번째 인자는 상수가 들어갈 수 없음.
# ShowGugu(end = 2, 6) # 이런식으로는 안됨.

print('\n\n가변인수 처리 -----------------------')
def func1(*ar): # *을 사용하면 인자값을 여러개 받을 수 있음. 패킹 문자는 하나, 아니면 뒤에 있어야 함.
    print(ar)
    
func1('a')
func1('a', 'b')
func1('a', 'b', 'c')
func1()

print()

def func2(a, *ar): # O
#def func2(*a, ar): # X
    print(a)
    print(ar)

func2('a')
func2('a', 'b')
func2('a', 'b', 'c')

print()

def func3(w, h, **etc): # ** : dict Type과 매핑
    print('몸무게{}, 키{}' .format(w, h))
    print(etc)
    
func3(66, 177, irum='홍길동', nai=33) # dict 값은 괄호하지말고 그냥 변수에 값을 쓰면 됨.
#func3(66, 177, {irum='홍길동'})
func3(66, 177, irum='고길동')
print()
def func4(a, *b, **c):
    print(a)
    print(b)
    print(c)
    
func4(1, 2)
func4(1, 2, 3, 4, 5)
func4(1, 2, m = 3, n = 4)
