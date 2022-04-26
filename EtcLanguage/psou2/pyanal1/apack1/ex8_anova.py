'''
Created on 2018. 11. 29.

anova를 하게 되면 사후 검정을 하여야 함.

* 서로 독립인 세 집단의 평균 차이 검정
실습) 세 가지 교육방법을 적용하여 1개월 동안 
교육받은 교육생 80명을 대상으로 실기시험을 실시. 
three_sample.csv'
'''

import pandas as pd
import scipy.stats as stats
from statsmodels.formula.api import ols
import urllib
import matplotlib.pyplot as plt
import statsmodels.api as sm # anova

# 파일에서 읽을 때
#data = pd.read_csv('../testdata/three_sample.csv')

# 웹에서 직접 파일을 읽어옴(GitHub)
url = "https://raw.githubusercontent.com/pykwon/python/master/testdata_utf8/three_sample.csv"
data = pd.read_csv(urllib.request.urlopen(url), delimiter=",", na_values=" ") # delimiter : 구분자, na_value : 공백인 경우 NaN으로 처리
print(data.head(3))

# 귀무가설 : 세 가지 교육방법에 따른 시험점수에 차이가 없다.
# 대립가설 : 세 가지 교육방법에 따른 시험점수에 차이가 있다.

#print(data.info())
#print(data.describe())
#plt.boxplot(data.score) # 보고 outlier(동떨어진 값)을 제거해주어야 함.
#plt.hist(data.score)
#plt.show()

# outlier(이상치) 처리
data = data.query('score <= 100') # 100이하에 값만 취한 값을 data 변수에 저장.
#plt.hist(data.score) # 이상치 처리 후 그래프로 정규성을 띄는지 확인.
#plt.show()
print(stats.shapiro(data.score)) # p-value 0.2986 > 0.05이므로 정규성을 띔.

# 교차표 : 교육방법별 건수
data2 = pd.crosstab(index = data['method'], columns = 'count') # columns에 count는 갯수를 카운트해서 총 합을 보여줌.
#print(data2)

reg_model = ols("data['score'] ~ data['method']", data = data).fit() # 앞에는 종속(변하는)변수, 뒤에는 독립(종속에 영향을 미치는)변수.
ano_result = sm.stats.anova_lm(reg_model, type = 2) # ols를 사용하면 뒤에 변수는 거의 2를 줌.
print(ano_result)
# F값, P값이 중요함.
# F 통계랑 검정에서 0.05(95%)내 일 때는 +-1.96안에 있어야지 채택역안에 있다고 할 수 있음.
# F값 : 0.122228 P값 : 0.727597 < 1.96안에 있으므로 채택 될 수 있다고 할 수 있음.
# 강사님 말씀 : 분산분석에서 신뢰수준 95%에서는 -1.96 ~ +1.96의 범위가 귀무가설 채택역임.

# 해석 : 귀무가설 채택.

print('**' * 40)

reg_model2 = ols("data['score'] ~ data['method'] + data['survey']", data = data).fit()
ano_result2 = sm.stats.anova_lm(reg_model2, type = 2)
print(ano_result2)
'''
                df(자유도) sum_sq(SST) mean_sq(평균처리제곱값) F    PR(>F)
data['method']   1.0     27.980888       27.980888  0.120810  0.729131
data['survey']   1.0     27.324458       27.324458  0.117976  0.732201
Residual        75.0  17370.810039      231.610801       NaN       NaN
'''

print('**' * 40)

'''
사후검정(Post Hoc Test) : 연구가설이 채택되었다면…
구체적으로 어떤 교육방법 간에 차이가 있는지를 확인할 수 있다. (R의 경우 Tukey HSD() 등)
분산분석의 사후검정은 예를 들어 분산분석에서 ‘3가지 교육방법에 따른 실기시험의 평균에 차이가 있다.’ 라는 
결론이 나왔다면 구체적으로 어떤 교육방법 간에 차이가 있는지를 보여준다.
'''
from statsmodels.stats.multicomp import pairwise_tukeyhsd
result = pairwise_tukeyhsd(data.score, data.method)
print(result)
result.plot_simultaneous() # 표에서 쭉 일자이면 비슷하다는 것. 일자이지 않고 떨어져 있으면 비슷하지 않다는 것.
plt.show()