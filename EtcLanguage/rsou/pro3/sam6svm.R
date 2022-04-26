library(e1071)
head(iris)
x <- subset(iris, select = -Species)
y <- iris$Species
y

svm_model <- svm(Species ~ ., data = iris)
summary(svm_model)

svm_model1 <- svm(x,y) # 위와 결과 동일
summary(svm_model1)

pred <- predict(svm_model1, x)
pred

table(pred, y)
(50 + 48 + 48) / nrow(x)

svm_tune <- tune(svm, train.x = x, train.y = y, ranges = list(cost = 10^(-2:2)))
svm_tune

bestmod <- svm_tune$best.model
bestmod

# svm의 다른 방법 : ksvm(R로 만든 패키지를 사용)
library(kernlab)

irismodel <- ksvm(Species ~ ., data = iris, C = 10, prob.model = T)
irismodel

predict(irismodel, iris[c(3,10,56,68,107,120), -5], type = "probabilities")
predict(irismodel, iris[c(3,10,56,68,107,120), -5])
newdata <- iris[c(3), -5]
predict(irismodel, newdata)
