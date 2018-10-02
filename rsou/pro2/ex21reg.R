# 단순회귀
head(cars)

m <- lm(dist ~ speed, cars)
m
names(m)
coef(m)
fitted(m)
fitted(m)[1:4] # 예측값 : 각 speed에 대한 dist의 예측값

residuals(m)[1:4] # 잔차 : 예측값과 실제값의 dist 간 거리

fitted(m)[1:4] + residuals(m)[1:4] # 예측값 + 잔차 = 실제값
cars$dist[1:4] # 실제값
confint(m)

# 회귀예측 : y = a + bx
coef(m)
newspeed = 10
coef(m)[1] + coef(m)[2] * newspeed # 21.74499

predict(m, newdata = data.frame(speed = 10)) # 위에와 동일, ★★★★★

# 예측값의 신뢰구간
predict(m, newdata = data.frame(speed = 10), interval = 'confidence')
# fit      lwr      upr[]
# 1 21.74499 15.46192 28.02807
predict(m, newdata = data.frame(speed = 10), interval = 'predict')
# fit       lwr      upr
# 1 21.74499 -9.809601 53.29959

plot(dist ~ speed, data = cars)
abline(m)
ci <- predict(m, interval = 'confidence')
lines(cars$speed, ci[,2], col="red")
lines(cars$speed, ci[,3], col="blue") # 그래프를 만들어서 유효범위를 구할 수 있음.

summary(m)

# anova로 두 모델 간 비교
full <- lm(dist ~ speed, data = cars) # 완전 모형
full

reduced <- lm(dist ~ 1, data = cars) # 축소 모형
reduced

anova(reduced, full) # p값 0.00000000000149 < 0.05 두 모델 간 차이가 있다.

par(mfrow=c(2,2))
plot(m)

# Normal Q-Q plot으로 정규성 확인
res <- residuals(m)
res
shapiro.test(res) # p-value = 0.02152 < 0.05 이므로 정규성을 따르지 못함.

