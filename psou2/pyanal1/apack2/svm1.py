'''
Created on 2018. 12. 4.

SVM으로 XOR 처리
'''
from sklearn import svm, metrics
from pandas import DataFrame

xor_data = [
    [0,0,0],
    [0,1,1],
    [1,0,1],
    [1,1,0],
]

xor_df = DataFrame(xor_data)
print(xor_df)
data = xor_df.iloc[:, 0:2]
label = xor_df.iloc[:, 2]
print(data)
print(label)

print('**' * 40)

# SVM 모델
train_model = svm.SVC()
train_model.fit(data, label)
#print(train_model)

pred = train_model.predict(data)
print(pred)

ac_score = metrics.accuracy_score(label, pred)
print('정확도 : ', ac_score)

print('\n 학습및 검정이 완료되었으므로 테스트 자료로 분류 해보기')
data2 = DataFrame([[0,0,1,1],[0,0,1,1]])
#data2 = DataFrame([[3,3,1,1],[0,0,1,1]]
data2 = data2.T
print(data2)
pred2 = train_model.predict(data2)
print('예측결과 : ', pred2)

ac_report = metrics.classification_report(label, pred)
print('report : ', ac_report)
#                            정밀도                재현율
# report :                precision    recall  f1-score   support
# 
#            0       1.00      1.00      1.00         2
#            1       1.00      1.00      1.00         2
# 
#    micro avg       1.00      1.00      1.00         4
#    macro avg       1.00      1.00      1.00         4
# weighted avg       1.00      1.00      1.00         4


