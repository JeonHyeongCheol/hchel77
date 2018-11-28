'''
Created on 2018. 11. 28.

@author: kitcoop
'''
#import platform
#print(platform.python_version()) # 버전 확인

import scipy.stats as stats
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

np.random.seed(1) # 난수때문에 Seed 씀.
print(stats.norm(loc=1, scale=2).rvs(10)) # loc : 분포의 기댓 값, scale : 분포의 표준편차, rvs : 랜덤표본 10개 출력.
print()

centers = [1, 1.5, 2]
col = 'rgb'

std = 0.1
data_1 = []
for i in range(3):
    data_1.append(stats.norm(centers[i], std).rvs(100)) # loc, scale은 변수로 넣어 랜덤표본 100개 생성
    plt.plot(np.arange(len(data_1[i])) + i * len(data_1[0]), data_1[i], '*', color = col[i])
    # 그래프 그리기. 
    
plt.show()

std2 = 2
data_2 = []
for i in range(3):
    data_2.append(stats.norm(centers[i], std2).rvs(100))
    plt.plot(np.arange(len(data_2[i])) + i * len(data_2[0]), data_2[i], '*', color = col[i])

plt.show()

# 집단 내 분산이 작을 수록 평균의 차이가 분명해짐.



