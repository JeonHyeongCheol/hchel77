no <- 7
no >= 2 + 2 * 2 | no <5 %% 2
no <= 5 & no <= 10
no != 5

# if
x <- 10
x <- 10; y <- 5
if(x + y >= 10) {
  cat("결과는 ", x+y)
  cat("참")
}

if(x + y >= 20) {
  cat("결과는 ", x+y)
  cat("참")
} else {
  print('거짓')
}

ifelse(x > 5, 'good', bad) # 3항 연산자

# switch
switch("age", id = "hong", age = "33", name = "홍길동") # 조건값에 맞는 것 출력

a <- 1
switch(a, mean(1:10), sd(1:10)) # 앞에 있는 것이 1번, 뒤에있는 것이 2번

a <- 2
switch(a, mean(1:10), sd(1:10))

# which  vector를 대상으로 index 반환 함.
name <- c("kor", "eng", "mat")
name
which(name == "kor") # index 값 리턴해줌.
which(name == "korea")

no <- 1:3
df <- data.frame(번호=no, 이름=name)
df
which(df$번호 == 2)
which(df$이름 == "kor") 

# 반복문 for(변수 in 값) {표현식}
i <- 1 : 5
for(n in i) {
  cat(n) # print는 line skip이 있고, cat는 line skip하지 않음.
}

for(n in i) {
  if(n %% 2 == 0) {
    next # 그냥 넘어감.
  } else {
    print(n)
  }
}

# while(조건){표현식}
i <- 0
while(i < 5) {
  i = i + 1
  print(i)
}
cat('반복문 수행 후 i : ', i)

i <- 0
while(i <= 5) {
  print(i)
  i = i + 1
}
cat('반복문 수행 후 i : ', i)

i <- 0
while(TRUE) { # 무한루프
  i = i + 1
  print(i)
  if(i == 3) break
}
cat('반복문 수행 후 i : ', i)

# repeat {수행 및 탈출 조건}
cnt <- 1
repeat { # do while이랑 비슷
  print(cnt)
  cnt = cnt + 2
  if(cnt > 10) break
}

