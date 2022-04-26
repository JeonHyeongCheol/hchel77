# 다항 로지스틱 회귀
# iris 꽃의 종류별로 결과(Label) 출력

ind <- sample(1:nrow(iris), nrow(iris) * 0.7, replace = F)
train <- iris[ind, ]
test <- iris[-ind, ] # 검정데이터

train

library(nnet)
m <- multinom(Species ~ ., data = train)

m$fitted.values
m_class <- max.col(m$fitted.values)
m_class

#df <- data.frame(c(5,4,3), c(4,5,6), c(3,2,1))
#df
#max.col(df)

table(m_class)
table(train$Species, m_class)

pred <- predict(m, newdata = test, type = 'class') # probs
pred
table(pred)
table(pred, test$Species) # 예측값, 실제값
(13 + 18 + 13) / nrow(test) # 0.9777778

install.packages("caret")
install.packages("e1071")
library(caret)
library(e1071)
confusionMatrix(pred, test$Species)

# 새로운 데이터로 분류 예측
my <- test
my <- my[c(1,2,3),]
my <- edit(my)
my

mypr <- predict(m, newdata = my, type = 'class')
mypr
table(mypr)

