'''
Created on 2018. 11. 29.

두 집단일 때

남녀의 성적, A반과 B반의 키, 경기도와 충청도의 소득 따위의 서로 독립인 두 집단에서 얻은 표본을 독립표본(two sample)이라고 한다.
실습) 남녀 두 집단 간 파이썬 시험의 평균 차이 검정
Male = [75, 85, 100, 72.5, 86.5]
female = [63.2, 76, 52, 100, 70]
'''

from scipy import stats
import pandas as pd
from numpy import average
from pandas.io.parsers import read_csv

# 귀무가설 : 남녀 두 집단 간 파이썬 시험의 평균에 차이가 없다.
# 대립가설 : 남녀 두 집단 간 파이썬 시험의 평균에 차이가 있다.
male = [75, 85, 100, 72.5, 86.5]
female = [63.2, 76, 52, 100, 70]

two_sam = stats.ttest_ind(male, female) # ttest_ind : 집단이 두개일 때 사용. 두 개의 표본에 대한 t 검정.
#two_sam = stats.ttest_ind(male, female, equal_var=True) 
print(two_sam)
# T값(검정통계량) : statistic=1.233193127514512, p-value=0.2525076844853278
t, p = two_sam
print('t검정통계량 : {}, p-value : {}'.format(t, p))
# p-value 0.2525 > 0.05 이므로 귀무가설 채택.

print('여 : ', average(female))
print('남 : ', average(male))

print('**' * 30)

'''
실습) 두 가지 교육방법에 따른 평균시험 점수에 대한 검정 수행 two_sample.csv'
'''

data = pd.read_csv("../testdata/two_sample.csv")
print(data.head())

df = data[['method', 'score']]
print(df.head())

#귀무가설 : 두 가지 교육방법에 따른 평균시험 점수에 차이가 없다.
#대립가설 : 두 가지 교육방법에 따른 평균시험에 점수에 차이가 있다.

m1 = df[df['method'] == 1] # 교육방법 1
m2 = df[df['method'] == 2] # 교육방법 2
score1 = m1['score'] # 교육방법 1 점수
score2 = m2['score'] # 교육방법 2 점수
#print('score1')
#print('score2')
# sco1 = score1.fillna())
sco1 = score1.fillna(score1.mean()) # 평균으로 NaN을 대체
sco2 = score2.fillna(score2.mean())
#print(sco2)

# 정규성 확인 : 히스토그램, shapiro()
result = stats.ttest_ind(sco1, sco2)
p, v = result
print('t검정통계량 : {}, p-value : {}'.format(p, v))
# t검정통계량 : -0.1964, p-value : 0.8450.
# p-value : 0.8450 > 0.05 이므로 귀무가설 채택.

print("**" * 30)
'''
* 서로 대응인 두 집단의 평균 차이 검정(paired samples t-test)
처리 이전과 처리 이후를 각각의 모집단으로 판단하여, 동일한 관찰 대상으로부터 처리 이전과 처리 이후를 1:1로 
대응시킨 두 집단으로 부터의 표본을 대응표본(paired sample)이라고 한다.
대응인 두 집단의 평균 비교는 동일한 관찰 대상으로부터 처리 이전의 관찰과 이후의 관찰을 비교하여 영향을 미친 정도를 밝히는데 주로 사용하고 있다. 
집단 간 비교가 아니므로 등분산 검정을 할 필요가 없다.
실습) 복부 수술 전 9명의 몸무게와 복부 수술 후 몸무게 변화
baseline = [67.2, 67.4, 71.5, 77.6, 86.0, 89.1, 59.5, 81.9, 105.5]
follow_up = [62.4, 64.6, 70.4, 62.6, 80.1, 73.2, 58.2, 71.0, 101.0]
'''

import numpy as np
import seaborn as sns
import matplotlib.pylab as plt
import scipy as sp

# 그냥 랜덤 값으로 해봄..?
np.random.seed(0)
x1 = np.random.normal(100, 10, 100)
x2 = np.random.normal(97, 10, 100)
#print(x1)

# 히스토그램으로 정규성 확인을 위해
sns.distplot(x1, kde=False, fit=sp.stats.norm)
sns.distplot(x2, kde=False, fit=sp.stats.norm)
#plt.show()

print(stats.ttest_rel(x1, x2))

# 실습) 복부 수술 전 9명의 몸무게와 복부 수술 후 몸무게 변화
baseline = [67.2, 67.4, 71.5, 77.6, 86.0, 89.1, 59.5, 81.9, 105.5]
follow_up = [62.4, 64.6, 70.4, 62.6, 80.1, 73.2, 58.2, 71.0, 101.0]

# 귀무가설 : 복부 수술 전/후 몸무게의 차이가 없다.
# 대립가설 : 복부 수술 전/후 몸무게의 차이가 있다.

paird_sam = stats.ttest_rel(baseline, follow_up)
print('t검정통계량 : %.5f, p-value : %.5f'%paird_sam)
# t검정통계량 : 3.66812, p-value : 0.00633 
# p-value : 0.00633 < 0.05 이므로 귀무가설 기각.

print("**" * 30)

'''
추론통계 분석 중 비율검정
- 비율검정 특징
: 집단의 비율이 어떤 특정한 값과 같은지를 검증.
: 비율 차이 검정 통계량을 바탕으로 귀무가설의 기각여부를 결정.
'''
'''
# one-sample
A회사에는 100명 중에 45명이 흡연을 한다. 국가 통계를 보니 국민 흡연율은 35%라고 한다. 비율이 같냐?
'''

# 귀무가설 : 국민 흡연률 35%와 비율이 같다.
# 대립가설 : 국민 흡연률 35%와 비율이 다르다.

from statsmodels.stats.proportion import proportions_ztest # 정규표현식에서 x의 값을 z로..?
count = np.array([45]) # 해당 수
nobs = np.array([100]) # 전체 수
val = 0.35 # 비율
#print(count)

z, p = proportions_ztest(count, nobs, val) # proportions_ztest : 추론통계 분석에서 비율검정할 때 씀.

print('z값 : ', z)
print('p-value : ', p)
# 해석 : p-value : 0.04442318 < 0.05 이므로 귀무가설 기각. 비율이 다름.

print("**" * 30)

'''
# two-sample
A회사 사람들 300명 중 100명이 커피를 마시고, B회사 사람들 400명 중 170명이 커피를 마셨다.비율이 같냐?
'''

# 귀무가설 : A회사와 B회사에 사람들이 커피를 마시는 비율은 같다.
# 대립가설 : A회사와 B회사에 사람들이 커피를 마시는 비율은 다르다.

count = np.array([100, 170]) # 해당 수.
nobs = np.array([300, 400]) # 전체 수.

z, p = proportions_ztest(count, nobs, value=0)
print('z값 : ', z)
print('p-value : ', p)
# 해석 : p-value : 0.013 < 0.05 이므로 귀무가설 기각. 비율이 다름.