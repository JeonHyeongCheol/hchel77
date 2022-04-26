'''
Created on 2018. 12. 4.

로지스틱 회귀분석 : 독립변수(연속형), 종속변수(범주형) - 분류
'''

from sklearn import datasets
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score
from sklearn.linear_model.logistic import LogisticRegression

iris = datasets.load_iris() # dataset에서 여러가지 데이터를 가져와서 사용 할 수 있음.
#print(iris)
#print(iris.DESCR)
x = iris.data[:, [2,3]] # petal.length, petal.width
y = iris.target
print(x.shape)
print(y.shape)

# 학습용 / 검정용 자료로 분리.
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size = 0.3, random_state = 0) # test_szie : 분석할 데이터와 테스트데이터를 분리(0.3 : 0.7), random_state : 난수 고정 값.
print(x_train.shape)
print(x_test.shape)
print(x_train[:3])

# 표준화(Scaling) - 전처리 : 안정성, 수렴속도를 향상. 오버플로, 언더플로 방지 효과.
sc = StandardScaler()
sc.fit(x_train)
x_train_std = sc.transform(x_train)
x_test_std = sc.transform(x_test)
print(x_train_std[:3])

# 모델 생성 후 객체로 저장
import pickle
filename = 'final_model.sav'

# ml = LogisticRegression(C=1.0) # 로지스틱 회귀모델 생성.
# print(ml)
# 
# result = ml.fit(x_train_std, y_train) # 학습자료로 모델 실행.
# print(result)
# pickle.dump(ml, open(filename, 'wb'))

ml = pickle.load(open(filename, 'rb'))

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
print((16 + 9 + 11) / len(y_test)) # 정확도

# * 붓꽃 자료에 대한 로지스틱 회귀 결과를 차트로 그리기 *
import numpy as np
# import matplotlib.pyplot as plt
# import matplotlib
# from matplotlib.colors import ListedColormap
# from matplotlib import font_manager, rc
# 
# font_name = font_manager.FontProperties(fname="c:/Windows/Fonts/malgun.ttf").get_name()
# rc('font', family=font_name)  #그래프에서 한글깨짐 방지용
# matplotlib.rcParams['axes.unicode_minus']= False
# 
# def plot_decision_region(X, y, classifier, test_idx=None, resolution=0.02, title=''):
#     markers = ('s', 'x', 'o', '^', 'v')  # 점표시 모양 5개 정의
#     colors = ('r', 'b', 'lightgreen', 'gray', 'cyan')
#     cmap = ListedColormap(colors[:len(np.unique(y))])
#     #print('cmap : ', cmap.colors[0], cmap.colors[1], cmap.colors[2])
#     
#     # decision surface 그리기
#     x1_min, x1_max = X[:, 0].min() - 1, X[:, 0].max() + 1
#     x2_min, x2_max = X[:, 0].min() - 1, X[:, 0].max() + 1
#     xx, yy = np.meshgrid(np.arange(x1_min, x1_max, resolution), 
#                              np.arange(x2_min, x2_max, resolution))
#     
#     # xx, yy를 ravel()를 이용해 1차원 배열로 만든 후 전치행렬로 변환하여 퍼셉트론 분류기의 
#     # predict()의 안자로 입력하여 계산된 예측값을 Z로 둔다.
#     Z = classifier.predict(np.array([xx.ravel(), yy.ravel()]).T)
#     Z = Z.reshape(xx.shape) #Z를 reshape()을 이용해 원래 배열 모양으로 복원한다.
#     
#     # X를 xx, yy가 축인 그래프상에 cmap을 이용해 등고선을 그림
#     plt.contourf(xx, yy, Z, alpha=0.5, cmap=cmap)
#     plt.xlim(xx.min(), xx.max())
#     plt.ylim(yy.min(), yy.max())
#     
#     X_test = X[test_idx, :]
#     for idx, cl in enumerate(np.unique(y)):
#         plt.scatter(x=X[y==cl, 0], y=X[y==cl, 1], c=cmap(idx), 
#                     marker=markers[idx], label=cl)
#         
#     if test_idx:
#         X_test = X[test_idx, :]
#         plt.scatter(X_test[:, 0], X_test[:, 1], c='', linewidth=1, 
#                     marker='o', s=80, label='testset')
#     
#     plt.xlabel('표준화된 꽃잎 길이')
#     plt.ylabel('표준화된 꽃잎 너비')
#     plt.legend(loc=2)
#     plt.title(title)
#     plt.show()
# print()
# 
# x_combined_std = np.vstack((x_train_std, x_test_std))
# y_combined = np.hstack((y_train, y_test))
# plot_decision_region(X=x_combined_std, y=y_combined, classifier=ml, 
#                     test_idx=range(105, 150), title='scikit-learn제공')

# 새로운 petal.length, petal.width 자료로 꽃 종류 예측(분류)
#print(x_test_std) #[[0.70793846  1.51006688]..]
new_data = np.array([[0.7,1.5],[5.7,5.5],[3.1,1.2]])
new_data_std = sc.transform(new_data) # 표준화.
print(new_data)
new_pred = ml.predict(new_data_std)
print('꽃 종류 예측 결과 : ', new_pred)