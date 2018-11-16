'''
Created on 2018. 11. 16.

Multiprocessing을 위해 Pool, Process
'''
from multiprocessing import Process
import os


def func():
    print('연속적으로 진행하고자 하는 어떤 작업')
    
def abc(number):
    result = number + 10
    func()
    proc = os.getpid()
    print('number:{0}, result:{1}, process id:{2}'.format(number, result, proc))
    
if __name__ == '__main__':
    numbers = [1,2,3,4,5]
    procs = []
    
    for index, number in enumerate(numbers):
        proc = Process(target=abc, args=(number,))
        procs.append(proc)
        proc.start()
        
    for proc in procs:
        proc.join()