ex_str <- "abcd한국12ABC345abc123-4567문자열"
base::sub("abc", "kbs_mbc", ex_str) # ex_str에 abc를 kbs_mbc로 바꾼다는 것. 첫번째만 바꿔줌.
gsub("abc", "kbs_mbc", ex_str) # patten에 맞는 모든 값을 바꿔줌.
gsub("[0-9]", "숫자", ex_str)

install.packages("stringr")
library(stringr)

str_extract(ex_str, "ab") # 값 들중에 맨 앞에 하나만 뽑아옴.
str_extract_all(ex_str, "ab") # 모든 값들을 뽑아옴.
str_extract_all(ex_str, "[0-9]") # 숫자 모두 뽑음.
str_extract_all(ex_str, "[0-9]+") # 붙어있는 숫자들을 뽑음.
str_extract_all(ex_str, "[^0-9]+") # 숫자는 뺀 나머지에 대한 붙어있는 모든 문자열 및 문자.
str_extract_all(ex_str, "[0-9]{2}") # 연속적으로 2개로 되어 있는 숫자를 뽑음.
str_extract_all(ex_str, "[0-9]{2,4}") # 2~4개씩 연속되어있는 숫자를 뽑음.
str_extract_all(ex_str, "[a-zA-Z]+") # 연속되는 문자열 뽑음.
str_extract_all(ex_str, "[가-힣]+") # 연속되어 있는 한글 뽑음.

str_sub(ex_str, 2,6) # 2번째 부터 6번째까지의 문자 및 숫자를 뽑아줌.

d <- c("김길동","신기해","김길동")
d
str_replace(d, "김길동", "홍길동")
str_c("k", "bs") # 문자열 합치기
str_split("aa-bb-cc", "-") # 문자열 짜르기.
str_count(c("apple", "banana"), "a") # a에 대한 값이 몇개인지 카운트해서 출력.
str_length(c("apple", "banana")) # 문자열에 대한 길이 출력

# 문자열 더하기
paste('a', 'b') # 기본적으로 문자열 더하기하면 띄어씀.
paste('a', 'b', sep='') # 띄어쓰기 안하고 싶을 경우 sep 사용. sep는 문자열 끝에 더해줌.
paste('a', 'b', sep='+') # 문자열 중간에 넣음
paste(rep('문제',5), 1:5, sep='')
paste("오늘은 ", date())
