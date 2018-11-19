'''
Created on 2018. 11. 19.

한글 형태소 분석
'''
from konlpy.tag import Kkma

kkma = Kkma()
print(kkma.sentences("여러분 안녕하세요 반가워요"))
print(kkma.nouns("여러분 안녕하세요 반가워요"))
print(kkma.pos(u"오류보고는 실행환경, 에러메세지와 함께"))
