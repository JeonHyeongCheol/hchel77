# <기술 통계량 관련 >
# 연습문제1> MASS 패키지에 있는 Animals 데이터셋을 이용하여 각 단계에 맞게 기술통계량을 구하시오.


# [단계 1] MASS 패키지 설치 및 메모리 로딩
# MASS 패키지 불러오기
install.packages("MASS")
# Animals 데이터셋 로딩
library(MASS)
# Animals 데이터셋 보기(위에서 5개)
head(Animals)

# [단계 2] R의 기본함수를 이용하여 brain 칼럼을 대상으로 다음 기술통계량 구하기
# 차원보기 
str(Animals)

# 요약통계량 
summary(Animals)

# 평균 
apply(Animals, 2, mean) # mean() : 특정자료에 함수 적용.

# 중위수
apply(Animals, 2, median) # median() : 중위수 구하기.

# 표준편차
apply(Animals, 2, sd) # sd() : 표준편차

# 분산
apply(Animals, 2, var) # var() : 분산 값 구하기

# 최댓값
apply(Animals, 2, max) # max() : 최댓값

# 최솟값
apply(Animals, 2, min) # min() : 최솟값

Animals

# [단계 3] 패키지에서 제공되는 describe()과 freq() 함수를 이용하여 Animals 데이터셋 전체를 대상으로 기술통계량 구하기
# <연습문제2> descriptive.csv 데이터셋을 대상으로 다음 조건에 맞게 빈도분석 및 기술통계량 분석을 수행하시오.
data <- read.csv("testdata/descriptive.csv", header=TRUE)

head(data) # 데이터셋 확인

# <조건 1> 명목척도 변수인 학교유형(type), 합격여부(pass) 변수에 대해 빈도분석을 수행하고 결과를 막대나 파이차트로 그리시오.
table(data$type, data$pass) # table() : 빈도 분석
pie(table(data$type, data$pass))
plot(table(data$type, data$pass))

# <조건 2> 비율척도 변수인 나이 변수에 대해 요약치(평균,표준편차)와 비대칭도(왜도와 첨도) 통계량을 구하고, 히스토그램을 작성하여 비대칭도를 설명하시오.
summary(data$age) # 요약치 구하기
# Min.   1st Qu.  Median  Mean    3rd Qu.  Max.  
# 40.00   48.00   53.00   53.88   60.00   69.00 

sd(data$age) # 표준편차 구하기
# 6.813247

install.packages("fBasics") # 왜도(skewness), 첨도(kurtosis)를 분석하는 함수가 들어있는 패키지.
library(fBasics)

skewness(data$age) # 왜도 구하기
# 0.3785883 > 0보다 크므로 오른쪽으로 꼬리가긴 분포도.

kurtosis(data$age) # 첨도 구하기
# -1.145801 < 0보다 작으므로 정규분포보다 납작함.

# <추론 통계량 관련 >
# <chi-square : 독립성 관련 연습문제>

# <chi-square 연습문제1>
# 교육수준(education)과 흡연율(smoking) 간의 관련성을 분석하기 위한 연구가설을 수립하고, 
# 이를 토대로 가설을 검정하시오.
smoke <- read.csv("testdata/smoke.csv", header=TRUE)

head(smoke)

# 귀무가설 : 교육수준과 흡연율 간에 관련성이 없다.
# 연구가설 : 교육수준과 흡연율 간에 관련성이 있다.

# 변수 리코딩    
# education(x) : 1:대졸, 2:고졸, 3:중졸 
# smoke(y)     : 1:과다흡연, 2:보통흡연, 3:비흡연

str(smoke)
smoke$education2[smoke$education == 1] <- "대졸" # 벡터 한줄을 바꾸는 것. 행열이 아님.
smoke$education2[smoke$education == 2] <- "고졸"
smoke$education2[smoke$education == 3] <- "중졸"

head(smoke)

smoke$smoking2[smoke$smoking == 1] <- "과다흡연"
smoke$smoking2[smoke$smoking == 2] <- "보통흡연"
smoke$smoking2[smoke$smoking == 3] <- "비흡연"

head(smoke)

# 교차분할표 작성    
table(smoke$education2, smoke$smoking2) # 빈도 보기

install.packages("gmodels") # 교차분할표 Package : CrossTable() 함수를 사용하기 위함.
library(gmodels)

install.packages("ggplot2")
library(ggplot2)

CrossTable(x = smoke$education2, y = smoke$smoking2) # CrossTable() : 교차분할표

# 독립성 검정

library(gmodels)   # CrossTable() 함수 사용
CrossTable(x = smoke$education2, y = smoke$smoking2, chisq = TRUE)


# 결론 : 
# p =  0.0008182573 값이 0.05 보다 작으므로 연구가설(대립) 채택!
# 교육수준과 흡연율 간에 관련성이 있다.



# <chi-square 연습문제2>
#나이(age3)와 직위(position) 간의 관련성 분석
  
setwd("c:/rwork/testdata")

data <- read.csv("cleanData.csv", header=TRUE)

# - 귀무가설 : 나이와 직위은 관련성이 없다.
# - 대립가설 : 나이와 직위은 관련성이 있다.



# < chi-square : 동질성 관련 연습문제 >
# <chi-square 연습문제3) 
# response.csv 파일의 두 변인에 대하여 다음 조건에 따라 동질성을 검정하시오.

# <조건1> 변수 모델링 : 직업유형(job) -> 응답정도(response)

# <조건2> 귀무가설과 연구가설 수립

# <조건3> mapvalues() 함수 이용 변수 리코딩

# job : 1: 학생, 2: 직장인, 3 : 주부 

# response : 1: 무응답, 2 : 낮음 , 3: 높음

# <조건4> 교차분할표 작성, 가설을 토대로 동질성 검정 및 해설

# 파일 가져오기

# 리코딩 - 직업2 필드 추가

# 리코딩 - 응답2 필드 추가

# 교차표 생성(1)
library(plyr)   # mapvalues() 지원 : 변수 추가 시 사용하는 함수. job3,response3 추가

result$job3 <- mapvalues(result$job, from=c(1, 2, 3), to=c("학생", "직장인", "주부"))

result$response3 <- mapvalues(result$response, from=c(1, 2, 3), to= c("무응답", "낮음", "높음"))


# 교차표 생성(2) : 3집단 빈도수(기술통계량)

# 동질성 검정 결과 : 



# * 단일표본 t 검정(One sample t-test)
# [가정] 표본은 정규분포를 따르는 모집단에서 얻어진 자료.

# [예제1] 통계학수업 기말 성적을 학년별로 나누고 성적순으로 정리한 자료는 다음과 같다. 
# 각 학년 평균성적이 80점인지를 검정하라.
# 학년	성적
# 2	93 92 86 83 81 79 76 76 75 74 72 66 67 60 46
# 3	97 93 92 90 89 87 87 85 84 83 80 78 78 75 73 70 68 65 63 62

# 1) 정규성 검정 : 데이터의 정규성(Normality) 여부는 ‘Shapiro-Wilk normality test’를 이용해 확인

# 2) 단일표본 검정



#[예제2] 영사기에 사용되는 구형 백열전구의 수명은 250시간이라고 알려졌다. 
# 한 연구소에서 수명이 50시간 더 긴 새로운 백열전구를 개발하였다고 발표하였다. 
# 연구소의 발표결과가 맞는지 새로 개발된 백열전구를 임의로 수집하여 수명시간을 수집하여 다음의 자료를 얻었다. 
# 연구소의 발표가 맞는지 새로운 백열전구의 수명을 분석하라.

# 305 280 296 313 287 240 259
# 266 318 280 325 295 315 278


# * 독립 두 집단 표본 t 검정(Two sample t-test)
# : 서로 독립인 두 개의 모집단의 평균 크기를 비교하는 방법으로 가장 많이 사용되는 방법이다.
# : 두 집단 평균차이 검정을 실시하기 전에 분산의 동일여부(등분산 검정)을 판단한 후, 그에 맞는 평균차이 검정을 실시한다.
# 1) (등분산인 경우) 합동분산을 이용한 t-검정

# 2) (이분산인 경우) 두 집단의 분산과 Satterthwaite 자유도를 이용한 t-검정

# [예제1] 다음 데이터는 동일한 상품의 포장지 색상에 따른 매출액에 대한 자료이다. 

# 포장지 색상에 따른 제품의 매출액에 차이가 존재하는지 분석하라.
# blue	70	68	82	78	72	68	67	68	88	60
# red	60	65	55	58	67	59	61	68	77	66





# [예제2] 30대의 남자와 여자를 각각 18명 15명씩 무작위로 추출하여 혈관에 포함되어 있는 콜레스테롤량의 차이가 있는지를 분석하라.
# 남자	0.9 2.2 1.6 2.8 4.2 3.7 2.6 2.9 3.3 1.2 3.2 2.7 3.8 4.5 4 2.2 0.8
# 여자	1.4 2.7 2.1 1.8 3 3.2 1.6 1.9 2.3 2.5 2.3 1.4 2.6 3.5 2.1


# * 대응표본 t 검정(Paired sample t-test)
# : 이는 한 모집단의 동일한 실험 단위에 대하여 두 가지 처리를 하였을 때 나타난 처리 결과를 대응 비교하는 방법이다. 
# 자료는 항상 쌍으로 이루어져 있어야 한다.


# [예제1] 동일 상품에 대한 TV광고여부에 따른 매출액의 차이가 존재하는가?
# 매장번호 1	 2	 3	 4	 5	 6	 7	 8	 9	 10	 11	 12
# 전	1500	2000	3500	1200	1020	1100	1150	5021	3000	3333	2112	1000
# 후	1500	1800	2000	1000	900	1400	1200	1150	1600	1800	2200	2700





# [예제2] 학생 12명을 무작위로 택하여 3개월 동안 하루 2시간씩 수영을 지도하였다. 
# 수영 지도 전,후에 폐활량을 조사하여 수영이 폐활량 증가에 좋은 효과가 있는지 연구하고자 한다. 
# 다음 자료를 분석하라.

# 학생	1	2	3	4	5	6	7	8	9	10	11	12
# 전	2750	2600	2930	2320	2550	2400	2450	2700	2130	2400	2830	2350
# 후	2870	2730	2980	2420	2720	2560	2550	2800	2350	2520	2900	2510