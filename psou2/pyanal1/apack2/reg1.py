'''
Created on 2018. 12. 4.

선형회귀분석 방법 알아보기
(선형회수의 조건 : 독립변수 x는 연속형이고 , 종속변수y또한 연속형이여야한다.)
머신러닝은 : 학습데이터로 모델을 만들고 결과를 예측및 분류 하는것이다. 그것이 regression일수도 있고 

    scipy module 의 linregress()
'''
from scipy import stats
import numpy as np
import pandas as pd
import matplotlib.pylab as plt

# score 와 iq의 관계.
# iq 를 독립변수로, score 를 종속변수로.
# iq 가 100이면 점수가 몇점? 이런식임.

data = pd.read_csv('testdata/score_iq.csv')
print(data.head())

#      sid  score   iq  academy  game  tv
# 0  10001     90  140        2     1   0
# 1  10002     75  125        1     3   3
# 2  10003     77  120        1     0   4
# 3  10004     83  135        2     3   2
# 4  10005     65  105        0     4   4

x = data.iq
y = data.score

print(data.corr())

#               sid     score        iq   academy      game        tv
# sid      1.000000 -0.014399 -0.007048 -0.004398  0.018806  0.024565
# score   -0.014399  1.000000  0.882220  0.896265 -0.298193 -0.819752
# iq      -0.007048  0.882220  1.000000  0.671783 -0.031516 -0.585033
# academy -0.004398  0.896265  0.671783  1.000000 -0.351315 -0.948551
# game     0.018806 -0.298193 -0.031516 -0.351315  1.000000  0.239217
# tv       0.024565 -0.819752 -0.585033 -0.948551  0.239217  1.000000
# score 와 iq만 봤을때, 상관관계가 0.882220 이기에 상관관계가 매우 높다. (양의 상관관계)
# 상관관계가 매우 높기 때문에 선형회귀 모델로 들어갈 수 있다.

model = stats.linregress(x, y)
print('linregress \n', model)
# LinregressResul
# (slope=0.6514309527270089, intercept=-2.8564471221976504,
# rvalue=0.8822203446134709, pvalue=2.8476895206666614e-50, stderr=0.02857793440930536)
# rvalue 는 상관계수를 제곱한 값임.

print('기울기 : ', model.slope)  # 0.6514309527270089
print('절편 : ', model.intercept)  # -2.8564471221976504
print('결정계수(설명력-정확도): ', model.rvalue ** 2)  # 0.8822203446134709, 곧 88%의 설명력을 가직 있다.!! 굳굳
# rvalue = 상관계수, 결정계수는 상관계수 ** 2

# y = ax + b;
x = 55  # iq 가 55일때,
y = 0.6514309527270089 * x + -2.8564471221976504
print('예상 점수는 : ', y)  # 32.97225527778784,  32.9점 정도 받을 수 있다.

print('--' * 20)

product = pd.read_csv('testdata/product.csv')
print(product.head())  # a: 제품에 대한 친밀도 점수 , b: 적절성 점수 , c: 만족도

# 제품 적절성에 따른 만족도 회귀모델 생성.
model2 = stats.linregress(product['b'], product['c'])
print(model2)
# slope=0.739276178597182, intercept=0.7788583344701911,
# rvalue=0.7668526996408371, pvalue=2.2353448575494205e-52,
# stderr=0.03822605528717564

print(model2.slope)  # 기울기
print(model2.intercept)  #
print(model2.rvalue ** 2)  # 결정계수(설명력)  58%.















