# install.packages("neuralnet")
library(neuralnet) # MLP 운영 시에 역전파 알고리즘을 구사

# 정규화하였을 
iris$Species2[iris$Species == 'setosa'] <- 1
iris$Species2[iris$Species == 'versicolor'] <- 2
iris$Species2[iris$Species == 'virginica'] <- 3
iris$Species <- NULL
head(iris)

set.seed(123)
idx <- sample(1:nrow(iris), 0.7 * nrow(iris))
idx
train <- iris[idx,]
test <- iris[-idx,]

# 데이터 정규화 (요소값 - 최소값) / (최대값 - 최소값)
func <- function(x) {
  num <- x - min(x)
  mm <- max(x) - min(x)
  return(num / mm)
}

# 학습데이터 정규화
train_nor <- as.data.frame(lapply(train, func))
head(train_nor)

# 검정데이터 정규화
test_nor <- as.data.frame(lapply(test, func))
head(test_nor)
summary(test_nor)

#model <- neuralnet(Species2 ~ Sepal.Length + Sepal.Width + Petal.Length + Petal.Width, data = train, hidden = 1)
#model_result <- compute(model, test[c(1:4)])
names(model_result)
head(model_result)
tail(model_result$net.result)

head(iris)
model <- neuralnet(Species2 ~ Sepal.Length + Sepal.Width + Petal.Length + Petal.Width, data = train_nor, hidden = 1)
model

plot(model)

# 모델 성능 평가
model_result <- compute(model, test_nor[c(1:4)])
names(model_result)
head(model_result)
tail(model_result$net.result)

# 상관관계
cor(model_result$net.result, test_nor$Species2) # 0.9730744, 양의 상관관계

# 빈도수
pred_weight <- model_result$net.result

func <- function(x) {
  if(x >= 1) {
    return("virginica") 
  } else if(x >= 0) {
    return("versicolor")
  } else {
    return("setosa")
  }
}

result <- apply(pred_weight, 1, func)
result
table(result, test_nor$Species2)
(8 + 17 + 12) / nrow(test_nor) # 0.8222222222

# 모델 성능 향상
model2 <- neuralnet(Species2 ~ Sepal.Length + Sepal.Width + Petal.Length + Petal.Width, data = train_nor, hidden = 2, 
                    algorithm = "backprop", learningrate = 0.01)
plot(model2)

model_result2 <- compute(model2, test_nor[c(1:4)])
names(model_result2)
cor(model_result2$net.result, test_nor$Species2) # 0.9736763241

result2 <- apply(model_result2$net.result, 1, func)
result2
table(result2,test_nor$Species2)
(5 + 17 + 12) / nrow(test_nor)
