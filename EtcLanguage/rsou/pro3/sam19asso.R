# 연관분석 - 무방향성 데이터마이닝 기법
install.packages("arules")
library(arules)

cust <- read.csv("testdata/priori_data2.csv", stringsAsFactors = F)
head(cust)

cust$sangpum[cust$irum == "홍길동"]

cust_list <- split(cust$sangpum, cust$irum) # 상품을 자르는데, 이름을 기준으로 짜름.
cust_list

# traansaction type으로 변환
cust_tran <- as(cust_list, "transactions")
cust_tran
inspect(cust_tran)

cust_rules <- apriori(cust_tran) # 모델 생성

summary(cust_rules)
inspect(cust_rules)
