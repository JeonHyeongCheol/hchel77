# * 세 집단 비율 검정 – prop.test() 사용
# : 독립된 세 집단 이상의 집단 간 비율차이 검정을 할 수 있다.빈도수에 대한 비율에 의미가 있다.
# <연구환경> IT 교육센터에서 3가지 교육방법을 적용하여 교육을 실시하였다.
# 3 가지 교육방법 중 가장 효과적인 교육방법을 조사하기 위해 교육생 150 명을 대상으로 설문을 실시하였다.

data <- read.csv("testdata/three_sample.csv", header = T)
head(data)
nrow(data)
str(data)
method <- data$method # 명목형 - 교육방법
survey <- data$survey # 연속형 - 만족도

table(method, useNA = "ifany")
table(method, survey)

# 양측검정
prop.test(c(34, 37, 39), c(50, 50, 50))
prop.test(c(34, 37, 39), c(50, 50, 50), alternative = "two", conf.level = 0.95)
# p-value = 0.5232 > 0.05 귀무 채택 : 3 가지 교육방법에 만족도에 차이가 없다.