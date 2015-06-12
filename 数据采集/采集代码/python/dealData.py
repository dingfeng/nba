# -*- coding: UTF-8 -*- 
import re
import os
import MyFuction
def dealOneGame(lines):
    result = ''
    for line in lines:
        if 'keywords' in line :
            dict1 = dealKeyWords(line)
            result +=  dict1['season']+'\n'
            result +=  dict1['ptop']+'\n'
            result += dict1['mon']+'\n'
            result += dict1['day']+'\n'
        elif 'teamImage' in line:
            result += 'team'+'\n'
            m = re.search('/\w+\.',line)
            result += m.group()[1:len(m.group())-1]+'\n'
        elif '\"score\"' in line:
            m = re.search('>\d+<',line)
            result+=m.group()[1:len(m.group())-1]+'\n'
        elif 'td' in line  and '第' in  line:
            result += 'q'+'\n'
            m = re.search('>.+<',line)
            temp = m.group()[2:len(m.group())-2]
            m = re.search('\d+',temp)
            result += m.group()+'\n'
        elif 'number' in line :
            m = re.search('>.+<',line)
            result += m.group()[1:len(m.group())-1]+'\n'
        elif 'player_name_out' in line and 'td' in line: 
            result += 'player'+'\n'
            m = re.search('/\d+\.',line)
#             result += m.group()[1:len(m.group())-1]+'\n'
            try:
                namefi = open('k:/allPlayer/'+ str(int(m.group()[1:len(m.group())-1])-1),'r')
                name = MyFuction.getPlayerName(namefi.readlines())
                result += name+'\n'
                namefi.close()
            except Exception,e:
                result+= 'none'+'\n'
        elif 'rank=' in line :
            m = re.search('rank=\".+\"',line)
            result += m.group()[6:len(m.group())-1] + '\n'
        elif isTeam(line):
            result += 'team'+'\n'
            m = re.search('/\w+\.',line)
            result += m.group()[1:len(m.group())-1]+ '\n'
        elif 'myhidden' in line :
            break
    return result

def isTeam(line):
    m = re.match('\s+<a href="/team/\w+\.html.+/a>', line)
    return m is  not None
def dealKeyWords(str):
    dict1 = {'ptop':'','season':'','mon':'','day':''}    
    patt = r'(\d*-\d*).+(,\d*-\d*)'
    mon = r',\d*月'
    day = r',\d*日'
    s = re.search(patt,str)
    dict1['ptop'] = s.group(1)
    dict1['season'] = s.group(2)[1:len(s.group(2))]
    s = re.search(mon,str)
    temp = s.group()
    m  = re.search('\d+',temp)
    temp = m.group()
    if int(temp)>10:
       dict1['mon'] = temp
    else :
        dict1['mon'] = '0'+temp
    s = re.search(day,str)
    temp = s.group()
    m  = re.search('\d+',temp)
    temp = m.group()
    if int(temp)>10:
       dict1['day'] = temp
    else :
        dict1['day'] = '0'+temp
#     for i in dict1.values():
#         print i
    return dict1

# filepath = 'k:/mydata1/6067'
# r = open(filepath,'r')
# # result = dealOneGame(r.readlines())
# filepath1 = 'k:/'+'6067'
# w = open(filepath1,'w')
# game_temp = dealOneGame(r.readlines())
# if game_temp is not None:
#  w.write(dealOneGame(r.readlines()))
# else 
#   
# m = open('k:/6067','r')
# print m.read()
# print 'end!'
# print dealOneGame(r.readlines())
filelist = os.listdir('k:/allData')
print len(filelist)
for name in  filelist:
   try:
    filepath = 'k:/allData/'+name
    r = open(filepath,'r')
    result = dealOneGame(r.readlines())
    filepath1 = 'k:/temp/'+name
    w = open(filepath1,'w')
    w.write(result)
    w.close()
    r.close()
   except Exception,e:
        print e
# str = '        <meta name="keywords" content="华盛顿子弹100-91亚特3兰大老鹰,比赛数据,文字记录,85-86赛季,1985年,10月,25日," />'
# print dealKeyWords(str)
