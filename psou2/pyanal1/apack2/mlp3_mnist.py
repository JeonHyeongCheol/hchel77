'''
Created on 2018. 12. 6.

MNIST
'''
from sklearn.datasets import fetch_mldata
from sklearn.neural_network import MLPClassifier

mnist = fetch_mldata('MNIST original')
print(mnist)
print(mnist.data[0] / 255) # 표준화 시킴. 255로 나눠서 0인건 검정색.
print(mnist.target)

x, y = mnist.data / 255., mnist.target
x_train, x_test = x[:60000], x[60000:]
print(x_train.shape)
print(x_test.shape)
y_train, y_test = y[:60000], y[60000:]
print(y_train.shape)
print(y_test.shape)

mlp = MLPClassifier(hidden_layer_sizes=(50,))
print(mlp)

mlp.fit(x_train,y_train)
pred = mlp.predict(x_test)
print(pred)

print('train 정확도 : ', mlp.score(x_train,y_train))
print('test 정확도 : ', mlp.score(x_test,y_test))