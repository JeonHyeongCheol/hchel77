'''
Created on 2018. 12. 12.

cost/loss function : 모델이 예측한 예측값 - 실제값 차이의 평균
'''
import math

#pred_value = [11, 5, 5, 4, 5] # 모델을 통해 얻은 값이라 가정
pred_value = [10, 8, 3, 1, 11]
real_value = [10, 9, 3, 2, 11] # 실제값.

# y^(y헷) = Wx + b, W : 기울기, b : 편향. 
# W값이 0에 가까울 수록 영향을 덜 받지만 0이 되면 안됨. 되도록 1을 만들어주면 좋은 모델이 됨.

cost = 0

for i in range(5):
    cost += math.pow(pred_value[i] - real_value[i], 2) # pow : 제곱 , 뒤에 2는 제곱 수.
    # cost 값을 누적한 후 값 만큼 나누면 그것이 cost function에 평균 값이 됨.
    print(cost)

print('cost : ', cost)    
print('cost 평균 값 : ', cost / 5)

# 모델의 정확도가 높다면(예측값이 정확) 비용함수 값은 낮아지고,
# 모델의 정확도가 떨어지면(예측값이 정확) 비용함수 값은 높아진다.

# Weight를 최소화 하는 알고리즘.
import tensorflow as tf
import matplotlib.pyplot as plt

x = [1,2,3] # feature
y = [1,2,3] # label

w = tf.placeholder(tf.float32)
b = 0 # 편의상 bias = 0.

hypothesis = w * x + b # Linear Model

cost = tf.reduce_mean(tf.square(hypothesis - y)) # cost에 평균값을 구함.
print(cost)
#cost = tf.reduce_sum(tf.pow(hypothesis - y, 2)) / len(x) # 위에 식과 같음. square 함수 : 이 식을 계산해주는 함수.

sess = tf.Session()
sess.run(tf.global_variables_initializer()) # 습관적으로 해주고 시작 할 것.

w_val = [] # 기울기 값 저장.
cost_val = [] # 비용 값 저장.

for i in range(-30, 50):
    feed_w = i * 0.1
    print(sess.run([cost, w], feed_dict={w: feed_w}))

for i in range(-30, 50):
    feed_w = i * 0.1 # 0.1은 learning rate라 생각(학습률).
    #print(feed_w)
    curr_cost, curr_w = sess.run([cost, w], feed_dict={w: feed_w}) # cost값은 -30일 때 평균부터 50일 때 평균이 계속 달라짐. w 값에 다라 달라짐.
    
    w_val.append(curr_w)
    cost_val.append(curr_cost)
    print(str(i) + ')' + 'cost : ' + str(curr_cost) + ' ' +
          'weight : ', str(curr_w))
     
plt.plot(w_val, cost_val)
plt.xlabel('w : 기울기')
plt.ylabel('cost')
plt.show()
