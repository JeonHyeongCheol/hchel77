# iris Datasets로 다중회귀분석
head(iris)
nrow(iris)

cor(iris[,-5])

# 학습데이터와 검정데이터로 분리(7:3)
sam_tt <- sample(1:nrow(iris), nrow(iris) * 0.7, replace = F) # 랜덤으로 분리 됨.
train <- iris[sam_tt, ]
test <- iris[-sam_tt, ]
dim(train); dim(test)

# 회귀 모델
model <- lm(Sepal.Length ~ Sepal.Width + Petal.Length + Petal.Width, data = train)
model

summary(model)

# 다중 공선성
library(car)
vif(model)
model <- lm(Sepal.Length ~ Sepal.Width + Petal.Width, data = train)
vif(model)

# 정규성
res <- residuals(model)
shapiro.test(res)

# 독립성
install.packages("lmtest")
library(lmtest)
dwtest(model) # DW =  2.0884 / 2에 가까우면 독립성이 좋다.

par(mfrow = c(2,2))
plot(model)

summary(model)

head(test)
y = 3.38155 + 0.42957 * 3.1 + 0.98060 * 1.5
y

# 추정치 예측 : predict()
pred <- predict(model, test)
pred
pred[1:5] # 예측값

head(test[,1], 2) # 실제값 4.6 5.0
head(pred, 2) # 예측값 4.909329 5.124112 
