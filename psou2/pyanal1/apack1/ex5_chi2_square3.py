'''
Created on 2018. 11. 28.

이원카이제곱
동질성 검정 - 두 집단의 분포가 동일한가? 다른 분포인가? 를 검증하는 방법이다. 두 집단 이상에서 각 범주(집단) 간의 비율이 서로
동일한가를 검정하게 된다. 두 개 이상의 범주형 자료가 동일한 분포를 갖는 모집단에서 추출된 것인지 검정하는 방법이다.
동질성 검정실습1) 교육방법에 따른 교육생들의 만족도 분석 - 동질성 검정 survey_method.csv
'''

import pandas as pd
import scipy.stats as stats

data = pd.read_csv('../testdata/survey_method.csv')
#print(data)

# 귀무가설 : 교육방법에 따른 교육생들의 만족도에 따라 차이가 없다.
# 대립가설 : 교육방법에 따른 교육생들의 만족도에 따라 차이가 있다.

ctab = pd.crosstab(index=data['method'], columns=data['survey']) # 빈도 수.
ctab.index = ['방법1', '방법2', '방법3'] # 행에 중복값 제거 후 이름 설정.
ctab.columns = ['매우만족', '만족', '보통', '불만족', '매우불만족'] # 열에 이름 설정.
print(ctab)

chi_attr = [ctab.loc["방법1"], ctab.loc["방법2"], ctab.loc["방법3"]]
chi2, pv, _, _ = stats.chi2_contingency(chi_attr)
# 위에나 밑에나 돌리면 똑같음. 방법만 다를뿐. 어차피 다쓰기 때문에 ctab을 그냥 집어 넣으면 됨.
chi2, pv, _, _ = stats.chi2_contingency(ctab)

print("ch2 : {}, p-value : {}".format(chi2, pv))
# ch2 : 6.544667820529891, p-value : 0.5864574374550608
# 해석 : p-value : 0.5864574374550608 > 0.05이므로 귀무가설 채택. 
# 교육 방법에 따른 교육생들의 만족도에 차이가 없다. 동질이다.


'''
동질성 검정 실습2) 연령대별 sns 이용률의 동질성 검정
20대에서 40대까지 연령대별로 서로 조금씩 그 특성이 다른 SNS 서비스들에 대해 이용 현황을 
조사한 자료를 바탕으로 연령대별로 홍보전략을 세우고자 한다.
연령대별로 이용 현황이 서로 동일한지 검정해 보도록 하자.
독립성 검정은 두 변수 사이의 연관성을 검정하는데 비해,동질성 검정은 하위 모집단 사이 특정 변수에 대한 분포의 동질성을 검정한다.
'''

data = pd.read_csv('../testdata/snsbyage.csv')
print(data.head(3))
# 귀무가설 : 연령대별 sns 이용률의 이용현황은 동일하다. (동질이다)
# 대립가설 : 연령대별 sns 이용률의 이용현황은 동일하지 않다. (비동질이다)

ctab = pd.crosstab(index=data['age'], columns=data['service'])
print(ctab)
ctab.index = ['10대', '20대', '30대']
chi2, pv, _, _ = stats.chi2_contingency(ctab)
print("ch2 : {}, p-value : {}".format(chi2,pv))
# ch2 : 102.75202494484225, p-value : 1.1679064204212775e-18
# 해석 : p-value : 1.1679 < 0.05 이므로 귀무가설 기각.
# 연령대별 sns 이용률의 이용현황은 동일하지 않다.(비동질이다)