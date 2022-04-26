'''
Created on 2018. 11. 7.

사용자 정의 모듈
'''

print('이런거 저런거 하다가')

import pack3.mymod1
print(pack3.mymod1.__file__) # 파일 경로명 보여줌.

list1 = [1, 3]
list2 = [1, 2]
pack3.mymod1.listHap(list1, list2) # 만든 모듈을 import하여 사용.
print(pack3.mymod1.tot)

print()
from pack3 import mymod1
mymod1.kbs()

from pack3.mymod1 import sbs
sbs()
print()

#print(dir())
if __name__ == '__main__': # 모든 모듈의 최상위 모듈로 시작 할 수 있음. 여기가 main인 것을 만족하기 때문에 뜸.
        print('응용 프로그램의 시작 - 최상위 모듈')
print()
        
print('패키지가 다른 경우 - Test')
import test.mymod2
result = test.mymod2.hap(10, 5)
print("합은", result)

from test.mymod2 import cha
print("차는", cha(10, 5))
print()

# Python Libraries 안에 어딘가에만 있어도 무조건 import 가능!
print("C:\ProgramData\Anaconda3\Lib")
print('폴더가 다른 경우')
import mymod3
print('곱하기', mymod3.gop(10, 5))

from mymod3 import nanugi
print('나누기', nanugi(10, 5))

# 그래픽 모듈
from turtle import *
p = Pen()
p.color('red', 'yellow')
p.begin_fill()
while True:
    p.forward(200)
    p.left(170)
    if abs(p.pos()) < 1:
        break
p.end_fill()
done()

# game_ex.py
# https://www.lfd.uci.edu/~gohlke/pythonlibs/ : 파이썬 하는 사람들은 이사이트를 꼭 알아둘 것. 모듈 다운 받을 때 쓰는 사이트
# 커맨드에서 다운 받은 경로로 이동 한 후  [ pip install 설치 할 모듈명 ] 쓰면 알아서 설치 됨.

# news_read.py
# 뉴스 읽기(뉴스만 됨!)
# 커맨드창에서 pip install newspaper3k 해서 설치. 알아서 url을 찾아서 설치함.