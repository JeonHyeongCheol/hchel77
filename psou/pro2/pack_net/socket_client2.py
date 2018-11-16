'''
Created on 2018. 11. 16.

client
'''
from socket import *

clientSock = socket(AF_INET, SOCK_STREAM)

clientSock.connect(('192.168.0.8', 7778))
clientSock.sendall('쉬는시간'.encode(encoding='utf_8', errors='strict'))

re_msg = clientSock.recv(1024).decode()

print('수신자료는 ' + re_msg)

clientSock.close()