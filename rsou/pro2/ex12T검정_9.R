# *분산분석 중 세 집단 평균 차이 검정(F검정 : ANOVA)
# : F통계량을 검정 통계량으로 사용
# : 예)제약회사에서 신약으로 개발된 3가지 치료제가 있다고 할 때 효능에 차이가 있는지를 검정
# : 마케팅 전략의 효과
# 소비자 집단의 반응차이 등과 같은 기업의 의사결정에 도움을 주는
# 비계량적인 독립변수와 계량적인 종속변수 간의 관계를 파악할 때 이용될 수 있다.
# <연구환경> 3 가지 교육방법을 적용하여 1개월 동안 교육받은 교육생 각 50 명씩을 대상을 실기시험을 실시하였다. 
# 세 집단 간 실기시험의 평균에 차이가 있는지 검정한다.

data <- read.csv("testdata/agedata.csv", header = T)
head(data)
str(data)

summary(data$scale)
summary(data$score)
data$score <- ifelse(data$score == 99, NA, data$score)
data$scale <- factor(data$scale)
data$gender<- factor(data$gender)
str(data)

# 귀무 : 지역 규모에 따라 나이의 평균은 차이가 없다.
# 대립 : 지역 규모에 따라 나이의 평균은 차이가 있다.

lmodel <- lm(age ~ scale, data = data)
anova(lmodel) #0.6941 > 0.05 귀무가설 채택.
