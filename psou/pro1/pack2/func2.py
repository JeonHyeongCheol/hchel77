'''
Created on 2018. 11. 6.

사용자 정의 함수
'''
# def 함수 정의
def DoFunc1():
    print('DoFunc1 Process')
    #return None # Python은 무조건 Return 값이 있음. 없으면 명시적으로 None이 됨.
    
def DoFunc2(arg1, arg2):
    re = arg1 + arg2
    return re
    
# 함수 호출
DoFunc1()
print('딴짓을 하다가...')
print(DoFunc1()) # 함수는 출력 할 수 있도록 만들어 줘야 함. 위에 같은 이유로..

print('딴짓을 하다가...')
print(DoFunc2(1, 2))
aa = DoFunc2('kbs', '9')
print(aa)
DoFunc3 = DoFunc2 # 주소 치환
print(id(DoFunc2), id(DoFunc3))
bb = DoFunc3('mbc', '11')
print(bb)
print()

def DoFunc4(arg1, arg2):
    imsi = arg1 + arg2
    if imsi % 2 == 1:
        return # return을 만나면 함수 탈출. 값이 아무것도 없기 때문에 None이 출력.
    else:
        return imsi
    

print(DoFunc4(1, 2))
print(DoFunc4(2, 2))
print('현재 파일의 객체 목록 :', globals())
print()

def Area_tri(a, b):
    c = a * b / 2
    Area_print(c) # 함수는 함수를 부를 수 있음.

def Area_print(c):
    print('삼각형의 면적은', c)
    
Area_tri(20, 30)

def swap(a, b): # 값을 바꿈
    return b, a # 리터값이 복수 개도 가능. 자바는 1개임 ㅠㅠ

a = 10; b = 20 # 여러개 설정 할 때는 ;(세미콜론) 사용 할 것.
print(swap(a, b))

# if 조건식에 함수 사용
def isodd(arg):
    return arg % 2 == 1

result = {x:x*x for x in range(11) if isodd(x)}
# x는 10까지 늘어나고 그 값을 가지고 isodd에 갔다옴. 홀수 일 경우에만 그값을 제곱 해줌.
print(result)

