# 분류분석 : Decision tree : 가지치기 적용
library(rpart)

set.seed(123)
n <- sample(2, nrow(iris), replace = T, prob = c(0.7, 0.3))
n

train <- iris[n==1,]
test <- iris[n==2,]
dim(train); dim(test)

# 가지치기 하지 않은 값.
iris_rpart <- rpart(Species ~ ., data = train, method = 'class')
iris_rpart

x11()
plot(iris_rpart)
text(iris_rpart)

plotcp(iris_rpart) # x 에러값 확인가능.
printcp(iris_rpart)

# 가지치기 한 값
cp <- iris_rpart$cptable[which.min(iris_rpart$cptable[,'xerror'])] # 이렇게 써야지 최선의 CP값을 줌.
cp

iris_rpart_prune <- prune(iris_rpart, cp = cp, 'cp')

x11()
plot(iris_rpart_prune)
text(iris_rpart_prune)

# 가지치기 한 값으로 분류 예측
pred <- predict(iris_rpart_prune, test, type = 'class')
pred

confusionMatrix(pred, test$Species) # Accuracy : 0.8864
