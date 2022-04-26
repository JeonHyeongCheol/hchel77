'''
Created on 2018. 11. 19.

웹의 정보 읽기
'''

import urllib.parse
import urllib.request

from bs4 import BeautifulSoup
from conda._vendor.urllib3.exceptions import HTTPError
from imageio.core.util import urlopen

import urllib.request as req


url = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108"  
data = urllib.request.urlopen(url).read()
print(data)
text = data.decode("utf-8")
#print(text)

data2 = urllib.request.urlopen(url)
soup = BeautifulSoup(data2, 'lxml')
#print(soup)

title = soup.find('title').string
print(title)
wf = soup.find('wf')
print(wf)
wfs = soup.find_all('wf')
print(wfs)

print('\n 다음 사이트 자료 읽기 ---------------------------------------')
url = "https://media.daum.net/society/"
daum = req.urlopen(url)
soup = BeautifulSoup(daum, 'lxml')
#print(soup)
print(soup.select_one("div strong.tit_thumb a").string)

#mw-content-text > div > p:nth-child(7) # 구글 크롬에서 copy selector를 사용해 태그를 바로 가져 올 수 있음.
url = "https://ko.wikipedia.org/wiki/%EC%9D%B4%EC%88%9C%EC%8B%A0"
wiki = req.urlopen(url)
soup = BeautifulSoup(wiki, 'html.parser')
print(soup.select("#mw-content-text > div > p"))
print("-------------------------------------------")
def getTitle(url):
    try:
        html = urlopen(url)
    except HTTPError as e:
        return None
    
    try:
        bsObj = BeautifulSoup(html, "html.parser")
        title2 = bsObj.body.h1
        
        print('find() 자식과 자손 태그 읽기 test ---')
        #for child in bsObj.find("table", {"id":"giftList"}).children: # 자식, 테이블 안에 id 값을 dict 타입으로 주어 값을 받아 옴. 바로 아래 단계 까지만.
        for child in bsObj.find("table", {"id":"giftList"}).descendants: # 자손, 자식에 자식까지해서 다 받아옴.
            print(child)

        print('형제 태그 읽기')
        #for sibling in bsObj.find("table", {"id":"giftList"}).tr:
        for sibling in bsObj.find("table", {"id":"giftList"}).tr.next_siblings: 
            print(sibling)    
        
        print('이전 태그 읽기 ---------------------------')
        print(bsObj.find("img", {"src":"../img/gifts/img1.jpg"})\
              .parent.previous_sibling.get_text())
        
    except Exception as e :
        return None
    
    return title2
    
title2 = getTitle("http://www.pythonscraping.com/pages/page3.html")

#print(title2)

if title2 == None:
    print("자료 없음")
else:
    print(title2)