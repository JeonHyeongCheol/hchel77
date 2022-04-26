import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score
from sklearn import metrics 

df = pd.read_csv('C:/work/python_data_analysis/weather.csv')

X = df[['MinTemp','MaxTemp','Rainfall']]
Label = df['RainTomorrow'].apply(lambda x : 1 if x=='Yes' else 0)

train_x, test_x, train_y, test_y = train_test_split(X, Label)

#print(train_x.head())
#print(train_y.head())

# 모델 생성 및 학습
clf = GaussianNB()
clf = clf.fit(train_x,train_y)

# 테스트 데이터 예측
pred_test_y = clf.predict(test_x)

# accuracy 
print()
print('accuracy')
accuracy =sum(test_y == pred_test_y)/len(pred_test_y)
print(accuracy)
acc = accuracy_score(test_y, pred_test_y)
print(acc)

# classification_report
cl_report = metrics.classification_report(test_y, pred_test_y)
print()
print('classification_report')
print(cl_report)

# 교차검증
# train train train train test  (5가지 경우에 대해 accuracy 확인)
from sklearn import model_selection
print()
print('cross validation')
print(model_selection.cross_val_score(clf, train_x, train_y, cv=5))  # cv=5 => kfold(5) 

# my data
my_weather = np.array([[0.2, 16, 1]
                       ,[10, 14, 0]
                       ,[10, 13, 13]
                       ,[20, 30, 5]
                       ,[20, 21, 4]
                        ])
print()
print('my weather 예측')
print(clf.predict(my_weather))