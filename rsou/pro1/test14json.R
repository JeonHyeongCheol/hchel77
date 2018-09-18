# JSON 파일 불러오기.
install.packages("rjson")
library("rjson")
result <- fromJSON(file = "testdata/json.json")
json_data_frame <- as.data.frame(result) # Convert JSON file to a data frame.
json_data_frame


install.packages("jsonlite")
install.packages("curl")
library(jsonlite)
library(curl)
json_file <- "http://jsonplaceholder.typicode.com/posts"
data <- fromJSON(json_file)
head(data)