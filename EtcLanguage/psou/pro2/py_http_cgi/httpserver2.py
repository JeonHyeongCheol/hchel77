'''
Created on 2018. 11. 20.

# cgi로 구현할 경우.
cgi-bin 폴더를 꼭(이름도) 만들어주고 그 안에는 py파일을 넣어줘야 함.
images 등 다른 파일을 쓰고 싶으면 폴더(package X)를 만들어서 넣어서 사용하면 됨.
경로를 잘 볼 것!!
'''
from http.server import CGIHTTPRequestHandler, HTTPServer

port = 8887

class Handler(CGIHTTPRequestHandler):
    cg_directories = ['/cgi-bin'] # 경로 설정 잘 해 줄것!! 여기안에 있는 py를 불러줌.
    
    
serv = HTTPServer(('192.168.0.8', port), Handler)

print("웹서버 서비스 출발........")
serv.serve_forever()