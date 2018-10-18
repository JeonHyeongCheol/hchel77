# 로지스틱 회귀 : 연속형 데이터를 이용해 결과를 그룹으로 분류()
mydata <- read.csv("http://stats.idre.ucla.edu/stat/data/binary.csv")
head(mydata)
summary(mydata)

str(mydata)
mydata$admit <- factor(mydata$admit)
str(mydata) # 1이면 합격, 0이면 불합격

table(mydata$admit, mydata$rank)
xtabs(~admit + rank, data = mydata)

lgmodel <- glm(admit ~ ., data = mydata, family = "binomial")
lgmodel

summary(lgmodel)

# 분류 예측
head(mydata, 3)
pred <- predict(lgmodel, newdata = mydata, type = "response")
head(pred, 3)
ifelse(pred > 0.5, '합격','불합격')

# 새로운 값으로 분류예측
my <- mydata[c(1:3),]
my <- edit(my)
pred <- predict(lgmodel, newdata = my, type = "response")
pred
ifelse(pred > 0.5, '합격','불합격')