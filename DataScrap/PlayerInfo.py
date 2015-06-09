#coding=utf-8
# -*- coding: UTF-8 -*- 
import MyFuction 
import re
def getContentOfLine(line):
    m = re.search('v>.+?<', line)
    name = m.group()
    name = name[2:len(name)-1]
    return name
def getPlayerInfo(content):
    
   result = []
   try:
    for line in content:
        if 'keywords' in line :
                m = re.search('=".+"',line)
                str = m.group()
                str = str[2:len(str)-1]
                arr = str.split(',')
                name = arr[1]
                result.append(name+'\n')
        if  '全　　名' in line or '位　　置' in line or '身　　高' in line or '体　　重' in line or '体　　重' in line or '出生日期' \
        in line or '出生城市' in line or '高　　中' in line or '大　　学' in line or '球衣号码' in line :
            result.append(getContentOfLine(line)+'\n')
        if (len(result) == 10) :
            break
   except Exception,e:
       return result
   return result
source = 'K:/allPlayer/'
des = 'F:/playerInfo/'
for i in range(4362):
    try:
         file = open(source+str(i),'r')
         content = getPlayerInfo(file.readlines())
         file.close()
         d = open(des+(content[0][0:len(content[0])-1]),'w')
         d.writelines(content)
         d.close()
    except Exception,e:
          print str(i)

    