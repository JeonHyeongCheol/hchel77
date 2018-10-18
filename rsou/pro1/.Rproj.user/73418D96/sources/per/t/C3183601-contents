# 표본 추출

# 단순임의추출
sample(1:10, 5) # 비복원 추출(중복을 나타내지 않음.)
sample(1:10, 5, replace = T) # 복원 추출(중복을 나타냄).
sample(1:10, 5, replace = T, prob = 1:10) # prob : 가중치(큰 수들이 많이 나옴).

# 층화임의추출
install.packages("sampling")
library(sampling)
head(iris)


aa <- strata(c('Species'), size = c(3,3,3), method = 'srswor', data = iris) # method = 'srswor' : 비복원 추출.
aa
sampling::getdata(iris, aa) # 비복원 추출한 방식으로 나온 값으로 실 데이터를 볼 수 있음.

bb <- strata(c('Species'), size = c(3,1,1), method = 'srswor', data = iris) # size크기에 따라 종류는 3개, 1개, 1개 추출 되었음.
bb
sampling::getdata(iris, bb)

# 계통추출
install.packages("doBy")
library(doBy)

(x <- data.frame(x=1:10)) # data.frame으로 1:10까지 x에 넣음.
sampleBy(~1, frac = .3, data = x, systematic = T) # frac : 갯수?, 
# doBy::sampleBy(
#   formula,              # ~ 우측에 나열한 이름에 따라 데이터가 그룹으로 묶인다. // 위에서는 x의 값이 우측에 나열한 이름이에 따라 묶임.
#   frac=0.1,             # 추출할 샘플의 비율이며 기본값은 10%
#   replace=FALSE,        # 복원 추출 여부
#   data=parent.frame(),  # 데이터를 추출할 데이터 프레임
#   systematic=FALSE      # 계통 추출(Systematic Sampling)7을 사용할지 여부
# )