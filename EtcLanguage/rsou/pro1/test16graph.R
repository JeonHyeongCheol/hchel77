stu <- read.csv("testdata/ex_studentlist.csv")
stu
names(stu) # 칼럼명 보기.

# 가로 막대 그래프.
barplot(stu$grade) # $쓰면 변수에 칼럼명이 나옴.
barplot(stu$grade, ylim = c(0,5), # y축을 0 ~ 5까지 지정.
        col=rainbow(3), # graph에 color를 줌.
        main = "차트 제목", # 차트제목을 설정 할 수 있음.
        xlab = "학년", ylab = "학생수" # x, y축에 이름 설정.
        ) 

# 세로 막대 그래프.
barplot(stu$grade, ylim = c(0,5),
        col=rainbow(3),
        main = "차트 제목",
        xlab = "학년", ylab = "학생수", horiz = T # horiz : 세로막대 값 주기.
) 

barplot(stu$grade, col=c(4,5,6))

# 한 화면에 여러개에 그래프를 볼 경우.
par(mfrow = c(1,2))
barplot(stu$grade, col=c(4,5,6))
title(main = "1열")

barplot(stu$grade, col=rainbow(3), space = 2) # space : 그래프 사이에 간격 띄우기.
title(main = "2열")

par(mfrow = c(1,1))
dotchart(stu$grade) # 점 그래프
dotchart(stu$grade, color = 2:5, pch = 1:2 , cex = 1.5) # 색깔, 도형, 간격 옵션 설정 가능.

# 파이차트
df <- na.omit(stu)
pie(df$age)
title("파이 차트")

# 박스차트
boxplot(df$height, range = 1)
boxplot(df$height, range = 1, notch = T) # notch V 자 모양
abline(h=170, col="blue") # 170인 곳에 line 그리기

hist(df$height, xlab = "height", col = "yellow") # 히스토 그램. 제목, 색깔 설정.

price <- runif(10, min = 1, max = 100)
price
plot(price) # 산포도

par(mfrow=c(2,2))
plot(price, type = "l") # 연속형 직선
plot(price, type = "o", pch = 15) # 점선
plot(price, type = "h") # 막대
plot(price, type = "s") # 연속형 직각

par(mar=c(1,1,1,1))
layout(matrix(c(2,0,1,3), 2, 2 , byrow = T), c(2,1),c(1,2)) # 0 : 빈공간, byrow 해서 행부터 채움.
plot(df$height)
hist(df$height)
boxplot(df$height)
