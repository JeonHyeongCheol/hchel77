result <- read.csv("testdata/drinking_water.csv", header = T)
head(result)

cor(result) #상관관계 확인

x = result$적절성
y = result$만족도
df <- data.frame(x, y)
df

# 단순선형회귀 모델 작성
model <- lm(formula = y ~ x, data =df)
model

options(scipen = 999) # 과학적 표기형식을 바꿔줌. 숫자로.
summary(model)
# 설명력(Adjusted R-squared):  0.5865

plot(y ~ x, data = df)
abline(model, col = "blue")

plot(jitter(y,5) ~ jitter(x,5), df)
sunflowerplot(df)

# 예측 :
newx <- 5.4
yh <- 0.77886 + 0.73928 * newx
cat('만족도', yh)
