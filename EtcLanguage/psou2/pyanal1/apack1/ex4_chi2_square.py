'''
Created on 2018. 11. 28.

이원카이제곱 - 교차분할표 이용

실습) 국가전체와 지역에 대한 인종 간 인원수로 독립성 검정 실습
두 집단(국가전체 - national, 특정지역 - la)의 인종 간 인원수의 분포가 관련이 있는가?
national = pd.DataFrame(["white"] * 100000 + ["hispanic"] * 60000 + ["black"] * 50000 + ["asian"] * 15000 + ["other"] * 35000)
la = pd.DataFrame(["white"] * 600 + ["hispanic"] * 300 + ["black"] * 250 + ["asian"] * 75 + ["other"] * 150)
'''

import pandas as pd
import scipy.stats as stats
from tracemalloc import Statistic

# 귀무가설 : 국가전체와 지역에 대한 인종 간 인원수는 관련이 없다.
# 대립가설 : 국가전체와 지역에 대한 인종 간 인원수는 관련이 있다.

#nat = pd.DataFrame(['white'] * 5 + ['hispanic'] * 2) # 그냥 데이터 만들어봄. 이렇게 데이터를 만들 수 있음.
#print(nat)

national = pd.DataFrame(["white"] * 100000 + ["hispanic"] * 60000 + ["black"] * 50000 + ["asian"] * 15000 + ["other"] * 35000)
la = pd.DataFrame(["white"] * 600 + ["hispanic"] * 300 + ["black"] * 250 + ["asian"] * 75 + ["other"] * 150)
#print(national)
#print(la)

national_tab = pd.crosstab(index=national[0], columns='count') # 빈도값 구하기.
la_tab = pd.crosstab(index=la[0], columns='count')
print("National : \n", national_tab)
print("la : \n", la_tab)

# 검정통계량 = sum((실제빈도 - 기대빈도)^2 / 기대빈도).
print('방법1 : 수식 사용 ------------------------------------------------------------')
observed = la_tab # 빈도값 집어 넣기.
national_ratio = national_tab / len(national) # 인구 비율.
print('national_ratio : ', national_ratio)

expected = national_ratio * len(la) # 기대값 : 예상 횟수.
print('expected : ', expected)
chi2_result = (((observed - expected) **2) / expected).sum()
print('chi2_result', chi2_result)
p_val = 1 - stats.chi2.cdf(x = chi2_result, df=4)
print("p_val", p_val) # 0.00113047
# c 누적
print()

print('방법2 : chisquare 사용 -----------------------------------------------------')
print(stats.chisquare(f_obs=observed, f_exp=expected))
#statistic = array([18.19480519), p-value=array[0.00113047]
print()

print('방법3 : chi2_contingency 사용 ----------------------------------------------')
# crosstab을 안쓰고 직접 작성
n_df = national_tab.rename(index = str, columns = {'count':'count_n'}) # count에 대한 칼럼명을 count_n이라  지정.
l_df = la_tab.rename(index = str, columns = {'count':'count_l'})
#print(n_df, type(n_df))
#print(l_df, type(n_df))
n_df["count_l"] = l_df["count_l"] # 열추가 
print(n_df)
print()

chi2, p, _, _ = stats.chi2_contingency(n_df)
print("통계량 : {}, p값 : {}".format(chi2, p))
# 통계량 : 18.099524243141698, p값 : 0.0011800326671747886

# 해석 : p-값 : 0.011 < 0.05 귀무가설 기각