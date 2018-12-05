'''
Created on 2018. 12. 5.

BMI(체질량 지수) = 몸무게(Kg) / (키(cm) * 키(cm)) 
'''
import random


# 만든 후 주석 처리
'''
def calc_bmi(h, w):
    bmi = w / (h / 100) ** 2 # 들어오는 값에 대해 bmi 계산.
    # 맞는 값에 대해서 리턴
    if bmi < 18.5: return 'thin'
    if bmi < 25: return 'normal'
    return 'fat'

fp = open('bmi.csv', 'w', encoding = 'utf-8')
fp.write('height,weight,label\n')

# 무작위로 자료 생성
cnt = {'thin':0, 'normal':0, 'fat':0}

random.seed(12)

for i in range(50000): # 5만개 생성.
    h = random.randint(130,200) # 130cm ~ 200cm
    w = random.randint(35, 100) # 35Kg ~ 100Kg
    label = calc_bmi(h, w) # h, w을 함수에 넣어서 label 변수에 저장.
    cnt[label] += 1 # 나오는 thin, normal, fat에 대해서 카운트.
    fp.write('{0},{1},{2}\n'.format(h,w,label))

fp.close()
print('저장 성공 결과 : ', cnt)
'''

# SVM으로 분류 분석
from sklearn import svm, metrics
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
import pandas as pd

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
#clf = svm.SVC().fit(data_train, label_train) # 기존.
clf = svm.LinearSVC().fit(data_train, label_train) # 개량형.
print(clf)

# 데이터 검증
from sklearn import model_selection
cross_vali = model_selection.cross_val_score(clf, data_train, label_train, cv = 5)
print('각각의 검증 정답율 : ', cross_vali)
print('평균 검증 정답율 : ', cross_vali.mean())

pred = clf.predict(data_test)
print(data_test) # 28650    0.91   0.835 ... 45834    0.37   0.975
print(pred) # ['fat' 'fat' 'fat' ... 'fat' 'thin' 'thin']

ac_score = metrics.accuracy_score(label_test, pred) # 검정 데이터와 모델
print('정확도 : ', ac_score)
cl_report = metrics.classification_report(label_test, pred)
print('리포트 : ', cl_report)

# 시각화
tbl2 = pd.read_csv("bmi.csv", index_col = 2)
print(tbl2.tail(3)) # 끝에서 3개만.

fig = plt.figure() # 이미지 저장 시작.

def scatter_func(lbl, color): # 이미지 함수 생성.
    b = tbl2.loc[lbl] # tbl2 값을 가져와서 변수별로.
    plt.scatter(b['weight'], b['height'], c=color, label=lbl) # 색깔 지정.

# 변수별 색깔 지정.
scatter_func('fat', 'red') 
scatter_func('normal', 'yellow')
scatter_func('thin', 'blue')
plt.legend() # 라벨 표시.
plt.savefig('bmi_test.png') # 이미지 저장.
plt.show()
