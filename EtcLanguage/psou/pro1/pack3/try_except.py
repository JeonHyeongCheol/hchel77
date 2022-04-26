'''
Created on 2018. 11. 9.

예외처리 : 파일, 네트워크, DB작업 ... 등 에서 예기치 않은 에러에 대처
'''

def divide(a, b):
    return a / b

print('이런 저런 작업을 하다가...')
# c = divide(5,0)
# prin(c)

try:
    c = divide(5, 2)
    #c = divide(5, 0)
    print(c)
    
    aa = [1, 2]
    print(aa[0])
    #print(aa[2])
    
    f = open('c:/aa.txt')
    
except ZeroDivisionError:
    print('0으로 나누지 맙시다.')
except IndexError as e:
    print('참조 범위 오류 :', e)
except Exception as e:
    print('에러 : ', e)
finally:
    print('에러와 상관없이 수행')
    
print('작업 계속')