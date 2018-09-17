df <- read.csv("testdata/ex_studentlist.csv")
df

table(df$gender, df$bloodtype) # 명목형 데이터, 성별, 혈액형별 빈도수 출력.

freq <- table(df$bloodtype)
freq

rfreq <- prop.table(freq) # 분할표로부터 각 셀의 비율을 계산.
rfreq

rtable <- rbind(freq, rfreq) # rtable에 freq, rfreq를 집어 넣음.
rtable

#rtable <- addmargins(rtable, margin = 1) # 1 : 열, 2: 행
rtable <- addmargins(rtable) # 행/열 합
rtable

df
fHeight <- cut(df$height, breaks = 4) # cut : 구간나누기, breaks : 몇 개로 나눌 것인지.
fHeight

fHeight <- table(fHeight)
fHeight

fHeight <- rbind(fHeight, prop.table(fHeight)) # 기존정보와 비율 정보 출력.
fHeight

rownames(fHeight)[2] <- "relaHeight"
fHeight

cumFreq <- cumsum(fHeight[2,]) # 누적
cumFreq

fHeight <- rbind(fHeight, cumFreq)
fHeight

rownames(fHeight) <- c("도수", "상대도수", "누적도수")
fHeight

fHeight <- addmargins(fHeight, margin =  2)
fHeight
