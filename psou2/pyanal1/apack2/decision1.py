'''
Created on 2018. 12. 6.

의사결정나무 : 해석이 쉽다.
'''

import pydotplus
from sklearn import tree
import collections

x = [[180, 15],[177, 42],[156, 35],[174, 65],[161, 28]]
y = ['man', 'woman', 'woman', 'man', 'woman']
label_name = ['height', 'hair_length']
model = tree.DecisionTreeClassifier(criterion='entropy', max_depth=3, random_state=0) 
# criterion : 혼잡도,  max_depth : 가지 깊이, random_state : seed  
print(model)

model.fit(x, y) # 모델 생성.
pred = model.predict(x) # 모델 예측값 생성.
print(pred)

# 시각화
dot_data = tree.export_graphviz(model, feature_names=label_name, out_file=None, filled=True, rounded=True)
graph = pydotplus.graph_from_dot_data(dot_data)
colors = ('red', 'orange')
edges = collections.defaultdict(list) # 리스트 타입의 변수를 준비.

for i in graph.get_edge_list():
    edges[i.get_source()].append(int(i.get_destination()))
    
for i in edges:
    edges[i].sort()
    for a in range(2):
        dest = graph.get_node(str(edges[i][a]))[0]
        dest.set_fillcolor(colors[a])

graph.write_png('tree.png') # 이미지 저장.

from matplotlib.pyplot import imread
import matplotlib.pyplot as plt
img = imread('tree.png')
plt.imshow(img)
plt.show()