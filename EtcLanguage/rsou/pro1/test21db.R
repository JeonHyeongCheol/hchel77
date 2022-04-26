install.packages("rJava")
install.packages("DBI")
install.packages("RJDBC")

# 자바 런타임을 사용 하기 위해 환경잡기.
Sys.setenv(JAVA_HOME="C:/Program Files/Java/jre1.8.0_171") 

library(rJava)
library(DBI)
library(RJDBC)

install.packages("RSQLite")
library(RSQLite)

# 내장된 DB(Sqlite)와 연결.
conn <- dbConnect(RSQLite::SQLite(),":memory:")
conn

head(mtcars)
dbWriteTable(conn, "mtcars", mtcars) # 테이블 생성.
dbListFields(conn, "mtcars") # 칼럼명 확인.

query <- "select * from mtcars" # Sql문.
result <- dbGetQuery(conn, query) # 연결 및 쿼리문보내기.
result

query2 <- "select mpg, cyl from mtcars where mpg >= 30"
result2 <- dbGetQuery(conn, query2) #
result2

dbDisconnect(conn) # DB 연결 해제.

# RDBMS(MariaDB)와 연동
drv <- JDBC(driverClass = "org.mariadb.jdbc.Driver", classPath = "C:/work/rsou/mariadb-java-client-1.3.6.jar")
conn <- dbConnect(drv, "jdbc:mysql://127.0.0.1:3306/test","root","123")
dbListTables(conn)

# SangData로 RUD 경험하기
query <- "select * from sangdata"
goodAll <- dbGetQuery(conn, query)
goodAll
class(goodAll)
barplot(goodAll$su, col = rainbow(10), names.arg = goodAll$sang)

query2 <- "select * from sangdata where sang like'가죽%'"
goods <- dbGetQuery(conn, query2)
goods

query3 <- "select * from sangdata order by code desc"
goods <- dbGetQuery(conn, query3)
goods

# 레코드 추가
query <- "insert into sangdata values(6, '칙촉', 10, 500)"
dbSendUpdate(conn, query)

df <- data.frame(code=7, sang='빈츠', su=7, dan=300)
df

dbSendUpdate(conn, "insert into sangdata values(?,?,?,?)", df$code, df$sang, df$su, df$dan)
dbGetQuery(conn, query)

# 레코드 수정
query <- "update sangdata set sang='새우깡' where code=7"
dbSendUpdate(conn, query)

query <- "update sangdata set sang=? where code=7"
dbSendUpdate(conn, query, "수미칩")

# 레코드 삭제
dbSendUpdate(conn, "delete from sangdata where code=7")
dbGetQuery(conn, "select * from sangdata")

# 여러 개의 행 추가
sangdf <- read.csv("testdata/sangpum.csv", fileEncoding = "UTF-8")
sangdf

# 여러개의 행 추가하는 함수 작성 %>% 
library(dplyr)
func <- function(conn, table, df) {
  batch <- apply(df, 1, FUN = function(x) paste0("'", trimws(x), "'", collapse = ",")) %>% 
    paste0("(",.,")", collapse = ",\n")
  # batch
  query <- paste("insert into", table, "values", batch)
  dbSendUpdate(conn, query)
}
func(conn, "sangdata", sangdf)
dbGetQuery(conn, "select * from sangdata")

dbDisconnect(conn)
