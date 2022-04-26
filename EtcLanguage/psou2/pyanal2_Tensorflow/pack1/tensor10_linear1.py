'''
Created on 2018. 12. 13.

Weight 값을 변화시켜 효율적인 회귀 모델 생성 과정을 Text 출력과 시각화 작업
'''
import numpy as np
import matplotlib.pyplot as plt


vectors = []
w = 0.1
b = 0.3

for i in range(1000):
    x1 = np.random.normal(0.0, 0.55)
    y1 = x1 * w + b + np.random.normal(0.0, 0.03)
    vectors.append([x1, y1])

x_data = [v[0] for v in vectors]
y_data = [v[1] for v in vectors]
plt.plot(x_data, y_data, 'ro')
plt.legend()
plt.show()

import tensorflow as tf

w = tf.Variable(tf.random_uniform([1], -1.0, 1.0)) # shape : (1,
b = tf.Variable(tf.zeros([1]))
model = w * x_data + b # 회귀식.
loss = tf.reduce_mean(tf.square(model - y_data))
optimizer = tf.train.GradientDescentOptimizer(0.5) # 학습률 : 0.5 간격이 0.5라고 생각하면 됨. 기울기가 0이되는 곳이 1이되는 것.
train = optimizer.minimize(loss)

init = tf.global_variables_initializer()
sess = tf.Session()
sess.run(init)

for step in range(10):
    sess.run(train)
    print(step, sess.run(w), sess.run(b), sess.run(loss))
    
    # 시각화
    plt.plot(x_data, y_data, 'ro')
    plt.plot(x_data, sess.run(w) * x_data + sess.run(b))
    plt.xlabel('x')
    plt.ylabel('y')
    plt.xlim(-2, 2)
    plt.ylim(0.1, 0.5)
    plt.show()
    
sess.close()