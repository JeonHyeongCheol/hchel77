'''
Created on 2018. 11. 16.

socket 통신 : TCP/IP 프로토콜
'''

import socket

print(socket.getservbyname('http', 'tcp')) # 포트 번호 확인.
print(socket.getservbyname('telnet', 'tcp'))
print(socket.getservbyname('ftp', 'tcp'))

print(socket.getaddrinfo('www.naver.com', 80, proto = socket.SOL_TCP))
