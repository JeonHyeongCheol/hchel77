'''
Created on 2018. 12. 12.

arange
'''

import tensorflow as tf

#tf.enable_eager_execution() # 일시적으로 확인 할 수 있는 것. 결과 값을.. Variable은 안됨.

node1 = tf.constant(3.0, tf.float32)
node2 = tf.constant(4.0)
print(node1)
print(node2)
c = node1 + node2
print(c)

sess = tf.Session()
print(sess.run([node1, node2]))
print(sess.run((node1, node2)))
#print(sess.run({node1, node2})) # Set Type X
node3 = tf.add(node1, node2)

print('**' * 50)
a = tf.placeholder(tf.float32)
b = tf.placeholder(tf.float32)
#ab_mul = a * b
ab_mul = tf.multiply(a, b)

print(sess.run(ab_mul, {a:3, b:4})) # 스칼라 곱.
print(sess.run(ab_mul, {a:[1,3], b:[2,4]})) # 벡터 곱.

ab_mul_add = ab_mul + 2
print(sess.run(ab_mul_add, {a:3, b:4.5}))

print('**' * 50)
w = tf.Variable([.3], tf.float32)
b = tf.Variable([-.3], tf.float32)
x = tf.placeholder(tf.float32)
model1 = w * x + b

init = tf.global_variables_initializer() # 모든 변수 초기화.
sess.run(init)

print(sess.run(model1, {x:[1,2,3,4]}))

print('**' * 50)

print('--- 변수 초기화 정리 ---')
x = tf.Variable(3, name='x')
y = tf.Variable(4, name='y')
f = x * x * y + 2
sess.run(x.initializer) # 초기화  방법1.
sess.run(y.initializer)
print(sess.run(f))
sess.close()

print('**' * 50)

with tf.Session() as sess: 
    x.initializer.run() # 초기화 방법2.
    y.initializer.run()
    result = f.eval()
    print(result)
    
print('**' * 50)

init = tf.global_variables_initializer()
with tf.Session() as sess:
    init.run() # 초기화 방법3.
    result = f.eval()
    print(result)

print('**' * 50)

init = tf.global_variables_initializer() # 초기화 방법4.
sess = tf.InteractiveSession()
init.run()
print(f.eval())