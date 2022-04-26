'''
Created on 2018. 11. 16.

날짜 시간 출력
'''

import time
now = time.localtime()
print('현재는 {0}년 {1}월 {2}일'.format(now.tm_year, now.tm_mon, now.tm_mday))
print('현재는 {0}시 {1}분 {2}초'.format(now.tm_hour, now.tm_min, now.tm_sec))
print('오늘 요일 : %d'%(now.tm_wday)) # format 대신 % 사용 가능.
print('올해 몇번 째 날 %d'%(now.tm_yday))

import threading

def cale_show():
    now = time.localtime()
    print('현재는 {0}시 {1}분 {2}초'.format(now.tm_hour, now.tm_min, now.tm_sec))

# def my_run():
#     while True:
#         cale_show()
#         time.sleep(1)
        
def my_run():
    while True:
        now2 = time.localtime()
        if now2.tm_sec == 30:
            break
        cale_show()
        time.sleep(1)
        
th = threading.Thread(target=my_run)
th.start()
th.join()

print('bye')
