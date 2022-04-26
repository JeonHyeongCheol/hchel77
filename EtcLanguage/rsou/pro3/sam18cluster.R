# Diamond Dataset 계층적/비계층적 분석
l
ibrary(ggplot2)

diamonds
t <- sample(1:nrow(diamonds), 1000)
t
test <- diamonds[t, ]
dim(test)
str(test)

mydia <- test[c("price", "carat", "depth", "table")]
head(mydia)

# 계층적 군집분석
result <- hclust(dist(mydia), method = "ave")
result
plot(result, hang = -1)

# 비계층적 군집분석
result2 <- kmeans(mydia, 4)
result2

names(result2)
result2$cluster

mydia$cluster <- result2$cluster
head(mydia)

cor(mydia[,-5], method = "pearson")
plot(mydia[,-5])

library(corrgram)
corrgram(mydia[,-5])
corrgram(mydia[,-5], upper.panel = panel.conf)

plot(mydia[c("carat", "price")], col=mydia$cluster) # 클러스트 산포도.
points(result2$centers[,c("carat", "price")], col = c(3,1,2), pch = 8, cex =5) # cluster에 중심점 확인.

install.packages("PerformanceAnalytics")
library(PerformanceAnalytics)
chart.Correlation(mydia[-5], histogram = , pch = "+")
