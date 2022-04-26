# 변수와 Data type
var <- 1 # R에서는 이 방식을 씀.
var <- 2
var = 3
4 -> var
var # 변수는 영문자로 시작.

(var_first <- 5)
var.second <- 6 # 이것도 객체 변수. 하지만 위에 방식들 보다는 이 방식을 많이 선호.


# () 괄호는 출력도 같이 실행.
# 전체 실행시 : Crtl + Alt + R 
# 주석 : Crtl + shift + C, #

# 함수입력 후 F2를 누르면 함수 내용을 볼 수 있음.
rnorm(10) # 정규분포를 따르는 난수, 함수사용.

# 데이터 유형
kbs <- 9
is.numeric(kbs) # 수치형 확인(숫자). numeric는 double, Interger로 나뉨.
typeof(kbs) # 자료형 타입확인 할 수 있음.
typeof(4) # 기본 숫자값은 Double.
typeof(3L) # L을 붙이면 Integer(정수)가 됨.
mbc <- 5L
typeof(mbc)

sbs <- as.integer(kbs) # as.interger : 정수형으로 형변환.
typeof(sbs)

is(sbs) # 작은 범위부터 type print

string <- "홍길동"
is.character(string) # 문자열 확인(문자)

b <- TRUE
is.logical(b) # 논리형 확인

x <- is.numeric(3)
is.logical(x)

aa <- NA # NA :  Missing Value(결측값 : 값이 입력되지 않은 상태)
is.na(aa)
sum(2, 3)
sum(2, 3, NA)
sum(2, 3, na.rm = T) # na.rm = T : na 값을 포함 하지 말 것.

bb <- NULL
typeof(bb) # NULL 값
typeof(NULL) # NULL 값
typeof(NA) # 논리형
typeof(NaN) # 더블형
0 / 0
Inf + -Inf # 숫자가 아닌 값
Inf - Inf 

sum(2, 3, NULL) # NULL이 있어도 연산함.
sum(2, 3, NA) # 연산하면 값이 없는 값
sum(2, 3, NaN) # 연산하면 숫자가 아닌 값

z <- 5.3 - 3i
z

is.complex(z) # ?
Re(z) # 복소수에서 실수 부분 가져옴.
Im(z) # 복소수에서 허수 부분 가져옴.

# Factor(요인형) 범주를 갖는 Vector
kbs <- c("second", "first", "third", "second") # c는 배열생성.
kbs
kbs[1] # R는 0부터 시작안함. 1부터 시작임.
is.character(kbs)
is.factor(kbs)

mbc <- as.factor(kbs) # factor type 으로 형변환함(범주형 : 명목형) # 데이터값에 대해서 순서를 매김.

mbc
is.factor(mbc)
base::levels(mbc) # 패키지명::함수명 - 나중에 package에서 많이 쓰기 때문에 이런식으로 나중에 쓰게 될 것.

summary(mbc) # 빈도수 확인 가능.

par(mar=c(1,1,1,1)) # 그래픽스가 안나올 경우 쓰면 됨. mar : margin, c : 배열인데 이 배열만큼 행을 만듬.
plot(mbc)

mbc2 <- factor(mbc, levels = c("third", "first", "second"), ordered = T) # 범주형인데 순서를 줌.
mbc2 # 실행시키면 순서가 보임.

names = c("고길동", "홍길동", "사임당")
gender <- c("2","2","1")
names
gender
str(gender) # 슬라이싱이라고 생각하면 됨.
gender[4] <- "여자" # 배열에 값 추가
gender
f.gender = factor(gender) # gender를 factor로 만들어서 변수값에 저장
f.gender

ans <- c(2,2,3,2,3,4,4,4,3,4)
f.ans <- factor(ans) # factor는 중복값을 하나로 정리해서 보여줌.
f.ans
str(f.ans)

# 함수
f <- function() {
  return('good')
}

f() # 함수 실행

typeof(f) # R에서 함수는 closure 임.

# 자료형 자료구조 확인 : mode(), class()
x <- TRUE
cat(mode(x), class(x), typeof(x)) # cat는 출력이라고 생각하면 됨.

x <- 1L
cat(mode(x), class(x), typeof(x))

x <- 1.2
cat(mode(x), class(x), typeof(x)) # mode : 물리적 자료형, class : 추상적 자료형 , type : 타입형

# mode : 숫자형(numeric), 문자형(character), 리스트(list), 함수(function) 등

x <- "aa"
cat(mode(x), class(x), typeof(x))

ls() # 현재까지 사용한 변수객체 목록들을 확인 가능.
objects() # 변수 객체 목록 확인 가능. ls()와 동일.
ls.str() # 변수객체들에 세부 내용까지 확인 가능.

rm(z, var) # 선택적 삭제
rm(list=ls()) # ls에 있는 list를 다 지운다는 것.
ls()
gc() # 메모리 점유한 것. 전부 없앰. garbage collector.

# package : R 함수들을 모아 놓은 컬렉션. 묶음. 꾸러미.
available.packages() # package에 대한 정보들.
dim(available.packages()) # package 갯수.
sessionInfo() # session에 대한 정보.

#package 설치
installed.packages() # 설치되어있는 패키지.
# 방법 1 : 오른쪽 하단 탭에 package - install - package명 검색 - install.
length(installed.packages()) # 설치한 갯수.

# 방법 2 : script에서 설치
install.packages("plyr")
library(plyr) # 설명을 볼 수 있음. 리턴 값 있음.
#require(plyr) # 리턴값 없음.

library(help = "plyr") # 도움말 보기

search() # 현재 로딩된 package 확인 가능.
ls("package:plyr") # 목록 확인.
data(package="plyr") # 패키지에 대한 데이터를 볼 수 있음. 데이타셋을 확인 할 수 있음.

remove.packages("plyr") # 패키지 제거.
library(plyr)