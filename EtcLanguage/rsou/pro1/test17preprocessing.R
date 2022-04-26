install.packages("plyr")
library(plyr) # inmemoery

# join
x <- data.frame(id=c(1,2,3,4,8), height=c(160, 170, 173, 162, 185))
y <- data.frame(id=c(5,4,1,3,8), weight=c(55, 73, 60, 57, 80))
x; y

# 두개의 data.frame과 공통 칼럼을 적어야 함. left, right join 있음.
join(x,y,by='id') # left join
join(x,y,by='id', type='inner')
join(x,y,by='id', type='full')

# tapply
head(iris)
names(iris) # 컬럼명 확인.
unique(iris$Species) # 컬럼안에 있는 종류 확인.

tapply(iris$Sepal.Length, iris$Species, sum) #  컬럼안에 종류에 대한 합계 구하기
tapply(iris$Sepal.Length, iris$Species, mean) # 컬럼안에 종류에 대한 평균 구하기
#tapply(iris$Sepal.Length, iris$Species, sum, mean) # 함수는 두개가 들어갈 수 없음.

# ddply
ddply(iris, .(Species), summarise, avg=mean(Sepal.Length)) # summarise : 요약 함수.
ddply(iris, .(Species), summarise, avg=round(mean(Sepal.Length),0), tot=sum(Sepal.Length))
# tapply와 ddply의 차이점은 ddply가 함수를 여러개 사용 할 수 있다는 것.

ddply(iris, .(Species), 
      function(sub) {
        data.frame(sLength_avg = mean(sub$Sepal.Length))
      })

ddply(iris, .(Species, Sepal.Width > 3.0),  # width가 3.0 초과한 애들만 뽑기. Ture, False로도 나타 내줌.
      function(sub) {
        data.frame(sLength_avg = mean(sub$Sepal.Length))
      })

ddply(iris, .(Species, Sepal.Width > 5.0), 
      function(sub) {
        data.frame(sLength_avg = mean(sub$Sepal.Length))
      })

# filter
stu <- read.csv("testdata/ex_studentlist.csv")
stu

head(stu,3)

install.packages("dplyr")
library(dplyr)
filter(stu, gender=="남", grade =="2") # and
filter(stu, gender=="남" | grade =="2") # or
length(filter(stu, gender=="남" | grade == 2))

arrange(stu, age) # asc 오름차순 정렬.
arrange(stu, desc(age)) # 내림차순.
rrange(stu, gender, age) # 여러개 정렬도 가능.

select(stu, name, age)
select(stu, name:age) # name 칼럼 부터 age 칼럼 까지 출력.
select(stu, -(name:age)) # name 칼럼 부터 age 칼럼을 제오 ㅣ나머지 출력.

bmi <- round((stu$weight / (stu$height^2)) *10000, 1) # 체질량 구하기.
bmi
mutate(stu, bmi=bmi) # 데이터 추가하기.
stu
stu_bmi <- mutate(stu, bmi=bmi) # 한번더 넣어줘야지 저장됨.
stu_bmi

mutate(stu_bmi, test=ifelse(absence =="유", 1, 0))

is.na(stu)
table(is.na(stu))
table(is.na(stu$age))
stu <- na.omit(stu) # na 값인 제외 시키고 저장.

stu

summarise(stu, avgAge=mean(age, na.rm=T)) # 요약 집계. 나이가 na인 사람을 제외 나머지에 평균

stu %>% filter(grade == 1) # 파이프 연산자.
stu %>% filter(grade != 1 )
stu %>% filter(grade == 1 & height >= 170)
stu %>% filter(grade == 1 | height >= 170)
stu %>% filter(grade %in% c(1,2)) # 1, 2 학년만 in을 써서 출력.

stu %>% arrange(grade, desc(height)) # 정렬

stu %>% select(name, grade) # 선택해서 출력
stu %>% select(-name, -grade) # 선택한 값을 제외한 출력

stu %>% mutate(imsi = ifelse(round(height,0) >= 180, 'long', 'normal')) # 180 이상인 애들 long으로 출력.
stu %>% summarise(hm = mean(height)) # 요약 통계

stu %>%  group_by(gender) %>%  summarise(hm = mean(height), sd=sd(height)) # 평균과 표준편차

stu %>%  group_by(gender) %>%  summarise(hm = mean(height)) 

# 함수 chain() 혹은 간단히 %>%를 이용함으로써 각 조작을 연결해서 한 번에 수행할 수 있습니다. 
# 즉, 최종 결과를 얻기 위해 임시로 데이터 프레임을 만들지 않아도 되는 편리한 기능

#  %>%로 연결하면 가장 먼저 데이터 프레임을 지정하면 그다음부터는 인수를 생략할 수 있을 뿐 아니라 
# 앞선 함수의 결과(데이터 프레임)를 뒤에 오는 함수의 입력값으로 사용