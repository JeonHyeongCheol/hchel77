'''
Created on 2018. 12. 13.

개인 학습 시간에 따른 성적 점수 결과 예측
'''

import tensorflow as tf
import matplotlib.pyplot as plt

xdata = [1,2,3,4,5]
ydata = [10, 25, 35, 50, 55]

#plt.scatter(xdata, ydata)
#plt.show()

w = tf.Variable(tf.random_uniform([1], -100, 100))
b = tf.Variable(tf.random_uniform([1], -100, 100))
x = tf.placeholder(tf.float32)
y = tf.placeholder(tf.float32)

hypothesis = w * x + b
cost = tf.reduce_mean(tf.square(hypothesis - y))
optimizer = tf.train.GradientDescentOptimizer(0.01)
train = optimizer.minimize(cost)

sess = tf.Session()
sess.run(tf.global_variables_initializer())

for i in range(2000):
    sess.run(train, feed_dict={x:xdata, y:ydata})
    print(i, sess.run(cost, feed_dict={x:xdata, y:ydata}), ' ', sess.run(w), ' ', sess.run(b))
    
    
print()
print(sess.run(hypothesis, feed_dict={x:[6]}))
print(sess.run(hypothesis, feed_dict={x:[6, 7.5, 8]}))
sess.close()