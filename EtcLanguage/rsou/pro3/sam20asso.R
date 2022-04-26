library(arules)

customer <- read.csv("testdata/priori_data.csv", stringsAsFactors = F)
customer

customer_list <- split(customer$sangpum, customer$irum)
customer_list

customer_tran <- as(customer_list, "transactions") # 중복이 있으면 생성 안됨. 중복제거 하여야 함.
customer_list <- sapply(customer_list, unique) # 중복제거
customer_tran <- as(customer_list, "transactions")
customer_tran

summary(customer_tran)

itemFrequency(customer_tran) # 구매빈도 확인
itemFrequencyPlot(customer_tran, support = 0.3)
itemFrequencyPlot(customer_tran, topN = 5)

# 연관규칙 생성
customer_rule <- apriori(customer_tran, parameter = list(supp=0.1))
customer_rule <- apriori(customer_tran)
summary(customer_rule)
inspect(customer_rule)

customer_rule <- apriori(customer_tran, parameter = list(supp = 0.2, confidence = 0.2))
inspect(customer_rule)

inspect(customer_rule, by="lift")[1:5]
inspect(customer_rule, by="lift", decreasing = F)[1:5] # decreasing : 내림차순.
inspect(customer_rule, by="support")[1:5]
inspect(subset(customer_rule, items %in% c("맛동산","짱구")))

# 시각화
install.packages("arulesViz")
library(arulesViz)
plot(customer_rule)
plot(customer_rule, method = "grouped")
plot(customer_rule, method = "graph")
plot(customer_rule, method = "graph", control = list(type = "items"))
plot(customer_rule, method = "graph", engine = 'interactive') # 역동적으로 만들 수 있음. 사용자가 조정 가능.

# 연관분석용 식료품 구매 관련 Sample Data
data("Groceries")
Groceries # tranactions 화 되어 있음.
gdf <- as(Groceries, "data.frame")
head(gdf)

rules <- apriori(Groceries, parameter = list(supp = 0.001, conf = 0.8))
inspect(rules)

plot(rules, method = "grouped")

rules <- apriori(Groceries, parameter = list(supp = 0.001, conf = 0.8, maxlen = 3))
rules

so <- sort(rules, decreasing = T, by = "confidence")
inspect(so)

library(arulesViz)
plot(so, method = "graph", control = list(type="items"))

wmlik <- subset(so, rhs %in% 'whole milk')
wmlik
plot(wmlik, method = "graph")

veg <- subset(so, rhs %pin% 'other vegetables')
veg
plot(veg, method = "graph")
