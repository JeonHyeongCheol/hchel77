# 인공 신경망(ANN)
df <- data.frame(
  x1 = c(1:6),
  x2 = c(6:1),
  y = factor(c('n','n','n','y','y','y'))
)

library(nnet)
model_net1 <- nnet(y ~ ., df, size=1)
model_net1

summary(model_net1)

library(devtools)
source_url('https://gist.githubusercontent.com/fawda123/7471137/raw/466c1474d0a505ff044412703516c34f1a4684a5/nnet_plot_update.r')
par(mar = c(1,1,1,1))
plot.nnet(summary(model_net1))


# 분류 예측
model_net1$fitted.values
predict(model_net1,df)

pred <- predict(model_net1, df, type = "class")
pred
table(pred, df$y) # 예측값, 실제

prop.table(table(pred, df$y)) * 100
