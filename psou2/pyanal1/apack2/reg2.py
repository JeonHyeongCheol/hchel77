'''
Created on 2018. 12. 4.

@author: kitcoop
'''
'''
scipy모듈의 linregress()
'''
# 아이큐에 따라 시험성적이 높은지 낮은지에대한 상관관계로 보겠다. 
# 아이큐가 독립변수 시험성적이 종속변수로 설정

from scipy import stats
import  numpy as np
import pandas as pd
import matplotlib.pylab as plt

score_iq = pd.read_csv('../testdata/score_iq.csv')
#print(score_iq.head())
x = score_iq.iq #독립변수
y = score_iq.score #종속변수

#print(score_iq.corr()) #0.882220 상관관계가 매우 높다 선형회귀 할 수 있다. 
model = stats.linregress(x, y)
#print(model)
# print('기울기 : ', model.slope)
# print('절편 : ', model.intercept)
# print('결정계수(설명력:', model.rvalue)
x = 55 #아이큐가 55라면 
y = 0.6514309527270075*x + -2.8564471221974657 # 예상점수 
print(y)
# 아이큐가 55일때 
# 예상점수가 32.722552777... 점 

product = pd.read_csv('../testdata/product.csv')
print(product.head(2)) # a : 친밀도, b : 적절성, c: 만족도
# 제품 적절성에 따른 만족도 회귀모델 생성
model2 = stats.linregress()(product['b'], product['c'])
print(model2) # 기울기.
print(model2.slope, model2.intercept)
print(model2.rvlaue ** 2) # 결정계수(설명력) 50%.