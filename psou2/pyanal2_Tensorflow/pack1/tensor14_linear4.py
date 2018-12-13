'''
Created on 2018. 12. 13.

시험성적 총점 예측. 독립변수 복수 - vector type => matrix type 하면 효과적.
'''

import tensorflow as tf
import numpy as np
# 그냥 수동으로 입력.
# x_data = [[73., 80., 75.], # 기존 1차에서 2차원으로 변경.
#           [93., 88., 93.],
#           [89., 91., 90.],
#           [96., 98., 100.],
#           [73., 66., 70.]]
# y_data = [[152.], [185.], [180.], [196.], [142.]] # Matrix로 변경
# x_data에 1행이 152, 2행이 185 .. 쭈우욱..

# 파일로 읽어서 입력 시킴.(웹도 가능)
examdata = np.loadtxt('텐서회귀1.csv', delimiter=',', skiprows = 1, dtype=np.float32)
print(examdata)

x_data = examdata[:,0:-1]
y_data = examdata[:,[-1]] # y data를 파일로 읽어올 때 마지막 값이 출력 값으로 읽어 줘야 할 때 []를 꼭 쓸 것.

x = tf.placeholder(tf.float32, shape=[None, 3]) # x의 열의 갯수를 맞춰줘야 함.
y = tf.placeholder(tf.float32, shape=[None, 1]) # y의 열의 갯수를 맞춰줘야 함.


w = tf.Variable(tf.random_normal([3, 1])) # 입력 값 3, 출력 값 1.
b = tf.Variable(tf.random_normal([1])) # 출력 값 1.

h = tf.matmul(x, w) + b # 매트릭스 곱.

cost = tf.reduce_mean(tf.square(h - y_data))
optimizer = tf.train.GradientDescentOptimizer(0.00001)
train = optimizer.minimize(cost)

sess = tf.Session()
sess.run(tf.global_variables_initializer())

for s in range(7000):
    cost_v, h_v, _ = sess.run([cost, h, train], feed_dict={x:x_data, y:y_data})
    if s % 500 == 0:
        print(s, '\n', 'cost : ', cost_v, '\n', 'pred : \n', h_v)
        
print('새로운 x값에 대한 y값 예측하기')
print('예측 결과는', sess.run(h, feed_dict={x:[[100., 90., 100.]]})) # New Data.
print('예측 결과는', sess.run(h, feed_dict={x:[[100., 90., 100.],[20, 30, 20]]}))