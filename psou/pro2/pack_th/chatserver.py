'''
Created on 2018. 11. 16.

멀티 채팅 서버
'''

import socket
import threading

ss = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
ss.bind(('192.168.0.8', 5557))
ss.listen(5)
print('채팅 서버 서비스 시작.......!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
users = []

def ChatUser(conn):
    name = conn.recv(1024)
    data = '^^' + name.decode("utf_8") + '님이 입장하셨습니다^^'
    print(data)
    
    try:
        for p in users: # users 하나씩 추가하여 Send 해주면 됨.
                p.send(data.encode('utf_8'))
        
        while True:
            msg = conn.recv(1024)
            data = name.decode('utf_8') + '님 메세지 : ' + msg.decode
            print('수신 자료 : ', data)
            for p in users:
                p.send(data.encode('utf_8'))
        
    except Exception as e:
        print("ChatUser Err : ", e)
        users.remove(conn)
        data = '~~' + name.decode('utf_8') + '님 퇴장하셨습니다ㅠㅠ'
        print(data)
        if users:
            for p in users:
                p.send(data.encode('utf_8'))
            else:
                print('exit')
        
    finally:
        ss.close()
    
                
while True:
    conn, addr = ss.accept() # 연결 요청이 들어옴.
    users.append(conn) # 연결 계속 해좀. 추가해준다고 생각하면 됨.
    th = threading.Thread(target=ChatUser, args = (conn,)) # 사람 인원수 대로 스레드 갯수가 늘어남.
