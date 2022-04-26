# * 회귀분석
# - 특정변수(독립변수)가 다른 변수(종속변수)에 어떤 영향을 미치는가 분석. 즉, 인과관계 분석.
# - 독립, 종속변수는 등간 또는 비율척도로 구성되어야 한다. 한 변수의 변화에 따른 다른 변수의 값을 파악.
# - 독립변수가 종속변수에 영향을 미치는 변수를 규명하고, 이 들 변수들에 의해서 회귀방정식을 도출하여 회귀선을 추정한다.     

# <단순 선형회귀 분석 연습문제>
data(iris)
data

names(iris)   # 5개 변수


# 조건1) 두 변수간의 상관성 - cor(x,y) 
# Sepal Length : 꽃받침의 길이, Petal Width  : 꽃잎의 너비
# Sepal Length : 꽃받침의 길이, Petal Length : 꽃잎의 길이
x <- iris$Petal.Width
y <- iris$Sepal.Length

cor(x, y) # 0.8179411, 강한 상관관계 / cor : 상관관계 분석 함수.
df <- data.frame(x, y)

#조건2) 선형회귀 분석: lm() -> x변수를 대상으로 y변수값 유추 
# Sepal.Length(종속변수), Sepal.Width(독립변수)

# lm : 회귀분석 함수.

model <- lm(formula = y ~ x, data = df)
# Coefficients:
#(Intercept)      x  
# 1.084        2.230  

summary(model)

# Residuals:
#   Min       1Q   Median       3Q      Max 
# -1.38822 -0.29358 -0.04393  0.26429  1.34521 

# Coefficients:
#   Estimate Std. Error t value Pr(>|t|)    
# (Intercept)  4.77763    0.07293   65.51   <2e-16 ***
#   x            0.88858    0.05137   17.30   <2e-16 ***
#   ---
#   Signif. codes:  0 ‘***’ 0.001 ‘**’ 0.01 ‘*’ 0.05 ‘.’ 0.1 ‘ ’ 1

# Residual standard error: 0.478 on 148 degrees of freedom
# Multiple R-squared:  0.669,	Adjusted R-squared:  0.6668 
# F-statistic: 299.2 on 1 and 148 DF,  p-value: < 2.2e-16

plot(y ~ x, data = df)
abline(model, col = "blue")

plot(jitter(y,5) ~ jitter(x,5), df)
sunflowerplot(df)


# <군집분석 연습문제> 물류서비스 이용 고객사들의 거래실적 데이터 
# 파일 : RFM.csv   : RFM - 고객의 충성도 평가 시 많이 사용

# 변수 설명
# Recency: 최신성(1: 6개월 이전, 2: 3개월 이전, 3: 최근 1달간) 
# Frequency : 거래빈도
# Monetary : 구매금액

# <조건> 다음 단계별로 군집분석을 수행하시오.
# 1) 패키지 설치 및 데이터 가져오기
install.packages("cluster") 
library(cluster)

# 2) 계층적 군집분석
rfm <- read.csv("testdata/RFM.csv", stringsAsFactors = T)
rfm

plot(rfm, hang = -1)

hc_rfm <- hclust(rfm, method = "average")

rect.hclust(rfm, k = 2,border = "red")

# 3) 비계층적 군집분석 - 확인적 군집분석

# 4) 변수의 관계 시각화
library(corrgram)

# 5) 차트에 곡선과 별표 추가
library(PerformanceAnalytics) 

# 6) 비계층적 군집시각화와 중심점 표시




# * 연관분석

# <연습문제1> tranExam.csv 파일을 대상으로 single 형식으로 1~2컬럼만 중복항목을 제거하여 트랜잭션 객체를 생성하시오.

# transcation으로 가져오기(칼럼1 : Transaction ID, 칼럼2 : item)
# <조건1> 트랜잭션 데이터를 확인한다.

# <조건2> 각 item별로 빈도수를 구한다.

# <조건3> supp=0.3, conf=0.1를 지정한 후 발견된 규칙(rule) 수를 작성한다.

# <조건4> 10번째 규칙의 지지도, 신뢰도, 향상도 수치를  작성한다.

# lhs    rhs support confidence lift     
# [1]  {}  => {4} 0.4     0.4000000  1.0000000
# [2]  {}  => {2} 0.6     0.6000000  1.0000000
                      … 
# [11] {3} => {1} 0.4     0.6666667  0.8333333
# [12] {1} => {3} 0.4     0.5000000  0.8333333




# <연습문제2> Adult 데이터셋을 대상으로 다음 조건에 맞게 연관분석을 수행하시오.
# <조건1> 최소 support가 0.5가 되고, 최소 confidence가 0.9가 되도록 AR을 수정한다.

# <조건2> 수행한 결과를 lift 기준으로 정렬하여 상위 10개 패턴을 확인한다.

# <조건3> 연관분석 결과 시각화 - 산포도/네트워크형 그래프

# 미국, 백인, 남성, 자본 무손실 등 연관성