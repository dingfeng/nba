#coding=utf-8
 # -*- coding: UTF-8 -*- 
  #@author dingfeng 
 #@date 2015/5/2
import re
import json
import urllib2
import sys
import chardet
import urllib
def getJsonKeys(result,json1,i=0):
    global l 
    if isinstance(json1,dict) :
        for key in json1:
            if isinstance(json1[key],dict):
                result = result + key+"{\n"
                result =  getJsonKeys(result,json1[key],i+1)
                result = result + "}\n"
            else :
                result = result + key + "\n"
            
            if isinstance(json1[key],list) and l < 2:
                print "sdf----------------:  "+getJsonKeys('', json1[key][0],0)
                for item in json1[key]:
                    print item
                l += 1
    return result
def scrap14Games(filepath,type):
    out = open(filepath,'w')
    out.write('d')
    start = "http://data.nba.com/data/v2014/json/mobile_teams/nba/2014/scores/gamedetail/"+type
    end = "_gamedetail.json"  
    try: 
     for i in range(2000):
            i += 1
            str_temp = ""
            if i<10:
                str_temp = '0000'+str(i)
            elif 10<=i<100:
                str_temp = '000'+str(i)
            elif 100<=i<1000:
                str_temp = '00'+str(i)
            elif 1000<=i<10000:
                str_temp = '0'+str(i)
            elif 10000<=i<100000:
                str_temp = str(i)
            url = start + str_temp+end
            req = urllib2.Request(url)
            content = urllib2.urlopen(req).read()
            out.write(content+'\n')
    except Exception,e:
            print e 
    out.close()
    return 

def convertToFile(filepath,dict1):
    result = ''
    g = dict1['g']
    gcode = g['gcode']
    filename = '14-15_'+gcode[4:6]+'-'+gcode[6:8]+'_'+gcode[9:12]+'-'+gcode[12:]
    file = open(filepath+'/'+filename,'w')
    hls = g['hls']
    vls = g['vls']
    result = gcode[4:6]+'-'+gcode[6:8]+';'+gcode[9:12]+'-'+gcode[12:]+';'+str(vls['s'])+'-'+ str(hls['s'])+';'+'\n'
    vlsqs=[vls['q1'],vls['q2'],vls['q3'],vls['q4'],vls['ot1'],vls['ot2'],\
           vls['ot3'],vls['ot4'],vls['ot5'],vls['ot6'],vls['ot7'],vls['ot8'],vls['ot9'],vls['ot10']]
    hlsqs = [hls['q1'],hls['q2'],hls['q3'],hls['q4'],hls['ot1'],hls['ot2'],hls['ot3'],hls['ot4'],\
             hls['ot5'],hls['ot6'],hls['ot7'],hls['ot8'],hls['ot9'],hls['ot10']]
    for v,h in zip(vlsqs,hlsqs):
        if v == 0 and h == 0:
            continue
        else:
            result += str(v)+'-'+str(h)+';'
    vlsPlayers = vls['pstsg']
    hlsPlayers = hls['pstsg']
    result += '\n'
    result += vls['ta']+'\n'
    for player in vlsPlayers:
        result += player['fn']+' '+player['ln']+';'+player['pos']+';'\
        +str(player['min'])+':'+'00;'+str(player['fgm'])+';'+str(player['fga'])+';'\
        +str(player['tpm'])+';'+str(player['tpa'])+';'+str(player['ftm'])+';'+str(player['fta'])+';'\
        +str(player['oreb'])+';'+str(player['dreb'])+';'+str(player['reb'])+';'+str(player['ast'])+';'+str(player['stl'])+';'\
        +str(player['blk'])+';'+str(player['tov'])+';'+str(player['pf'])+';'+str(player['pts'])+';'+'\n'
    result += hls['ta']+'\n'
    for player in hlsPlayers:
        result += player['fn']+' '+player['ln']+';'+player['pos']+';'\
        +str(player['min'])+':'+'00;'+str(player['fgm'])+';'+str(player['fga'])+';'\
        +str(player['tpm'])+';'+str(player['tpa'])+';'+str(player['ftm'])+';'+str(player['fta'])+';'\
        +str(player['oreb'])+';'+str(player['dreb'])+';'+str(player['reb'])+';'+str(player['ast'])+';'+str(player['stl'])+';'\
        +str(player['blk'])+';'+str(player['tov'])+';'+str(player['pf'])+';'+str(player['pts'])+';'+'\n'
    file.write(result)
    file.close()
    return

def getTeamStr(team):
    result = ''
    teamname = team['ta']
    result += teamname+'\n'
    player_list = team['pstsg']
    for player in list:
        pass
def getPlayerName(lines):
    for line in lines:
        if 'keywords' in line :
            m = re.search('=".+"',line)
            str = m.group()
            str = str[2:len(str)-1]
            arr = str.split(',')
            return arr[1]

l = 0
