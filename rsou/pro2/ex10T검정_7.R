# <연구환경>
#   IT교육센터에서 PT를 이용한 프레젠테이션 교육방법과 실시간 코딩 교육방법을 적용하여 교육을 실시하였다. 
# 2 가지 교육방법 중 더 효과적 인교육방법을 조사하기 위해서 교육생 300 명을 대상으로 설문을 실시하였다. 
# 조사한 결과는 표와 같다.

data <- read.csv("testdata/two_sample.csv", header = T)
head(data)
# 귀무 : 두 가지 교육방법에 따른 교육생의 만족율에 차이가 없다.
# 대립 : 두 가지 교육방법에 따른 교육생의 만족율에 차이가 있다.

x <- data$method
y <- data$survey
table(x) # 교육방법 1, 2
table(y) # 만족1, 불만족 0

table(x, y, useNA = "ifany") # useNA = "ifany" 결측치도 작업에 참여.

# 양측 검정
prop.test(c(110, 135), c(150,150)) # 150명중 만족
# p-value = 0.0003422 < 0.05 귀무가설 채택

# 단측 검정
prop.test(c(110, 135), c(150,150), alternative = "greater", conf.level = 0.95)
# p-value = 0.9998 > 0.05 귀무가설 채택