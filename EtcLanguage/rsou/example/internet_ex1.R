# 회귀분석(인터넷 예제)

install.packages("UsingR")
library(UsingR)

# galton 데이터 구조 확인
require(UsingR)
data(galton); str(galton)

# 화면을 둘로 나누고 히스토그램 확인
par(mfrow=c(1,2)) # 1행 2열
hist(galton$child, col = "blue", breaks = 100)
hist(galton$parent, col = "blue", breaks = 100)
par(mfrow=c(1,1)) # 실행 후 다시 되돌림

# x : parent, y : child

# 상관관계 구함
cor.test(galton$child, galton$parent)
# Pearson's product-moment correlation
# data:  galton$child and galton$parent
# t = 15.711, df = 926, p-value < 2.2e-16
# alternative hypothesis: true correlation is not equal to 0
# 95 percent confidence interval:
#  0.4064067 0.5081153
# sample estimates:
#       cor 
#   0.4587624 

# 결과를 보면 상관계수 cor는 0.458이고 p값은 < 2.2e-16으로 되어있다. 
# 이것은 2.2×10^−16 이하라는 뜻으로 매우 유의(오차가능성)한 상관관계가 있다는 것을 알 수 있다. 

# 부모와 아이의 키를 표로 요약
xtabs(~ child + parent, data = galton) # xtabs : 표로 요약
# child와 parent의 값을 더한 결과값을 표로 요약해 보여줌

#       parent
# child  64 64.5 65.5 66.5 67.5 68.5 69.5 70.5 71.5 72.5 73
#   61.7  1    1    1    0    0    1    0    1    0    0  0
#   62.2  0    1    0    3    3    0    0    0    0    0  0
#   63.2  2    4    9    3    5    7    1    1    0    0  0
#   64.2  4    4    5    5   14   11   16    0    0    0  0
#   65.2  1    1    7    2   15   16    4    1    1    0  0
#   66.2  2    5   11   17   36   25   17    1    3    0  0
#   67.2  2    5   11   17   38   31   27    3    4    0  0
#   68.2  1    0    7   14   28   34   20   12    3    1  0
#   69.2  1    2    7   13   38   48   33   18    5    2  0
#   70.2  0    0    5    4   19   21   25   14   10    1  0
#   71.2  0    0    2    0   11   18   20    7    4    2  0
#   72.2  0    0    1    0    4    4   11    4    9    7  1
#   73.2  0    0    0    0    0    3    4    3    2    2  3
#   73.7  0    0    0    0    0    0    5    3    2    4  0


# galton은 작성된 표를 통해 부모의 키와 자녀의 키 간에는 직선관계가 있음을 발견하였고 
# 또한 자녀의 키는 평균 키를 중심으로 회귀하려는 경향이 있음을 언급하였다. 
# 부모의 키와 자녀의 키 사이의 수학적 관계를 나타내는 공식은 회귀분석을 통하여 구할 수 있다.
# R에서 회귀분석은 선형모형(linear model)을 쓴다. 
# lm은 linear model의 약자로 사용법은 다음과 같다.
# lm(종속변수(결과) ~ 독립변수(원인),데이터)

# 즉 부모의 키에 의해 자녀의 키가 결정되므로 이 경우 다음과 같이 회귀공식을 구할 수 있다.
out = lm(child ~ parent, data = galton)
summary(out)

# Call:
#   lm(formula = child ~ parent, data = galton)
# 
# Residuals:
#   Min      1Q  Median      3Q     Max 
# -7.8050 -1.3661  0.0487  1.6339  5.9264 
# 
# Coefficients:
#             Estimate Std. Error t value Pr(>|t|)    
# (Intercept) 23.94153    2.81088   8.517   <2e-16 ***
#   parent     0.64629    0.04114  15.711   <2e-16 ***
#   ---
#   Signif. codes:  0 ‘***’ 0.001 ‘**’ 0.01 ‘*’ 0.05 ‘.’ 0.1 ‘ ’ 1
# 
# Residual standard error: 2.239 on 926 degrees of freedom
# Multiple R-squared:  0.2105,	Adjusted R-squared:  0.2096 
# F-statistic: 246.8 on 1 and 926 DF,  p-value: < 2.2e-16

# 결과를 보면 y절편(Intercept)이 23.94이고 
# parent의 기울기는 0.65인 것으로 나타난다. 
# 즉 y=0.65x+23.94이다.

# 이를 그래프로 그려보면 다음과 같다.
plot(child ~ parent, data = galton)
abline(out, col = "red")

require(ggplot2)
ggplot(data = galton, aes(x=parent, y=child)) +
         geom_count() +
         geom_smooth(method = "lm")

# 회귀의 다양한 얼굴
# 일반적으로 회귀라고 할 때 보통의 최소제곱(Ordinary Least Square, OLS)에 의한 회귀를 말하지만 
# 그 외에도 수 많은 회귀 방법이 있다. 
# 2005년에 Vito Ricci가 만든 목록을 보면 R에서 사용하는 회귀와 관련된 함수는 
# 모두 205개이다
# (http://cran.r-project.org/doc/contrib/Ricci-refcard-regression.pdf)
