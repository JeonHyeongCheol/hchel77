install.packages("jsonlite")
install.packages("curl")

library(jsonlite)
library(curl)
url <- "http://localhost/webjsp/r/zipjson.jsp"
data <- fromJSON(url)
head(data)

class(data)
df <- data.frame(data) # data frame으로 바꿈.
class(df)

table(df$datas.jikwon_jik)

mean(as.numeric(df$datas.jikwon_pay))
