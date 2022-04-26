# 정규화 하지 않았을 때

library(neuralnet)

set.seed(123)
idx <- sample(1:nrow(iris), 0.7 * nrow(iris))
idx
train <- iris[idx,]
test <- iris[-idx,]

train <- cbind(train, train$Species == "setosa")
train <- cbind(train, train$Species == "versicolor")
train <- cbind(train, train$Species == "virginica")
train

head(train, 2)
names(train)[6:8] <- c("setosa", "versicolor", "virginica")

head(train, 3)

model <- neuralnet(setosa + versicolor + virginica ~ Sepal.Length + Sepal.Width + Petal.Length + Petal.Width, data = train, hidden = 3)
model
plot(model)

# 예측
comp <- compute(model, test[-5])
comp

pred_weight <- comp$net.result
pred_weight

idx <- apply(pred_weight, 1, which.max)
idx

c("setosa", "versicolor", "virginica")[3]

pred <- c("setosa", "versicolor", "virginica")[idx]
pred

table(pred, test$Species)
(12 + 15 + 15) / nrow(test) # 정확도 : 0.9333333333

# 새로운 자료로 분류 수행
my <- test
my <- my[c(1:3),]
my <- edit(my)
my

mycomp <- compute(model, my[-5])
mypred <- mycomp$net.result

idx2 <- apply(mypred, 1, which.max)
idx2

pred2 <- c("setosa", "versicolor", "virginica")[idx2]
pred2
