'''
Created on 2018. 12. 11.

@author: kitcoop
'''
import tensorflow as tf
# Tensor에 영역이 나눠 진다는 것을 꼭 알아 둘 것. Session을 해줘야지 출력 할 수 있음.


# 계산 그래프를 작성 : 알고리즘을 기술 - 인터페이스 영역.
# 상수 텐서를 생성
hello = tf.constant(3.) # 0 Tensor (Scala)
world = tf.constant('world')
world2 = tf.constant('월드 만세')
const = tf.constant([10, 20, 30]) # 1 Tensor (Vector)
#const = tf.constant([[10, 20, 30]]) # 2 Tensor (Matrix)

a = tf.constant([3], dtype=tf.float32, shape=(1,))
b = tf.constant([[5,10]], dtype=tf.float32)
c = a + b # +, -, *, /
print('c : ', c)

print(hello)
# Tensor("Const:0", shape=(), dtype=int32) # dtype : numpy 기반.
print(world)
print(const)

# 계산 그래프를 실행하는 영역 : 세션을 생성하여 연산 수행 (알고리즘을 실행).
print('-----------------------------')
sess = tf.Session() # 텐서를 실행하는 역할.
result1 = sess.run(hello)
print(result1)
result2 = sess.run(world)
print(result2)
result3 = sess.run(world2)
print(result3.decode(encoding='utf-8')) # 한글 처리시 뒤에 encoding = 'utf-8'을 써주면 됨.
result4 = sess.run(const)
print(result4, result4.shape, result4.size)
result5 = sess.run(c)
print(result5)
sess.close()