'''
Created on 2018. 11. 16.

BeautifulSoup : html, XML 파일로 부터 원하는 자료를 추출 해 낼 수 있는 라이브러리.
'''

import requests
from bs4 import BeautifulSoup
from astropy.units import aa

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

print('\nfind() 메소드 사용 ------------------------------------')
html_data2="""
<html>
<body>
<h1 id='title'>제목태그</h1>
<p>웹 페이지 분석</p>
<p attr='my' id='my'>원하는 자료 추출</p>
</body>
</html>
"""

soup2 = BeautifulSoup(html_data2, 'html.parser')
print('#title : ' + soup2.find(id="title").string)
print('#my' + soup2.find(id="my").string)
print('#my' + soup2.find(attr="my").string)


print('\nfind_all() 메소드 사용 ------------------------------------')
html_data3="""
<html>
<body>
<h1 id='title'>제목태그</h1>
<p>웹 페이지 분석</p>
<p attr='my' id='my'>원하는 자료 추출</p>
<div>
    <a href = 'http://www.naver.com>naver</a><br>
    <a href = 'http://www.daum.net>daum</a>
</div>
</body>
</html>
"""

soup3 = BeautifulSoup(html_data3, 'html.parser')
result = soup3.find('p')
print(result.string)
result2 = soup3.find_all('p')
print(result2)
result3 = soup3.find(['p','h1'])
print(result3)
#print(soup3.prettify())
links = soup3.find_all('a')
print(links)

for i in links:
    href = i.attrs('href')
    text = i.stirng
    print(href, ' ', text)
    
result4 = soup3.find_all(string=['제목태그','원하는 자료 추출'])
print(result4[0])
print(result4[1])

print("\n 정규표혀식 사용")
import re
link2 = soup3.find_all(href=re.compile(r'^http:///'))
print(link2)
for i in link2:
    print(i.attrs['htef'])
    
print("\nCSS selector사용방법 이용")
html_data4="""
<h1 id='title'>제목태그</h1>
<html>
<body>
<h1 id='title'>제목태그</h1>
<p>웹 페이지 분석</p>
<p attr='my' id='my'>원하는 자료 추출</p>
<div>
    <a href = 'http://www.naver.com>naver</a><br>
    <a href = 'http://www.daum.net>daum</a>
</div>
</body>
</html>
"""

soup4 = BeautifulSoup(html_data4, 'lxml')
a = soup4.select_one("div#hello > a").string
print('a : ', a)

ul = soup4.select("div#hello > ul.world > li")
print('ul :', ul)
for i in ul:
    print("li : ",i.string)
    
    
