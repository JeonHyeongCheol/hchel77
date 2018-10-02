names(iris)

# 상관관계 분석
cor(iris$Sepal.Length, iris$Sepal.Width) # -0.1175698
cor(iris$Sepal.Length, iris$Petal.Length) # 0.8717538

result <- lm(formula = Sepal.Length ~ Sepal.Width, data = iris)
summary(result) #  p-value: 0.1519, Adjusted R-squared:  0.007159

result2 <- lm(formula = Sepal.Length ~ Petal.Length, data = iris)
summary(result2) # p-value: < 0.00000000000000022, R-squared:  0.7583 
