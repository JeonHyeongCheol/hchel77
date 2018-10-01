study <- read.csv("testdata/pass_cross.csv")
# 귀무가설 (H0, 영가설) : 공부하는 것과 합격여부는 관계가 없다.
# 대립가설 (H1, 영가설) : 공부하는 것과 합격여부는 관계가 있다.
# 유의 수준 0.05

study
table(study$공부함, study$합격)
nrow(study[study$공부함==1 & study $불합격 == 1,])
subset(study, study$공부함==1 & study $불합격 == 1) # nrow랑 동일

library(dplyr)
filter(study, study$공부함==1 & study $불합격 == 1)
study %>% filter(study$공부함==1 & study $불합격 == 1)

df <- table(study$공부안함, study$불합격)
colnames(df) <- c("합격", "불합격")
rownames(df) <- c("공부함", "공부안함")
df

margin.table(df, 1) # 행도수의 합
margin.table(df, 2)

# 기대값 = 각행의 주변합 * 각열의 주변값 / 총합.

# 카이제곱 검정 통계값 : 3, 임계값 : 3.84.(카이제곱 분포표)
# 귀무가설 채택.

install.packages("gmodels")
library(gmodels)
CrossTable(study$공부함, study$합격, chisq = T)
