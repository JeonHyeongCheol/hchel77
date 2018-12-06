'''
Created on 2018. 12. 5.

Weather
'''

import numpy as np
import pandas as pd
from pandas import DataFrame
import urllib
from sklearn import metrics
from sklearn.naive_bayes import GaussianNB
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

url = "https://raw.githubusercontent.com/pykwon/python/master/testdata_utf8/weather.csv"
data = pd.read_csv(urllib.request.urlopen(url), delimiter = ',')
print(data.head(3))

# 데이터 전처리
rain_df = DataFrame(data)
data1 = rain_df.iloc[:, 1:4]
data2 = rain_df.iloc[:, 11]
#print(data1) 
#print(data2)

data = pd.concat([data1,data2], axis=1) # 데이터 합치기.
#print(data) 

data['RainTomorrow'] = data['RainTomorrow'].apply(lambda r:1 if r == 'Yes' else 0)  
#print(data)

data = np.array(data)

x = data[:, 0:3]
y = data[:, 3]

# 학습용 / 검정용 자료 분리.
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size = 0.3, random_state = 0)

ml = GaussianNB()
print(ml)

result = ml.fit(x_train, y_train) # 학습자료로 모델 실행.
print('예측치  : \n', result)

y_pred = ml.predict(x_test) # 학습 모델 검정.

print('실제값 : ', y_test)
print('추정값 : ', y_pred)

print('총 검정 수 %d, 오류 수 %d'%(len(y_test), (y_test != y_pred).sum()))

print('분류 정확도 : %.3f'%accuracy_score(y_test, y_pred))

ac_report = metrics.classification_report(y_test, y_pred)
print('리포트 :', ac_report)
# 교차검증
# train train train train test  (5가지 경우에 대해 accuracy 확인)
from sklearn import model_selection

print('교차검증 : \n',model_selection.cross_val_score(ml, x_train, y_train, cv=5))

# 새로운 데이터로 예측
new_data = np.array([[3.8, 29.3, 3.3],
                     [12, 22.2, 8.8],
                     [0, 12.1, 9.3],
                     [12.3, 34.5, 1.4],
                     [10, 15.7, 6.7]
                     ])
new_pred = ml.predict(new_data)
print('새로운 데이터 예측 : \n', new_pred)