'''
Created on 2018. 11. 19.


'''
import json
import requests


data = {
    'b' : 5.6,
    'a' : 3,
    'c' : 'Hello world',
    'd' : {
        'sbs':5
        }
    }
 
print(type(data)) # <class 'dict'>
 
json_data = json.dumps(data) # str type으로 반환
print(type(json_data)) # <class 'str'>
print()
 
json_data2 = json.loads(json_data)
print(type(json_data2)) # dict type으로 반환

print('/n------------------------------------------')
def gogo2():
    base_url = "http://openapi.seoul.go.kr:8088/sample/json/SeoulLibraryTime/1/5/"
    sou_code = requests.get(base_url)
    re_text = sou_code.text
    
    print(re_text)
    print(type(re_text))
    print()
    
    json_data = json.loads(re_text)
    print(json_data)
    print(type(json_data))
    print()
    
    print(json_data["SeoulLibraryTime"]["RESULT"]["CODE"])
    print()
    
    print(json_data["SeoulLibraryTime"]["row"][0]["LBRRY_NAME"])
    print(json_data["SeoulLibraryTime"]["row"][0]["ADRES"])
    print(json_data["SeoulLibraryTime"]["row"][0]["TEL_NO"])
    print()
    
    print(len(json_data["SeoulLibraryTime"]["row"])) # 55
    print()
    
    for i in range(len(json_data["SeoulLibraryTime"]["row"])):
        print(json_data["SeoulLibraryTime"]["row"][i]["LBRRY_NAME"])
        print(json_data["SeoulLibraryTime"]["row"][i]["TEL_NO"])
        print()
        
if __name__=="__main__":
    gogo2()