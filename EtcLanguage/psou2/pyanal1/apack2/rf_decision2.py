'''
Created on 2018. 12. 6.

@author: kitcoop
'''
from sklearn import svm, metrics
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.ensemble.forest import RandomForestClassifier

tbl = pd.read_csv("bmi.csv")
print(tbl.head(3))
# 정규화.
label = tbl['label']
w = tbl['weight'] / 100 # 소수점으로.
h = tbl['height'] / 200 # m로 계산

wh = pd.concat([w,h], axis=1) 
# axis = 1 : 왼,오른쪽 으로 합치기(cbind), 0 : 위아래 합치기(rbind)
# R에 cbind, rbind와 유사.
print(wh.head(3))

data_train, data_test, label_train, label_test = train_test_split(wh, label)
print(data_train.shape)
print(data_test.shape)
print(label_train.shape)
print(label_test.shape)

# 학습 모델 생성 후 처리.
clf = RandomForestClassifier(criterion='entropy').fit(data_train, label_train)
print(clf)

# 데이터 검증
from sklearn import model_selection
cross_vali = model_selection.cross_val_score(clf, data_train, label_train, cv = 5)
print('각각의 검증 정답율 : ', cross_vali)
print('평균 검증 정답율 : ', cross_vali.mean())

pred = clf.predict(data_test)
#print(data_test) # 28650    0.91   0.835 ... 45834    0.37   0.975
#print(pred) # ['fat' 'fat' 'fat' ... 'fat' 'thin' 'thin']

ac_score = metrics.accuracy_score(label_test, pred) # 검정 데이터와 모델
print('정확도 : ', ac_score)
cl_report = metrics.classification_report(label_test, pred)
print('리포트 : ', cl_report)
