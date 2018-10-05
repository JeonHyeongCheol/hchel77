# 날씨정보로 분류연습
library(rpart)

weather <- read.csv("testdata/weather.csv", header = T)
str(weather)
head(weather)

weather_m <- rpart(RainTomorrow ~ ., data = weather[, c(-1, -11)], cp = 0.01)
weather_m

x11() # 깨지거나 안보일 때 하면 창이 새로 뜸.
plot(weather_m)
text(weather_m, use.n = T) # 글씨가 안보일 때 : use.n = T 로 하면 보임.

# 분류예측
pred <- predict(weather_m, weather)
head(pred)

result <- ifelse(pred[,2] >= 0.5, "Yes", "No")
result

table(result, weather$RainTomorrow)
(285 + 38) / nrow(weather) # 비가 오지 않을 확률:  0.8825137
