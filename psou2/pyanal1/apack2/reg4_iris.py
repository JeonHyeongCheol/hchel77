'''
Created on 2018. 12. 4.

iris dataset
'''
import pandas as pd
import statsmodels.formula.api as smf
import seaborn as sns

iris = sns.load_dataset('iris')
print(iris.head(3))
print(iris.corr()) # 빈도수 확인.

result1 = smf.ols(formula = 'sepal_length ~ sepal_width', data = iris).fit()
print(result1.summary())
print(result1.rsquared)
# result1.R-squared : 0.014
print()

result2 = smf.ols(formula = 'sepal_length ~ petal_width', data = iris).fit()
print(result2.summary())
print(result2.rsquared)
# result2.R-squared : 0.669
print()

result3 = smf.ols(formula = 'sepal_length ~ petal_width + petal_length', data = iris).fit()
print(result3.summary())
print(result3.rsquared)
print()

# R과 달리 독립변수 영역에 .을 찍어 모든 칼럼을 지적하는 방법 불가.
col_select = "+".join(iris.columns.difference(['sepal_width', 'sepal_length', 'species'])) # list안에는 제외할 변수 입력하면, 제외한 나머지만 사용.
result4 = smf.ols(formula = 'sepal_length ~ ' + col_select , data = iris).fit()
print(result4.summary())
print(result4.rsquared)