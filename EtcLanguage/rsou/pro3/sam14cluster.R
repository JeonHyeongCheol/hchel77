# 계층적 굽집 분석 : 가까운 데이터끼리 순차적으로 그룹으로 묶기

x <- c(1,2,2,4,5)
y <- c(1,1,4,3,4)
df <- data.frame(cbind(x,y))
df

plot(df, pch = 20, xlim = c(0,6), ylim = c(0,6)) # xlab : xlabel, xlim : xlimit(x 간격, y도 설정 가능)
text(df[,1], df[,2], labels = abbreviate(rownames(df)), cex = 0.8, pos = 1, col = "blue")
abline(v=c(3), col = "gray", lty = 2)
abline(h=c(3), col = "gray", lty = 2)

dist(df, method = "euclidean") ^ 2 # 행, 열간의 거리 계산, 작을 수록 가까운 것.

# 그룹간의 유사성 계산 방법
# 1) 단일 연결법(최단 연결법)
hc_sl <- hclust(dist(df)^2, method = 'single')
hc_sl

plot(hc_sl) # 그룹을 볼 수 있으며, 붙어 있는 것들끼리가 거리가 짧은 것.
plot(hc_sl, hang = -1) # 같은 라인에서 볼 수 있음.

# 2) 완전 연결법(최장 연결법)
hc_cl <- hclust(dist(df)^2, method = 'complete')
hc_cl

plot(hc_cl) # 최단과 차이점은 y축의 길이 달라짐.
plot(hc_cl, hang = -1) 

# 3) 평균 연결법
hc_avg <- hclust(dist(df)^2, method = 'average')
hc_avg

plot(hc_avg) 
plot(hc_avg, hang = -1) 

par(omg = c(3,0,1,0)) # omg : 바깥쪽 마진, mar : 내부쪽 마진
par(mfrow = c(1,3))
plot(hc_sl, main = "단일 연결법", hang = -1)
plot(hc_cl, main = "완전 연결법", hang = -1)
plot(hc_avg, main = "평균 연결법", hang = -1)

# 4) 
hc_cl <- hclust(dist(df)^2, method = '')
hc_cl

plot(hc_cl) 
plot(hc_cl, hang = -1) 
