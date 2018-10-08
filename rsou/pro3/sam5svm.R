# SVM의 이해
set.seed(1)
x <- matrix(rnorm(20 * 2), ncol = 2)
x
y <- c(rep(-1,10), rep(1,10)) # 앞에 10개는 -1, 뒤에 10개는 1
x[y == 1,] <- x[y == 1,] + 1
y
plot(x, col = (3 -y))

library(e1071)

dat <- data.frame(x = x, y = as.factor(y))
dat

svmfit <- svm(y ~ ., data = dat, kernel = 'linear', cost = 10, scale = F)
svmfit

plot(svmfit, dat)

attributes(svmfit) # 속성 값 확인.
svmfit$index # support vactor # 경계값을 나눠주는 벡터값을 확인.
dat
summary(svmfit)

svmfit <- svm(y ~ ., data = dat, kernel = 'linear', cost = 0.1, scale = F)
svmfit
# cost : 에러를 최소화 하기 위한 값.

# tune() : best cost 얻기.
set.seed(1)
tune_out <- tune(svm, y ~ ., data = dat, kernel = 'linear', 
                 ranges = list(cost = c(0.001, 0.01, 0.1, 1.5, 5, 10, 100)))
summary(tune_out) # error 값이 작은 것이 best cost.

bestmod <- tune_out$best.model
bestmod

# 예측하기
xtest <- matrix(rnorm(20 * 2), ncol = 2)
ytest <- sample(c(-1, 1), 20, rep = T)
xtest[ytest == 1,] <- xtest[ytest == 1,] + 1
xtest
ytest
testdat <- data.frame(x = xtest, y = as.factor(ytest))
testdat

pred <- predict(bestmod, testdat)
pred
table(예측값 = pred, 실제값 = testdat$y)
(8 + 1) / nrow(testdat)
