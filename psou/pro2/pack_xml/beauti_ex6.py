'''
Created on 2018. 11. 19.

xml 처리
'''
from bs4 import BeautifulSoup

with open("my.xml", mode="r", encoding="utf-8") as f:
    xmlfile = f.read()
    #print(xmlfile)
    
soup = BeautifulSoup(xmlfile, 'lxml')
#print(soup)
#print(sopup.prettify())
itemtag = soup.findAll("item")
print(itemtag)
print()

ntag = soup.findAll("name")
print(ntag)
print()

for i in ntag:
    print(i['id'])
    
print('---------------------------------')
for i in itemtag:
    nametag = i.findAll('name')
    for j in nametag:
        print('id : ' + j['id'] + ' name : ' + j.string)