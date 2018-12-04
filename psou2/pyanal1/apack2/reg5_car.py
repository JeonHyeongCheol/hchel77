'''
Created on 2018. 12. 4.

mtcar로 회귀분석 모델 생성 후 추정치 구하기
'''

import statsmodels.api
import numpy as np
import statsmodels.formula.api as smf
import matplotlib.pylab as plt
from pandas import DataFrame, Series

mtcars = statsmodels.api.datasets.get_rdataset('mtcars').data
print(mtcars.head(3))
#print(mtcars.describe())
print(np.corrcoef(mtcars.hp, mtcars.mpg)) # 마력수와 연비. 두 개의 대한 상관관계(음의 상관관계를 가지고 있음).
print(mtcars.corr()) # 모든 컬럼의 대한 상관관계.

# plt.scatter(mtcars.hp, mtcars.mpg) # 산포도 확인
# plt.xlabel('hp')
# plt.ylabel('mpg')
# slope, intercept = np.polyfit(mtcars.hp, mtcars.mpg, 1)
# plt.plot(mtcars.hp, mtcars.hp * slope + intercept, 'b') # 선 그리기.
# plt.show()

# intercept : y절편을 뜻함..?
result = smf.ols('mpg ~ hp', data = mtcars).fit()
print(result.summary())

result2 = smf.ols('mpg ~ hp + wt', data = mtcars).fit()
print(result2.summary())

result3 = smf.ols('mpg ~ wt', data = mtcars).fit()
print(result3.summary()) # 마력수, 차체무게.
reg_2 = result3.predict() # 마력수, 연비.

print('\n 단순 선형회귀모델을 이용한 추정치 얻기 ')
kbs = result.predict() # 마력수, 연비
print(kbs)
print('실제값  : ', mtcars.mpg[0], '\n 추정값 : ', kbs[0]) # 실제값과 추정값 비교
print()

data = {
    'mpg' : mtcars.mpg,
    'mpg_predict':kbs
}
df = DataFrame(data)
print(df)

mtcars.wt = 6
ytn = result3.predict(DataFrame(mtcars.wt))
print(ytn[0])

mtcars.wt = 0.4
ytn = result3.predict(DataFrame(mtcars.wt))
print(ytn[0])

wt_new = DataFrame({'wt':[6,3,1,0.4]})
mpgs = result3.predict(wt_new)
print('예상연비 : \n', mpgs)

print('**' * 40)

print(mtcars[:3])
x = mtcars[['hp']].values
y = mtcars[['mpg']].values
print(x[:3])
print(y[:3])

from sklearn.linear_model import LinearRegression
lmodel = LinearRegression().fit(x, y)
print(lmodel)
print('기울기 : ', lmodel.coef_[0])
print('절편 : ', lmodel.intercept_[0])

new_hp = np.array([[250]])
pred_mpg = lmodel.predict(new_hp)
print('%s 마력의 경우 예상연비는 약 %s'.format(new_hp, pred_mpg))