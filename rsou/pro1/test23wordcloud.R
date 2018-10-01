install.packages("wordcloud")

library(KoNLP)
library(tm)
library(rJava)
library(wordcloud)

data1 <- readLines("testdata/wordclo.txt")
data1

data2 <- sapply(data1, extractNoun, USE.NAMES = F) # sapply 함수를 실행시키는 녀석.
data2 # list Type

data3 <- unlist(data2)
typeof(data3) # List에서 character 변환.
data3

library(dplyr)
data3 <- gsub('[~!@#$%^&*()_+?<>-]','',data3) # 특수문자 제거.
data3
data3 <- gsub('\\d+','',data3) # 숫자 제거. [0-9] 써도 상관없음.
data3
data3 <- gsub(' ','',data3) # 공백 제거.
data3
data3 <- Filter(function(x) {nchar(x) >= 2}, data3) # 문자갯수 걸러내기. 2개이상인 문자만 남김.
data3

# 파일로 저장.
write(unlist(data3), 'testdata/wordclomy.txt')

data4 <- read.table('testdata/wordclomy.txt')
head(data4)

word <- table(data4)
head(sort(word),10)

install.packages("RColorBrewer")
library(RColorBrewer)

display.brewer.all()

palete <- brewer.pal(9, "Set3")
windowsFonts(font=windowsFont("궁서체"))

library(wordcloud)
wordcloud(names(word), freq = word, min.freq = 2, random.order = F, random.color = T, colors = palete)
#wordcloud::wordcloud(names(word), freq = word, min.freq = 2, random.order = F, random.color = T, colors = palete)

