'''
Created on 2018. 12. 6.

타이타닉 생존 여부
'''
from pandas.core.frame import DataFrame
from sklearn import model_selection
from sklearn.ensemble.forest import RandomForestClassifier
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
from sklearn.preprocessing.data import StandardScaler

import numpy as np
import pandas as pd


data = pd.read_csv('titanic.csv')
#print(data.head())

titanic = DataFrame(data)
#print(titanic['Age'].head(200))

titanic['Sex'] = titanic['Sex'].apply(lambda s:0 if s == 'male' else 1)
print(titanic)

x = titanic.iloc[:, [1,3,4]]
y = titanic.iloc[:, 0]


# 학습용 / 검정용 자료 분리.
titanic_x, test_x, titanic_y, test_y = train_test_split(x, y, test_size = 0.3, random_state = 0)
# print(titanic_x.head())
# print(titanic_y.head())

ml = RandomForestClassifier(criterion='entropy').fit(titanic_x, titanic_y) # (criterion='entropy', random_state = 0)
print(ml)

titanic_pred_y = ml.predict(test_x)
print('실제값 : ', test_y)
print('추정값 : ', titanic_pred_y)

print('총 검정 수 %d, 오류 수 %d'%(len(test_y), (test_y != titanic_pred_y).sum()))
print('분류 정확도 : %.3f'%accuracy_score(test_y, titanic_pred_y))

# 교차검증
print('교차검증 : \n',model_selection.cross_val_score(ml, titanic_x, titanic_y, cv=5))

# 새로운 데이터로 예측
new_data = np.array([[1, 0, 24],
                     [1, 1, 43],
                     [2, 1, 48],
                     [3, 0, 33],
                     [2, 0, 28]
                     ])

new_pred = ml.predict(new_data)
print('새로운 데이터 예측 : \n', new_pred)