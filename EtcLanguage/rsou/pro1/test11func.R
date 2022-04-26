# 주요 내장 함수
seq(0, 5, by = 1.5) # 반복. 1.5씩 증가
seq(length = 10, from = 5, by = .2) # length는 10, 5부터 시작, 0.2씩 증가

set.seed(12) # 결과가 같은 난수를 발생시키고 싶을때 이 함수도 같이 사용 하여야 함.
rnorm(10, mean = 0, sd = 1) # 난수 발생. 10개를 뽑는데 평균은 0, 표준편차는 1.
hist(rnorm(10, mean = 0, sd = 1)) # histogram 출력.

runif(10, min = 0, max = 100) # 연속 균등 분포(Uniform).
hist(runif(10, min = 0, max = 100))

sample(0:100, 10) # 주어진 데이터에서 임의로 샘플을 추출하는 목적으로 사용.

vec <- 1:10
mean(vec) # 평균
max(vec)
range(vec) # 범위
median(vec) # 중간값?
var(vec) # 분산값
sqrt(var(vec)) # 이렇게하면 표준편차가 되는데. 분산에 루트 씌우면 표준편차
sd(vec) # 표준편차함수가 따로 있음.
sd(vec) / mean(vec)
quantile(vec) # 4분위값
sum(vec)
prod(vec) # 원소간 곱하기
factorial(5) # 팩토리얼
table(vec)
abs(-5) # 절대값

# 사용자 정의 함수
func1 <- function() {
  print('함수')
}

func1()

trifunc <- function(a, h) { # 삼각형 넓이 구하기.
  re = a * h / 2 # <- 이렇게 넣을수도 있지만 함수내에서는 = 를 쓸 것.
  cat("삼각형의 넓이 : ", re)
}

trifunc(5,4)

gugu_func <- function(dan) { # 구구단 출력.
  for(d in dan) { # dan이 d.
    for (i in 1:9) { # i가 1~9.
      cat(d, "*", i, "=", d * i, ' ')
    }
  }
}
gugu_func(3)

test <- read.csv("testdata/test.csv", header = T)
head(test, 3)

fa <- function() {
  a = table(test$A)
  cat("A 빈도수 : \n", a)
  cat("\nmax : ", max(a))
  cat("\nmin : ", min(a))
}
fa()
