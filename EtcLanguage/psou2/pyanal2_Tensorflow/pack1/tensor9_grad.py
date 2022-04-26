'''
Created on 2018. 12. 12.

경사하강법 알고리즘 수행
'''
import tensorflow as tf
x = tf.placeholder(tf.float32)
y = tf.placeholder(tf.float32)
w = tf.Variable(tf.random_normal([1]))
b = 0

hypothesis = w * x + b
cost = tf.reduce_mean(tf.square(hypothesis - y))

print('경사하강법 수식')
# learning_rate = 0.01
# gradient = tf.reduce_mean((w * x - y)/x)
# decent = w - learning_rate * gradient
# train = w.assign(decent)

# 경사하강법 수식 함수가 있음.

optimizer = tf.train.GradientDescentOptimizer(learning_rate=0.01)
train = optimizer.minimize(cost) # 최소값이 되었을때 알아서 멈춤.

sess = tf.Session()
sess.run(tf.global_variables_initializer())

for step in range(50):
    sess.run(train, feed_dict={x:[1,2,3], y:[1,2,3]})
    print(step, ' ', sess.run(cost, feed_dict={x:[1,2,3], y:[1,2,3]}), sess.run(w))
    
sess.close()