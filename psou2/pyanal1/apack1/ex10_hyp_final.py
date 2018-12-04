'''
Created on 2018. 11. 29.

RDBMS의 자료로 자료검정
'''
import pandas as pd
import numpy as np
import scipy.stats as stats
import MySQLdb
import ast
import matplotlib.pyplot as plt
from statsmodels.formula.api import ols
import statsmodels.api as sm

# db 정보를 다른파일에 저장 한 후 불러와 사용.
with open('dbconn.txt', 'r') as f:
    config = f.read()
    
config = ast.literal_eval(config) # str(문자열) -> dict으로 변경 
#print(type(config))

conn = MySQLdb.connect(**config)
cursor = conn.cursor()
sql = """
select jikwon_no, jikwon_name, jikwon_jik, jikwon_pay
from jikwon
where jikwon_jik = '과장'
"""
# cursor.execute(sql)
# for data in cursor.fetchall():
#     print('%s %s %s %s'%data)

print('\n', '**' * 10, "교차분석(이원카이제곱) 검정", '**' * 10)
df = pd.read_sql('select * from jikwon', conn)
print(df.head(3))

print('\n', '**' * 10, "각 부서와 직원 평가 점수 간의 관련성 분석 : 귀무 - 관련이 없다", '**' * 10)
buser = df['buser_num'] # 범주형
#print(buser)
rating = df['jikwon_rating']
#print(rating)
ctab = pd.crosstab(buser, rating)
#print(ctab) # 빈도수
chi, p, df, exp = stats.chi2_contingency(ctab)
print("chi검정통계량 : {}, p-value : {}".format(chi, p))
# chi검정통계량 : 7.339285714285714, p-value : 0.2906064076671985
# 해석 : p : 0.29 > 0.05 이므로 귀무가설 채택 - 관련이 없다.

print('\n', '**' * 10, "각 부서와 직급 간의 관련성 분석  : 귀무 - 관련이 없다", '**' * 10)
df = pd.read_sql('select * from jikwon where jikwon_no <= 10', conn)
buser = df['buser_num']
jik = df['jikwon_jik']
ctab2 = pd.crosstab(buser, jik)
print(ctab2)
chi2, p2, _, _ = stats.chi2_contingency(ctab2)
print('chi검정통계량 : {}, p-value : {}'.format(chi2, p2))
# 해석 : p : 0.08 > 0.05 이므로 귀무가설 채택 - 관련이 없다.

print('\n', '**' * 10, "T-test(평균 차이분석 : 독립변수(x)-범주, 종속변수(y)-연속", '**' * 10)
print('10, 20번 부서 간 평균연봉 값의 차이여부를 검정')

# 귀무가설 : 두 부서간 평균연봉에 차이가 없다.

df_10 = pd.read_sql('select buser_num, jikwon_pay from jikwon where buser_num=10', conn)
df_20 = pd.read_sql('select buser_num, jikwon_pay from jikwon where buser_num=20', conn)
buser_10 = df_10['jikwon_pay'] # 10번 부서 집단의 연봉
buser_20 = df_20['jikwon_pay'] # 20번 부서 집단의 연봉
t_result = stats.ttest_ind(buser_10, buser_20)
print(t_result)
# statistic(검정통계량)=0.4683, p-value=0.6455
# 해석 : p-value : 0.6455 > 0.05 이므로 귀무가설 채택(평균 연봉에 차이가 없다).
print(np.mean(buser_10), np.mean(buser_20)) # 각 부서간 연봉 평균. 

print('\n', '**' * 10, "ANOVA(분산분석(집단 3개이상) : 독립변수(x) - 범수, 종속변수(y) - 연속", '**' * 10)
df3 = pd.read_sql("select * from jikwon", conn)
print(df3)
buser = df3['buser_num'] # x
pay = df3['jikwon_pay'] # y

# 각 부서에 대한 직원 페이
group1 = df3[df3['buser_num'] == 10]['jikwon_pay']
group2 = df3[df3['buser_num'] == 20]['jikwon_pay']
group3 = df3[df3['buser_num'] == 30]['jikwon_pay']
group4 = df3[df3['buser_num'] == 40]['jikwon_pay']
print(group1)

plot_data = [group1, group2, group3,group4]
ax = plt.boxplot(plot_data)
plt.show()

f_sta, pv = stats.f_oneway(group1, group2, group3,group4)
if pv < 0.05:
    print('유의확률(p-value) 값이 작아므로 부서 간 평균연봉에 유의미한 차이가 있다.')
else:
    print('유의확률(p-value) 값이 작아므로 부서 간 평균연봉에 유의미한 차이가 없다.')
    print('귀무가설 채택')
    
print()
reg_model = ols('pay ~ C(buser)', data = df3).fit()
result = sm.stats.anova_lm(reg_model, type = 2)
print(result)
#             df        sum_sq       mean_sq         F    PR(>F)
# C(buser)   3.0  5.762976e+06  1.920992e+06  0.419107  0.740797
# Residual  26.0  1.191720e+08  4.583539e+06       NaN       NaN