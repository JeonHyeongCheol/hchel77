from django.shortcuts import render
import requests
import base64
import json
import urllib.request
from mtranslate import translate
import numpy as np
from keras.models import model_from_json
from keras.preprocessing import sequence
from gensim.models.word2vec import Word2Vec
import os
import mpl_toolkits.mplot3d.axes3d as p3
import matplotlib.pyplot as plt
import colorsys
from PIL import Image
from bs4 import BeautifulSoup
import requests
import re


GOOGLE_CLOUD_VISION_API_URL = 'https://vision.googleapis.com/v1/images:annotate?key='
loaded_model=''
API_KEY = "AIzaSyCHbwkxb-m7pctThCL8JOePkATm698wBq8"

def imagemake1(path):
    # (1) Import the file to be analyzed!
    img_file = Image.open(path)
    img = img_file.load()
    
    # (2) Get image width & height in pixels
    [xs, ys] = img_file.size
    max_intensity = 100
    hues = {}
    
    # (3) Examine each pixel in the image file
    for x in range(0, xs):
        for y in range(0, ys):
            #print(r,g,b)
        # (4)  Get the RGB color of the pixel
            [r, g, b] = img[x, y]
    
        # (5)  Normalize pixel color values
            r /= 255.0
            g /= 255.0 
            b /= 255.0
    
        # (6)  Convert RGB color to HSV
            [h, s, v] = colorsys.rgb_to_hsv(r, g, b)
    
        # (7)  Marginalize s; count how many pixels have matching (h, v)
            if h not in hues:
                hues[h] = {}
            if v not in hues[h]:
                hues[h][v] = 1
            else:
                if hues[h][v] < max_intensity:
                    hues[h][v] += 1
    
    # (8)   Decompose the hues object into a set of one dimensional arrays we can use with matplotlib
    h_ = []
    v_ = []
    i = []
    colours = []
    
    for h in hues:
        for v in hues[h]:
            h_.append(h)
            v_.append(v)
            i.append(hues[h][v])
            [r, g, b] = colorsys.hsv_to_rgb(h, 1, v)
            colours.append([r, g, b])
    
    # (9)   Plot the graph!
    fig = plt.figure()
    ax = p3.Axes3D(fig)
    ax.scatter(h_, v_, i, s=5, c=colours, lw=0)
    
    ax.set_xlabel('Hue')
    ax.set_ylabel('Value')
    ax.set_zlabel('Intensity')
    fig.add_axes(ax)
    plt.savefig('project_3\\myapp\\static\\images\\test.png')
    #plt.show()
    
    return "end"
    

def request_cloud_vison_api(image_base64):
    api_url = GOOGLE_CLOUD_VISION_API_URL + API_KEY
    req_body = json.dumps({
        'requests': [{
            'image': {
                'content': image_base64.decode('utf-8')
            },
            'features': [{
                'type': 'LABEL_DETECTION',
                'maxResults':9999999,
            }]
        }]
    })
    res = requests.post(api_url, data=req_body)
    return res.json()

def Main(request):
    return render(request, 'main.html')

def post_new(request):
    
    return render(request, 'post_new.html')

def ajaxs(request):
    pass

import time
def post_like(request):
    return render(request, 'post_view.html')

class getModel:   
    def __init__(self):
        global loaded_model
        json_file = open(os.path.realpath('project_3\\myapp\\static\\model\\forth_model.json'), 'r')
        load_model_json = json_file.read()
        json_file.close()
        loaded_model = model_from_json(load_model_json)
        
        loaded_model.load_weights(os.path.realpath("project_3\\myapp\\static\\model\\forth_model.h5"))
        
        loaded_model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
        
     

def post_view(request):
    if request.method == "POST":
        global loaded_model
        url = request.POST.get('imgs')
        if loaded_model == '':
            getModel()
        tag_val = recommend_tags(loaded_model, 4, url)
    return render(request, 'post_view.html', {'blah':url,'tag':tag_val})

def sequence_to_text(reverse_word_map, list_of_indices):
    # Looking up words in dictionary
    words = [reverse_word_map.get(letter) for letter in list_of_indices]
    return(words)

def texts_to_sequences(word_map, list_of_words):
    return [word_map.get(letter.strip()) for letter in list_of_words]

def img_to_base64(filepath):
    with open(filepath, 'rb') as imgs:
        img_byte = imgs.read()
    return base64.b64encode(img_byte)

def recommend_tags(model,maxlen, url): 
    urllib.request.urlretrieve(url, os.path.realpath('project_3\\myapp\\static\\Image\\imsi.png'))         
    img_base64 = img_to_base64(os.path.realpath('project_3\\myapp\\static\\Image\\imsi.png'))
    with open(os.path.realpath('project_3\\myapp\\static\\word\\word2idx.json'), 'r', encoding='utf-8-sig') as f:
        data = json.load(f)
    
    result = request_cloud_vison_api(img_base64)
    wtv = Word2Vec.load(os.path.realpath('project_3\\myapp\\static\\word\\word2vec.model'))
    
    text_result=[]
    try:
        count = len(result["responses"][0]["labelAnnotations"])
    except:
        return "사진 분석 실패"
    for i in range(0,count):
        text_r = result["responses"][0]["labelAnnotations"][i]["description"]
        text_result.append(text_r)

    pre_trans = ",".join(text_result)
    trans_data = translate(pre_trans, 'ko', 'en').split(",")   
    
    img_tags =texts_to_sequences(data,trans_data)
    
    img = [img for img in img_tags if img != None]
   
    imsi = np.zeros((1, 415))

    if len(img) - 3 <= 0:
        su = 1
    else :
        su = len(img) -3
    for i in range(su):
        x = sequence.pad_sequences([img[i:i+4]], maxlen=4)
        
        imsi += model.predict(x)

    best_tag = np.argsort(imsi)
    try:
        result = [wtv.wv.index2word[j] for j in best_tag[0][-5:]]
    except Exception as e:
        print("err : ",e)
    #make image
    imagemake1('project_3\\myapp\\static\\Image\\imsi.png')
    
    
    return result