'''
Created on 2018. 11. 16.

client
'''
from socket import *

clientSock = socket(AF_INET, SOCK_STREAM)
clientSock.connect(('192.168.0.34', 9999))
clientSock.sendall('안녕 반가워'.encode(encoding='utf_8', errors='strict')) # 인코더는 보내는 값..! 

clientSock.close()