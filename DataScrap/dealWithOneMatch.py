#coding:utf_8
import re
import urllib
import os
import thread
import time
monMap = {'January':'01','February':'02','March':'03','April':'04','May':'05','June' :'06','July':'07','August':'08','September':'09','October':'10','November':'11','December':'12'}
def dealWithOneMatch(xml,filepath):
    global monMap
    begin = False
    result = []
    for line in xml:
        if 'align_center padding_bottom small_text' in line:
            day = getContent(line)
            array = day.split(',')
            array = array[0].split(' ')
            array[0] = monMap[array[0]]
            day = array[0]+'-'+array[1]
            result.append(day)
        if '<td><a href="/teams/' in line and begin == True:
            result.append('team2')
            line = getContent(line)
            result.append(line)
            team2 = line
            continue
        if '<td><a href="/teams/' in line and begin == False :
            result.append('team1')
            line = getContent(line)
            result.append(line)
            team1 = line
            begin = True
            continue
        if begin :
            if 'td' in line:
                result.append(getContent(line))
            if '/table' in line:
                begin = False 
        if '<img src=' in line:
            imgUrl = getImgUrl(line)
            if imgUrl != None:
              break
    filename = result[0]+'-'+team1+'-'+team2
    fileg = open(filepath+'/'+filename,'w')
    print str(result)
    for one in result:
        fileg.write(str(one)+'\n')
    fileg.close()
    if  imgUrl != None:
        print 'imgBeign...'
        img = urllib.urlopen(imgUrl).read()
        time.sleep(0.2);
        imgFile = open(filepath+'/image/'+filename+'.jpg','wb');
        imgFile.write(img)
        imgFile.close()
        print 'imgEnd...'
    return
def getContent(line):
    m = re.search('>([^<].+?)</',line)
    result = None
    if m :
        result = m.group(1)
    return result
#     print monMap['January']

def getImgUrl(line):
    m = re.search('src="(http.*?jpg)"', line)
    result = None
    if m :
        result = m.group(1)
    return result

def dealMatches(dpath,spath):
    dfilenames = os.listdir(spath)
    for name in dfilenames:
         if (int(name)==1980 or int(name) == 1984):
            os.mkdir(dpath+name)
            os.mkdir(dpath+name+'/image')
            games = os.listdir(spath+'/'+name)
            thread.start_new_thread(dealGames, (games,spath,name,dpath))
    return

def dealGames(games,spath,name,dpath):
    for game in games :
        try:
            game = spath+'/'+name+'/'+game
            rfile = open(game,'r')
            dealWithOneMatch(rfile.readlines(),dpath+'/'+name)
        except Exception,e:
            time.sleep(3000)
            print e
    return

dregularbase = 'H:/regularGame/'
dplayoffbase = 'H:/playoffGame/'  
sregularbase = 'K:/regular/'
splayoffbase = 'K:/playoff/'
dealMatches(dregularbase,sregularbase)
# dealMatches(dplayoffbase,splayoffbase)
time.sleep(-1)
# dealMatches(dplayoffbase,splayoffbase)
# thread.start_new_thread(dealMatches, (dregularbase,sregularbase))
# thread.start_new_thread(dealMatches,(dplayoffbase,splayoffbase))
# time.sleep(-1)