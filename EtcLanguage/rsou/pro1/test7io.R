# 키보드 입력
n <- scan()
sum(1:n)

ss <- scan(what = "")
ss

df1 <- data.frame()
df2 <- edit(df1) # 데이터 편집기를 통해 입력 받을 수도 있음.
df2

# 파일
list.dirs() # 숨겨진(?) 파일?
list.files() # file 목록 보여줌.
list.files("testdata") # testdata에 있는 파일 목록을 보여줌.

# 공백
stud <- read.table("testdata/student.txt")
stud

stud1 <- read.table(file = "testdata/student.txt", header = T) # file은 써도되고 안써도 됨.
stud1

# 구분자가 ;인 경우
stud2 <- read.table("testdata/student2.txt", header = T, sep = ";") # ;로 구분하여 만들어줌.
stud2

stud3 <- read.table("testdata/student3.txt", header = T, sep = "", na.strings = "-") # -을을 NA로 처리 해주고 있음.
stud3

stud4 <- read.csv("testdata/student4.txt") # csv는 hearder를 T로 가지고 있는 듯..
stud4

install.packages("xlsx")
library(xlsx)
stud5 <- read.xlsx("testdata/student.xlsx", sheetIndex = 1, encoding = "UTF-8") # 엑셀을 읽을 경우 별도의 package를 설치해줘야 함.
stud5

stud6 <- read.xlsx(file.choose(), sheetIndex = 1, encoding = "UTF-8") # 파일을 선택해서 출력 해줄 수 있음.
stud6

stud7 <- read.csv("http://www.kma.go.kr/XML/weather/sfc_web_map.xml", encoding = "UTF-8") # 웹페이지에서 읽어서 사용 할 수 있음.
stud7
