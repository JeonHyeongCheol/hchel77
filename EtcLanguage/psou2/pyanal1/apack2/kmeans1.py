'''
Created on 2018. 12. 7.

계층적 클러스터링(군집화) - 비지도 학습
'''
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import re
from statsmodels.genmod.families import family
from pandas.io.sas.sas_constants import column_data_length_length
plt.rc('font', family = 'malgun gothic')

np.random.seed(123)
var = ['x', 'y']
labels = ['점1', '점2', '점3', '점4', '점5']
x = np.random.random_sample([5,2]) * 10
print(x)

df = pd.DataFrame(x, columns = var, index = labels)
print(df)

from scipy.spatial.distance import pdist, squareform
distmatrix = pdist(df, metric='euclidean')
print(distmatrix)

row_distmatrix = pd.DataFrame(squareform(distmatrix)) # 표의 형식으로 바꿔줌.
print(row_distmatrix)

# plt.scatter(x[:,0], x[:,1], c='blue', marker='o', s=50)
# plt.grid(True)
# plt.show()

row_dist = pd.DataFrame(squareform(distmatrix), columns = labels, index = labels)
print(row_dist)

print('**' * 50)

# 완전 연결법 : linkage()
from scipy.cluster.hierarchy import linkage
row_cluster = linkage(distmatrix, method = "complete")
#print(row_cluster)
df = pd.DataFrame(row_cluster,\
                  columns = ['cluster1', 'cluster2', '거리', '클러스터멤버수'],\
                  index=['클러스터%d'%(i + 1) for i in range(row_cluster.shape[0])])
print(df)

# linkage의 반환값으로 dendrogram(계통도) 그리기
from scipy.cluster.hierarchy import dendrogram
row_dendro = dendrogram(row_cluster, labels=labels)
plt.tight_layout()
plt.ylabel("유클리드 거리값")
plt.show()

print()
from sklearn.cluster import AgglomerativeClustering
ac = AgglomerativeClustering(n_clusters=2, affinity='euclidean', linkage='complete')
pred = ac.fit_predict(x) # 최초의 값을 넣어주면 됨.
print('클러스터링 결과 : \n', pred)