# *단일 모집단의 비율에 대한 가설검정
# 실습 예제)공인 야구공의 불량률 검정 수행
# KBO에서는 공인구를 납품 받을 때 샘플의 반발계수가 0.4134 ~ 0.4374인 경우에만 정상으로 인정하고,
# 비정상률이 10%를 넘는 경우에는 납품을 거부한다고 한다.
# 공인구 제조사는 자사 제품의 100개를 표본으로 불량률이 10%를 넘는지 확인하기 위해 
# 모비율에 대한 가설검정을 실시하기로 하였다.


data <- read.table("testdata/restitution.txt", header = T)
head(data)
rel <- ifelse(data$rst < 0.4134 | data$rst > 0.4374, 1, 0) # 0.4134 ~ 0.4374인 경우 1, 아닌 경우 0.
rel

n <- length(rel)
nos <- sum(rel)
nos
prop.test(nos, n, p=0.1, alternative = "greater", correct = F)  # correct : 보정 여부
# p-value = 0.3694 > 0.05 이므로 귀무가설 채택.