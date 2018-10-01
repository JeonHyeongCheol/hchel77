# *단일 모집단의 평균에 대한 가설검정(one samples t-test) - 1
# 실습 예제)여아 신생아 몸무게의 평균 검정 수행
# 여아 신생아의 몸무게는 평균이 2800(g)으로 알려져 왔으나 이보다 더 크다는 주장이 나왔다.
# 표본으로 여아 18명을 뽑아 체중을 측정하였다고 할 때 새로운 주장이 맞는지 검정해 보자.

# 귀무 : 여아 신생아의 몸무게는 평균이 2800g 이다.
# 대립 : 여아 신생아의 몸무게는 평균이 2800g 보다 크다.

data <- read.csv("testdata/babyboom.csv", header = T)
head(data)
str(data)
(tmp <- subset(data, gender == 1)) # 여아 Gender로만 사용. 여야는 1.
(weight <- tmp[[3]]) # 여아의 weight만 사용.
length(weight)  

avg <- mean(weight) # 평균값
s_dev <- sd(weight) # 표준 편차
n <- length(weight)
h0 <- 2800 # 평균이 2800인 것.
(t_value <- (avg - h0) / (s_dev / sqrt(n))) # T 값 : 2.233188

alpha <- 0.05 # 유의수준(우측검정)
(cv <- qt(1 - alpha, df = n - 1)) # dt, pt, rt, qt 등 : T검정할 때 나오는 함수 / 임계치(값) :  1.739607
# 해석1 : 우측검정에서 통계량 t값이 임계치보다 우측에 있으므로 귀무가설 기각.

(p_value <- 1- pt(t_value, df = n - 1)) # p 값 : 0.01963422
# 해석2 : p 값 0.01963422 < 0.05 이므로 귀무 가설 기각.

# t.test() 함수를 이용해 판정(한번에 할 수 있는 방법)
t.test(weight, mu = 2800, alternative = "greater")
# t = 2.2332, df = 17, p-value = 0.01963



