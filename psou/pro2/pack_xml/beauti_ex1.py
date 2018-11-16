'''
Created on 2018. 11. 16.

BeautifulSoup : html, XML 파일로 부터 원하는 자료를 추출 해 낼 수 있는 라이브러리.
'''

import requests
from bs4 import BeautifulSoup

def go():

    base_url = "http://www.naver.com/index.html"

    #storing all the information including headers in the variable source code
    source_code = requests.get(base_url)
    print(source_code)

    #sort source code and store only the plaintext
    plain_text = source_code.text
    #print(plain_text)

    #converting plain_text to Beautiful Soup object so the library can sort thru it
    convert_data = BeautifulSoup(plain_text, 'lxml')
    #print(convert_data)
    print(type(convert_data))

    #sorting useful information
    for link in convert_data.findAll('a', {'target': '_blank'}):
        href = base_url + link.get('href')  #Building a clickable url
        print(href)                         #displaying href

go()

