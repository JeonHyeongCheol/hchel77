# 형태소 분석
install.packages("KoNLP") # 형태소 분석
install.packages("tm") # 데이터 마이닝

library(KoNLP) # 순서대로 inmemory 할 것. 안하면 오류 남.
library(tm)
library(rJava)

sentense <- "내 이름은 홍길동. 홍길동의 동생이 방에서 나온다"
extractNoun(sentense)

SimplePos09(sentense) # 어절 나누기.
SimplePos22(sentense)

ss <- SimplePos09(sentense)
ss

library(stringr)
extn <- str_match(ss, '([가-힣]+)/N')

mo <- MorphAnalyzer(sentense)
mo

sum(sapply(mo, length))

library(ggplot2)

library(data.table)
morth_cnt <- rbind(data.table(eojeol=names(mo), cnts = sapply(mo, length), kind='어절'))
ggplot(morth_cnt, aes(eojeol, cnts)) + geom_bar(stat = 'identity', aes(fill=kind), position = 'dodge') + 
  coord_flip() + 
  ggtitle('어절별 분석 결과')
