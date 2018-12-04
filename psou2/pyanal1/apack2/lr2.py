'''
Created on 2018. 12. 4.

statsmodels 패키지의 glm(), logit()을 사용
'''
import statsmodels.api as sm
import statsmodels.formula.api as smf
import numpy as np

mtcars = sm.datasets.get_rdataset('mtcars').data
print(mtcars)
mtcar = mtcars.loc[:, ['mpg','hp','am']] # 0 : 자동, 1 : 수동
print(mtcar.head(3))

# 연비와 마력수에 따라 변속기 분류 모델 생성 후 처리 1(logit 사용)
formula = 'am ~ hp + mpg'
result = smf.logit(formula, data = mtcar).fit() # 수식을 줌.
print(result.summary())

# Optimization terminated successfully.
#          Current function value: 0.300509
#          Iterations 9
#                            Logit Regression Results                           
# ==============================================================================
# Dep. Variable:                     am   No. Observations:                   32
# Model:                          Logit   Df Residuals:                       29
# Method:                           MLE   Df Model:                            2
# Date:                Tue, 04 Dec 2018   Pseudo R-squ.:                  0.5551
# Time:                        15:01:57   Log-Likelihood:                -9.6163
# converged:                       True   LL-Null:                       -21.615
#                                         LLR p-value:                 6.153e-06
# ==============================================================================
#                  coef    std err          z      P>|z|      [0.025      0.975]
# ------------------------------------------------------------------------------
# Intercept    -33.6052     15.077     -2.229      0.026     -63.156      -4.055
# hp             0.0550      0.027      2.045      0.041       0.002       0.108
# mpg            1.2596      0.567      2.220      0.026       0.147       2.372
# ==============================================================================
# 
# Possibly complete quasi-separation: A fraction 0.12 of observations can be
# perfectly predicted. This might indicate that there is complete
# quasi-separation. In this case some parameters will not be identified.


pred = result.predict()
print('\n 예측값 : \n', np.around(pred))
print('\n 실제값 : \n', mtcar['am'])

print(result.pred_table())
print((16 + 10) / len(mtcar))

print('**' * 40)

# 연비와 마력수에 따라 변속기 분류 모델 생성 후 처리 2(glm 이용)
result2 = smf.glm(formula = formula, data = mtcar, family = sm.families.Binomial()).fit()
#print(result2)
pred2 = result2.predict()
#print('예측값 : ', np.around(pred2)) 
print('예측값 : ', np.rint(pred2)) # 예측값은  rint로 볼 수도 있음.
print('실제값 : ', mtcar['am'])
print()

# 새로운 자료(연비, 마력)로 변속기 추정
import copy
newdf = copy.deepcopy(mtcar.iloc[:2])
print(newdf)
newdf['mpg'] = [10, 30]
newdf['hp'] = [100, 130]
print(newdf)
print()
pred3 = result2.predict(newdf)
print(np.rint(pred3))
