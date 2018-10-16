# iris Dataseet으로 계층적 군집분석
library(cluster)
iris[,1:4]
idist <- dist(iris[,1:4])
idist

hc <- hclust(idist)
hc

plot(hc, hang = -1)

rect.hclust(hc, k = 2, border = "red") # 그룹으로 잘라줌.

# 군집 수 자르기
k = length(unique(iris$Species))
ghc <- cutree(hc, k = k) # cutree로 변수안에 있는 값들을 수만큼 만들거나, 자를 수 있음.
ghc

iris$ghc <- ghc
head(iris)

table(ghc)

# 요약 통계량
g1 <- subset(iris, ghc == 1)
g2 <- subset(iris, ghc == 2)
g3 <- subset(iris, ghc == 3)
g1; g2; g3

summary(g1)

