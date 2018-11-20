'''
Created on 2018. 11. 20.

@author: kitcoop
'''

from http.server import SimpleHTTPRequestHandler, HTTPServer

# 서버키고 나서 접속 할 때는 (아이피:포트번호) 를 써줘야지 접속 가능.

port = 7778 

handler = SimpleHTTPRequestHandler
serv = HTTPServer(("192.168.0.8", port), handler)
print('Webserver start.................')
serv.serve_forever()