data() # R이 가지고 있는 DataSet을 보여줌

Nile

head(Nile) # 앞에 부분만 보여줌.
head(Nile, 3) # 행에 갯수 만큼도 보여줄수 있음.
hist(Nile) # Histgram을 보여줌.
hist(Nile, freq = F) # freq = F로 하면 밀도로 보여줌.
lines(density(Nile)) # 선 그래프를 그려줌.

help("mean") # 도움말 보기.
? mean

args(max) # 함수 상세정보

example(seq) # 등차수열
seq(1, 9, by = 2)
example(mean) # 예를 들어서 사용할 수 있음.
x <- c(0:10, 50)
x
mean(x) 
c(xm, mean(x, trim = 0.10)) # trim : 제외하고 함.

women
str(women) # str : 구조 확인 할 수 있음.
? women
mode(women)
class(women)

plot(women)
plot(women$height, women$weight) # 기본적으로 축이름이 나타나지만 $하면 축 이름을 설정할 수 있음.


