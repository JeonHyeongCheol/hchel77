'''
Created on 2018. 11. 19.

구글의 검색기능으로 검색된 결과를 브라우저 탭으로 출력
'''

import requests
from bs4 import BeautifulSoup
import webbrowser

def gogo():
    base_url = "https://www.google.co.kr/search?q={0}"
    sou_code = base_url.format("파이썬")
    re_data = requests.get(sou_code)
    #print(re_data.text)
    parse_data = BeautifulSoup(re_data.text, "lxml")
    #print(parse_data)
    #link_data = parse_data.select('a')
    link_data = parse_data.select('.r a')
    #print(link_data)
    
    for link in link_data[:5]:
        url = link.get('href')
        #print(url)
        url = "http://google.co.kr" + url
        # print(url)
        webbrowser.open(url)
    
gogo()