'''
Created on 2018. 12. 12.

Tensorboard
'''
import tensorflow as tf

with tf.name_scope('group1'): # tensorboard에서 그룹핑 할 수 있음.
    a = tf.add(10, 20, name='a_add')
    b = tf.multiply(a, 5, name='b_mul')

with tf.name_scope('group2'):
    c = tf.add(3, 4, name="c_add")
    d = tf.multiply(c, 5, name="d_mul")

e = tf.add(b, d, name="e_add_result")

print()

with tf.Session() as sess:
    res = sess.run(e)
    print(res)
    tf.summary.FileWriter("./kbs/mbc", sess.graph)