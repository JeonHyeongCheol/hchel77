'''
Created on 2018. 11. 16.

Multiprocessing을 위해 Pool, Process
'''
from multiprocessing import Pool # python의 병렬 처리
import time
import os

def f(x):
    print('값은 ', x, "에 대한 직업 pid : ", os.getpid())
    time.sleep(1)
    return x*x

if __name__ == '__main__':
    p = Pool(3)
    startTime = int(time.time())
    for i in range(0, 10):
        print(f(i))
    
    print(p.map(f, range(1, 10))) # p.map(병렬처리)
        
    endtime = int(time.time())
    print('작업시간 : ', endtime - startTime)
    