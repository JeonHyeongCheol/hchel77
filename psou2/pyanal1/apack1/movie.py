from bs4 import BeautifulSoup
import requests
from konlpy.tag import Okt
from collections import Counter
import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer
# sklearn : 문서 전처리 클래스

def movie_scrap(url):
    result = []
    for p in range(10):
        r = requests.get(url + "&page=" + str(p))
        soup = BeautifulSoup(r.content,'lxml', from_encoding='ms949')
        #print(soup)
        title = soup.find_all("td", {"class":"title"})
        #print(title)
        sub_result = []
        for i in range(len(title)):
            sub_result.append(title[i].text
                            .replace("\r","")
                            .replace("\n","")
                            .replace("\t","")
                            .replace("신고","")
                            .replace("버닝","")
                            .replace("데드풀 2","")
                            .replace("어벤져스: 인피니티 워","")
                            .replace("레슬러","")
                            .replace("챔피언",""))

        result = result + sub_result
    return("".join(result)) 

# 모든 리뷰들 마다 영화제목이 크롤링 됨. 이건 네티즌들이 쓴 것이 아니다. 그래서 싹 다 지워주도록 함수를 짰다. 그 외에도 신고 버튼에 있는 '신고' 글자나 \로 시작하는 이상한 문자들을 모두 지우도록 했다.또 "".join() 함수를 이용해서 영화 하나에 해당하는 모든 후기들을 하나의 스트링으로 변환했다.

burning = movie_scrap("https://movie.naver.com/movie/point/af/list.nhn?st=mcode&sword=155263&target=after")
dead = movie_scrap("https://movie.naver.com/movie/point/af/list.nhn?st=mcode&sword=149236&target=after")
avenger = movie_scrap("https://movie.naver.com/movie/point/af/list.nhn?st=mcode&sword=136315&target=after")
wresling = movie_scrap("https://movie.naver.com/movie/point/af/list.nhn?st=mcode&sword=159877&target=after") 
champ = movie_scrap("https://movie.naver.com/movie/point/af/list.nhn?st=mcode&sword=169347&target=after")

movies = [burning, dead, avenger, wresling, champ]
print(movies) 
# 한 영화에 대한 모든 리뷰들이 1개의 스트링으로 되어 있다. movies는 총 5개의 스트링이 있는 리스트다.

# 가장 많이 언급된 단어들을 확인해 보자. 빈도 수 높은 단어?
words_basket = []
twitter = Okt()

for movie in movies:
    words = twitter.pos(movie)    # 형태소 분석
    for word in words:
        if word[1] in ['Noun', "Adjective"] and len(word[0]) >= 2:
            words_basket.append(word[0])

print(Counter(words_basket).most_common(50))  # [('영화', 158), ('너무', 49),...

# 영화평과 관련이 적은 단어 제거
movies = [m.replace("영화","") for m in movies]
movies = [m.replace("너무","") for m in movies]
movies = [m.replace("진짜","") for m in movies]
movies = [m.replace("그냥","") for m in movies]
movies = [m.replace("진짜","") for m in movies]
movies = [m.replace("입니","") for m in movies]
print(movies)

print('-----')
def word_separate(movies):
    result = []
    for movie in movies:
        words = twitter.pos(movie)
        one_result = []
        for word in words:
            if word[1] in ['Noun', "Adjective"] and len(word[0]) >= 2:
                one_result.append(word[0])
        result.append(" ".join(one_result))
    return result

word_list = word_separate(movies)
print(word_list)
# 영화 한 개에 대해 언급된 두 글자 이상의 명사, 형용사들을 모두 모아 1개의 스트링에 넣었다. 
# word_list는 영화가 5개니 총 5개의 스트링이 있는 상태다.

count = CountVectorizer(min_df=2)  # 텍스트 문서를 토큰 카운트 행렬로 변환
#print(count)
tf_dtm = count.fit_transform(word_list).toarray()
#print(tf_dtm)  #[[0 0 0 ... 0 0 1] ...

tf_dtm = pd.DataFrame(tf_dtm, 
            columns = count.get_feature_names(),
            index = ['burning', 'dead', 'avenger', 'wresling', 'champ'])
print(tf_dtm)  # 단어별 빈도 수

# 단어 빈도수에 따른 tf_dtm이다. 하지만 문서 유사도를 구할 때는 단어들의 빈도수 뿐만 아니라 단어들의 중요성까지 고려한 tfidf를 이용하는 것이 낫다. 

print("---단어별 유사도---")
idf_maker = TfidfVectorizer(min_df=2)
tf_idf = idf_maker.fit_transform(word_list).toarray()
tf_idf_dtm = pd.DataFrame(tf_idf, 
columns = idf_maker.get_feature_names(),
 index = ['burning', 'dead', 'avenger', 'wresling', 'champ'])
print(tf_idf_dtm)

# 이제 각 영화끼리 코사인 유사도(구글링)를 구하자.
# 텍스트마이닝에서 주로 사용됨
def cosin_similarity(doc1, doc2):
    bunja = sum(doc1 * doc2)
    bunmo = (sum((doc1) ** 2) * sum((doc2) ** 2)) ** 0.5
    return bunja / bunmo  #코사인 유사도 계산

res = np.zeros((5, 5))
for i in range(5):
    for j in range(5):
        res[i,j] = cosin_similarity( tf_idf_dtm.iloc[i],tf_idf_dtm.iloc[j].values)

res = pd.DataFrame(res,
 index = ['burning', 'dead', 'avenger', 'wresling', 'champ'],
columns = ['burning', 'dead', 'avenger', 'wresling', 'champ']) 

print(res)
