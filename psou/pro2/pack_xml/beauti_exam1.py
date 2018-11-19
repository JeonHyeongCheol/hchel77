'''
Created on 2018. 11. 19.

문제 1
'''
import urllib.request  as req
import urllib.parse

from bs4 import BeautifulSoup
from imageio.core.util import urlopen
import re

def getCar(url):
    try:
        html = urlopen(url).read().decode('utf-8','ignore')
    except Exception as e:
        return None
    #listcar > div > ul
    try:
        beautiObj = BeautifulSoup(html, "html.parser")
        #divCarPopularList > div.car-list.ty-thumnail.slider_2.slick-initialized.slick-slider > div > div > div:nth-child(7) > div:nth-child(1) > div > dl > dt > a
        cars = beautiObj.select("div.list-inner")

        for car in cars:
            print("차종  :", car.select_one("div.title").p.text.strip(), " || 금액 :", car.select_one("div.price").b.text.strip()) # class가 
        
    except Exception as e:
        return e
    
    #return title

title = getCar("http://www.bobaedream.co.kr/cyber/CyberCar.php?sel_m_gubun=ALL")

if title == None:
    print("자료 없음")