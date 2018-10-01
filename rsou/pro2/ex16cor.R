result <- read.csv("testdata/drinking_water.csv", header = T)
head(result)
summary(result)

sd(result$친밀도) # 0.9703446
sd(result$적절성) # 0.8596574
sd(result$만족도) # 0.8287436

# 정규성 확인
hist(result$친밀도)
hist(result$적절성)
hist(result$만족도)

# cov : 공분산 확인. 상관계수 확인 하기 위함.

cov(1:5, 2:6) # 2.5
cov(1:5, c(3, 3, 3, 3, 3)) # 0 , 0이면 관련이 없다는 것. 한쪽값에 바뀌는 것에 대해 관련이 있는지 확인.
cov(1:5, 5:1) # -2.5

# 공분산
cov(result$친밀도, result$적절성) # 0.4164218
cov(result)

# 상관계수
?cor

cor(result$친밀도, result$적절성) # 0.4992086
cor(result$친밀도, result$만족도) # 0.467145
cor(result$적절성, result$만족도) # 0.7668527
cor(result$적절성, result$만족도, result$친밀도) # X
cor(result$적절성 + result$만족도, result$친밀도) # O / 0.5143693
cor(result$적절성 + result$친밀도, result$만족도) # 0.7017394
cor(result)
cor(result, method = "pearson") # 대상변수가 등간, 비율척도
#cor(result, method = "spearman") # 대상변수가 서열척도

symnum(cor(result)) # 기호로 보여줌.

install.packages("corrgram")
library(corrgram)

corrgram(result)
corrgram(result, upper.panel = panel.conf) # 위쪽
corrgram(result, lower.panel = panel.conf) # 아래
