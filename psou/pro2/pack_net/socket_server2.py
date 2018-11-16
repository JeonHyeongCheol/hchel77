'''
Created on 2018. 11. 16.

server service를 계속 유지
'''


import socket
import sys

serverSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
HOST = '192.168.0.8' # 공백으로 해놔도 알아서 받아서 처리 해줌.
PORT = 7778

try:    
    serverSock.bind((HOST, PORT))
    print('server start.........!!!!!!!!!!!!!!!!!!!!!!!!!!!')
    serverSock.listen(5)
    
    while True:
        conn, addr = serverSock.accept()
        print('client Info : ', addr[0], addr[1]) # addr[0] : IP, addr[1] : PORT번호
        print('from client message : ', conn.recv(1024).decode()) # 바이트 값으로 암호메세지를 풀어줌.
    
    # 메세지 송신
    conn.send(('from server : ' + '나이스').encode('utf_8'))
    
except Exception as e:
    print("serverSock Err : ", e)
    sys.exit()

finally:
    serverSock.close()