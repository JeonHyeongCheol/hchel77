'''
Created on 2018. 11. 16.

멀티 채팅 클라이언트
'''
import socket
import threading
import sys

def handle(socket):
    while True:
        data = socket.recv(1024)
        if not data:continue
        print(data.decode('utf-8'))
        
        
sys.stdout.flush()

name = input("채팅 아이디 입력 : ")
cs = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
cs.connect(('192.168.0.34', 5555))
cs.send(name.encode('utf-8'))

th = threading.Thread(target=handle, args=(cs,))
th.start()

while True:
    msg = input()
    sys.stdout.flush()
    if not msg: continue
    cs.send(msg.encode('utf_8'))
    
cs.close()