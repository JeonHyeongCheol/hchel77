df <- read.csv("testdata/exam.csv", sep = " ", header = T)
df

ddf <- dist(df)
ddf

# 거리를 그림으로 표현
gra_df <- cmdscale(ddf)
gra_df
plot(gra_df, type = "n")
text(gra_df, as.character(1:10))


str(df)
df$avg <- apply(df[,2:5], 1, mean)
df

kmodel <- kmeans(df[,c("bun", "avg")], centers = 4)
kmodel
table(kmodel$cluster)

cluster <- kmodel$cluster
cluster

kmeans_df <- cbind(cluster, df[, c("bun", "avg")])
kmeans_df
str(kmeans_df)
kmeans_df <- transform(kmeans_df, cluster = as.factor(cluster))
str(kmeans_df)

library(ggplot2)
df_plot <- ggplot(data = kmeans_df, aes(x = bun, y = avg, color = cluster)) +
           geom_point(aes(shape = factor(cluster), size = 4)) +
           ggtitle("K-Means 결과")
df_plot
