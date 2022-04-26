# 날씨 정보로 비 예측
weather <- read.csv("testdata/weather.csv", stringsAsFactors = F)
dim(weather)
head(weather)
str(weather)

weather_df <- weather[, c(-1, -6, -8, -14)]
str(weather_df)

head(weather_df$RainTomorrow)
weather_df$RainTomorrow[weather_df$RainTomorrow == 'Yes'] <- 1
weather_df$RainTomorrow[weather_df$RainTomorrow == 'No'] <- 0
weather_df$RainTomorrow <- as.numeric(weather_df$RainTomorrow)
str(weather_df)
head(weather_df)

# 자료를 7 : 3으로 분리
temp <- sample(1:nrow(weather_df), nrow(weather_df) * 0.7)
train <- weather_df[temp,]
test <- weather_df[-temp,]
dim(train); dim(test)

weather_model <- glm(RainTomorrow ~ ., data = train, family = "binomial")
weather_model
summary(weather_model)

# 예측값 얻기
pred <- predict(weather_model, newdata = test, type='response')
head(pred, 20) # 1에 가까울 수록 내일 비올 확률이 높음.

pred
result_pred <- ifelse(pred >= 0.5, 1, 0)
result_pred
table(result_pred)

table(result_pred, test$RainTomorrow)

(85 + 10)  / nrow(test) # accuracy(모델의 정확도) : 0.8636364

t <- table(result_pred, test$RainTomorrow)
sum(diag(t) / nrow(test)) # accuracy(모델의 정확도) : 0.863636

# ROC curve를 모델 평가
install.packages("ROCR")
library(ROCR)

pr <- prediction(pred, test$RainTomorrow)
prf <- performance(pr, measure = "tpr", x.measure = "fpr")
prf
plot(prf)

auc <- performance(pr, measure = "auc")
auc
auc <- auc@y.values[[1]]
auc # 0.9050602 / 0.8 ~ 0.9 : good
plot(auc)
