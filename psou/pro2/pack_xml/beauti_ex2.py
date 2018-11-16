'''
Created on 2018. 11. 16.

BeautifulSoup : html, XML 파일로 부터 원하는 자료를 추출 해 낼 수 있는 라이브러리.
'''

import requests
from bs4 import BeautifulSoup

html_data="""
<html>
<body>
<h1>제목태그</h1>
<p>웹 페이지 분석</p>
<p>원하는 자료 추출</p>
</body>
</html>
"""

print(type(html_data))

soup = BeautifulSoup(html_data, "html.parser") # BeautifulSoup을 사용하면 type BeautifulSoup Class로 바뀜.
print(soup)

print(type(soup))
print()

h1 = soup.html.body.h1
p1 = soup.html.body.p
p2 = p1.next_sibling.next_sibling # p1에 next_sibling에서 한번더 해줘야 함.

print('h1 : ' + h1.string)
print('p1 : ' + p1.string) # 최초의  p태그를 찾음
print('p2 : ' + p2.string)