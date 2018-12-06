from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
import pandas as pd
from sklearn import model_selection

df = pd.read_csv('https://raw.githubusercontent.com/pykwon/python/master/testdata_utf8/titanic_data.csv')

# nan 값이 존재하는 행 삭제 891개 -> 714개
df = df.dropna(subset=['Pclass', 'Age', 'Sex'])
len(df)

df_X = df[['Pclass', 'Age', 'Sex']]
print(df_X.head(3))

#Scaling ----------------------
from sklearn.preprocessing import LabelEncoder, OneHotEncoder
df_X.loc[:, 'Sex'] = LabelEncoder().fit_transform(df_X['Sex'])
#df_X['Sex'] = df_X['Sex'].apply(lambda x: 1 if x == 'male' else 0)
df_Y = df['Survived']
print(df_X.head(3))

import numpy as np
df_X2 = pd.DataFrame(OneHotEncoder().\
        fit_transform(df_X['Pclass'].\
        as_matrix()[:, np.newaxis]).toarray(),\
        columns = ['f_class','s_class','t_class'],\
        index = df_X.index)
df_X = pd.concat([df_X, df_X2], axis=1)
#-------------------------------
print(df_X)

(train_X, test_X, train_Y, test_Y) = train_test_split(df_X, df_Y)

# model 생성 및 fit
model = RandomForestClassifier(criterion='entropy', n_estimators = 7)
fitted_model = model.fit(train_X, train_Y)

# 예측
pred_Y = fitted_model.predict(test_X)

# 정확도 확인
print('acc: ' ,sum(test_Y == pred_Y)/len(test_Y))

# 교차검증
cross_vali = model_selection.cross_val_score(model, train_X, train_Y, cv = 5)
print(cross_vali)

