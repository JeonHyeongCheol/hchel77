'''
Created on 2018. 12. 11.

상수와 변수의 구조
'''
import tensorflow as tf

g1 = tf.Graph()

with g1.as_default():
    c1 = tf.constant(1, name="c_one") # 정수를 1가지고 있고 name은 c_one으로 설정 되어 있음.
    print(c1) # 그냥 값만 있기 때문에 Scala임.
    print(type(c1)) # tensor Class 타입
    # c1 Type : <class 'tensorflow.python.framework.ops.Tensor'>
    print(c1.op.node_def) # c1.op : 구조
    
    print('------------------------------------------------------------')
    
    c2 = tf.constant(2, name="c_two")
    print(g1.as_graph_def()) # 그래프는 노드로 여러가지를 가지고 있음.
    
print('**' * 50)

print(c1)

print('**' * 50)
g2 = tf.Graph()
with g2.as_default():
    v1 = tf.Variable(initial_value='var_1', name='var_1')
    print(v1)
    print(type(v1))
    print(v1.op)
    print()
    print(v1._variable)
    print()
    print(v1._variable.op) # attr가 4개.
    
print('**' * 50)
print(g2.as_graph_def()) # 노드 4개. 각 노드마다하는 일이 다름.  
#Variable는 내부적으로 여러가지 일이 일어나고 있다.