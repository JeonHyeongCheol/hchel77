'''
Created on 2018. 12. 11.

변수 - 반드시 초기화
'''
import tensorflow as tf

hello = tf.constant(3.)

w = tf.Variable([1,2,3,4,5], dtype=tf.float32) 
v1 = tf.Variable([3])
v2 = tf.Variable([5])
v3 = v1 * v2 + 10  # 연산을 위한 Tensor.

print(v1)
print(v3)

with tf.Session() as sess: # with문을 걸면 모든 코드 실행 후 세션이 닫힘.
    #sess.run(v1.initializer) # 각 변수에 대한 초기화
    sess.run(tf.global_variables_initializer()) # 모든 변수에 대한 초기화 작업 실행.
    sess.run(v1)
    result1 = sess.run(w + 10)
    print(result1)
    result2 = sess.run(v3)
    print(result2)
    # sess.run() 자동으로 close 됨.
    
print('**' * 50)
m = tf.Variable(tf.random_normal([5,5], stddev = 0.3)) # 정규 분포에 따른 난수 발생.
n = tf.Variable(tf.zeros([10])) # 모든 변수가 제로(0)임.

a = tf.constant([3]) # 그냥 Tensor일 뿐.
b = tf.constant([4])
c = a * b
v_mul = tf.Variable([0]) # Tensor를 이용한 변수. 값을 받는 다는 것.
assign_op = tf.assign(v_mul, c) # c의 값을 v_mul에 넣어줌.
#  sess.run(tf.assign(변수 텐서명, 새로운 값)) # 새로운 값을 변수 Tensor에 넣어줌.

with tf.Session() as sess:
    sess.run(tf.global_variables_initializer())
    result3 = sess.run(m)
    print(result3)
    result2 = sess.run(n)
    print(result2)
    print()
    result3 = sess.run(assign_op)
    print(result3)