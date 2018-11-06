'''
Created on 2018. 11. 6.

클로저 : 내부함수의 멤버를 모듈에서 계속 참조
'''

def times(a, b):
    #pass # 함수는 만들었는데 내용 없을 때.
    c = a * b
    #print(c)
    return c
    
print(times(2,3))
kbs = times
print(kbs(2,3))

del times # 함수 삭제.
#print(times(2, 3))
print(kbs(2,3))

print('\nClosure')
def outer():
    count = 0
    def inner():
        nonlocal count
        count += 1
        return count
    #inner()
    return inner # closure : 내부 함수 객체를 반환. 값을 계속 가지고 있어 값이 증가됨.

add1 = outer()
print(add1, id(add1)) # inner 함수객체의 주소를 가짐.
print(add1())
print(add1())
print(add1())
print()

print('수랑 * 단가를 계산해 세금 결과 출력')
def outer2(tax):
    def inner2(su, dan): 
        amount = su * dan * tax
        return amount
    return inner2 # closure

r1 = outer2(0.1) # r1는 inner2의 주소를 가지고 있음. outer2 에 tax 값을 줌.
result = r1(5, 10000) # r1는 inner2 주소값을 가지고 있으므로 su, dan의 값을 주면 세금 계산 완료.
print(result)
result2 = r1(10,50000) # 다시 해도 일단 전에 적용되었던 0.1에 값을 유지한 상태로 계산
print(result2)
print()

r2 = outer2(0.15)
result3 = r2(5, 10000)
print(result3)