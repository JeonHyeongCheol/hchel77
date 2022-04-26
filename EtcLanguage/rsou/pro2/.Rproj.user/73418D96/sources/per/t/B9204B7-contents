# 선형회귀로 예측 - 정규성, 독립성, 선형성, 등분산성, 다중공선성

mtcars
head(mtcars)
dim(mtcars) # 행, 열 확인
str(mtcars) # 구조 확인

# 단순회귀 예) hp(마력수)를 독립변수, mpg(연비)를 종속변수.
cor(mtcars$hp, mtcars$mpg) # -0.7761684 / 강한음의 상관관계.

plot(mpg ~ hp, data = mtcars)
lm <- lm(mpg ~ hp, data = mtcars)
abline(lm, col = 'blue')
lm
confint(lm)
summary(lm)

par(mfrow = c(2,2))
plot(lm)
par(mfrow = c(1,1))

y = 30.09886 + -0.06823 * 300
cat('예측 연비:', y)

mynew <- mtcars[c(1,2),]
mynew <- edit(mynew)
mynew
pred <- predict(lm, newdata = mynew)
pred

# 다중회귀 예) hp(마력수)를 독립변수, mpg(연비)를 종속변수
lm2 <- lm(formula = mpg ~ hp + wt, data = mtcars)
lm2
summary(lm2)
y = 37.22727 + -0.03177 * 110 + -3.87783 + 2.5
y

pred2 <- predict(lm2)
pred2
mtcars$mpg[1] # 실제값 : 21
pred[1] # 예측값 : 19.86462 

mtcars[c(1, 5),]
# 마력수와 차체 무게를 입력해 예상 연비 추측
newCar <- data.frame(hp=110, wt=2.62) # 실제값으로 먼저 확인
newCar
predict(lm2, newdata = newCar) # 21을 예측값 23.57233

# 22.8 4 108 93 3.85 2.320
newCar <- data.frame(hp=93, wt=2.32)
newCar
predict(lm2, newdata = newCar) # 22.8을 예측값 25.27582

# 임의의 값으로 연비 예측
newCar <- data.frame(hp=300, wt=10.32)
predict(lm2, newdata = newCar)

newCar <- data.frame(hp=30, wt=0.5)
predict(lm2, newdata = newCar)
