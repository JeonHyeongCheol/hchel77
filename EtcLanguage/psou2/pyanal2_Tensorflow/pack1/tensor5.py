'''
Created on 2018. 12. 12.

placeholder() : template 처럼 값을 넣을 공간을 확보하고, feed_dict 속성으로 값과 맵핑.
'''
import tensorflow as tf
import numpy as np

ph = tf.placeholder(dtype= tf.float32, shape=[3,3]) # 타입 설정을 꼭 해줘야 함!!
y = tf.add(ph, 10.) # 노드임. 연결 해줌.

with tf.Session() as sess:
    #print(sess.run(y))
    rand_arr = np.random.rand(3, 3)
    print(sess.run(y, feed_dict={ph:rand_arr})) # ph : shape(행렬설정), feed_dict : 값.

print('**' * 50)

v1 = 3; v2 = 4; v3 = 5
ph1 = tf.placeholder(dtype=tf.int32)
ph2 = tf.placeholder(dtype=tf.int32)
ph3 = tf.placeholder(dtype=tf.int32)

print(ph1)
print(ph2)

result_value = ph1 + ph2 * ph3
print(result_value) # 하나가 만들어짐.

with tf.Session() as sess:
    fdict = {ph1:v1, ph2:v2, ph3:v3} # 각각의 변수를 초기화하고 값을 넣어주는 것. 이것이 placeholder의 기능.
    result = sess.run(result_value, feed_dict = fdict)
    print(result)
    
print('**' * 50)
arr1 = [[1,2,3], [4,5,6], [7,8,9]] # 2차 Tensor.
arr2 = [1,2,3] # 3차 Tensor
ph_arr1 = tf.placeholder(dtype=tf.float32)
ph_arr2 = tf.placeholder(dtype=tf.float32)
fdict = {ph_arr1:arr1, ph_arr2:arr2}
res = ph_arr1 * ph_arr2

with tf.Session() as sess:
    result2 = sess.run(res, fdict)
    print(result2)
    
print('**' * 50)
#a = tf.placeholder(dtype=tf.int32, shape = [None]) # 배열의 크기를 가변적으로 정의. 알아서 해줌.
a = tf.placeholder(dtype=tf.int32) # 이렇게해도 shape를 가변적으로 정의 해줌. 원래는 위에가 맞음.
#a = tf.placeholder(dtype=tf.int32, shape = None) # 이것도 가능.
b = 10
c = a * 10

with tf.Session() as sess:
    r1 = sess.run(c, feed_dict={a:[1,2,3,4,5]})
    print(r1)
    r2 = sess.run(c, feed_dict={a:[1,2]})
    print(r2)
    r3 = sess.run(c, feed_dict={a:[[1,2],[3,4]]})
    print(r3)
    r4 = sess.run(c, feed_dict={a:(1,2)}) # 튜플(Tuple)도 사용 가능.
    print(r4)
    r5 = sess.run(c, feed_dict={a:(1,)})
    print(r5)
    r6 = sess.run(c, feed_dict={a:100})
    print(r6)



    