# iris dataset ANN 연습

library(nnet)

set.seed(123)
idx <- sample(1:nrow(iris), 0.7 * nrow(iris))
idx
train <- iris[idx,]
test <- iris[-idx,]

model_net1 <- nnet(Species ~ ., train, size = 1)
model_net1

model_net3 <- nnet(Species ~ ., train, size = 3)
model_net3

summary(model_net1)
summary(model_net3)

library(devtools)
source_url('https://gist.githubusercontent.com/fawda123/7471137/raw/466c1474d0a505ff044412703516c34f1a4684a5/nnet_plot_update.r')
par(mar = c(1,1,1,1))

plot.nnet(summary(model_net1))

plot.nnet(summary(model_net3))

# 분류모델평가
predict(model_net1, test, type="class")
table(predict(model_net1, test, type="class"), test$Species)
(12 + 15 + 16) / nrow(test) # 0.9555556

predict(model_net3, test, type="class")
table(predict(model_net3, test, type="class"), test$Species)
(12 + 14 + 15) / nrow(test) # 0.9111111
