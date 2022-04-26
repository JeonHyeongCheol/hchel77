# 최소 자승법(최소제곱) -> 인터넷에서 공식 찾아보기. 그것을 프로그램으로 만든 것.

x <- c(110, 120, 130, 140, 150) # 부모의 IQ
y <- c(100, 105, 128, 115, 142) # 자식의 IQ

x_dev <- x - mean(x) # 편차구하기
y_dev <- y - mean(y)

df <- data.frame(x, y, x_dev, y_dev)
df
dev_mul <- (x - mean(x)) * (y-mean(y))

square <- x_dev ** 2 # x값에 대한 제곱

df <- data.frame(x, y, x_dev, y_dev, dev_mul, square)

mean(df$x) # 130
mean(df$y) # 118

sum(df$dev_mul) # 940 분자
sum(df$square) # 1000 분모
df

940 / 1000 # 기울기 : 0.94

b0 = 118 - 0.94 * 130

b0 # 절편 : -4.2

newx = 117
yh = -4.2 + 0.94 * newx
yh

plot(x, y)

lmodel <- lm(y ~ x) # lm을 쓸 경우에는 x 절편 값과 기울기를 바로 구 할 수 있음.
lmodel

abline(lmodel, col="blue")
