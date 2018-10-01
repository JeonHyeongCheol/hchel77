# 이원카이제곱 - 교차분할표 이용
# : 두 개 이상의 변인(집단 또는 범주)을 대상으로 검정을 수행한다. 
#   분석대상의 집단 수에 의해서 독립성 검정과 동질성 검정으로 나뉜다.
# 독립성(관련성) 검정 : CrossTable(x, y, chisq = TRUE)함수를 사용 – gmodels 패키지
# - 동일 집단의 두 변인(학력수준과 대학진학 여부)을 대상으로 관련성이 있는가 없는가?
# - 독립성 검정은 두 변수 사이의 연관성을 검정한다.

data <- read.csv("testdata/cleanDescriptive.csv")
data
names(data)
head(data$level2) # 부모의 학력수준.
head(data$pass2) # 대학진학 여부.

# 귀무 : 부모의 학력수준과 자녀의 대학입학여부는 관련이 없다.(독립적)
# 대립 : 부모의 학력수준과 자녀의 대학입학여부는 관련이 있다.(독립적이지 않다)

x <- data$level2
y <- data$pass2
result <- data.frame(level=x, pass=y)
result
dim(result) # 248 2
table(result) # 빈도 .

# 방법1
# chisq.test(x, y, correct = T) # 야트보정 자동.
chisq.test(x,y)
# data:  x and y
# X -squared = 2.767, df = 2, p-value = 0.2507
# 해석 : p-value : 0.2507 > 0.05 이므로 귀무가설 채택.

# 방법2
library(gmodels)
CrossTable(x, y, chisq = TRUE) # 크로스 테이블을 사용하면 좀 더 많은 정보를 확인 가능함.
# Chi^2 =  2.766951     d.f. =  2     p =  0.2507057 
# 해석 : p-value : 0.2507 > 0.05 이므로 귀무가설 채택.

# 실습 : 성별에 따른 대학원 입학 여부의 독립성 검정
# 어느 대학원 입시에서 입학 과정 중 여성에 대한 성차별이 있었다는 내용의 소송이 발생했다. 
# 고소인은 근거 자료로 성별에 따라 합격에 영향을 미쳤음을 주장했는데, 그 근거를 살펴 보자.
# R의 내장 dataset으로 UCBAdmissions 가 있다. 
# 1973년 미국 버클리 대학원에서 있었던 사건으로 모집 인원이 가장 많은 상위 6개학과의 성별 합격 여부를 저장한 자료다.
# 귀무 : 성별과 합격 여부는 관련이 없다. (서로 독립)
# 대립 : 성별과 합격 여부는 관련이 없다. (서로 독립이 아니다)

data("UCBAdmissions")
UCBAdmissions
str(UCBAdmissions)
(ucba_tab <- apply(UCBAdmissions, c(1,2), sum))
round(prop.table(ucba_tab, margin = 2) * 100, 1)
# 기대도수, 기대빈도 등을 통해 카이제곱 추출가능

# 함수 사용
chisq.test(ucba_tab)
# X-squared = 91.61, df = 1, p-value < 2.2e-16
chisq.test(ucba_tab, correct = F)
# X-squared = 92.205, df = 1, p-value < 2.2e-16
CrossTable(ucba_tab, chisq = T)
# 해석 : 유의확률(p값)이 0.05 보다 작으므로 귀무가설을 기각
# 성별과 합격 여부는 관련이 있다.

# 동질성 검정
# 두 집단의 분포가 동일한가? 다른 분포인가? 를 검정하는 방법으로, 
# 두 집단 이상에서 각 범주(집단) 간의 비율이 서로 동일한가를 검정하는 방법이다. 
# 즉, 두 개 이상의 범주형 자료가 동일한 분포를 갖는 모집단에서 추출된 것인지 검정하는 방법이다.
# - 귀무가설 예) 모든 샘플들의 비율은 동일하다.
# - 독립성 검정은 두 변수 사이의 연관성을 검정하는데 비해, 
#   동질성 검정은 하위 모집단 사이 특정 변수에 대한 분포의 동질성을 검정한다.

data <- read.csv("testdata/homogenity.csv") # 설문조사 결과
data

table(data$method)
table(data$survey)

data$method2[data$method == 1] <- "방법1"
data$method2[data$method == 2] <- "방법2"
data$method2[data$method == 3] <- "방법3"
head(data)

data$survey2[data$survey == 1] <- "매우만족"
data$survey2[data$survey == 2] <- "만족"
data$survey2[data$survey == 3] <- "보통"
data$survey2[data$survey == 4] <- "불만족"
data$survey2[data$survey == 5] <- "매우 불만족"
head(data)

table(data$method2, data$survey2)
# 주의 각 집단별 길이는 같아야 한다.
chisq.test(data$method2, data$survey2) # 야트보정 자동으로 해줌.
# X-squared = 6.5447, df = 8, p-value = 0.5865
# 해석 : p-value : 0.5865 > 0.05 이므로 귀무가설 채택
CrossTable(data$method2, data$survey2, chisq = T)
# Chi^2 =  6.544668     d.f. =  8     p =  0.5864574 
# 다른방법으로 했지만 위와 동일.

# 실습) 연령대별 sns 이용률의 동질성 검정
# 20대에서 40대까지 연령대별로 서로 조금씩 그 특성이 다른 SNS 서비스들에 대해 이용 현황을 
# 조사한 자료를 바탕으로 연령대별로 홍보 전략을 세우고자 한다. 
# 연령대별로 이용 현황이 서로 동일한지 검정해 보도록 하자.

# 귀무 : 연령대별 SNS 서비스사 이용현황은 동일하다.(동질이다)
# 대립 : 연령대별 SNS 서비스사 이용현황은 동일하지 않다.(동질이 아니다)

sns_data <- read.csv("testdata/snsbyage.csv", stringsAsFactors = FALSE) # 벡터 아닌 방식으로 데이터를 읽어옴. 옵션 값을 주면 됨.
tail(sns_data)
str(sns_data)
sns_data <- transform(sns_data, fage = factor(age, levels = c(1,2,3), labels = c("20대","30대","40대")))
# sns_data <- transform(sns_data, fage = factor(age, levels = c(1,2,3)))
sns_data <- transform(sns_data, fservice = factor(service, levels = c("F","T","K","C","E"), ordered = T))

head(sns_data)
tab <- table(sns_data$fage, sns_data$fservice)
tab

chisq.test(tab)
# X-squared = 102.75, df = 8, p-value < 2.2e-16
CrossTable(tab, chisq = T)
# Chi^2 =  102.752     d.f. =  8     p =  1.167906e-18 

# 해석 : p 값이 0.05 보다 작으므로 귀무가설을 기각.