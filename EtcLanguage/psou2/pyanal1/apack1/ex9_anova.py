'''
Created on 2018. 11. 29.

일원분산분석 방법 알아보기
'''
import urllib

from statsmodels.formula.api import ols
from statsmodels.stats.anova import anova_lm

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import scipy.stats as stats
import statsmodels.api as sm  # anova


# 웹에서 직접 파일을 읽어옴(GitHub)
url = "https://raw.githubusercontent.com/pykwon/python/master/testdata_utf8/group3.txt"
data = np.genfromtxt(urllib.request.urlopen(url), delimiter=",", comments='#')
print(data, type(data)) # <class 'numpy.ndarray'>

gr1 = data[data[:,1] == 1, 0]
gr2 = data[data[:,1] == 2, 0]
gr3 = data[data[:,1] == 3, 0]
print(gr1)
print(gr2)
print(gr3)

print(stats.shapiro(gr1)) # p-value : 0.3336 > 0.05 정규성을 띔.

# 세 그룹의 데이터 간 차이를 시각화로 표현.
plot_data = [gr1, gr2, gr3]
plt.boxplot(plot_data)
#plt.show()

print('**' * 40)

# 일원분산분석 방법1
df = pd.DataFrame(data, columns=['value', 'group'])
print(df.head(5))
lm_model = ols('value ~ C(group)', df).fit() # C : indicator(인디케이터?)
print(anova_lm(lm_model))

print('**' * 40)

# 일원분산분석 방법2
f_sta, pv = stats.f_oneway(gr1, gr2, gr3)
print('f sta:%f, p-value:%f'%(f_sta, pv)) # 방법1과 방법2의 값이 다름.. 다르면 안되는데.. 이럴 경우 ols의 독립변수에 C()으로 묶어줌.

print('**' * 40)

# 이원분산분석 방법 알아보기
url = "https://raw.githubusercontent.com/pykwon/python/master/testdata_utf8/group3_2.txt"
data = pd.read_csv(urllib.request.urlopen(url))
print(data.head(3))

plt.rc('font', family='malgun gothic')
data.boxplot(column = '머리둘레', by = '태아수', grid = False)
#plt.show()

formula = '머리둘레 ~ C(태아수) + C(관측자수) + C(태아수):C(관측자수)'
lm = ols(formula, data).fit
()
print(anova_lm(lm))
