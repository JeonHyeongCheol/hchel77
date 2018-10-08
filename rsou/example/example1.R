# <연습문제1> name, age, address 라는 변수에 적당한 데이터를 대입하시오.
# 조건1) 변수의 특성에 맞게 값을 초기화하고 결과를 확인한다.
name <- "홍길동"
age <- 10
address <- "서울특별시 강남구 테헤란로"

# 조건2) 각 변수에 데이터 타입 보기 함수와 타입확인 함수 적용
# typeof(), is.***()
typeof(name) # 타입 확인
is.character(name) # 캐릭터 타입인지 boolean으로 리턴.

typeof(age) # 여기서는 numeric(dobule)
is.numeric(age) # R에서 숫자는 기본 Double형, Integer는 타입을 바꿔줘야 함.
age <- as.integer(age) # 여기서는 integer, as는 형변환 해줌.
is.integer(age)

typeof(address)
is.character(address)

# <연습문제2> R에서 제공하는 women dataset을 이용하여 아래의 조건을 처리하시오.
# <조건1> women dataset은 어떤 데이터의 모음인가?
is(women) # 답 : "data.frame" "list"       "oldClass"   "vector"   
# Dataset의 데이터형태를 알고 싶으면 is 사용.

# <조건2> women dataset의 자료 유형과 자료구조는?
typeof(women) # 답 : list
class(women) # 답 : data.frame

# <조건3> plot() 함수를 이용하여 기본 차트를 그리시오.
plot(women) # plot : 기본 차트 그림.

# <연습문제3> 다음과 같은 벡터를 생성하시오.
# 1) vec1 벡터 변수를 만들고, "R" 문자가 5회 반복되도록 하시오.
vec1 <- rep("R", 5)
vec1

# 2) vec2 벡터 변수에 1~10까지 3간격으로 연속된 정수를 만드시오.
vec2 <- seq(1, 10, by = 3) # seq : 순서대로 올라감. by는 숫자 간격 옵션.
vec2

# 3) vec3에는 1~10까지 3간격으로 연속된 정수가 3회 반복되도록 만드시오.
vec3 <- rep(seq(1, 10, by = 3), 3) # 함수는 겹쳐서 사용 가능. rep()는 연속적으로 반복 되는 것.
vec3

# 4) vec4에는 vec2~vec3가 모두 포함되는 벡터를 만드시오.
vec4 <- c(vec2, vec3) # 벡터 중복 허용은 c() 함수를 사용. 중복허용하지 않을 경우 union 사용.
vec4

# 5) 25~ -15까지 5간격으로 벡터 생성 : seq() 함수 이용 
seq(-15, 25, by = 5)

# 6) vec4에서 홀수번 째 값들만 선택하여 vec5에 할당하시오.
vec5 <- vec4[seq(1, length(vec4), by = 2)] # 순서대로 vec4의 크기만큼 2씩 증가시켜 맞는 값만 출력.
vec5

# <연습문제4> 다음의 벡터를 컬럼으로 데이터프레임을 생성하시오.
name <- c("최민수","유관순", "이순신","김유신","홍길동")
age <- c(55,45,45,53,15) # 연령
gender <- c(1,2,1,1,1)    # 1:남자, 2: 여자
job <- c("연예인","주부","군인","직장인","학생")
sat <-c(3,4,2,5,5)        # 만족도
grade <- c("C","C","A","D","A")
total <-c(44.4,28.5,43.5,NA,27.1)   # 총구매금액(NA:결측치)

typeof(df)
class(df)

# <조건1> 위 7개 벡터를 user이름으로 데이터 프레임 생성
user <- data.frame(name, age, gender,job,sat,grade,total)
user

# <조건2> 성별(gender) 변수를 이용하여 히스토그램 그리기
hist(user, xlab = "gender") # 
barplot(prop.table(table(user$gender))) # gender를 테이블로 만들고 비율을 계산해 세로형 막대로 보여줌.

# <조건3> 만족도(sat) 변수를 이용하여 산점도(geom_point) 그리기
install.packages("ggplot2")
library(ggplot2)
plot(sat ~ name, user, pch = 1)

# <조건4> user에서 짝수행만 선택해서 user2에 넣으시오.
seq(1, nrow(user), by = 2)
user2 <- user[seq(2, nrow(user), by = 2),] # 2행부터 마지막행까지 뽑아냄. by = 2씩 증가. []는 행,열 출력
user2
? seq

# <연습문제5> 
r1 <- c(100, 80, 90)
r2 <- c(90, 80, 75)
r3 <- c(86, 78, 95)
da <-data.frame(r1, r2, r3)

# da를 대상으로 apply()를 적용하여 행/열 방향으로 내장 함수(max,mean())를 적용하시오.
da
# <조건1> 행/열 방향 max()함수 적용
apply(da, MARGIN = 1, max) # 행 단위
apply(da, 2, max) # 열 단위
# <조건2> 행/열 평균 mean()함수 적용
apply(da, 1, mean)
apply(da, 2, mean)
# apply에서 1는 행, 2는 열을 뜻함.

# <연습문제6> kor(국어 점수 2개)과 eng(영어 점수 2개)를 id로 merge해서 score에 할당하시오.
kor <- data.frame(id=c(1,2), kor=c(85,75))
eng <- data.frame(id=c(1,2), eng=c(95,86))

kor
eng

score <- merge(x = kor, y = eng, by.x = "id", by.y = "id")
# socre <- merge(kor, eng, by.x = "id", by.y = "id") # 위에와 동일
score

# <문자열 처리 연습문제> 아래의 data 객체를 대상으로 정규표현식 등을 적용하여 문자열을 처리하시오
data <- c("2018-02-05 income1coin","2018-02-06 income2coin", "2018-02-07 income3coin")
data

# <처리1> 일자별 수입(코인 수)을 출력하시오.
# 출력 결과) 1coin 2coin 3coin
str_extract(data, "[0-9]{1}[a-z]{4}")    # 숫자 1개 이후에 영문자 4개 등장

# <처리2> 위 벡터에서 연속하여 2개 이상 나오는 모든 숫자를 제거하시오. 
# 출력 결과) "-- income1coin" "-- income2coin" "-- income3coin" 
gsub("[0-9]","", data) # gsub : 정규표현식 및 문자열이 맞는 값을 바꾸는 함수.

# <처리3> 위 벡터에서 - 를 / 로 치환하시오.
gsub("-", "/", data)

# <처리4> 모든 원소를 쉼표(,)에 의해서 하나의 문자열로 합치시오.
# 조건) paste() 함수를 이용 
paste(data[1], data[2], data[3], sep = ", ") # 모든 데이터를 엮어 주면 가능.

# <if 연습문제> 데이터프레임을 대상으로 조건에 맞게 처리하시오.
name <- c("aa","bb","cc","dd")
gender <- c("F","M","M","F")
price <- c(50, 65, 45, 75)
person <- data.frame(name,gender,price)
person

# <처리1> price가 65만원 이상인 고객은 "Best" 미만이면 "Normal" 문자열을 result 변수에 넣고, person 객체에 칼럼으로 추가하기
result <- ifelse(person$price >= 65, "Best", "Normal") # 먼저 값을 구해서 result 변수에 넣고,
result
person <- data.frame(person, result) # 기존 data.frame에 추가 해주면 됨.
person

# <처리2> result의 빈도수를 구하시오.
# 힌트) table()함수 이용
table(person$result) # table() : 빈도수 구하기.

# <처리3> gender가 'M'이면 "Male", 'F'이면 "Female" 형식으로 client의 객체에 gender2 칼럼을 추가하고 빈도수 구하기
gender2 <- ifelse(person$gender == 'M', "Male", "Female") # if문으로 값 확인 후 변환.
client <- data.frame(gender2) # Client 객체 만든 후 칼럼 추가하기.
table(client$gender2) # 빈도수 구하기.

# <사용자 정의 함수 연습문제>
# income 객체에서 날짜별 자판기의 코인 수가 2 이상이면 "수입금 맑음"을, 아니면 "수입금 흐림"를 출력하는 사용자 정의 함수를 작성하시오.
income <- c("2018-06-05 income1coin", "2018-06-06 income2coin", "2018-06-07 income3coin")
# 힌트) 사용자 정의 함수에 사용할 내장함수 : stringr 패키지 - str_extract(), str_replace()함수
# 숫자 변환은 as.numeric()함수를 사용한다.
library(stringr)
my <- function() {
  ifelse(as.numeric(str_extract(income, pattern = "(?<=income)[0-9]*")) >= 2, "수입금 맑음", "수입금 흐림")
}
my()
str_extract(income, pattern = "(?<=income)[0-9]*") # income 보다 뒤에 있는 것.
substr(income, 18, 18) # 위에 라인이나 현재라인 이나 둘다 가능.

# ** if 조건판단문이 있는 함수 작성 
# 문제1) 입력되는 인수가 5보다 클 때는 1을 출력하고 5 보다 작거나 같으면 무조건 0을 출력하는 함수 myf1( ) 을 작성 후 실행.
myf1 <- function() {
  ifelse(scan() > 5, 1, 0) # 조건에서, 참일때, 거짓일때 값 출력.
}

myf1()

# 문제2) 입력되는 인수가 양수일 때는 1을 출력하고 나머지는 항상 0 을 출력하는 함수 myf2( ) 를 작성 후 실행.
myf2 <- function() {
  ifelse(scan() > 0, 1, 0)
}

myf2()

# 문제3) 두 숫자를 입력해서 첫 번째 숫자가 두 번째 숫자보다 클 경우 첫번째 숫자에서 두 번째 숫자를 뺀 값을 출력하고 
# 두 번째 숫자가 첫 번째 숫자 보다 클 경우 두 번째 숫자에서 첫 번째 숫자를 뺀 값을 출력하는 함수 myf3( ) 을 작성 후 실행
myf3 <- function() { # 방법 1
  num1 <- scan()
  num2 <- scan()
  ifelse(num1 > num2, num1 - num2, num2 - num1) # ifelse 안에서 값 계산하여 출력.
}

myf3 <- function() { # 방법 2
  num <- scan()
  ifelse(num[1] > num[2], num[1] - num[2], num[2] - num[1])
}

myf3()

# 문제4) 입력 값이 0 이하일 경우 0으로 출력하고 1-5 사이의 값일 경우 1 을 출력하고 
# 5 초과의 값이 입력될 경우 10을 출력하는 함수 myf4( )를 작성 후 실행.

myf4 <- function() { # 방법 1
  num <- scan()
  ifelse(num < 0, 0, ifelse(num > 5, 10, 1)) # ifelse 안에 ifelse 넣기.
}

myf4 <- function() { # 방법 2
  num <- scan()
  ifelse(num > 5, 10, ifelse(num < 0, 0, 1))
}

myf4()


# ** for 반복문이 있는 함수 작성
# 사용자에게 출력을 원하는 단 수를 입력 받아서 해당 단의 구구단을 출력하는 함수를 작성하라. 
gugudan <- function(num) {
  for(i in 1:9) {
    cat(num, " * ", i, "=", num*i, "\n") # 구구단 출력, for
  }
}

gugudan(3)

# ** while 반복문이 있는 함수 작성
# 감자
# 고구마
# 양파
# 당근
# 미나리
# 1. 채소.txt 를 작업 디렉토리에 생성.
# 2. 채소.txt 를 R로 불러와서 변수 var1 에 저장.
# 3. 채소 이름을 출력하되 양파가 나오면 양파를 건너뛰고 출력하는 문장을 작성. 
# (if 문과 while 문을 사용하면 되겠죠?)
x <- c("감자","고구마","양파","당근","미나리")
write(xx, file = "output/채소.txt")
var1 <- read.table("../example1/output/채소.txt", stringsAsFactors = F) # read.table 가져올 때 stringsAsFactors 확인하기.

nrow(var1)
str(var1)

ifelse(var1[1:5,] == "양파", 1, 0)


i <- 0
while(i < 5) {
  i <- i + 1
  if(var1[i,] == "양파") {
    
  } else {
    cat(var1[i,], "좋아\n")
  }
}

# [출력결과]
# [1] "감자 좋아"
# [1] "고구마 좋아"
# [1] "당근 좋아"


# xml 문제)
# 기상청이 제공 (http://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108) 하는 XML 문서를 읽어 
# 각 지역의 온도를 갖는 data.frame을 작성하시오.

#       도시      상태    최저  최고
# 1     서울    구름많음   -9   -4
# 2     인천    구름많음   -9   -4
# 3     수원    구름조금  -12   -5
# 최저온도평균
# 최저온도 표준편차 

install.packages("XML")
library(XML)

fileurl <- "http://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108"
doc <- xmlTreeParse(fileurl, useInternalNodes = TRUE)
rootNode <- xmlRoot(doc)
xmlName(rootNode)

rootNode[[1]][[1]]

city <- xpathSApply(rootNode,"//body//city",xmlValue)
city
status <- xpathSApply(rootNode,"//body//wf",xmlValue)
status
stamin <- xpathSApply(rootNode,"//tmn",xmlValue)
stamin
stamax <- xpathSApply(rootNode,"//tmx",xmlValue)

weath <- data.frame(city, status, stamin, stamax, stringsAsFactors = F)

# <차트 연습문제> 
# iris 데이터를 대상으로 plot() 함수를 이용하여 조건에 맞게 차트를 그리시오.
# 조건1) 1번 칼럼이 x축, 3번 칼럼을 y축으로 차트 그리기
head(iris)
plot(iris$Sepal.Length, iris$Petal.Length) # 차트 출력시 x, y 값 입력
# 조건2) 5번 컬럼으로 색상 지정하기
plot(iris$Sepal.Length, iris$Petal.Length, col = iris$Species) # col는 컬럼으로도 가능.

# 조건3) 제목 추가("iris 데이터 테이블 산포도 차트")
plot(iris$Sepal.Length, iris$Petal.Length, col = iris$Species, main = "iris 데이터 테이블 산포도 차") # 제목은 main 사용.

# 조건4) 파일로 차트 저장하기("output/iris.jpg")
jpeg("../example1/output/iris.jpg") # 차트 저장시 jpeg 사용.
plot(iris$Sepal.Length, iris$Petal.Length, col = iris$Species, main = "iris 데이터 테이블 산포도 차") # 차트 확인 후
dev.off() # off 하면 출력 됨.

# <dplyr 패키지 관련>
# <연습문제 1> 평균 비행시간(AirTime)을 구하시오
install.packages("dplyr")
install.packages("hflights")
library(dplyr)
library(hflights)

hflight <- hflights

head(hflight)

mean(hflights$AirTime, na.rm = T)

# <연습문제2> n(), sum()를 이용하여 평균 비행시간을 구하시오.
sum(hflight$AirTime, na.rm = T) / nrow(hflight)

# <연습문제3> '연습문제2'에서 NA값으로 인하여 평균에 차이가 발생하였다. 평균 비행시간의 차이를 보정하시오.
hflight <- na.omit(hflight) # NA값 제거.
nrow(hflight)

sum(hflight$AirTime, na.rm = T) / nrow(hflight)

mean(hflight$AirTime, na.rm = T)

# <연습문제4> 도착시간(ArrTime) 표준편차와 분산(표준편차의 제곱근) 구하기
sd(hflight$ArrTime)
var(hflight$ArrTime)

