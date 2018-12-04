'''
Created on 2018. 12. 4.

ols()
'''
import pandas as pd

df = pd.read_csv('../testdata/drinking_water.csv')
print(df.head(3))
print(df.corr())

import statsmodels.formula.api as smf
result = smf.ols(formula = '만족도~적절성', data = df).fit() # 만족도에 영향을 미치는 변수는 적절성. # fit()은 예측값까지 만들어 놓음. R에서는 그냥 됨.
print(result.summary())
# y = 0.7789 + 0.7393 * x # 선형회귀모델.

#  OLS Regression Results                            
# ==============================================================================
# Dep. Variable:                    만족도    R-squared:                       0.588 # R-squared(결정계수) : 58%
# Model:                            OLS   Adj. R-squared:                  0.586 # 수정된 결정계수 : 58%
# Method:                 Least Squares   F-statistic:                     374.0 # F - 통계량 (모양적합도)
# Date:                Tue, 04 Dec 2018   Prob (F-statistic):           2.24e-52 # F - 검정에 의 한 P 값 
# Time:                        09:55:46   Log-Likelihood:                -207.44
# No. Observations:                 264   AIC:                             418.9
# Df Residuals:                     262   BIC:                             426.0
# Df Model:                           1                                         
# Covariance Type:            nonrobust                                         
# ==============================================================================
#                  coef    std err          t      P>|t|      [0.025      0.975]
# ------------------------------------------------------------------------------
# Intercept      0.7789      0.124      6.273      0.000       0.534       1.023
# 적절성            0.7393      0.038     19.340      0.000       0.664       0.815
# ==============================================================================
# Omnibus:                       11.674   Durbin-Watson:                   2.185
# Prob(Omnibus):                  0.003   Jarque-Bera (JB):               16.003
# Skew:                          -0.328   Prob(JB):                     0.000335
# Kurtosis:                       4.012   Cond. No.                         13.4
# ==============================================================================

# 다중 선형회귀
result2 = smf.ols(formula = '만족도 ~ 적절성 + 친밀도', data = df).fit()
print(result2.summary())