# 비계층적 군집분석 : 군집수를 파악 후 모델 생성
# k-means clustering

iris_s <- scale(iris[-5]) # scale() : 크기 조정, 열을 빼줌.
head(iris_s)

?scale

# 군집에 갯수 결정 해주는 함수
install.packages("NbClust")
library(NbClust)

nc <- NbClust(iris_s, min.nc = 2, max.nc = 10, method = "kmeans")
nc

plot(table(nc$Best.nc[1,])) # Best Cluster를 알려줌.

iris_k <- kmeans(iris_s, centers = 3) # 위에서 구한 best cluster를 써주면됨.
iris_k

names(iris_k)
table(iris_k$cluster)

# 시각화
# K-Means 결과
plot(iris[c("Sepal.Length", "Sepal.Width")], col=iris_k$cluster)

# 원래 Species
plot(iris[c("Sepal.Length", "Sepal.Width")], col=iris$Species)


library(cluster)
iris_p <- pam(iris_s, 3)
iris_p
table(iris_p$clustering)

clusplot(iris_p)
