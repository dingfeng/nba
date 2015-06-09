#coding:utf-8
import re
import os
def dealOneSeasonUrl(xmlLines,out):
     isPlayoff =False
     for line in xmlLines:
         if ('Box Score' in line):
             url = getBoxScoreUrl(line)
             if url != None:
                 out.write(url)
             if isPlayoff:
                 out.write(' playoff\n')
             else :
                 out.write('\n')
         if ('div_games_playoffs' in line):
            isPlayoff = True
            
     return 
         

def getBoxScoreUrl(line):
    m = re.search('<a href="(.*?.html)"',line)
    result = None
    if m :
        result = m.group(1)
    return "http://www.basketball-reference.com"+result

# str = "  <td align=\"center\" ><a href=\"/boxscores/197402270MIL.html\">Box Score</a></td>"
# print getBoxScoreUrl(str)
filebase = 'k:/basketballR/'
des = 'K:/seasons/'
a = os.listdir(filebase)
for filename in a:
    file_in = open(filebase+filename,'r')
    lines = file_in.readlines()
    file_out = open(des+filename,'w')
    dealOneSeasonUrl(lines,file_out)
    file_out.close()
    file_in.close()