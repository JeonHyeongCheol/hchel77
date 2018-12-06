'''
Created on 2018. 12. 6.

MLP (다층 신경망) : 입력층과 출력층 사이에 중간층을 
'''
from sklearn.neural_network import MLPClassifier
from sklearn.model_selection import train_test_split
x = [[0,0], [0,1], [1,0], [1,1]]
y = [0,1,1,0]

clf = MLPClassifier(hidden_layer_sizes=(3,2), random_state=0)
print(clf)
clf.fit(x,y)
x = [[1,1],[1,1],[1,0],[1,2]]
print(clf.predict(x))
print('정확도 : ', clf.score(x, y))

print('**' * 50)
from sklearn.datasets import load_breast_cancer
cancer = load_breast_cancer()
print(cancer.keys())

x = cancer['data']
y = cancer['target']
print(x[0])
print(y[0])

print(x.shape) # (569, 30)
print(y.shape) # (569,)

x_train, x_test, y_train, y_test = train_test_split(x,y)
print(x_train.shape) # (426, 30)
print(x_test.shape) # (143, 30)

from sklearn.preprocessing import StandardScaler
scaler = StandardScaler()
scaler.fit(x_train)
scaler.fit(x_test)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)
print(x_train[:1])

mlp = MLPClassifier(hidden_layer_sizes=(30, 30, 30), random_state=1)
mlp.fit(x_train, y_train)

pred = mlp.predict(x_test)
print(x_test)
print('예측값 : ', pred)
print()
print('실제값 : ', y_test[:50])
print('예측값 : ', pred[:50])

print('정확도 : ', mlp.score(x_test, y_test)) # mlp에 있는 모델과 x,y_test와 비교해 정확도를 보여줌.

from sklearn.metrics import classification_report
print(classification_report(y_test, pred))