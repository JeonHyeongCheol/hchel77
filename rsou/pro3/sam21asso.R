library(KoNLP)
Sys.setenv(JAVA_HOME="C:/Program Files/Java/jre1.8.0_171")
library(rJava)

txt_data <- file("testdata/news_data.txt", encoding = "UTF-8")
t_data <- readLines(txt_data)
close(txt_data)
t_data

lword <- Map(extractNoun, t_data)
lword

length(lword)
lword <- unique(lword) # 중복제거
length(lword)

lword <- sapply(lword, unique)
lword

func1 <- function(x) { # 2 ~ 4 이내의 한글만 추출
  nchar(x) <= 4 && nchar(x) >= 2 && is.hangul(x)
}

func2 <- function(x) {
  Filter(func1, x)
}

lword <- sapply(lword, func2)
str(lword)

library(arules)
wordtran <- as(lword, "transactions")
wordtran
inspect(wordtran[1:5])
tranrule <- apriori(wordtran, parameter = list(supp=0.25))
inspect(tranrule)

plot(tranrule, method = "graph")
