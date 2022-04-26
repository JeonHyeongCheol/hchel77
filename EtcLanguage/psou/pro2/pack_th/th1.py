'''
Created on 2018. 11. 16.

process / thread
'''

from subprocess import *

# Popen('calc.exe') # Popen : 응용 프로그램 실행.
# print('다음1')
# call('notepad.exe') # Call : 응용 프로그램을 실행하지만 그 다음 코드를 실행 할려면 사용하고 있는 프로그램을 닫아야 만 실행 가능.
# print('다음2')

def run(id):
    for i in range(1, 11):
        print('id={}-->{}'.format(id, i))

# thread를 사용하지 않은 경우
#run(1)
#run(2)

# thread를 사용하는 경우
# th1 = threading.Thread(target=run, args=('일',))
# th2 = threading.Thread(target=run, args=('둘',))
# th1.start()
# th2.start()
# th1.join() 
# th2.join() # 스레드가 하나 다 끝날 때 까지 기다렸다가 실행됨.

ths = []
for i in range(2):
    th = threading.Thread(target=run, args=(i,)) # th는 Thread 실행, args 값은 증가. 
    th.start() # thread 시작.
    ths.append(th) # ths에 담김.
    
for th in ths:
    th.join() # 하나가 끝날 때가지 다음이 실행 안됨.

print('exit')
