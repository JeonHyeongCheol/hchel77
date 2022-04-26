# 분류분석 : Decision Tree
set.seed(123)
idx <- sample(1:nrow(iris), nrow(iris) * 0.7) # sample 데이터 만들기
train <- iris[idx, ]
test <-  iris[-idx,]
dim(train)

install.packages("party")
library(party)

# formula 만들기
formula <- Species ~.

ir_ctree <- ctree(formula = formula, data = train)
ir_ctree

plot(ir_ctree)
# plot에서 루트노드와 그밑에 있는 애들이 중요한 것. 나오지 않은 속성들은 중요 하지 않다는 것.

# 1) Petal.Length <= 1.9; criterion = 1, statistic = 97.203 # 수치가 작을 수록 좋음. # 첫번째 : 노드, 두번째 : 기준, 세번째 : 통계 확률.
#   2)*  weights = 32  # 별은 마지막 노드를 뜻함.
# 1) Petal.Length > 1.9 # 
#   3) Petal.Width <= 1.7; criterion = 1, statistic = 53.436
#     4)*  weights = 41 
#   3) Petal.Width > 1.7
#     5)*  weights = 32 

plot(ir_ctree, type="simple") # 간단한 시각화

# 분류 예측 : predict()
pred <- predict(ir_ctree, test)
pred

table(pred, test$Species)
(18 + 10 + 13) / nrow(test) # 0.9111111

library(caret)
confusionMatrix(pred, test$Species)

newdf <- iris[1,]
newdf
newdf <- rbind(newdf, c(5,4,3,2,"setosa"))
newdf <- rbind(newdf, c(5,4,3,2,"versicolor"))
newdf <- rbind(newdf, c(5,4,3,2,"virginica"))
newdf

newdf$Sepal.Length <- as.numeric(newdf$Sepal.Length)
newdf$Sepal.Width <- as.numeric(newdf$Sepal.Width)
newdf$Petal.Length <- as.numeric(newdf$Petal.Length)
newdf$Petal.Width <- as.numeric(newdf$Petal.Width)

pred2 <- predict(ir_ctree, newdf)
pred2

confusionMatrix(pred2, test$Species)
