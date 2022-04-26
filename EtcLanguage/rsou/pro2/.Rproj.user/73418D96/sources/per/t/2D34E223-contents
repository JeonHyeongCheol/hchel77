head(women) # 미국 성인 여성들의 키와 몸무게

plot(weight ~ height, data = women)

lmodel <- lm(weight ~ height, data = women)
lmodel

abline(lmodel, col = "red")

summary(lmodel)

cor.test(women$weight, women$height) # cor : 상관계수 값, 0.9954948

0.9954948 ** 2 # r을 제곱한 것. R-squared.

plot(lmodel)

par(mfrow = c(2,2))
plot(lmodel)

# Residuals vs Fitted : 선형회귀로 잔차를 보여줌.
# Normal Q-Q : 정규성에 문제가 있는지.
# Scale-Location : 잔차의 정도를 파악하는데 골고루 퍼저있어야 하함.
# Residuals vs Leverage : Outline이 있는지 확인. Outline(1) 밖에 있으면 좋지 않음.

# 첫 번째 차트인 Residuals vs Fitted는 X 축에 선형 회귀로 예측된 Y 값, Y 축에는 잔차를 보여준다. 
# 선형 회귀에서 오차는 평균이 0이고 분산이 일정한 정규 분포를 가정하였으므로, 
# 예측된 Y 값과 무관하게 잔차의 평균은 0이고 분산은 일정해야 한다. 
# 따라서 이 그래프에서는 기울기 0인 직선이 관측되는 것이 이상적이다.

# 두 번째 차트인 Normal Q-Q는 잔차가 정규 분포를 따르는지 확인하기 위한 Q-Q도다.

# 세 번째 차트인 Scale-Location은 X 축에 선형 회귀로 예측된 Y 값, Y 축에 표준화 잔차Standardized Residual3 를 보여준다. 
# 이 경우도 기울기가 0인 직선이 이상적이다. 
# 만약 특정 위치에서 0에서 멀리 떨어진 값이 관찰된다면 해당 점에 대해서 표준화 잔차가 크다, 
# 즉, 회귀 직선이 해당 Y를 잘 적합하지 못한다는 의미다. 이런 점들은 이상치outlier일 가능성이 있다.

# 네 번째 차트인 Residuals vs Leverage는 X 축에 레버리지Leverage, Y 축에 표준화 잔차를 보여준다. 레버리지는 설명 변수가 얼마나 극단에 치우쳐 있는지를 뜻한다. 예를 들어, 다른 데이터의 X 값은 모두 1 ~ 10 사이의 값인데 특정 데이터만 99999 값이라면 해당 데이터의 레버리지는 큰 값이 된다. 이런 데이터는 입력이 잘못되었거나, 해당 범위의 설명 변숫값을 가지는 데이터를 보충해야 하는 작업 등이 필요하므로 유심히 살펴봐야 한다.
# 네 번째 차트의 우측 상단과 우측 하단에는 선으로 쿡의 거리Cook’s Distance가 표시되어 있다. 
# 쿡의 거리는 회귀 직선의 모양(기울기나 절편 등)에 크게 영향을 끼치는 점들을 찾는 방법이다. 
# 쿡의 거리는 레버리지와 잔차에 비례하므로 두 값이 큰 우측 상단과 우측 하단에 쿡의 거리가 큰 값들이 위치하게 된다. 
# 이 차트에 더 관심이 있는 독자는 인터넷에 공개된 책자인 <Advanced Statistical Modelling>[9]을 참고하거나, 
# 그림과 예제로 매우 잘 설명한 같은 강의의 PPT 슬라이드[10]에서 이상치 진단에 대한 내용을 참고하기 바란다.

# 선형성에 문제가 있을 경우에는 다항회귀를 수행
lmodel2 <- lm(weight ~ height + I(height ^ 2), data = women)

summary(lmodel2)
plot(lmodel2)

par(mfrow = c(2,2))
plot(lmodel2, which = c(4, 6)) # Cook's distance
