'''
Created on 2018. 12. 7.

밀도 기반 클러스터링
'''
import matplotlib.pylab as plt
import numpy as np
from sklearn.datasets import make_moons
from sklearn.cluster import KMeans, DBSCAN

x, y = make_moons(n_samples=200, noise=0.05, random_state=0)
print(x)
print(y)

plt.scatter(x[:,0], x[:,1])
plt.show()

km = KMeans(n_clusters=2, random_state=0)
pred = km.fit_predict(x)

def plotfunc(x, pred):
    plt.scatter(x[pred==0, 0], x[pred==0, 1], c='blue', marker='o', label='cluster1')
    plt.scatter(x[pred==1, 0], x[pred==1, 1], c='red', marker='o', label='cluster2')
    
    plt.title('result')
    plt.legend()
    plt.show()
    
plotfunc(x, pred) # 완전한 분류가 되지 않은 것을 볼 수 있음.

# 완전한 분류가 되지 않았을 때 DBSCAN을 쓰면 됨.
dbsmodel = DBSCAN(eps=0.2, min_samples=5, metric='euclidean')
pred2 = dbsmodel.fit_predict(x)

plotfunc(x, pred2)