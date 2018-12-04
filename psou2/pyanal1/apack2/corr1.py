'''
Created on 2018. 11. 30.

상관분석 : 두 변수 간에 선형적 관계가 있는 지 분석
'''
import pandas as pd
from pandas import Series
import matplotlib.pyplot as plt
import numpy as np

d1 = Series([3,5,8,11,13]) # 다 - 붙이면 음의상관계로 바뀔 확률이 높음.
d2 = Series([1,2,3,4,5])
r = d1.corr(d2) # d1, d2의 상관계수.

print('상관계수', r)

#plt.plot(d1, d2)
#plt.scatter(d1, d2, color='blue')
#plt.show()
print()

product = pd.read_csv("../testdata/product.csv")
print(product.head()) # a : 효율성, b : 생산성, c: 가성
r_result = product.corr(method='pearson')
print(r_result)
print()

lam1 = lambda p:p['a'].corr(p['c'])
re = lam1(product)
print('효율성과 가성 간의 상관관계 : ', re)

lam2 = lambda p:p['b'].corr(p['c'])
re2 = lam2(product)
print('생산성과 가성 간의 상관관계 : ', re2)

print('**' * 40)
df = pd.DataFrame({'id1':(1,2,3,4,5), 'id2':[2,3,-1,7,9]})
print(df)
print(df.cov()) # 공분산 : 두 개 이상의 확률변수에 대한 관계를 확인. 공통된 치우침을 수치로 표현.
print(df.corr()) # 상관계수
print()

data = pd.read_csv("../testdata/drinking_water.csv")
print(data.head(2))
#print(data.describe()) # 요약 통계값 확인

print('numpy로 표준편차 확인')
print(np.std(data.친밀도))
print(np.std(data.적절성))
print(np.std(data.만족도))
# 0.9685051269352726
# 0.858027707764203
# 0.8271724742228972

plt.hist([np.std(data.친밀도), np.std(data.적절성), np.std(data.만족도)])
#plt.show()

print('공분산으로 확인 -----')
print(np.cov(data.친밀도, data.적절성))
print(np.cov(data.친밀도, data.만족도))
print(data.cov())


print('상관관계로 확인 -----')
print(np.corrcoef(data.친밀도, data.적절성))
print()
print(data.corr()) # 변수가 등간, 비율 척도
print(data.corr(method='pearson')) # 위와 동일
print(data.corr(method='spearman')) # 변수가 서열척도일 때

# 시각화
import seaborn as sns
sns.heatmap(data.corr()) # 상관관계 일 때 많이 사용. 색의 농도 : 진할 수록 약한 상관관계

plt.show()