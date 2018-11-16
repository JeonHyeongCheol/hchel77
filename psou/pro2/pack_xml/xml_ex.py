'''
Created on 2018. 11. 16.

xml 자료 처리
'''
import xml.etree.ElementTree as etree

# etree로 xml 파일을 불러올 수 있음.

xmlfile = etree.parse("my.xml")
#print(xmlfile)
etree.dump(xmlfile)
print()

root = xmlfile.getroot()
print(root.tag)
print(root[0].tag)
print(root[0][1].tag)
print(root[0][2].tag)
print(root[0][0].attrib)
print(root[0][0].attrib.keys())
print(root[0][0].attrib.values())
print()

myname = root.find("item").find("name").text
print(myname)

print()
for child in root:
    print(child.tag)
    for child2 in child:
        print(child2.tag, ' ', child2.attrib)
    print()
    
print()
for a in root.iter('exam'):
    print(a.attrib)
    
print()
children = root.findall("item")
for it in children:
    re1 = it.find('name').get('id')
    re2 = it.find('name').text
    re3 = it.find('tel').text
    print(re1, re2, re3)