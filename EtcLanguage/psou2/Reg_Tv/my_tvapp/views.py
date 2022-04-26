from django.shortcuts import render
from scipy import stats
import numpy as np
import pandas as pd

# Create your views here.

def Main(request):
    return render(request, "main.html")


def Regression(request):
    x = float(request.GET['tv'])
    print(type(x))
    
    tv = pd.read_csv('C:/work/psou2/Reg_tv/my_tvapp/templates/tv.txt')
    tv = tv.fillna(tv.mean())
    tv = std_based_outlier(tv)
    print(tv)
    print(type(tv))
    
    model1 = stats.linregress(tv['지상파'], tv['종편'])
    #print(model1)
    model2 = stats.linregress(tv['지상파'], tv['운동'])
    #print(model2)
    
    # model 1
    y1 = model1.slope*x + model1.intercept
    #print(y1)
    #print("지상파 - 종편 상관관계",tv['지상파'].corr(tv['종편']))
    # model 2
    y2 = model2.slope*x + model2.intercept
    #print(y2)
    #print("지상파 - 운동 상관관계",tv['지상파'].corr(tv['운동']))
    
    return render(request, "main.html", {'y1':round(y1,1), 'y2':round(y2,1), 'x':x})
    
def std_based_outlier(df):
    for i in range(0, len(df.iloc[1])):
        df.iloc[:,i] = df.iloc[:,i].replace(0, np.NaN)
        df = df[~(np.abs(df.iloc[:,i] - df.iloc[:,i].mean()) > (3*df.iloc[:,i].std()))].fillna(0)
    return(df)
