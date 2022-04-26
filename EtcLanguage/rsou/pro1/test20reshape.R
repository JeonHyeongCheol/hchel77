library(reshape2)

no <- c(1,1,2,2)
day <- c(1,2,1,2)
a1 <- c(40,30,50,25)
a2 <- c(70,55,80,45)

df <- data.frame(no, day, a1, a2)
df

m_data <- melt(df, id=c('no','day'))
m_data
#no day a1 a2
#1  1   1 40 70
#2  1   2 30 55
#3  2   1 50 80
#4  2   2 25 45

#no day variable value
#1  1   1       a1    40
#2  1   2       a1    30
#3  2   1       a1    50
#4  2   2       a1    25
#5  1   1       a2    70
#6  1   2       a2    55
#7  2   1       a2    80
#8  2   2       a2    45

# 위에 1개에 행에 대해 두개씩 풀어줌. 1, 1 = a1, 1, 1 a2로 

# dcast 완전 중요!!!!!!!!!!!!!!!!!!!!!!
dcast(m_data, no + day ~ variable) # 다시 되돌림.

dcast(m_data, no + variable ~ day)

dcast(m_data, no ~ variable + day) # 열변수 더하기

dcast(m_data, no ~ variable, mean)

dcast(m_data, day ~ variable, mean)

dcast(m_data, no ~ day, mean) 

#--------------------------------------------------------------------------------
pay_data <- read.csv("testdata/pay_data.csv")
head(pay_data)

table(pay_data$product_type)

product_price <- dcast(pay_data, user_id ~ product_type, sum, na.rm = T) # userid(행)에 대한 product_type(열)별로 합계 구함.
head(product_price)
names(product_price) <- c('user_id', '식료품1','생필품2','주류3','잡화4','기타5')
head(product_price)

pay_price <- dcast(pay_data, user_id ~ pay_method, length)
head(pay_price)

product_pay_data <- join(product_price, pay_price, by='user_id') # 만든 데이터로 조인.
product_pay_data
