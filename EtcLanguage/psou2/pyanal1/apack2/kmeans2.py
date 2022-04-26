'''
Created on 2018. 12. 7.

iris dataset으로 계층적 군집분석
'''
import pandas as pd
from sklearn.datasets import load_iris
import matplotlib.pyplot as plt

iris = load_iris()
print(iris)

iris_df = pd.DataFrame(iris.data, columns = iris.feature_names)
print(iris_df.head(3))
print(iris_df.loc[0:3, ['sepal length (cm)','sepal width (cm)']])
print()

from scipy.spatial.distance import pdist, squareform
distmatrix = pdist(iris_df.loc[0:4, ['sepal length (cm)','sepal width (cm)']], metric='euclidean')
print('distmatrix : \n', distmatrix)
print()

row_dist = pd.DataFrame(squareform(distmatrix))
print(row_dist)
print()

from scipy.cluster.hierarchy import linkage, dendrogram
from sklearn.cluster import AgglomerativeClustering

row_clusters = linkage(distmatrix, method = 'complete')
print('row_clusters : ', row_clusters)
df = pd.DataFrame(row_clusters, columns = ['cluster1', 'cluster2', '거리', '멤버수'])
print(df)

row_dendro = dendrogram(row_clusters)
plt.tight_layout()
plt.ylabel('유클리드 거리')
plt.show() # 표를 확인할 때 잘 확인 할 것.  [1 0 0 0 1] 요렇게 된 것은 왼쪽, 오른쪽 하나씩 묶이고, 가운데는 없다고 보면 됨.

ac = AgglomerativeClustering(n_clusters=2, affinity='euclidean', linkage='complete')
x = iris_df.loc[0:4, ['sepal length (cm)','sepal width (cm)']]
pred = ac.fit_predict(x) # 최초의 값을 넣어주면 됨.
print('클러스터링 결과 : \n', pred)
