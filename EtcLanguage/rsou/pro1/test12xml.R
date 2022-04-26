# xml 읽기
install.packages("XML")
library(XML)

xmlData <- xmlTreeParse("testdata/score.xml", useInternalNodes = T) # 데이터 가저오기(파싱)
xmlData

rootNode <- xmlRoot(xmlData) # xml에 root를 가져옴.
rootNode

rootNode[[1]]
rootNode[[1]][[1]]

names <- xpathSApply(rootNode, "//name", xmlValue) 
# root 노드 안에 있는 name을 찾아 value값을 얻음.
# 경로는 맞게 설정되어 있어야지 가져옴. 
names

scores <- xpathSApply(rootNode, "//score", xmlValue)
scores

df <- data.frame(names, scores)
df

df2 <- xmlToDataFrame("testdata/score.xml") # 요렇게 한번에 읽어올수도 있음.
df2

# 웹페이지 값 읽어오기.
install.packages("httr")
library("httr")

url <- "https://www.melon.com/song/popup/lyricPrint.htm?songId=111369"

source <- htmlParse(rawToChar(GET(url)$content)) # get 사용을 위해 httr 설치.
# htmlParse
# HTML Parser는 HTML 문법에 따라 해석 작업을 수행하므로, 
# HTML로 너무 불규칙하게 문법 오류가 있을 경우에는 
# 바르게 정보를 분석 할 수 없을 가능성이 있습니다.

# rawTochar
# 어느 하나의 문자열이나 (0 ""와) 단일 바이트의 문자 벡터 원시 바이트를 변환.

source

Lyrics <- xpathSApply(source, "//div[@class='box_lyric_text']", xmlValue)

# 웹페이지를 소스보기 하여 목표값을 찾은 후 읽는다.
# #여기서는 Class 속성값을 “box_lyric_text”으로 가지는 div 태그 안의 문자열.

Lyrics
