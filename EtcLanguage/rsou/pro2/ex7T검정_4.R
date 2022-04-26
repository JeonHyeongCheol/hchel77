# 두 집단의 가설검정 – 실습 시 분산을 알지 못하는 것으로 한정하겠다.
# * 서로 독립인 두 집단의 평균 차이 검정(independent samples t-test)
# 남녀의 성적, A반과 B반의 키,경기도와 충청도의 소득 따위의 서로 독립인 
# 두 집단에서 얻은 표본을 독립표본(two sample)이라고 한다.
# 실습) 여 신생아와 남 신생아의 몸무게 평균에 대한 검정 수행

bdata <- read.csv("testdata/babyboom.csv", header = T)
# 귀무 : 남여 신생아의 몸무게 평균이 같다.
# 대립 : 여 신생아의 몸무게 평균이 남아의 몸무게 평균보다 작다.

head(data)
data <- bdata[, c(2,3)]
# data <- bdata[, c("gender","weight")] 위에라인과 동일.
data

# 정규성
# 등분산성
var.test(data$weight ~ data$gender)
# p-value = 0.07526 > 0.05 이므로 등분산성 띔.

t.test(data$weight ~ data$gender, mu=0, alternative = "less", var.equal = T)
#  p-value = 0.06764 > 0.05 이므로 귀무가설 채택.