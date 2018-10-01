# <연구환경>
#  IT교육센터에서 PT를 이용한 프레젠테이션 교육방법과 실시간 코딩 교육방법을 적용하여 
# 1개월 동안 교육받은 교육생 각 150명을 대상으로 실기시험을 실시하였다. 
# 두 집단간 실기시험의 평균에 차이가 있는가 검정한다.

data <- read.csv("testdata/two_sample.csv", header = T)
nrow(data) # 행 갯수 확인
head(data)

summary(data)
result <- subset(data, !is.na(score), c(method, score))
nrow(result)
length(result$score)

# 교육방법 별로 데이터 분리
a <- subset(result, method == 1)
b <- subset(result, method == 2)
a; b
a1 <- a$score # 점수 추
b1 <- b$score 
length(a1); mean(a1)
length(b1); mean(b1)

# 귀무 : 교육방법에 따른 두 집단 간 실기시험 평균에 차이가 없다.
# 대립 : 교육방법에 따른 두 집단 간 실기시험 평균에 차이가 있다.

# 정규성 확인
shapiro.test(a1)
shapiro.test(b1)

# 등분산성 확인
var.test(a1, b1)

# 양측 검정
t.test(a1, b1) # t = -2.0547, df = 218.19, p-value = 0.0411
# 귀무 기각
t.test(a1, b1, alternative = "two.sided", conf.level = 0.95)

# 단측 검정 (우측)
t.test(a1, b1, alternative = "greater", conf.level = 0.95)
# 단측 검정 (좌측)
t.test(a1, b1, alternative = "less", conf.level = 0.95)