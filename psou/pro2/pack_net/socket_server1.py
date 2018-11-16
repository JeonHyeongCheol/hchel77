'''
Created on 2018. 11. 16.

echo server : client의 요청에 대해 1회만 받음
'''

from socket import *

serverSock = socket(AF_INET, SOCK_STREAM) # AF : Address Families # (소켓의 종류, 유형)
serverSock.bind(('127.0.0.1',9999))
serverSock.listen(1) # 연결 정보수 (1 ~ 5)
print('server start..................................!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')

conn, addr = serverSock.accept() # 연결 대기
print('client addr : ', addr)
print('from client message : ', conn.recv(1024).decode()) # 디코더는 받은..값!!

conn.close()
serverSock.close()
