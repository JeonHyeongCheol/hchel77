# Data Frames : 구조화된 데이터 오브젝트이다. 
# 데이터베이스의 테이블 구조와 유사, 칼럼 단위로 type이 달라도 됨
# - 가장 많이 사용되는 객체 타입
# - 데이터프레임과 행렬은 모두 2차원 데이터이지만 다음과 같은 점이 다르다.
# 데이터프레임의 각 열은 서로 다른 자료형이 될 수 있다.
# 데이터프레임은 열 이름, 행 이름을 가질 수 있다.
# - 생성 함수 : data.frame(열이름1=요소, 열이름2=요소, …)
# - 처리 함수 : apply(), str(), ncol(), nrow(), summary()

no <- c(1,2,3)
name <- c("hong","kim","park")
pay <- c(150, 250, 300)

df <- data.frame(bunho=no, irum=name, imkum=pay) # 데이터프레임. 칼럼명을 줌.
df # 데이터 통계, 추론 통계를 할 수 있음.

class(df)
is(df)

df <- data.frame(irum=c('james','oscar','tom'),
                 nai = c(25, 27, 29),
                 row.names =c("one", "two", "three")) # 행에 Name을 설정.
df
nrow(df) # 행수 확인.
ncol(df) # 칼럼수 확인.
str(df) # 구조 확인.

summary(df) # 통계값 확인.
head(df, n=2) # 위에서 부터 숫자만큼 출력.
tail(df, n=2) # 밑에서 부터 숫자만큼 출력.
? data.frame # 데이터 프레임에 대한 도움말 확인.

m <- matrix(c(1, "hong", 150, 2, "lee", 250, 3, "kim", 350), 3, by=T) # Matrix
m

mdf <- data.frame(m) # Matrix를 data.frame으로 바꿈.
mdf
class(mdf)

m <- matrix(1:6, nrow = 3)
m
mdf <- data.frame(m)
mdf

colnames(mdf)[1] <- "열1" # 1번째 열에 대해서만 이름을 줄 수 있음.
colnames(mdf) <- c("c1","c2") # ?
mdf
rownames(mdf)[1] <- "행1" # 행에서 한개만 설정 가능.
mdf

mdf$c1 # 인덱싱
class(mdf$c1)
typeof(mdf$c1)
mdf["c1"]
mdf[1,]
mdf[,1]
mdf[1,1]

mdf[2:3, c(1,2)]
mdf[2:3, c('c1', 'c2')]

# 조건 지정
mdf[mdf$c1 == 2,]
subset(mdf, c1 == 2) # 데이터프레임, 조건

mdf[mdf$c1 == 2 & mdf$c2 == 5,]
subset(mdf, c1 == 2 & c2 == 5)

subset(mdf, select = c(2)) # 2열만 선택.
subset(mdf, c1 == 2, select = c(2))

mdf$c2 <- ifelse(mdf$c2 == 4, NA, mdf$c2) # 두번째열 이름 바꿈.
mdf

summary(mdf)
mean(mdf$c2)
mean(mdf$c2, na.rm = T)

# 행/열 추가
mdfr <- rbind(mdf, c(10, 11)) # 행 추가.
mdfr
mdfc <- cbind(mdf, c3=c("a","b","c"), c4=c("a1","b1","c1")) # 열추가
mdfc

# 행/열 삭제
mdfc[, "c1"] <- NULL # c1열에 값 삭제
mdfc

mdfr <- mdfr[-1, ] # 첫번째행 값 삭제
mdfr

# txt 읽기
getwd() # 현재경로 가져오기.
setwd()

txtdf <- read.table("testdata/emp.txt", header = T) 
# read.가져오고 싶은 타입으로 선택 후 경로 설정, 
# header는 첫번째 행을 칼럼명으로 읽는 다는 것. 기본족으로 header = f로 되어 있음.
txtdf

csvdf <- read.csv("testdata/emp.csv", header = T)
csvdf

csvdf2 <- read.csv("testdata/emp2.csv", header = F, col.names = name)
csvdf2

df <- data.frame(eng=c(90,80,78), mat=c(88,77,99), class=c(1,2,3))
df
save(df, file = "output/df_1.rda") # 자료 저장.
rm(df) # 자료 지움.
df
load("output/df_1.rda") # 자료 읽어 오기.
df

# apply()
r1 <- c(100,90,80)
r2 <- c(90,80,75)
r3 <- c(86,78,95)
da <- data.frame(r1, r2 ,r3)
da

apply(da, 1, min) # 행 단위 제일 작은 값 출력
apply(da, 2, min) # 열 단위 제일 작은 값 출력

# iris Dataset 사용 
head(iris)
base::apply(iris[1:4], 1, mean) # 1행 부터 4행까지 평균
apply(iris[1:4], 2, mean) # 열 단위 평균

lapply(iris[1:4], mean) # 행단위로 안됨. 열단위로만 가능.
sapply(iris[1:4], mean) # 열단위 평균

install.packages("data.table")
library(data.table)
? data.table # \자료의 양이 정말 많을 경우(GB, TB) frame보다 table을 권장함.

dt <- as.data.table(df) # 데이터 테이블은 행 옆에 : 이 붙어 있음.
dt
class(dt)
dt2 <- data.table(bun=c(1,2), irum=c("tom","oscar"))
dt2

idata <- as.data.table(iris) # 데이터 테이블로 바뀜.
idata
str(idata)
mean(idata$Sepal.Length)

# merge
height <- data.frame(id=c(1,2,2), h=c(180, 175, 170))
weight <- data.frame(id=c(1,2), h=c(80, 75))
height; weight
merge(height, weight) # X
merge(height, weight, by.x = "id", by.y = "id") # id 기준으로 병합한다는 것.
merge(height, weight, all = T) # All : 데이터가 없는 경우에도 모두 참여 시킴.

