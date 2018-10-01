# 구글맵에 지하철 2,3 호선 역이름 표기.
data <- read.csv("testdata/subway.csv")
data

head(data,3)

library(dplyr)
install.packages("ggmap")
library(ggmap)
install.packages("ggplot2")
library(ggplot2)

data2 <- data[,c(2,3,8,9)] # 필요한 데이터만 뽑아옴.
data2

colnames(data2) <- c('전철역명','호선','위도','경도')
head(data2, 3)

s_2 <- data2 %>% filter(호선 == '2')
s_2

center <- c(mean(s_2$경도), mean(s_2$위도)) # center에 평균값을 구해 위도, 경도 저장하기
seoul <- get_map(center, zoom = 11, maptype = 'roadmap') # seoul에 중앙지점 보여주기위해서 설정하기. zoom, maptype 설정.
ggmap(seoul)

ggmap(seoul) + geom_point(data = s_2, aes(x=경도,y=위도), size = 2.5, alpha = 0.7)

# 3호선
s_3 <- data2 %>% filter(호선 == '3')
s_3

center <- c(mean(s_3$경도), mean(s_3$위도)) # center에 평균값을 구해 위도, 경도 저장하기
seoul <- get_map(center, zoom = 11, maptype = 'roadmap') # seoul에 중앙지점 보여주기위해서 설정하기. zoom, maptype 설정.
ggmap(seoul)

? geom_point()

ggmap(seoul) + geom_point(data = s_3, aes(x=경도,y=위도), size = 2.5, alpha = 0.7)

ggmap(seoul) + geom_point(data = s_2, aes(x=경도,y=위도), size = 2.5, alpha = 0.7, col='green') +
             geom_point(data = s_3, aes(x=경도,y=위도), size = 2.5, alpha = 0.7, col='orange') +
             geom_text(data = s_3, aes(x=경도,y=위도, label=전철역명), size = 2.5)
