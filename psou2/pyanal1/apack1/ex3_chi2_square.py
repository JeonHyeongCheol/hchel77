'''
Created on 2018. 11. 28.

이원카이제곱 - 교차분할표 이용

독립성(관련성) 검정
- 동일 집단의 두 변인(학력수준과 대학진학 여부)을 대상으로 관련성이 있는가 없는가?
- 독립성 검정은 두 변수 사이의 연관성을 검정한다.
실습 : 교육수준과 흡연율 간의 관련성 분석 : smoke.csv'
'''

import pandas as pd
import scipy.stats as stats

# 귀무가설 : 교육수준과 흡연율 간의 관련성이 없다.(독립)
# 대립가설 :교육수준과 흡연율 간의 관련성이 있다.(비독립)

data = pd.read_csv('../testdata/smoke.csv')
print(data.head(3))
print(data['education'].unique()) # 종류확인 : unique해서 중복제거해서 종류가 무엇인지 확인.
print(data['smoking'].unique())

# 교육수준과 흡연인원 수
ctab = pd.crosstab(index = data['education'], columns = data['smoking']) # 교차표 이용. 빈도수로 나옴.
#ctab = pd.crosstab(index = data['education'], columns = data['smoking'], normalize=True) # normalize : 비율로 나옴.
# 인덱스는 data에 education으로 설정. 칼럼은 smoking으로 설정.
print(ctab) # 빈도수 확인.

ctab.index = ["대학원졸", "대졸", "고졸"] # index설정. ["대학원졸" : 1, "대졸" : 2, "고졸" : 3]
ctab.columns = ["과흡연", "보통", "비흡연"] # columns설정. ["과흡연" : 1, "보통" : 2, "비흡연" : 3]
print(ctab)
chi_result = [ctab.loc["대학원졸"], ctab.loc["대졸"], ctab.loc["고졸"]]
chi2, p, ddof, expected = stats.chi2_contingency(chi_result) # 이원 카이 제곱. 이렇게하면 4개의 리턴값이 각각의 변수에 들어감.
# ddof : 자유도, expected : 기대값
#chi2, p, _, _ = stats.chi2_contingency(chi_result) # 필요없는 값들은 언더바로 써줘도 됨.
msg = '결과 - 통계량 : {}, p-값 : {}, df값 : {}'
print(msg.format(chi2, p, ddof)) # msg에 chi2, p, ddof에 값을 format을 통해 넣어줌.
print(expected) 
# 해석 : 통계량 - 18.910915739853955, p-값  = 0.0008182572832162924, df값 = 4
# p-값  = 0.0008 < 0.05 이므로 귀무가설을 기각.
# 대립가설 :교육수준과 흡연율 간의 관련성이 있다.(비독립)
