'''
Created on 2018. 11. 19.

NAVER 영화 순위 스크래핑
'''
import urllib.request
from bs4 import BeautifulSoup

url = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn"
data = urllib.request.urlopen(url).read()
print(data)
soup = BeautifulSoup(data, "lxml")
#print(soup)
#print(soup.select("div.tit3")) # div에 클래스가 tit3
print(soup.select('div[class=tit3]'))

for tag in soup.select("div.tit3"):
    print(tag.text.strip())
    
print('------------------------------------------------')
import requests
data2 = requests.get(url).text
print(data2) 
soup2 = BeautifulSoup(data2, "lxml")
m_list = soup2.findAll("div", "tit3")
#print(m_list)
for i in m_list:
    #print(i)
    title = i.findAll('a')
    print(str(title)[str(title).find('title="') + 7:str(title).find('">')])
    
    
