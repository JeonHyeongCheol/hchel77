'''
Created on 2018. 11. 16.

thread 상속
'''

import threading, time, sys

class MyThread(threading.Thread):
    def run(self):
        for i in range(1, 11):
            print('id:{}--->{}'.format(self.getName(), i))
            time.sleep(0.1)

ths = []
for i in range(2):
    th = MyThread()
    th.start()