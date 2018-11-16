'''
Created on 2018. 11. 16.

공유 자원에 대한 이야기
'''

import threading, time

g_count = 0 # 공유 대상.
lock = threading.Lock()

# syncronize(동기화)를 활용해 공유자원에 대한 충돌이 일어나지 않도록 함.

def threadCount(id, count):
    global g_count
    for i in range(count):
        lock.acquire() # 공유자원에 대한 Lock. 한 작업이 실행되는 동안에는 다른 프로세스들을 비활성화.
        print('id:%s ==> count: %s, g_count: %s'%(id, i, g_count))
        g_count = g_count + 1
        lock.release() # 작업이 끝나면 비활성화를 풀어주고 다음 프로세스를 실행 할 수 있도록 해줌.

for i in range(1, 6): # 스레드 5개 호출, 총 25번 돔.
    threading.Thread(target=threadCount, args=(i,5)).start() # 실행 하다보면 전역변수가 똑같을 때가 있는 충돌이 일어나고 있는 것.
    
time.sleep(1)
print('final g_count : ', g_count)
print('bye')