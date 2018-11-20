'''
Created on 2018. 11. 20.

단어 출현 빈도
'''
from konlpy.tag import Okt
from gensim.models import word2vec
twitter = Okt()
#mallist = twitter.nouns("멋진 봄은 엄청 무더운 여름과 한들한들 시원한 가을의 중간이고 겨울의 상반된다") # 단어만.
#mallist = twitter.morphs("멋진 봄은 엄청 무더운 여름과 한들한들 시원한 가을의 중간이고 겨울의 상반된다") # 폼사 까지.
#mallist = twitter.pos("멋진 봄은 엄청 무더운 여름과 한들한들 시원한 가을의 중간이고 겨울의 상반된다") # 어떤 폼사인지 알려줌.
#print(mallist)
# 
import nltk # 그림으로 알려줌.
#parser_ko = nltk.RegexpParser("NP:{<Adjective>* <Noun>*}")
#chunk_ko = parser_ko.parse(mallist)
#chunk_ko.draw()

"""
f = open('aaa.txt', 'r', encoding="utf-8")
#print(f.read())

word_dic = {} # 저장할 변수 설정
lines = f.read().split("\n") # 행 단위로 끊음
for line in lines:
    mallist = twitter.pos(line)
    #print(mallist)
    for word in mallist:
        if word[1] == "Noun":  #명사 일 때
            if not(word[0] in word_dic):
                word_dic[word[0]] = 0
            word_dic[word[0]] += 1
    #print(word_dic)

keys = sorted(word_dic.items(), key=lambda x:x[1], reverse = True) # Word_dic에 있는 데이터에서 명사인 부분을 Key로 설정. 
for word, count in keys: #[:20] : 20번째 라인까지만?
    print("{0}({1})".format(word, count))
    
f.close()
"""


with open('aaa.txt', "r", encoding='utf-8') as fr:
    results= []
    lines = fr.read().split("\n")
    for line in lines:
        mallist = twitter.pos(line, norm=True, stem=True)
        #print(mallist)
        r = []
        for word in mallist :
            if not word[1] in ["Josa", "Punctuation", "Foreign", "Number"]: # 뺄 품사들 설정.
                r.append(word[0]) # 나온 품사를 제외한 단어만 집어 넣음.
        rlist = (" ".join(r)).strip() # 앞 뒤 공백 제거
        results.append(rlist)
        #print(rlist)
#print(results)
data_file = "news.txt"
with open(data_file, "w", encoding="utf-8") as fw:
    fw.write('\n'.join(results)) # 행 단위로 Join!

    

# data = word2vec.LineSentence(data_file)
# #print(data)
# 
# model = word2vec.Word2Vec(data, size=100, window=2, min_count=2, workers=4, iter=100, sg=1) 
# # 단어로 주변단어를 봄. window : 앞뒤로 2개씩 본 다는 것. iter : 학습 횟수.
# 
# model.init_sims(replace=True)
# model.save("news.model")
# print("ok")

model = word2vec.Word2Vec.load("news.model")
print(model.similarity("반려동물", "시장"))
print(model.most_similar(positive=["반려동물"]))
print(model.most_similar(positive=["반려동물"], topn=3))
print(model.most_similar(positive=["반려동물","시장"]))
print(model.most_similar(positive=["반려동물"],negative=["기자"], topn=3))