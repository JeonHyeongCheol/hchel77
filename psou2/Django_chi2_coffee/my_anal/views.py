from django.shortcuts import render
import pandas as pd

from my_anal.models import Survey
from scipy.stats.contingency import chi2_contingency


# Create your views here.
def Main(request):
    return render(request, "main.html")

def SurveyView(request):
    return render(request, "survey.html")

def SurveyProcess(request):
    InsertData(request)
    rdata = list(Survey.objects.all().values()) # 값들을 list로 rdata에 저장.
    #print(rdata)
    #이원 카이제곱 검점
    crosstbl, result = Analysis(rdata) # 메소드 호출. 리턴 값을 변수에 저장.
    #print(crosstbl, result)
    return render(request, 'result.html', {'crosstbl':crosstbl.to_html(), 'result':result}) # to_html()를 쓰면 html에서 출력가능(DataFrame만 가능).
    
    
def Analysis(rdata):
    df = pd.DataFrame(rdata) # 데이터를 DataFrame으로 바꿔서 변수에 저장.
    #df.dropna() # 노파심에 함.
    #print(df)
    df['genNum'] = df['gender'].apply(lambda g:1 if g == '남' else 2) # 칼럼 추가. genNum이라는 컬럼을 추가하고 남자일 때 1, 아닐 때 2.
    df['coNum'] = df['co_survey'].apply(lambda c:1 if c == '스타벅스' else 2 # 칼럼 추가. coNum이라는 컬럼을 추가하고 스타벅스 일 때 1, 커피빈일 때 2, 이디아일 때 3, 탐앤탐스 일 때는 4.
                                        if c == '커피빈' else 3
                                        if c == '이디아' else 4)
    #print(df)
    
    crosstal = pd.crosstab(index = df['genNum'], columns = df['co_survey']) # 인덱스가 열
    #print(crosstal)
    #st, pv, _, _ = chi2_contingency(crosstal) # 이 방법이나 밑에 방법이나 똑같음.
    st, pv, _, _ = chi2_contingency((df['genNum'], df['coNum']))
    #print("통계값 : {}, p-value : {}".format(st, pv))
    
    if pv > 0.05:
        result = "<b>p 값이  {}</b>이므로 유의수준 0.05보다 커 <b>귀무가설을 채택</b><br>(성별에 따라 선호하는 커피 브랜드에는 차이가 없다)".format(pv)
    else:
        result = "<b>p 값이  {}</b>이므로 유의수준 0.05보다 작아 <b>귀무가설을 기각</b><br>(성별에 따라 선호하는 커피 브랜드에는 차이가 있다)".format(pv)

    return crosstal, result
    
def InsertData(request):
    if request.method == 'POST':
        Survey(
            #rnum = len(list(Survey.objects.all().values())) + 1 # DB에서 자동증가하지 않을 경우 이렇게 사용 해야 함.
            gender = request.POST.get('gender'),
            age = request.POST.get('age'),
            co_survey = request.POST.get('co_survey')
        ).save()

def ListFunc(request):
    return render(request, "list.html")