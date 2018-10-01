# 한국복지패널 자료
install.packages("foreign")
library(foreign)
library(dplyr)
library(ggplot2)
install.packages("readxl")
library(readxl)

ori_welfare <- read.spss("testdata/Koweps_hpc10_2015_beta1.sav", to.data.frame = T)
welfare <- ori_welfare
View(welfare)

str(welfare) # 구조 확인.
summary(welfare) # 요약 통계
names(welfare) # 컬럼명 확인.

welfare <- rename(welfare, sex = h10_g3, birth = h10_g4, marriage = h10_g10, 
                  religion = h10_g11, income = p1002_8aq1, code_job = h10_eco9,
                  code_region = h10_reg7)
welfare
head(welfare$sex)

# 성별에 따른 월급 차이


class(welfare$sex)
table(welfare$sex) # 1은 남, 2는 여, 값 확인
is.na(welfare$sex) # na(null) 값이 있는지 확인.
table(is.na(welfare$sex))

welfare$sex <- ifelse(welfare$sex == 1, 'Male', 'female') # 칼럼안에있는 값에 대한 명칭 바꾸기.
table(welfare$sex)
qplot(welfare$sex)

class(welfare$income) # income? 월급?
qplot(welfare$income)

class(welfare$income)
qplot(welfare$income) + xlim(0, 1000) # x축의 값 설정

summary(welfare$income)

welfare$income <- ifelse(welfare$income %in% c(0, 9999), NA, welfare$income)
table(is.na(welfare$income))

sex_income <- welfare %>% 
  filter(!is.na(income)) %>% 
  group_by(sex) %>% 
  summarise(mean_income=mean(income))

sex_income

ggplot(data=sex_income, aes(x=sex, y=mean_income)) + geom_col()
