install.packages("ggvis")
library(ggvis)

iris %>% ggvis(~Petal.Length, ~Petal.Width, fill = ~factor(Species))

head(iris[1:4])

# 데이터 정규화 (요소값 - 최소값) / (최대값 - 최소값)
func <- function(x) {
  num <- x -min(x)
  mm <- max(x) - min(x)
  print(num / mm)
}

test_df <- data.frame(x = c(1,2,3,4,5))
test_df
func(test_df)
lapply(test_df, func) # 함수를 사용하는 함수

normal_d <- as.data.frame(lapply(iris[1:4], func))
head(iris, 2)
head(normal_d,2)
summary(normal_d) # 최소값, 최댓값 확인

df <- data.frame(normal_d, Species = iris$Species)
head(df)

set.seed(123)
idx <- sample(1:nrow(df), 0.7 * nrow(df))
idx
train <- df[idx,]
test <- df[-idx,]

dim(train); dim(test)

# knn 모델 생성
library(class)
model <- knn(train = train[,-5], test = test[,-5], cl = train$Species, k = 3) # species 제외[,-5]
model

summary(model)
t <- table(model, test$Species)
t
acc <- (t[1,1] + t[2,2] + t[3,3]) / nrow(test)
acc


library(gmodels)
CrossTable(x = test$Species, y = model, prop.chisq = F) # 위에 t 와 동일. 보는 방법은 여러가지.

# 최적의 k 값 찾기
model <- knn(train = train[,-5], test = test[,-5], cl = train$Species, k = 11)
k = seq(from = 3, to = 13, by = 2)
result <- c()

for(i in k) {
  m <- knn(train = train[,-5], test = test[,-5], cl = train$Species, k = i)
  t <- table(m, test$Species)
  acc <- (t[1,1] + t[2,2] + t[3,3]) / nrow(test)
  result <- c(result, acc)
}

result
acc_df <- data.frame(k값 = k, 정확도 = result)
acc_df
