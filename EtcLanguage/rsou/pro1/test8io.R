#화면 출력 : cat(), print()
x <- 10; y<- 20; z <- x * y
cat("x * y의 결과는 ", z, "입니다")
cat(x, "*", y, "=", z)
print(x) # print는 하나씩 출력 해줌.
print(x, y, z) # 이렇게 해도 하나만 출력.
cat(x, y, z) # cat은 출력할 때 많이 사용.

# 파일
sink("output/savedata.txt") # 저장(자료) 선언 : 진행 도중에 출력하는 값을 저장해줌.
data <- read.csv(file.choose(), encoding = "UTF-8")
data # 꼭 봐야 함. 보지 않으면 저장 안됨.
kbs <- 9
kbs
sink() # 저장 종료

result <- read.csv("output/savedata.txt") # 저장된 자료 보면 나옴.
result

name <- c("관우","장비","유비")
age <- c(35, 33, 15)
gender <- c("M", "M", "F")
myframe <- data.frame(name, age, gender)
myframe

write.table(myframe, "output/my1.txt", fileEncoding = "UTF-8")

write.table(myframe, "output/my2.txt", fileEncoding = "UTF-8", row.names = T) # row.names를 설정 할 수도 있음.

write.table(myframe, "output/my3.txt", fileEncoding = "UTF-8", row.names = F)

write.table(myframe, "output/my4.txt", fileEncoding = "UTF-8", row.names = F, quote = FALSE) # quote

cat("파일목록 : ", dir("output"), sep = "\n")

read.csv("output/my1.txt", fileEncoding = "UTF-8") 
# 저장할 때 fileEncoding UTF-8이면 불러 올 때도 써줘야 함. 안써주면 출력 안됨.

