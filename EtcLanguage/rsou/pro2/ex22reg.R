state.x77

states <- as.data.frame(state.x77[, c('Murder', 'Population', 'Illiteracy', 'Income', 'Frost')])
head(states)
dim(states)

# 다중회귀 모델
model <- lm(Murder ~ Population + Illiteracy + Income + Frost, data = states)
model

summary(model)

# 다중공신성 확인
install.packages('car', dependencies = T) # 종속적인 모든 패키지 설치.
library(car)

vif(model) # 결과가 10을 넘으면 다중공선성 문제 발생.
vif(model) > 10 # False이면 다중공산성에 문제가 없음.
sqrt(vif(model)) # 결과가 2가 넘으면 다중공선성 문제 발생.

# 이상 관측치 확인 - 제거 보다는 검토 대상이 되어야 함.
influencePlot(model, id.method = "identify")


# 회귀모형의 교정 관련 작업 ------------------
# 독립성을 만족하지 않는 경우
boxplot(states$Murder, horizontal = T)
summary(powerTransform(states$Murder)) # P값 0.14512 > 0.05 독립성을 만족.
boxplot(log(states$Murder), horizontal = T)

# 선형성을 만족하지 않는 경우.
boxTidwell(Murder ~ Population + Illiteracy, data = states)
# p 값이 0.05 보다 크므로 선형성에 문제가 없다.
# 선형성에 문제가 있다면 다항회귀로 들어가면 됨.

# 등분산을 만족하지 않는 경우
ncvTest(model) # p = 0.18632 > 0.05 이므로 등분산성을 만족

# 등분산성을 만족하지 않는다면
spreadLevelPlot(model)
# Suggested power transformation:  1.209626 
# 종속변수에 ㄹ1.209626배를 적용하라.

# 예측변수 선택1 -- 가장 고민해야할 부분 중 하나 --
fit1 <- lm(Murder ~ ., data = states)
summary(fit1) # 설명력(Adjusted R-squared) :  0.5285
# Income, Forst 변인은 빼야 하는 고민

fit2 <- lm(Murder ~ Population + Illiteracy, data = states)
summary(fit2)

# 예측변수 선택2 - AIC 통계량
# Akaike 정보 기준. AIC 값이 작을수록 더 나은 모델임.
AIC(fit1, fit2) # fit2가 좋은 모델. 숫자가 적은 좋은 것.
# df      AIC
# fit1  6 241.6429
# fit2  4 237.6565
# fit2가 수치가적으므로 더 좋은 모델이라 판정 할 수 있음.

# 예측변수 선택3
# stepwise regression : 단계적으로 회귀모형을 검정하면서 AIC 값을 비교 후 적합한 회귀모형을 찾아줌.
# AIC 값을 비교 후 적합한 회귀모형을 찾아줌.

# "backword" : 기여도가 낮은 것부터 제거.
full.model <- lm(Murder ~ ., data=states)
reduced.model <- step(full.model, direction = "backward")
# 추천값
# Step:  AIC=93.76 이 것이 최선이므로 제거작업 멈춤.
# Murder ~ Population + Illiteracy
summary(reduced.model)

# "forward" : 유익한 변수부터 추가.
min.model <- lm(Murder ~ 1, data=states)
fwd.model <- step(min.model, direction = "forward", 
                  scope = (Murder ~ Population + Illiteracy + Income + Frost), trace = 1)
# Step:  AIC=93.76 이 것이 최선이므로 제거 작업 멈춤.
# Murder ~ Illiteracy + Population

# ★★★★★ : backword, f는 기여도가 낮은 것부터 체 제거, forward는 유익한 변수부터 추가 하지만 값은결과 값은 같다.

# 예측변수 선택 4 : all subset regression
library(leaps)
abc <- regsubsets(Murder ~ Population + Illiteracy + Income + Frost, data = states, nbest = 4)
abc
plot(abc, scale = "adjr2", main = "변수 선택 그래프")
