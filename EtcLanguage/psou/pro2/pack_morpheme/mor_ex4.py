'''
Created on 2018. 11. 20.

문자열 자르고 의미 단어 검출 후 워드클라우드 출력
'''

from konlpy.corpus import kobill
from anaconda_navigator.utils.encoding import write

files_ko = kobill.fileids()

doc_ko = kobill.open("news.txt").read()
#print(doc_ko)

from konlpy.tag import Okt
t = Okt()
tokens_ko = t.morphs(doc_ko) # Tokenize.
print(tokens_ko)

import nltk

ko = nltk.Text(tokens_ko, name="뉴스")
print(ko)
print()

print(len(ko.tokens)) # 토큰 갯수 684.
print(len(set(ko.tokens))) # Set으로 중복 제거. 고유 토큰 수.

fre_dist = ko.vocab()
print(fre_dist) # <FreqDist with 303 samples and 684 outcomes>.

print("차트 그리기")
from matplotlib import rc
rc('font', family="malgun gothic") # 한글방지하기위해 써줌.
#ko.plot(20)

print(ko.count(str("한국")))
#ko.dispersion_plot(["한국","유치원","국회"]) # 분산 차트.
ko.concordance("한국") # 색인 : 해당 검색어가 포함된 문장 출력.

#print(ko.similar("국회")) # 문장이 많아야지 나옴.

# 워드 클라우드
print(type(ko.vocab()))
print(dir(ko.vocab()))
print("===========================================")
data = ko.vocab().items()
print(data)
print(type(data))

import csv
with open('words.csv', 'w', encoding="utf-8") as f:
    f.write('word, freq\n')
    writer = csv.writer(f)
    writer.writerows(data)
    
# 파이썬 server 시작...(기본적으로 지원). cmd에서 명령어 쓰면 됨.> python -m http.server 8888

