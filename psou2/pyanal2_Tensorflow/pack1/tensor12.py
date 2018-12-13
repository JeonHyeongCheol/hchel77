'''
Created on 2018. 12. 13.

독립변수가 복수 개
'''
import tensorflow as tf

x1_data = [1, 0, 3, 0, 6]
x2_data = [0, 2, 0, 4, 5]
y_data = [1, 2, 3, 4, 5]

w1 = tf.Variable(tf.random_uniform([1], -1.0, 1.0))
w2 = tf.Variable(tf.random_uniform([1], -1.0, 1.0))
b = tf.Variable(tf.random_uniform([1], -1.0, 1.0))

h = w1 * x1_data + w2 * x2_data + b
cost = tf.reduce_mean(tf.square(h - y_data))
optimizer = tf.train.GradientDescentOptimizer(0.01)
train = optimizer.minimize(cost)

sess = tf.Session()
sess.run(tf.global_variables_initializer())

print('step\t', 'cost\t', 'w1\t', 'w2\t', 'b')
for step in range(1001):
    sess.run(train)
    if step % 10 == 0:
        print(step, sess.run(cost), sess.run(w1), sess.run(w2), sess.run(b))
        
sess.close()