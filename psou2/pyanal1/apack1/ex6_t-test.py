'''
Created on 2018. 11. 29.

t-test는 정규성을 확인...하는 것이 중요!!!!!!!!!!!!!!!!

집단이 하나일 때

one sample t-test
독립변수 : 범주형, 종속변수 : 연속형
'''

import numpy as np
import pandas as pd
import scipy.stats as stats
import matplotlib.pyplot as plt 
import seaborn as sbn

'''
실습 예제 1)
A중학교 1학년 1반 학생들의 시험결과가 담긴 파일을 읽어 처리 (국어 점수 평균검정) student.csv
'''
data = pd.read_csv('../testdata/student.csv')
print(data.head(3))

# 귀무가설 : 학생들의 국어점수 평균은 80이다.
# 대립가설 : 학생들의 국어점수 평균은 80점이 아니다.

print(data.describe())

result = stats.ttest_1samp(data.국어, popmean = 80) # popmean : 평균값.
print('t값  : %.3f , p-value : %.3f'%result) 
# 해설 : p-value : 0.199 > 0.05 유의수준보다 높으므로 귀무가설 채택.

print( '**' * 30)

'''
실습 예제 2)
여아 신생아 몸무게의 평균 검정 수행 babyboom.csv
여아 신생아의 몸무게는 평균이 2800(g)으로 알려져 왔으나 이보다 더 크다는 주장이 나왔다.
표본으로 여아 18명을 뽑아 체중을 측정하였다고 할 때 새로운 주장이 맞는지 검정해 보자.
'''

# 귀무가설 : 여아 신생아의 몸무게는 평균이 2800(g)이다.
# 대립가설 : 여아 신생아의 몸무게는 평균이 2800(g)이 아니다.

data = pd.read_csv('../testdata/babyboom.csv')
print(data.head(3))

fdata = data[data.gender == 1] # 여아를 1이라고 생각하고, fdata로 1인 애들만 뺌.
print(fdata.head())

#print(fdata.describe())

sbn.distplot(fdata.iloc[:, 2], fit = stats.norm) # 모든행에 weight열을 그래프로 그림.
plt.show()

print(stats.shapiro(fdata.iloc[:,2])) # 모든행에 weight열에 정규성 확인.
print()

result = stats.ttest_1samp(fdata.weight, popmean = 2800)
print('t값  : %.3f , p-value : %.3f'%result)
# 해석 : p-value : 0.039 < 0.05 이므로 귀무가설 기각.
# 여아 신생아의 몸무게는 평균이 2800(g)이 아니다.