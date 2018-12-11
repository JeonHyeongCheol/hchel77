'''
Created on 2018. 12. 11.

읽기
'''
import tensorflow as tf

tf.reset_default_graph()

# Create some variables.
v1 = tf.get_variable("v1", shape=[3])
v2 = tf.get_variable("v2", shape=[5])
x = tf.Variable(tf.random_normal([3]))

# Add ops to save and restore all the variables.
saver = tf.train.Saver()

# Later, launch the model, use the saver to restore variables from disk, and
# do some work with the model.
with tf.Session() as sess:
    # Restore variables from disk.
    saver.restore(sess, "./tmp/model.ckpt")
    print("Model restored.")
    # Check the values of the variables
    print("v1 : %s" % v1.eval())
    print("v2 : %s" % v2.eval())
    print("v2 : %s" % sess.run(v1))
    print("v2 : %s" % sess.run(v2))
    print("x : ", sess.run(x))