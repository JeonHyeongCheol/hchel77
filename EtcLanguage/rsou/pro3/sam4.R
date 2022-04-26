# Random Forest : 의사결정나무 보다 예측력이 향상되고, 정확도가 높다.

install.packages("randomForest")
library(randomForest)

set.seed(123)
idx <- sample(1:nrow(iris), nrow(iris) * 0.7) # sample 데이터 만들기
train <- iris[idx, ]
test <-  iris[-idx,]
dim(train)

model <- randomForest(Species ~ ., data = train) # .(점)은 나머지 속성들이 독립변수들로 들어감?
model
(38 + 31 + 33) / nrow(train) # 0.9714286

# Number of trees: 500
# No. of variables tried at each split: 2

model2 <- randomForest(Species ~ ., data = train, ntree = 200, mtry = 3, na.action = na.omit) # mtry : 자식노드 수. na.action : Null 값 제거.
# randomForest에 값이 중요함. 어떻게 주느냐에 따라 다름.
model2
(38 + 31 + 32) / nrow(train) # 0.9619048

model3 <- randomForest(Species ~ ., data = train, importance = T) # importance에서 T로 했으면 다음 라인에 importance로 감싸서 확인해야 함.
importance(model3) # Petal.Length는 가장 중요한 변수

# 분류 예측
pred <- predict(model2, test)
pred

t <- table(observed = test[, 'Species'], predict = pred)
t            
prop.table(t, 1) # 비율로 보기

# 최적의 모델을 위한 파라미터값 얻기

ntree <- c(400, 500, 600)
mtry <- c(2:4)
param <- data.frame(n=ntree, m=mtry)
param
for(i in param$n) {
  cat('ntree = ', i, '\n')
  for(j in param$m) {
    cat('mtry = ', j, '\n')
    model_test <- randomForest(Species ~ ., data = iris, ntree = i, mtry = j, na.action = na.omit)
    print(model_test)
  }
}




