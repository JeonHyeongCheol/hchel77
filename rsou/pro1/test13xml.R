library(XML)
library(httr)

url <- "http://media.daum.net"
#doc <- htmlTreeParse(url, useInternalNodes = T, trim = T, encoding = "UTF-8")
doc <- htmlParse(rawToChar(GET(url)$content), encoding = "UTF-8")
doc

news <- xpathSApply(doc, "//a[@class='link_txt']", xmlValue)
news

# gusb, stringr 사용해서 제거 해줄 수 있음.(필요없는 값)
result <- gsub("[\r\n\t]", "", news) # 엔터나 탭 값을 ""로 바꿔줌.
result <- gsub("[[:punct:]]", "", result) # 문장부호 제거.
result <- gsub("[[:cntrl:]]", "", result) # 특수기호 제거.
result <- gsub("\\d+", "", result) # 숫자 제거.
result <- gsub("\\s+", " ", result) # 공백 제거.
result

