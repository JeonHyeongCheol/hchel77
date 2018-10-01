# 이항분포 : 베루누이 분포를 기반으로 성공/실패가 있는 상황에서 사용

# 실습1) 동전을 50회 던져서 앞면이 27회 나왔다. 앞면이 나올 확률이 50%라 할 수 있는가?
# 귀무 : 확률이 0.5 이다.
# 대립 : 확률은 0.5가 아니다

# 양측 검정
binom.test(c(27), 50, p=0.5, alternative = "two.sided", conf.level = 0.95)
binom.test(27, 50 , p=0.5)
binom.test(27, 50 , 0.5) # (성공, 수행수, 확률) p-value = 0.6718
           
# 단측검정
binom.test(27, 50, 0.5, alternative = "greater", conf.level = 0.95) # p-value = 0.3359

# 실습2) 게임에서 상대방이 편향된 주사위를 가지고 있다고 의심된다.
# 주사위를 300번 굴리고 이항 테스트를 하여 눈금 당 1/6의 갖는지 검정하라.
# 귀무 : 일반 주사위이다.
# 대립 : 일반 주사위가 아니다.
binom.test(60, 300, 1/6, alternative = "greater") # p-value = 0.07299

# 실습3) 2018년도 114 안내에 대한 불만을 갖는 고객을 20%였다.
# 이를 개선하기위해 2018년 직원 cs교육을 실기한 후 150명 고객을 대상으로
# 서비스 만족여부를 조사한 결과 14명이 불만을 갖고 있었다.
# 기존 20% 보다 불만률이 낮아졌다고 할 수 있는가?

# 귀무 : 서비스에 대한 불만률에 차이가 없다.
# 대립 : 서비스에 대한 불만률에 차이가 없다.

data <- read.csv("testdata/one_sample.csv", header = T)
head(data)
x <- data$survey
summary(x)
length(x) # 150

table(x) # 불만족 : 14, 만족 : 136
table(x, useNA = "ifany")

install.packages("prettyR")
library(prettyR)
freq(x)

# 이항분포 비율검정
# 만족률 80% 검정 -----------------------------
# 양측검정
binom.test(c(136, 14), p = 0.8) # 기존 80% 만족률 기준 검정.
# p-value = 0.0006735 < 0.05 / 연구가설 채택

# 단측검정
binom.test(c(136, 14), p = 0.8, alternative = "greater", conf.level = 0.95)

# 만족률 20% 검정 -----------------------------
# 양측검정
binom.test(c(14, 136), p = 0.2)
# p-value = 0.0006735 < 0.05 / 연구가설 채택

# 단측검정
binom.test(c(14, 136), p = 0.2, alternative = "less", conf.level = 0.95)
# p-value = 0.0003179