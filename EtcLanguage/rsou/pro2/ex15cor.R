# 상관관계 분석 : 상관계수(밀도를 수치로 표현)의 수치로 변수간의 관계를 파악

hf <- read.csv("testdata/galton.csv", header = T, stringsAsFactors = F)
head(hf)

hf$sex <- factor(hf$sex, levels = c("M", "F"))
hf_son <- subset(hf, sex == "M") # 아들만 뽑음.
hf_son <- hf_son[c("father", "height")]
head(hf_son) # 아버지와 아들의 키만 추출.

# 공분산
cov(hf_son$father, hf_son$height) # 2.368441

# 상관계수
cor(hf_son$father, hf_son$height) # 0.3913174, 느슨한 양의 상관관계이다.

plot(height ~ father, data = hf_son, pch = 16, xlab = "아버지 키", ylab = "아들 키")
abline(lm(height ~ father, data = hf_son))

