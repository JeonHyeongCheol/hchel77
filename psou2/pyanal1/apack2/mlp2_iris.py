'''
Created on 2018. 12. 4.

mlp를 이용한 iris 분석.
'''

from sklearn import datasets
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score
from sklearn.neural_network import MLPClassifier
import numpy as np

iris = datasets.load_iris() # dataset에서 여러가지 데이터를 가져와서 사용 할 수 있음.
x = iris.data[:, [2,3]] # petal.length, petal.width
y = iris.target

# 학습용 / 검정용 자료로 분리.
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size = 0.3, random_state = 0) # test_szie : 분석할 데이터와 테스트데이터를 분리(0.3 : 0.7), random_state : 난수 고정 값.

# 표준화(Scaling) - 전처리 : 안정성, 수렴속도를 향상. 오버플로, 언더플로 방지 효과.
sc = StandardScaler()
sc.fit(x_train)
x_train_std = sc.transform(x_train)
x_test_std = sc.transform(x_test)

ml = MLPClassifier(hidden_layer_sizes=(10))
 
result = ml.fit(x_train_std, y_train) # 학습자료로 모델 실행.
print(result)

y_pred = ml.predict(x_test_std) # 학습모델을 검정
print('실제값 : ', y_test)
print('추정값 : ', y_pred)

print('총 검정 수 %d, 오류 수 %d'%(len(y_test), (y_test != y_pred).sum()))

# 정확도 구하기 1
print('분류 정확도 : %.3f'%accuracy_score(y_test, y_pred))

# 정확도 구하기 2
import pandas as pd
con_mat = pd.crosstab(y_test, y_pred, rownames=['예측값'], colnames=['관측값'])
print(con_mat)
print((con_mat[0][0] + con_mat[1][1] + con_mat[2][2]) / len(y_test)) # 정확도

# 새로운 petal.length, petal.width 자료로 꽃 종류 예측(분류)
#print(x_test_std) #[[0.70793846  1.51006688]..]
new_data = np.array([[0.7,1.5],[5.7,5.5],[3.1,1.2]])
new_data_std = sc.transform(new_data) # 표준화.
print(new_data)
new_pred = ml.predict(new_data_std)
print('꽃 종류 예측 결과 : ', new_pred)