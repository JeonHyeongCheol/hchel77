# 세 개 이상의 모집단에 대한 가설검정 – 분산분석
# ‘분산분석’이라는 용어는 분산이 발생한 과정을 분석하여 요인에 의한 분산과 요인을 통해 
# 나누어진 각 집단 내의 분산으로 나누고 요인에 의한 분산이 의미 있는 크기를 크기를 가지는지를 검정하는 것을 의미한다.
# 세 집단 이상의 평균비교에서는 독립인 두 집단의 평균 비교를 반복하여 실시할 경우에 
# 제1종 오류가 증가하게 되어 문제가 발생한다.
# 이를 해결하기 위해 Fisher가 개발한 분산분석(ANOVA, ANalysis Of Variance)을 이용하게 된다.
# *서로 독립인 세 집단의 평균 차이 검정(일원분산분석, one-way ANOVA)
# ‘일원’은 집단을 구분하는 요인이 하나(집단 구분 변수가 하나)임을 뜻한다.
# 실습 ) 지역 규모 별 나이의 차이에 대한 평균 검정 수행 : 정규분포로부터 독립적으로 추출된 표본임을 가정한다.

# 귀무 : 세 집단(교육방법)간의 실기시험의 평균에 차이가 없다.
# 대립 : 세 집단(교육방법)간의 실기시험의 평균에 차이가 있다.

data <- read.csv("testdata/three_sample.csv", header = T) 
head(data)

data <- subset(data, !is.na(score), c(method, score))
head(data)

plot(data$score)
boxplot(data$score)

# outline 제거
# 이상치 제거
length(data$score)
data2 <- subset(data, score <= 10) # 14 이상인 값들은 삭제.
length(data2$score)
boxplot(data2$score)

table(data2$method)

data2$method2[data2$method == 1] <- "방법1"
data2$method2[data2$method == 2] <- "방법2"
data2$method2[data2$method == 3] <- "방법3"
table(data2$method2)

x <- table(data2$method2)
y <- tapply(data2$score, data2$method2, mean)
y

df <- data.frame(교육방법=x, 성적=y)
df

# 정규성 검정
shapiro.test(data2$score) # p-value = 0.1897 > 0.05 정규성

# 등분산성(동질성 검정)
bartlett.test(score ~ method, data = data2) # score에 대한 method 값 확인.

# 집단이 3개 일 때 밀도 확인을 위해 밑에 있는 package 설치 후 확인 가능.
install.packages("lattice")
library(lattice)

densityplot(score ~ method, data = data2) # method 1, 2, 3에 대해서 밀도가 비슷한 것을 확인 할 수 있음.

# 세 집단 평균차이 검정
# 방법1) aov() 함수 사용.
result <- aov(score ~ method2, data = data2)
result

summary(result) # aov 값은 summary로 확인하여야 함.
# p-value = 9.39e-14 < 0.05 이므로 귀무가설 기각.

# 방법2) anova() 함수 사용.
lmodel <- lm(score~ method2, data = data2)
anova(lmodel)
# p-value = 9.394e-14 // 위에 방법과의 값은 동일 함.

# 방법 3) anova(), one-waytest 사용.
oneway.test(score ~ method2, data = data2, var.equal = T)
# p-value = 9.394e-14 // 세 가지 방법다 값은 동일하게 나옴.

# 참고 : 세 그룹이 연속변수가 아니거나 정규분포를 따르지 않는 경우
kruskal.test(score ~ factor(method2), data = data2)

# 참고 : 세 그룹이 연속변수이고 정규분포를 따르나 등분산성이 아닌 경우
oneway.test(score ~ method2, data = data2, var.equal = F)

# 사후검정 : 여러개의 평균을 동시에 비교해서 분석했다라고 해서 다중비교라 함.
TukeyHSD(result)
# diff        lwr        upr     p adj
# 방법2-방법1  2.612903  1.9424342  3.2833723 0.0000000 // 집단 간 평균의 차이가 가장 큼.
# 방법3-방법1  1.422903  0.7705979  2.0752085 0.0000040 
# 방법3-방법2 -1.190000 -1.8656509 -0.5143491 0.0001911 // 집단 간 평균의 차이가 가장 작음.

