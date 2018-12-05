'''
Created on 2018. 12. 5.

MNIST 학습
'''
from sklearn import model_selection, svm, metrics

# CSV 파일을 읽어 들이고 가공
def load_csv(fname):
    labels = []
    images = []
    with open(fname, "r") as f:
        for line in f:
            cols = line.split(",") # train.csv 데이터를 (,) 기준으로 쪼갬.
            if len(cols) < 2: # cols에 길이가 2보다 작을 경우.
                continue # 건너 뜀.
            labels.append(int(cols.pop(0))) # labels에 cols에 있는  처음인덱스인 0을 추가.
            vals = list(map(lambda n: int(n) / 256, cols)) # n에 cols 값을 줌. n값을 256으로 나누는데...왜?
            # 나눈 값을 map 형식으로 list에 넣음. 그 값은 vals에 저장.
            images.append(vals) # images에 추가.
    return {"labels":labels, "images":images} # return 값은 labels, images.

data = load_csv("./mnist/train.csv")
test = load_csv("./mnist/t10k.csv")
#print(data)
#print(test)

#clf = svm.SVC()
clf = svm.LinearSVC() # 모델 생성.
clf.fit(data["images"], data["labels"]) # fit() : 훈련 데이터와 훈련 범주를 입력받아 분류기를 만듬.
predict = clf.predict(test["images"]) # predict() : 새로운 입력 데이터에 대한 출력 데이터 예측.
print(predict)

# 결과 확인
ac_sco = metrics.accuracy_score(test["labels"], predict) # 정답률.
cl_rep = metrics.classification_report(test["labels"], predict) # 리포트
print("정답률 : ", ac_sco)
print("리포트 : ")
print(cl_rep)

print('**' * 40)
#print(data["images"]) # [[0.0, 0.0, 0.0, ...]
#print(data["labels"]) # [5, 0, 4, 1, 9, ...]
#print(len(data["images"])) # 1001
#print(len(data["labels"])) # 1001
#print(data["images"][0]) # [0.0, 0.0, 0.0, ..]
#print(data["labels"][0]) # 5
#print(len(data['images'][0])) # 784

import numpy as np
# array로 되어 있음.
arr = np.array([test['images'][3]]) # 기존에 있던 값으로 함. 2차원이기 때문에 밑에서 유저가 만들때도 2차원으로 해야함.
# print(arr)
new_predict = clf.predict(arr)
#print(new_predict)

# 이미지를 시각화
from PIL import Image
import matplotlib.pyplot as plt
# print(type([test['images'][3]])) # np이기 때문에 List -> ndarray로 변경.
# print(type(np.array(test['images'][0])))
# plt.imshow(np.array(test['images'][0]).reshape(28, 28), cmap = 'gray') 
# reshape는 784개 1차원 배열을 다차원 배열인 28 X 28 행렬을 만들어 준것. 이미지를 보이기 위해서.
# plt.show()

print('**' * 40 , "my")
pic = Image.open('abc.png') # 분석할 이미지 가져오기.
img = np.array(pic.resize((28, 28), Image.ANTIALIAS).convert("L")) # 이미지에 w, h를 28,28로 조정. # ANTIALIAS : 이미지를 부드럽게 처리.
#print(img)
data = img.reshape([1, 784]) # data 값을 1차원 배열로 설정
#print(data)
data = data / 255 # data 값들을 255로 나누기. 화소강도 숫자화.

print(data[0]) # 0.         0.    ... 0.00784314 0.01568627 : 화소강도를 숫자화.
print(type(data)) # 타입 확인. # <class 'numpy.ndarray'>


plt.imshow(data.reshape(28, 28), cmap = 'gray') # 2차원 배열로(Matrix).
plt.show()

#print(data)

# 내가 만든 이미지 예측.
myarr = np.array([data[0]]) # 2차원배열로 되어있기 때문에 이차원 배열에 첫번째값을 읽음(넣으면 0번째에만 값있음)
#print(myarr)
new_predict2 = clf.predict(myarr) # 내가 만든 사진으로 분석하여 변수에 저장.
print(new_predict2) # 분석된 값 나
