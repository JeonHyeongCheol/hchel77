'''
Created on 2018. 12. 13.

시험성적 총점 예측. 독립변수 복수
'''

import tensorflow as tf

x1_data = [73., 93., 89., 96., 73.]
x2_data = [80., 88., 91., 98., 66.]
x3_data = [75., 93., 90., 100., 70.]
y_data = [152., 185., 180., 196., 142.]

x1 = tf.placeholder(tf.float32)
x2 = tf.placeholder(tf.float32)
x3 = tf.placeholder(tf.float32)
y = tf.placeholder(tf.float32)


w1 = tf.Variable(tf.random_normal([1]))
w2 = tf.Variable(tf.random_normal([1]))
w3 = tf.Variable(tf.random_normal([1]))
b = tf.Variable(tf.random_normal([1]))

h = x1 * w1 + x2 * w2 + x3 * w3 + b

cost = tf.reduce_mean(tf.square(h - y_data))
optimizer = tf.train.GradientDescentOptimizer(0.00001)
train = optimizer.minimize(cost)

sess = tf.Session()
sess.run(tf.global_variables_initializer())

for s in range(5000):
    cost_v, h_v, _ = sess.run([cost, h, train], feed_dict={x1: x1_data, x2: x2_data, x3: x3_data, y: y_data})
    if s % 500 == 0:
        print(s, 'cost : ', cost_v, 'pred : ', h_v)