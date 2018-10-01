# *단일 모집단의 평균에 대한 가설검정(one samples t-test) - 2
# 실습 예제) 국내에서 생산된 대다수의 노트북 평균 사용 시간이 5.2 시간으로 파악되었다. 
# A회사에서 생산된 노트북 평균시간과 차이가 있는지를 검정하기 위해서 
# A회사 노트북 150대를 랜덤하게 선정하여 검정을 실시한다.

data <- read.csv("testdata/one_sample.csv", header = T)
head(data)

# 귀무 : 국내에서 생산된 노트북 평균 사용 시간이 5.2 시간으로 평균 사용시간에 차이가 없다.
# 대립 : 국내에서 생산된 노트북 평균사용시간에 차이가 있다.

x <- data$time
x
summary(x) # 결측치 확인
mean(x) # 오류, NA 값이 있기 때문에.
mean(x, na.rm = T) # NA 값 제거 후 계산.

x1 <- na.omit(x)
mean(x1) # 평균시간 : 5.556881

# T 검정시에는 정규성 확인가능. 다른 검정에서는 불가능.
hist(x1)
qqnorm(x1) # 일직선을 띄면 정규성을 띔.
qqline(x1, col='blue')

shapiro.test(x1) # W = 0.99137, p-value = 0.7242 // 정규분포 구하는 함수.
# p-value : 0.7242 > 0.05 정규분포를 따름

# 단일표본 평균차이 검정 : 정규성 O => t.test() // t.test() : 양측 검정.
# 단일표본 평균차이 검정 : 정규성 X => wilcox.test()

t.test(x1, mu = 5.2)
data:  x1
# t = 3.9461, df = 108, p-value = 0.0001417
# alternative hypothesis: true mean is not equal to 5.2
# 95 percent confidence interval: // 95% 신뢰 구간
#   5.377613 5.736148 // 5.377 ~ 5.736 까지
# sample estimates:
#   mean of x 
# 5.556881 // 평균값

t.test(x, mu = 5.2, alternative = "two.side", conf.level = 0.95) # 신뢰구간내에 양측 검정.
# t = 3.9461, df = 108, p-value = 0.0001417
# p-value : 0.0001417 < 0.05 이기 때문에 귀무가설 기각.

# 참고
#wilcox.test(x1, mu = 5.2)

# 참고
t.test(x1, mu = 5.2, alternative = "greater", conf.level = 0.95)
# t = 3.9461, df = 108, p-value = 7.083e-05
qt(0.05, 108) # 임계값 : -1.659085

# t검정 변수보기
result <- t.test(x1, mu = 5.2, alternative = "greater", conf.level = 0.95)
names(result)
result$statistic # 앞에 변수 쓰지 않고 올리기 위해서는 sattach(변수명)을 쓰면 가능. 전역변수로 올림(메모리에).
search()
attach(result) # 메모리에 올리기
statistic
parameter
p.value
detach(result) # 메모리에 올린거 빼기, 삭제
