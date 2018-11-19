'''
Created on 2018. 11. 19.

웹 자료를 읽어서 한글 형태소 분석
'''
import urllib.request
from bs4 import BeautifulSoup
from konlpy.tag import *
from dask.dataframe.core import DataFrame

#kkma = Kkma()
#komo = komoran()
#han = hannanum()

twitter = Okt()

#url = "https://ko.wikipedia.org/wiki/%EC%9D%B4%EC%88%9C%EC%8B%A0" 
# 인코딩되지 않으면 불러 올 수 없음. 그래서 밑에 parse를 사용해 인코딩 될 수 있도록 하여서 url에 붙여주면 됨.
from urllib import parse
para = parse.quote("이순신")
url = "https://ko.wikipedia.org/wiki/" + para
page = urllib.request.urlopen(url)
soup = BeautifulSoup(page.read(), "lxml")
#print(soup)

#mw-content-text > div > p
wordlist = []
for item in soup.select("#mw-content-text > div > p"):
    if item.string != None:
        #print(item.string)
        ss = item.string
        wordlist += twitter.nouns(ss)

print('wordlist : ')
print(wordlist, '\n', len(wordlist))

print()
word_dict = {}
for i in wordlist:
    if i in word_dict:
        word_dict[i] += 1
    else:
        word_dict[i] = 1
        
print('word_dict : ')
print(word_dict)

print()
setdata = set(wordlist)
print(setdata)
print("중복 제거 된 단어 수  :" , str(len(setdata))) # 180개

# csv 파일로 저장
import csv
import pandas as pd

try :
    f = csv.writer(open('ws1.csv'), 'w', encoding="utf-8")
    f.writer(word_dict)
    f = csv.writer(open('ws2.csv'), 'w', encoding="utf-8")
    f.writer(word_dict)
    print(pd.read_csv('ws1.csv'))
    print(pd.read_csv('ws2.csv'))

except Exception as e:
    print(e)

from pandas import Series, DataFrame

li_data2 = Series(word_dict)
print(li_data2)
print(li_data2.value_counts()[:5])
      
import matplotlib.pyplot as plt
plt.rc("font", family="malgun gothic")
plt.plot(li_data2.value_counts()[:5])
plt.xlabel("횟수 종류")
plt.ylabel("종류별 발생수")
plt.legend("횟수")
plt.show()

print('!!!!!!!!!!!!!!!!!!!!!!!!!!')
df = DataFrame(wordlist)
print(df)
    

    