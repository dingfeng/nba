#encoding:UTF8
import os
import time
from s import downloadTool
def downloadOneSeason(filelines,filename):
    num = 0
    for line in filelines:
        num+=1
        print str(num)
        if 'playoff'in line:
            line = line.split(' ')[0]
            xml = downloadTool.url_user_agent(line)
            file = open('K:/playoff/'+filename+'/'+str(num),'w')
            file.write(xml)
            file.close()
            
        else :
            line = line[0:len(line)-1]
            xml = downloadTool.url_user_agent(line)
#             print xml
            file = open('k:/regular/'+filename+'/'+ str(num),'w')
            file.write(xml)
            file.close()
        time.sleep(0.3)

def readSeasons():
    files = os.listdir('k:/seasons')
    for f in files:
     if int(f) == 1980:
      try:
#         os.mkdir('K:/regular/'+f)
#         os.mkdir('k:/playoff/'+f)
        file = open('k:/seasons/'+f,'r')
        downloadOneSeason(file.readlines(), f)
      except Exception,e:
         print e
readSeasons()

