# Matrices : 2차원의 dataset 이다. 이는 매트릭스 함수에 벡터를 제공함으로써 만들 수 있다.
# 동일 데이터 타입을 갖는 2차원 배열(행렬 구조).
# 선형대수학 정리 http://kipid.tistory.com/entry/Linear-Algebra
# - 생성 함수 : dim(), matrix(), rbind(), cbind()
# : matrix: 긴 벡터를 잘라서 행렬로 변환
# : cbind: 여러개의 벡터를 좌우로 붙여서 행렬로 변환
# : rbind: 여러개의 벡터를 위아래로 붙여서 행렬로 변환
# - 처리함수 : apply()

a <- 1:8
a
dim(a) # 행이 있는지 NULL

dim(a) <- c(2,4) # 2행 4열로 만들어줌
a
class(a) # Matrix로 만들어짐.
is(a)

m <- matrix(c(1:5))
m
m <- matrix(c(1:9), nrow = 3) # nrow : 행을 3으로 만들으라는 것.
m
m <- matrix(c(1:9), nrow = 3, byrow = T) # byrow : 열 우선인데 행우선으로 값을 채우라는 뜻.
m

m <- matrix(1:10, 2) # 2행으로 만들으라는 것.
m

m <- matrix(1:10, 3) # 3행으로 만들으라는 것. 비어있는데는 처음부터 값이 채워짐.
m
dim(m)
mode(m)
class(m)

m[1,] # 1행 값 출력.
m[,3] # 3열 값 출력.
m[2,3] # 2행 3열 값 출력.
m[1, c(2:4)] # 1행에 2열부터 4열 값 출력.
m[-1,] # 1행을 제외한 나머지 행 값 출력.
m[,-1] # 1열을 제외한 나머지 열 값 출력.
m[-1,-1] # 1행 1열 값을 제외한 나머지 값 출력.

a <- matrix(c(1:9), nrow = 3, ncol = 3) # 3행 3열 matrix를 만든다는 것.
a
rownames(a) <- c("r1", "r2", "r3") # 행 이름 설정.
colnames(a) <- c("one", "two", "three") # 열 이름 설정.
a
length(a) # 전체 값.
dim(a) # 행열 값.
ncol(a) # 열 값.
nrow(a) # 행 값.

a <- matrix(c(1,2,3,4), 2,2)
a
b <- matrix(5:8, 2)
b

a + b # matrix 구성원소끼리 합.
a * b # matrix 구성원소끼리 곱.

a%*%b # 행렬의 곱.
# a% + %b 이건 아님.

a
diag(a) # 행렬 값을 벡터로 출력.
diag(2) # 단위 행렬.
diag(3)

a
t(a) # 행열 전환.

solve(a) # 역행렬 값. inverse
solve(solve(a))
solve(a) %*% a # 역행렬 값을 원래의 값을 되돌릴 때. 기존행렬의 값을 곱하면 됨.

x1 <- c(5, 40, 50:52)
x2 <- c(30, 5, 6:8)
x1; x2
mr <- rbind(x1, x2) # 행 우선. 2 X 5
mr
mc <- cbind(x1, x2) # 열 우선. 5 X 2
mc

x <- matrix(c(1:9), nrow = 3) # 열 부터 채움. byrow을 하지 않았기 때문에.
x

#apply() : 특정자료에 함수를 적용
base::apply(x, 1, max) # 행 단위 최대값.
apply(x, 1, min)
apply(x, 2, min) # 열 단위 최소값.
apply(x, 2, mean) # 여기서 mean : 평균값.

myfunc <- function(x){
  x + c(5,10,15)
}

apply(x,1,myfunc) # 함수적용, matrix에 모든 값을 +5.

# snippet pack에 lapply, sapply
a <- apply(x, 2, mean)
b <- lapply(x, mean) # 열 단위, 리턴 값 : list
b <- sapply(x, mean) # 행 단위, 리턴 값 : vector

a; b; c

matrix(c(1,2,3,'a'), nrow = 2, byrow = T)

# ------------------------------------------------------------------------
# array

d <- c(1:12)
is(d)

arr1 <- array(d)
arr1
is(arr1)
dim(arr1)

arr2 <- array(d, dim=c(6,2))
arr2
dim(arr2)

arr3 <- array(d, dim=c(3,2,2)) # 3차원 3행, 2열, 2면
arr3
#, , 1 //1면

#     [,1] [,2]
#[1,]    1    4
#[2,]    2    5
#[3,]    3    6

#, , 2 // 2면

#     [,1] [,2]
#[1,]    7   10
#[2,]    8   11
#[3,]    9   12
dim(arr3)

arr3[,,1] # 1면
arr3[,1,1] # 1면 1열
arr3[1,,1] # 1면 1행
arr3[1,1,1] # 1면 1행 1열

# 참고 : 메모리 주소에 ...
tracemem(arr3) # 메모리 주소값을 확인 할 수 있음.
c <- 10
tracemem(c)
untracemem(c) # 주소값 삭제

# ------------------------------------------------------------------------
# List : 서로 다른 타입의 데이터를 기억할 수 있다.
# - 성격이 다른 데이터(벡터, 행렬, 데이터프레임 등 모든 데이터)의 기억이 가능,
# c의 구조체, java의 레코드형 기억장소와 유사
# - 생성 함수 : list()
# - 처리 함수 : unlist(), lapply(), sapply()

# list는 key, value 값으로 되어 있음.
li <- list("1","이순신",95,"2","홍길동",85)
li
# [[1]] // 키
# [1] "1" // 값
class(li)
unli <- unlist(li) # unlist : 타입을 기억하지 않음. character형식으로 바뀜.
unli

num <- list(c(1:5), c(6:10), c("a","b","c")) # 1개의 키 값안에 여러값을 넣을 수 있음.
num
num[1]
class(num[1]) # 값
num[[1]]
class(num[[1]]) # 키

num[[1]][1] # [[키]][값]
num[[3]][2]

member <- list(name="hong", age=23) # = : list에 =을 쓰면 이름을 줄 수 있음. 출력시 $가 붙음.

member$name
member$age
member$name <- "홍길동" # 이름을 바꿀 수 있음.
member